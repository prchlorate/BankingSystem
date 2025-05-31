package com.bank.ui;

import com.bank.service.AdminService;
import com.bank.service.CustomerService;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner in = new Scanner(System.in);
    private final boolean useDb;

    public ConsoleUI(boolean useDb) {
        this.useDb = useDb;
    }

    public void start() {
        System.out.println("=== Welcome to BankingApp ===");
        System.out.print("Enter account number (or ADMIN): ");
        String id = in.nextLine().trim();

        System.out.print("Enter PIN: ");
        String pin = in.nextLine().trim();

        if ("ADMIN".equalsIgnoreCase(id)) {
            showAdminMenu();
        } else {
            showCustomerMenu(id, pin);
        }
    }

    private void showCustomerMenu(String acct, String pin) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1) Check balance");
            System.out.println("2) Deposit");
            System.out.println("3) Withdraw");
            System.out.println("4) Exit");
            System.out.print("Choice: ");

            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        double bal = CustomerService.checkBalance(acct, pin);
                        System.out.printf("Balance: $%.2f%n", bal);
                        break;
                    case "2":
                        System.out.print("Deposit amount: ");
                        double d = Double.parseDouble(in.nextLine().trim());
                        CustomerService.deposit(acct, pin, d);
                        System.out.println("Deposit complete.");
                        break;
                    case "3":
                        System.out.print("Withdraw amount: ");
                        double w = Double.parseDouble(in.nextLine().trim());
                        CustomerService.withdraw(acct, pin, w);
                        System.out.println("Withdrawal complete.");
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1) Open account");
            System.out.println("2) Close account");
            System.out.println("3) Modify account");
            System.out.println("4) Exit");
            System.out.print("Choice: ");

            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        System.out.print("New acct #: ");
                        String na = in.nextLine().trim();
                        System.out.print("Name: ");
                        String nm = in.nextLine().trim();
                        System.out.print("PIN: ");
                        String np = in.nextLine().trim();
                        AdminService.openAccount(na, nm, np);
                        System.out.println("Account opened.");
                        break;
                    case "2":
                        System.out.print("Acct # to close: ");
                        String ca = in.nextLine().trim();
                        AdminService.closeAccount(ca);
                        System.out.println("Account closed.");
                        break;
                    case "3":
                        System.out.print("Acct # to modify: ");
                        String ma = in.nextLine().trim();
                        System.out.print("New name: ");
                        String nn = in.nextLine().trim();
                        System.out.print("New PIN: ");
                        String np2 = in.nextLine().trim();
                        AdminService.modifyAccount(ma, nn, np2);
                        System.out.println("Account modified.");
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}