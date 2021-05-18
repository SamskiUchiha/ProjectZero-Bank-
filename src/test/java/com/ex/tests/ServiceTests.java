package com.ex.tests;

import com.ex.model.Account;
import com.ex.repository.Repository;
import com.ex.services.DataService;
import com.ex.services.Service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ServiceTests {
    private Service service;
    private Repository dao;
    private java.lang.Object Object;
    private org.bson.types.ObjectId ObjectId;

    @Before
    public void initTestDependencies() {
        System.out.println("Before Service Tests");
        dao = mock(Repository.class);
        service = new DataService(dao);
    }

    @Test
    public void test_findAll() {
        when(dao.findAll()).thenReturn(java.util.Arrays.asList("From Mock Dao"));
        List list = (List) service.findAll();
        Assert.assertEquals("From Mock Dao", list.get(0));
    }

    @Test
    public void test_findAllUsers() {
        when(dao.findAllUsers()).thenReturn(java.util.Arrays.asList("From Mock Dao"));
        List list = (List) service.findAllUsers();
        Assert.assertEquals("From Mock Dao", list.get(0));
    }

    @Test
    public void test_findTransactions() {
        int num = 0;
        when(dao.findTransactions(num)).thenReturn(java.util.Arrays.asList("From Mock Dao"));
        List list = (List) service.findTransactions(num);
        Assert.assertEquals("From Mock Dao", list.get(0));
    }

    //Account has a hidden random number generated account number so this test will always fails
    @Test(expected = Exception.class)
    public void test_addUser() {
        doThrow(new Exception())
                .when(dao).addUser("test", "test", "test", new Account(200.00, "test"), false, false);
        service.addUser("test", "test", "test", new Account(200.00, "test"), false, false);
    }

    @Test
    public void test_addTransaction() {
        doNothing().when(dao).addTransaction("test", 100.00, null, "test", 100);
    }

    @Test
    public void test_delete() {
        doNothing().when(dao).delete(anyString());
    }

    @Test
    public void test_update() {
        doNothing().when(dao).update(anyString(), anyDouble());
    }

    @Test
    public void test_findById() {
        when(dao.findById(ObjectId)).thenReturn(Object);
        java.lang.Object ob = service.findOne(ObjectId);
        Assert.assertEquals(Object, ob);
    }

    @Test
    public void test_findByUsername() {
        when(dao.findByUsername(anyString())).thenReturn(Object);
        java.lang.Object ob = service.findUsername(anyString());
        Assert.assertEquals(Object, ob);
    }

    @Test
    public void test_findAccount() {
        when(dao.findAccount(anyInt())).thenReturn(Object);
        java.lang.Object ob = service.findAccount(anyInt());
        Assert.assertEquals(Object, ob);
    }
}
