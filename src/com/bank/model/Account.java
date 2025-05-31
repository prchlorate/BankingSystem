package com.bank.model;

public class Account {
    private final String acctNum;
    private String name;
    private String pin;
    private double balance;

    public Account(String acctNum, String name, String pin, double balance) {
        this.acctNum = acctNum;
        this.name    = name;
        this.pin     = pin;
        this.balance = balance;
    }

    public String getAcctNum() { return acctNum; }
    public String getName()    { return name;    }
    public String getPin()     { return pin;     }
    public double getBalance() { return balance; }

    public void setName(String name)       { this.name = name;       }
    public void setPin(String pin)         { this.pin = pin;         }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Account{" +
               "acctNum='" + acctNum + '\'' +
               ", name='" + name + '\'' +
               ", balance=" + balance +
               '}';
    }
}