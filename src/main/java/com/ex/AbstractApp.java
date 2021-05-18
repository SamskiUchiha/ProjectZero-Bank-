package com.ex;

import com.ex.model.Transaction;
import com.ex.model.User;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public abstract class AbstractApp {
    private int accountNumber;
    protected HashMap<String, Object> context;
    public abstract void run(String[] args) throws IOException;

    private User currentUser;
    private Collection<User> usersList;

    private Transaction currentTransaction;
    private Collection<Transaction> transactionList;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public Collection<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(Collection<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Collection<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(Collection<User> usersList) {
        this.usersList = usersList;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void clearCurrentUser() {
        usersList = null;
        currentUser = null;

        currentTransaction = null;
        transactionList = null;

        accountNumber = 0;
    }

    public HashMap<String, Object> getContext() {
        return this.context;
    }
}
