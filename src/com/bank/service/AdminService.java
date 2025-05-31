package com.bank.service;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;

import java.sql.SQLException;
import java.util.Map;

public class AdminService {
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

    public static void openAccount(String acctNum, String name, String pin) {
        if (useDb) {
            try {
                Account a = new Account(acctNum, name, pin, 0.0);
                AccountDAO.insert(a);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (db.containsKey(acctNum)) {
                throw new RuntimeException("Account already exists");
            }
            db.put(acctNum, new Account(acctNum, name, pin, 0.0));
        }
    }

    public static void closeAccount(String acctNum) {
        if (useDb) {
            try {
                AccountDAO.delete(acctNum);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (db.remove(acctNum) == null) {
                throw new RuntimeException("Account not found");
            }
        }
    }

    public static void modifyAccount(String acctNum, String newName, String newPin) {
        if (useDb) {
            try {
                Account a = AccountDAO.findById(acctNum);
                if (a == null) {
                    throw new RuntimeException("Account not found");
                }
                a.setName(newName);
                a.setPin(newPin);
                AccountDAO.update(a);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Account a = db.get(acctNum);
            if (a == null) {
                throw new RuntimeException("Account not found");
            }
            a.setName(newName);
            a.setPin(newPin);
        }
    }
}