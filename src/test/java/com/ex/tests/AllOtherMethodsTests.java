package com.ex.tests;

import com.ex.AbstractApp;
import com.ex.model.Account;
import com.ex.model.Transaction;
import com.ex.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class AllOtherMethodsTests {
    private User user;
    private Account accountUser;
    private Account account;
    private AbstractApp app;
    private Object User;
    private Transaction trans;
    private LocalDateTime date;

    @Before
    public void beforeTests() {
        app = mock(AbstractApp.class);
        accountUser = mock(Account.class);
        account = new Account(100, "checking");
        user = new User("user", "username", "password", accountUser, false, false);
    }

    @Test
    public void test_checkUserTRUE() {
        String username = "username";
        String password = "password";
        Assert.assertTrue(user.checkUser(username, password));
    }

    @Test
    public void test_checkUserFALSE() {
        String username = "username1";
        String password = "password";
        Assert.assertFalse(user.checkUser(username, password));
    }

    @Test
    public void test_deposit() {
        double amount = 100;
        doNothing().when(accountUser).deposit(amount);
    }

    @Test
    public void test_withdrawalTRUE() {
        Assert.assertTrue(account.withdrawal(50.0));
    }

    @Test
    public void test_withdrawalFALSE() {
        Assert.assertFalse(account.withdrawal(5000.0));
    }

    @Test
    public void test_clearCurrentUser() {
        doNothing().when(app).clearCurrentUser();
    }

}
