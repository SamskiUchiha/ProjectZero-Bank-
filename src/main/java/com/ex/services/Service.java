package com.ex.services;
import com.ex.model.Account;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Abstraction that provide business logic
 * responsible for accessing the repository
 * @param <E> User
 * @param <T> Transaction
 */
public interface Service<E, T> {
    E findOne(ObjectId id);
    E findUsername(String username);
    E findAccount(int accountNumber);

    Collection<E> findAll();
    Collection<E> findAllUsers();
    Collection<T> findTransactions(int num);

    void delete(ObjectId id);
    void update(ObjectId id, double amount);
    void addUser(String type, String username, String password, Account account, boolean isEmployee, boolean inSession);
    void addTransaction(String type, double amount, LocalDateTime date, String msg, int accountNumber);
    void transfer(String toUsername, double amount);

    }

