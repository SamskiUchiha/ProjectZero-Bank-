package com.ex.screen;

import com.ex.AbstractApp;
import com.ex.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserHomeScreen implements Screen {
    public static final Logger logger = LogManager.getLogger(UserHomeScreen.class.getName());
    private final Scanner scanner = new Scanner(System.in);
    private final List<User> list = new ArrayList<>();
    private String username;

    /**
     * Displays User Home Screen Menu
     * @param app reference from the abstractapp class
     * @return a screen with a user scanner input
     */
    @Override
    public Screen makeScreen(AbstractApp app) {
        list.add(app.getCurrentUser());

        System.out.println("=============================");
        for(User u : list) {
            username = u.getUsername();
            System.out.println("Welcome " + username);
        }
        System.out.println("Please make your selection:");
        System.out.println("Press 1: Deposit");
        System.out.println("Press 2: Withdrawal");
//        System.out.println("Press 3: Transfer");
        System.out.println("Press 3: View History");
        System.out.println("Press 4: View Balance");
        System.out.println("Press 5: Logout");

        return makeSelection(this.scanner, app);
    }

    /**
     * User Logout
     * @param app  reference from the abstractapp class, calls clearCurrentUser data
     */
    public void logout(AbstractApp app) {
       app.clearCurrentUser();
    }

    /**
     * Grabbing user scanner input and compares each input to navigate a selection
     * @param scanner user scanner input which use to navigate to a screen
     * @param app reference from the abstractapp class
     * @return a screen based on the selection
     */
    public Screen makeSelection(Scanner scanner, AbstractApp app) {
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                return new DepositScreen();
            case "2":
                return new WithdrawScreen();
//            case "3":
//                return new TransferScreen();
            case "3":
                return new ViewTransactionScreen();
            case "4":
                return new ViewBalanceScreen();
            default:
                logout(app);
                System.out.println("Successfully Logged out.");
                logger.info("User {} successfully logged out.", username);
                return new WelcomeScreen();
        }
    }
}
