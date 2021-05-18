package com.ex.screen;

import com.ex.AbstractApp;
import com.ex.model.User;
import com.ex.services.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserLoginScreen implements Screen {
    public static final Logger logger = LogManager.getLogger(UserLoginScreen.class.getName());
    private final Scanner scanner = new Scanner(System.in);
    private final List<User> list = new ArrayList<>();

    /**
     * Make a User login screen that prompt login messages, takes username and password
     * @param app find and save current user to a Collection so it can be use later in the program
     * @return login screen that validates username and password
     */

    @Override
    public Screen makeScreen(AbstractApp app) {
        Service service = (Service)app.getContext().get("Service");

        System.out.println("=============================");
        System.out.println("CUSTOMER LOGIN");
        System.out.println("Login to your account:");
        System.out.println("Enter Username: ");
        String username = scanner.next();
        System.out.println("Enter Password: ");
        String password = scanner.next();

        User user = (User) service.findUsername(username);
        list.add(user);
        app.setCurrentUser(user);

        return login(username, password, app);
    }

    /**
     *
     * @param strusername takes a username string from the main login screen
     * @param strpassword takes a password string from the main login screen
     * @param app reference from the abstractapp class, inorder to set a current user account number
     * @return a screen
     */

    public Screen login(String strusername, String strpassword, AbstractApp app) {
        for (User u : list) {
            try {
                if(u.checkUser(strusername, strpassword)) {
                    app.setAccountNumber(u.getAccounts().getAccountNumber());

                    System.out.println("Successfully logged in!");
                    logger.debug("User: {} successfully logged in.", strusername);
                    return new UserHomeScreen();
                } else {
                    System.out.println("Try Again: Username and password does not match.");
                    logger.error("Username and password does not match.");
                    return new UserLoginScreen();
                }
            } catch (NullPointerException ignored) {
                System.out.println("Try Again: Username and password does not match.");
                logger.debug("Username and password does not match.");
            }
        }
        return new UserLoginScreen();
    }
}
