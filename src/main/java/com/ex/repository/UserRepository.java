package com.ex.repository;

import com.ex.MongoConnector;
import com.ex.model.Account;
import com.ex.model.Transaction;
import com.ex.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

/**
 * Much of the abstract methods from the interface Repository are being implemented here
 */
public class UserRepository implements Repository {
    private MongoConnector connector;
    private MongoCollection<User> users;
    private MongoCollection<Transaction> transactions;

    /**
     * Constructor UserRepository
     * @param connector use to connect to the database of "bank" and document "user" and "transaction"
     */
    public UserRepository(MongoConnector connector) {
        this.connector = connector;
        this.users = this.connector
                .getClient()
                .getDatabase("bank")
                .getCollection("user", User.class);

        this.transactions = this.connector
                .getClient()
                .getDatabase("bank")
                .getCollection("transactions", Transaction.class);
    }

    /**
     * Search the User database given a username
     * @param username to search
     * @return a first document that matched given username
     */
    @Override
    public User findByUsername(String username) {
        return this.users
                .find(eq("username", username))
                .first();
    }

    /**
     * Add a new User to the database
     * @param type write user type "User" or "Employee"
     * @param username write username
     * @param password write password
     * @param account write account "new Account( balance, type)"
     * @param isEmployee write is this User is Employee
     * @param inSession write if this user is in Session (don't really need it)
     */
    @Override
    public void addUser(String type, String username, String password, Account account, boolean isEmployee, boolean inSession) {
        this.users.insertOne(new User(type, username,
                password,
                new Account(account.getBalance(), account.getType()),
                isEmployee, inSession
        ));
    }

    /**
     * Add a new Transaction to the database
     * @param type type write user type "User" or "Employee"
     * @param amount write amount
     * @param date write date
     * @param msg write message
     * @param accountNumber write account number
     */
    @Override
    public void addTransaction(String type, double amount, LocalDateTime date, String msg, int accountNumber) {
        this.transactions.insertOne(new Transaction(type, amount, date, msg, accountNumber));
    }

    /**
     * Search the User database given a objectId
     * @param objectId to search
     * @return a first document that matched given objectId
     */
    @Override
    public User findById(Object objectId) {
        return this.users
                .find(eq("_id", objectId))
                .first();
    }

    /**
     * Delete a document that matched the given objectId
     * @param objectId to search
     */
    @Override
    public void delete(Object objectId) {
        this.users.deleteOne(eq("_id", objectId));
    }

    /**
     * Search Transaction that match the given account number
     * @param accountNumber to search
     * @return a first document that matched given account number
     */
    @Override
    public Transaction findAccount(int accountNumber) {
        return this.transactions
                .find(eq("accounts", accountNumber))
                .first();
    }

    /**
     * Search all Users that has field name type "user"
     * @return a collection of users
     */
    @Override
    public Collection<User> findAllUsers() {
        BasicDBObject where = new BasicDBObject();
        where.put("type", "user");
        FindIterable<User> results = this.users.find(where);
        return StreamSupport.stream(results.spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Search all Transactions that has the field name type "transaction" and accountNumber given a number
     * @param num to search (account number)
     * @return a collection of transactions
     */
    @Override
    public Collection<Transaction> findTransactions(int num) {
        BasicDBObject where = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<>();
        obj.add(new BasicDBObject("type", "transaction"));
        obj.add(new BasicDBObject("accountNumber", num));
        where.put("$and", obj);
        FindIterable<Transaction> results = this.transactions.find(where);
        return StreamSupport.stream(results.spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Update one document that matched given objectId and set the new amount
     * @param objectId to search
     * @param amount new amount
     */
    @Override
    public void update(Object objectId, double amount) {
        this.users.updateOne(Filters.eq("_id", objectId), Updates.set("accounts.balance", amount));
    }

    @Override
    public void transfer(String toUsername, double amount) {
        this.users.updateOne(Filters.eq("_id", toUsername), Updates.set("accounts.balance", amount));
    }

    /**
     * Find all users
     * @return collection of users
     */
    @Override
    public Collection<User> findAll() {
        FindIterable<User> results = this.users.find();
        return StreamSupport.stream(results.spliterator(), false)
                .collect(Collectors.toList());
    }
}
