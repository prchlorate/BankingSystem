package com.bank.app;

import com.bank.util.AccountLoader;
import com.bank.util.DBConnectionUtil;
import com.bank.dao.AccountDAO;
import com.bank.service.AdminService;
import com.bank.service.CustomerService;
import com.bank.ui.ConsoleUI;
import com.bank.model.Account;

import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Choose backend (1) JSON  (2) MySQL: ");
        boolean useDb = in.nextLine().trim().equals("2");

        if (useDb) {
            try {
                DBConnectionUtil.init("jdbc:mysql://localhost:3306/bank_app", "user", "pass");
                AccountDAO.init();
                CustomerService.initDb();
                AdminService.initDb();
            } catch (Exception e) {
                System.err.println("Database initialization failed: " + e.getMessage());
                return;
            }
        } else {
            Map<String,Account> db = AccountLoader.loadFromJson(Paths.get("accounts.json"));
            CustomerService.init(db);
            AdminService.init(db);
        }

        new ConsoleUI(useDb).start();

        if (useDb) {
            try {
                DBConnectionUtil.close();
            } catch (Exception e) {
                System.err.println("Failed to close DB connection: " + e.getMessage());
            }
        } else {
            AccountLoader.saveToJson(
                Paths.get("accounts.json"),
                CustomerService.getAll().stream()
                    .collect(Collectors.toMap(Account::getAcctNum, a -> a))
            );
        }
    }
}