package com.ex.services;

import com.ex.model.Account;
import com.ex.model.Transaction;
import com.ex.model.User;
import com.ex.repository.Repository;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Much of the abstract methods from the interface Service are being implemented here
 */
public class DataService implements Service<User, Transaction> {

    private Repository<Transaction, User, ObjectId> userRepository;

    public DataService() {}

    /**
     * Constructor Data Service that takes in userRepository
     * @param userRepository setting User Repository
     */
    public DataService(Repository<Transaction, User, ObjectId> userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Converts findOne from repository for application use
     * finds one document that matched an ObjectId
     * @param id to search by given ObjectId
     * @return a User
     */
    @Override
    public User findOne(ObjectId id) {
        return this.userRepository.findById(id);
    }

    /**
     * Converts findUsername from repository for application use
     * @param username to search a User by given Username
     * @return a User
     */
    @Override
    public User findUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    /**
     * Converts findAll from repository for application use
     * finding all document with no filters
     * @return a collection of users
     */
    @Override
    public Collection<User> findAll() {
        return this.userRepository.findAll();
    }

    /**
     * Converts addUser from repository for application use
     * Creating new User
     * @param type new type
     * @param username new username
     * @param password new password
     * @param account new account
     * @param isEmployee is Employee?
     * @param inSession is in Session?
     */
    @Override
    public void addUser(String type, String username, String password, Account account, boolean isEmployee, boolean inSession) {
        this.userRepository.addUser(type, username, password, account, isEmployee, inSession);
    }

    /**
     * Converts addTransaction from repository for application use
     * creating new Transaction
     * @param type type
     * @param amount amount of differences
     * @param date current date and time
     * @param msg message of transactions
     * @param accountNumber provide account number for referencing
     */
    @Override
    public void addTransaction(String type, double amount, LocalDateTime date, String msg, int accountNumber) {
        this.userRepository.addTransaction(type, amount, date, msg, accountNumber);
    }

    /**
     * Converts update from repository for application use
     * @param id objectId for referencing
     * @param amount amount need to update
     * Mainly uses for Deposit and Withdrawal that updates balances in mongoDB
     */
    @Override
    public void update(ObjectId id, double amount) {
        this.userRepository.update(id, amount);
    }

    /**
     * Converts findAccount from repository for application use
     * @param accountNumber to search accounts
     * @return a User
     */
    @Override
    public User findAccount(int accountNumber) {
        return this.userRepository.findAccount(accountNumber);
    }

    /**
     * Converts findAllUsers from repository for application use
     * @param id objectId to delete
     */
    @Override
    public void delete(ObjectId id) {
        this.userRepository.delete(id);
    }

    /**
     * Converts findAllUsers from repository for application use
     * @return a collection of Users
     */
    @Override
    public Collection<User> findAllUsers() {
        return this.userRepository.findAllUsers();
    }

    /**
     * Converts findTransaction from repository for application use
     * @param num Account number to search
     * @return a collection of Transactions
     */
    @Override
    public Collection<Transaction> findTransactions(int num) {
        return this.userRepository.findTransactions(num);
    }

    @Override
    public void transfer(String toUsername, double amount) {
        this.userRepository.transfer(toUsername, amount);
    }

}
