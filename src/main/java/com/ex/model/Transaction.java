package com.ex.model;
import java.time.LocalDateTime;

/**
 * A Transaction model used for data modeling from and to the mongoDB
 */

public class Transaction {
    private double amount;
    private LocalDateTime date;
    private String msg;
    private String type;
    private int accountNumber;

    public Transaction() {
        super();
    }

    public Transaction(String type, double amount, LocalDateTime date, String msg, int accountNumber) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.msg = msg;
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", date=" + date +
                ", msg='" + msg + '\'' +
                ", type='" + type + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
