package com.ex.screen;

import com.ex.AbstractApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class EmployeeLoginScreen implements Screen {
    public static final Logger logger = LogManager.getLogger(EmployeeLoginScreen.class.getName());

    private Scanner scanner = new Scanner(System.in);
    private String username;
    private String password;

    /**
     * Displays Employee Login Screen
     * @param app reference from the abstractapp class
     * @return login screen takes @param username and password
     */
    @Override
    public Screen makeScreen(AbstractApp app) {
        System.out.println("=============================");
        System.out.println("EMPLOYEE LOGIN");
        System.out.println("Login to your account:");
        System.out.println("Enter Username: ");
        username = scanner.nextLine();
        System.out.println("Enter Password: ");
        password = scanner.nextLine();

        return login(username, password);
    }

    /**
     * Takes Employee username and password
     * @param username compares with "admin"
     * @param password compares with "password"
     * @return screen, enable program to navigate
     */
    public Screen login(String username, String password) {
        if (username.equals("admin") && password.equals("password")) {
            System.out.println("Successfully logged in!");
            logger.info("Admin logged in {}", username);
            return new EmployeeHomeScreen();
        } else {
            System.out.println("Try Again: Username and password does not match.");
            return new EmployeeLoginScreen();
        }
    }

}
