package com.ex.model;

import java.util.Random;

/**
 * An Account model used for data modeling from and to the mongoDB
 */
public class Account {
    private Random rand = new Random();

    private int accountNumber;
    private double balance;
    private String type;

    public Account() {}

    /**
     * @Auto generated code
     * @param balance balance
     * @param type type
     */
    public Account(double balance, String type) {
        this.accountNumber = rand.nextInt(Integer.SIZE - 1);
        this.balance = balance;
        this.type = type;
    }

    /**
     * Adding the given amount to current account's balance
     * @param amount to add from balance
     */
    public void deposit(double amount) {
        setBalance(getBalance() + Math.abs(amount));
    }

    /**
     * Compares if the current balance is < the given amount to withdrawal
     * @param amount to subtract from balance
     * @return a boolean value whether amount is less than the current balance
     */
    public boolean withdrawal(double amount) {
        double abs = Math.abs(amount);
        if(getBalance() < abs) {
            return false;
        } else {
            setBalance(getBalance() - abs);
            return true;
        }
    }

    /**
     * @Auto generated code
     * @return Account Number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @Auto generated code
     * @param accountNumber given account number
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @Auto generated code
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @Auto generated code
     * @param balance balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @Auto generated code
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @Auto generated code
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @Auto generated code
     * @return a String
     */
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                '}';
    }
}
