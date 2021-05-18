package com.ex.tests;

import com.ex.AbstractApp;
import com.ex.Main;
import com.ex.screen.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class ScreenTests {
    private AbstractApp app;
    private EmployeeHomeScreen employeeHomeScreen;
    private DepositScreen depositScreen;
    private EmployeeLoginScreen employeeLoginScreen;
    private OpenUserAccountScreen openUserAccountScreen;
    private TransferScreen transferScreen;
    private UserHomeScreen userHomeScreen;
    private UserLoginScreen userLoginScreen;
    private ViewAllUsersScreen viewAllUsersScreen;
    private ViewBalanceScreen viewBalanceScreen;
    private WelcomeScreen welcomeScreen;
    private WithdrawScreen withdrawScreen;
    private ViewTransactionScreen viewTransactionScreen;
    private Screen screen;
    private java.lang.Object Object;
    private Scanner scanner = new Scanner(System.in);
    private Main main;

    @Before
    public void initTestScreens() {
        screen = mock(Screen.class);
        employeeHomeScreen = mock(EmployeeHomeScreen.class);
        depositScreen = mock(DepositScreen.class);
        employeeLoginScreen = mock(EmployeeLoginScreen.class);
        openUserAccountScreen = mock(OpenUserAccountScreen.class);
        transferScreen = mock(TransferScreen.class);
        userHomeScreen = mock(UserHomeScreen.class);
        userLoginScreen = mock(UserLoginScreen.class);
        viewAllUsersScreen = mock(ViewAllUsersScreen.class);
        viewBalanceScreen = mock(ViewBalanceScreen.class);
        welcomeScreen = mock(WelcomeScreen.class);
        withdrawScreen = mock(WithdrawScreen.class);
        viewTransactionScreen = mock(ViewTransactionScreen.class);
        main = mock(Main.class);
    }

    @Test
    public void test_viewTransactionScreenMakeSelection() {
        Assert.assertEquals(Object, viewTransactionScreen.makeSeletion(scanner));
    }

    @Test
    public void test_viewTransactionScreen() {
        Assert.assertEquals(Object, viewTransactionScreen.makeScreen(app));
    }

    @Test
    public void test_withdrawScreen() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Assert.assertEquals(Object, withdrawScreen.makeScreen(app));
    }

    @Test
    public void test_welcomeScreen() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        when(welcomeScreen.makeSelection(scanner)).thenReturn(new UserLoginScreen());
        UserLoginScreen u = new UserLoginScreen();
        Assert.assertNotSame(u, welcomeScreen.makeSelection(scanner));
    }

    @Test
    public void test_welcomeScreenMakeScreen() {
        Assert.assertEquals(Object, welcomeScreen.makeScreen(app));
    }

    @Test
    public void test_viewBalanceScreenMakeSelection() {
        Assert.assertEquals(Object, viewBalanceScreen.makeSeletion(scanner));
    }

    @Test
    public void test_viewBalanceScreen() {
        Assert.assertEquals(Object, viewBalanceScreen.makeScreen(app));
    }

    @Test
    public void test_viewAllUserScreenDeleteUI() {
        Assert.assertEquals(Object, viewAllUsersScreen.deleteUI());
    }

    @Test
    public void test_viewAllUserScreenMakeSelection() {
        Assert.assertEquals(Object, viewAllUsersScreen.makeSelection(scanner));
    }

    @Test
    public void test_viewAllUsersScreen() {
        Assert.assertEquals(Object, viewAllUsersScreen.makeScreen(app));
    }

    @Test
    public void test_userLoginScreen() {
        Assert.assertEquals(Object, userLoginScreen.makeScreen(app));
    }

    @Test
    public void test_userLoginScreenLogin() {
        Assert.assertEquals(Object, userLoginScreen.login("anyString()", "anyString()", app));

    }

    @Test
    public void test_userHomeScreen() {
        Assert.assertEquals(Object, userHomeScreen.makeScreen(app));
    }

    @Test
    public void test_userHomeScreenLogout() {
        doNothing().when(userHomeScreen).logout(app);
    }

    @Test
    public void test_userHomeScreenMakeSelection() {
        Assert.assertEquals(Object, userHomeScreen  .makeSelection(scanner,app));

    }

    @Test
    public void test_transferScreen() {
        Assert.assertEquals(Object, transferScreen.makeScreen(app));
    }

    @Test
    public void test_openUserAccountScreen() throws IOException {
        Assert.assertEquals(Object, openUserAccountScreen.makeScreen(app));
    }

    @Test
    public void test_employeeLoginScreen() {
        Assert.assertEquals(Object, employeeLoginScreen.makeScreen(app));
    }

    @Test
    public void test_employeeLoginScreenLogin() {
        Assert.assertEquals(Object, employeeLoginScreen.login(anyString(), anyString()));
    }

    @Test
    public void test_logout() {
        doNothing().when(employeeHomeScreen).logout(app);
    }

    @Test
    public void test_makeScreenInEmployeeHomeScreen() {
        Assert.assertEquals(Object, employeeHomeScreen.makeScreen(app));
    }

    @Test
    public void test_makeSelectionnEmployeeHomeScreen() {
        Assert.assertEquals(Object, employeeHomeScreen.makeSelection(scanner, app));
    }

    @Test
    public void test_makeScreenInDepositScreen() {
        Assert.assertEquals(Object, depositScreen.makeScreen(app));

    }

//
}
