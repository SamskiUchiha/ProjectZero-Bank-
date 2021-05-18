package com.ex.screen;

import com.ex.AbstractApp;
import com.ex.model.User;
import com.ex.services.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ViewAllUsersScreen implements Screen{
    public static final Logger logger = LogManager.getLogger(ViewAllUsersScreen.class.getName());
    private final Scanner scanner = new Scanner(System.in);
    private Service service;
    private final Collection<User> list = new ArrayList<>();
    private final Collection<User> displayList = new ArrayList<>();

    /**
     * Displays All Users
     */
    private void displayUsers() {
        System.out.println("| User ID | Username | Password | Balance | Account Type |");
        for(User u: displayList) {
            System.out.println(
                    u.getId() + " " +
                    u.getUsername() + " " +
                    u.getPassword() + " " +
                    u.getAccounts().getBalance() + " " +
                    u.getAccounts().getType()
                    );
            logger.info("Displaying users: {} ", u.getUsername());
        }
    }

    /**
     * Displays View All Users Screen
     * @param app reference from the abstractapp class
     * @return a screen that takes user scanner input for selection
     */
    @Override
    public Screen makeScreen(AbstractApp app) {
        service = (Service)app.getContext().get("Service");

        System.out.println("=============================");
        System.out.println("View All Users");
        System.out.println("Press 1: Back");
        System.out.println("Press 2: Delete User");

        app.setUsersList(service.findAllUsers());
        for(User u : app.getUsersList()) {
            displayList.add(u);
        }

        displayUsers();

        return makeSelection(this.scanner);
    }

    /**
     * make a selection navigation
     * @param scanner gets input from user
     * @return a screen based on the selection
     */

    public Screen makeSelection(Scanner scanner) {
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                return new EmployeeHomeScreen();
            case "2":
                return deleteUI();
            default:
                return new ViewAllUsersScreen();
        }
    }

    /**
     * Displays Delete User Screen
     * @return a screen based on the selection
     */
    public Screen deleteUI() {
        System.out.println("Press 1: To Delete (USER)");
        System.out.println("Press exit: Back");
        String input = scanner.nextLine();
        switch (input) {
            case "exit":
                logger.info("Exiting Delete User Screen.");
                return new ViewAllUsersScreen();
            case "1":
                System.out.println("Enter USER ID to delete:");
                String userID = scanner.nextLine();
                User temp = (User) service.findOne(new ObjectId(userID));
                list.add(temp);

                for(User u : list) {
                    if(u.getId().equals(new ObjectId(userID))) {
                        System.out.println("ID: " + u.getId());

                        service.delete(new ObjectId(userID));
                        System.out.println("Successfully deleted User: " + u.getId());
                        logger.info("Successfully deleted User: {} ", u.getId());
                        return new ViewAllUsersScreen();
                    } else {
                        System.out.println("User ID does not exist. Please Try again");
                        logger.debug("User ID does not exist: {} ", userID);
                        return deleteUI();
                    }
                }
            default:
                return deleteUI();
        }
    }
}
