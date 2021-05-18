package com.ex.repository;
import com.ex.model.Account;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Abstraction that use to access the database
 * Any abstract methods here can be use in the Services
 * @param <T> Transaction
 * @param <E> User
 * @param <ID> ObjectId
 */
public interface Repository<T, E, ID> {
    E findById(ID id);
    E findByUsername(String username);
    E findAccount(int accountNumber);

    Collection<E> findAll();
    Collection<E> findAllUsers();
    Collection<T> findTransactions(int num);

    void addUser(String type, String username, String password, Account account, boolean isEmployee, boolean inSession);
    void addTransaction(String type, double amount, LocalDateTime date, String msg, int accountNumber);
    void delete(ID id);
    void update(ID id, double amount);
    void transfer(String toUsername, double amount);


    //for initial testing of JUNIT
//    int add(int x, int y);

}
