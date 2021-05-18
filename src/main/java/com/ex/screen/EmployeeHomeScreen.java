package com.ex.screen;

import com.ex.AbstractApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class EmployeeHomeScreen implements Screen {
    private Scanner scanner = new Scanner(System.in);
    public static final Logger logger = LogManager.getLogger(EmployeeHomeScreen.class.getName());

    /**
     * Displays Employee Home Screen
     * @param app reference from the abstractapp class, to logout
     * @return return a screen that takes in scanner
     */
    @Override
    public Screen makeScreen(AbstractApp app) {
        System.out.println("=============================");
        System.out.println("Employee Home");
        System.out.println("Please make your selection:");
        System.out.println("Press 1: Open New User Account");
        System.out.println("Press 2: View all users");
        System.out.println("Press 3: logout");

        return makeSelection(this.scanner, app);
    }

    /**
     * Logout, clears current user data and set them to null
     * calls clearCurrentUser() from AbstractApp
     * @param app reference AbstractApp
     */
    public void logout(AbstractApp app) {
        logger.info("Admin logged out");
        app.clearCurrentUser();
    }

    /**
     * Displays selection and get input from user
     * @param scanner get and check user input
     * @param app reference AbstractApp
     * @return screen, enable program to navigate
     */
    public Screen makeSelection(Scanner scanner, AbstractApp app) {
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                return new OpenUserAccountScreen();
            case "2":
                return new ViewAllUsersScreen();
            default:
                logout(app);
                return new WelcomeScreen();
        }
    }
}
