package com.ex.screen;

import com.ex.AbstractApp;
import com.ex.model.Account;
import com.ex.model.User;
import com.ex.services.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class OpenUserAccountScreen implements Screen {
    public static final Logger logger = LogManager.getLogger(OpenUserAccountScreen.class.getName());
    private Scanner scanner = new Scanner(System.in);
    private Service service;

    private String username;
    private String password;
    private String accType;
    private double balance;

    /**
     * Displays Employee Open Account Screen
     * creates User a account
     * @param app reference from the abstractapp class
     * @return screen a screen, depending on if user account was successfully created
     * else return to this current screen
     * @throws IOException
     */
    @Override
    public Screen makeScreen(AbstractApp app) throws IOException {
        service = (Service)app.getContext().get("Service");

        System.out.println("=============================");
        System.out.println("Open New User Account        ");
        System.out.println("Create username:             ");
        this.username = scanner.next();
        System.out.println("Create password:             ");
        this.password = scanner.next();
        System.out.println("Create account type:         ");
        this.accType = scanner.next();
        System.out.println("Create balance:              ");
        this.balance = scanner.nextDouble();

        User u = (User) service.findUsername(username);

        if(u == null) {
            service.addUser("user",username, password, new Account(balance, accType), false, false);
            System.out.println("Successfully created account.");
            logger.info("Account {} created.", username);
        }

        try {
            if(u.getUsername().equals(username)) {
                System.out.println("Username already exist, Please try again.");
                logger.error("Username {} already exist.", username);

                return new OpenUserAccountScreen();
            } else {
                service.addUser("user", username, password, new Account(balance, accType), false, false);
                System.out.println("Successfully created account.");
                logger.info("Account {} created.", username);
            }
        } catch (NullPointerException ex) {}

        logger.info("Account {} created.", username);
        return goToEmployeeHomeScreen();
    }

    private Screen goToEmployeeHomeScreen() {
        return new EmployeeHomeScreen();
    }

}
