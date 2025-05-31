package com.bank.service;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerService {
    private static Map<String,Account> db;
    private static boolean useDb = false;

    public static void init(Map<String,Account> store) {
        db = store;
        useDb = false;
    }

    public static void initDb() throws SQLException {
        useDb = true;
        AccountDAO.init();
    }

    public static double checkBalance(String acct, String pin) {
        if (useDb) {
            try {
                Account a = AccountDAO.findById(acct);
                if (a == null || !a.getPin().equals(pin)) {
                    throw new RuntimeException("Invalid account or PIN");
                }
                return a.getBalance();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Account a = db.get(acct);
            if (a == null || !a.getPin().equals(pin)) {
                throw new RuntimeException("Invalid account or PIN");
            }
            return a.getBalance();
        }
    }

    public static void deposit(String acct, String pin, double amt) {
        if (amt <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (useDb) {
            try {
                Account a = AccountDAO.findById(acct);
                if (a == null || !a.getPin().equals(pin)) {
                    throw new RuntimeException("Invalid account or PIN");
                }
                a.setBalance(a.getBalance() + amt);
                AccountDAO.update(a);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Account a = db.get(acct);
            if (a == null || !a.getPin().equals(pin)) {
                throw new RuntimeException("Invalid account or PIN");
            }
            a.setBalance(a.getBalance() + amt);
        }
    }

    public static void withdraw(String acct, String pin, double amt) {
        if (amt <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (useDb) {
            try {
                Account a = AccountDAO.findById(acct);
                if (a == null || !a.getPin().equals(pin)) {
                    throw new RuntimeException("Invalid account or PIN");
                }
                if (a.getBalance() < amt) {
                    throw new RuntimeException("Insufficient funds");
                }
                a.setBalance(a.getBalance() - amt);
                AccountDAO.update(a);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Account a = db.get(acct);
            if (a == null || !a.getPin().equals(pin)) {
                throw new RuntimeException("Invalid account or PIN");
            }
            if (a.getBalance() < amt) {
                throw new RuntimeException("Insufficient funds");
            }
            a.setBalance(a.getBalance() - amt);
        }
    }

    public static List<Account> getAll() {
        if (useDb) {
            try {
                return AccountDAO.findAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ArrayList<>(db.values());
        }
    }
}