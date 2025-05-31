package com.bank.dao;

import com.bank.model.Account;
import com.bank.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public static void init() throws SQLException {
        findAll();
    }

    public static List<Account> findAll() throws SQLException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT acct_num, name, pin, balance FROM accounts";
        try (PreparedStatement ps = DBConnectionUtil.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Account(
                    rs.getString("acct_num"),
                    rs.getString("name"),
                    rs.getString("pin"),
                    rs.getDouble("balance")
                ));
            }
        }
        return list;
    }

    public static Account findById(String acctNum) throws SQLException {
        String sql = "SELECT acct_num, name, pin, balance FROM accounts WHERE acct_num = ?";
        try (PreparedStatement ps = DBConnectionUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, acctNum);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                        rs.getString("acct_num"),
                        rs.getString("name"),
                        rs.getString("pin"),
                        rs.getDouble("balance")
                    );
                }
                return null;
            }
        }
    }

    public static void insert(Account a) throws SQLException {
        String sql = "INSERT INTO accounts (acct_num, name, pin, balance) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DBConnectionUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, a.getAcctNum());
            ps.setString(2, a.getName());
            ps.setString(3, a.getPin());
            ps.setDouble(4, a.getBalance());
            ps.executeUpdate();
        }
    }

    public static void update(Account a) throws SQLException {
        String sql = "UPDATE accounts SET name = ?, pin = ?, balance = ? WHERE acct_num = ?";
        try (PreparedStatement ps = DBConnectionUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, a.getName());
            ps.setString(2, a.getPin());
            ps.setDouble(3, a.getBalance());
            ps.setString(4, a.getAcctNum());
            ps.executeUpdate();
        }
    }

    public static void delete(String acctNum) throws SQLException {
        String sql = "DELETE FROM accounts WHERE acct_num = ?";
        try (PreparedStatement ps = DBConnectionUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, acctNum);
            ps.executeUpdate();
        }
    }
}