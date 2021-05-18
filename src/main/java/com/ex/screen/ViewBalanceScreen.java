package com.ex.screen;

import com.ex.AbstractApp;
import com.ex.model.User;
import com.ex.services.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewBalanceScreen implements Screen{
    public static final Logger logger = LogManager.getLogger(ViewBalanceScreen.class.getName());
    private final Scanner scanner = new Scanner(System.in);
    private final List<User> list = new ArrayList<>();
    private final NumberFormat format = new DecimalFormat("#0.00");

    /**
     * Displays View User Balance Screen
     * @param app reference from the abstractapp class
     * @return a screen that takes user scanner input for selection
     */
    @Override
    public Screen makeScreen(AbstractApp app) {
        Service service = (Service)app.getContext().get("Service");
        list.add(app.getCurrentUser());
        System.out.println("=============================");
        System.out.println("Press 1: Return to Home");
        for(User u: list) {
            double totalBalance = u.getAccounts().getBalance();
            String accountType = u.getAccounts().getType();
            System.out.println("TOTAL BALANCE: $" + format.format(totalBalance) + " " + "ACCOUNT: " + accountType);
        }
        logger.info("Displays current user total balance and Account type");

        return makeSeletion(this.scanner);
    }

    /**
     * make a selection navigation
     * @param scanner gets input from user
     * @return a screen based on the selection
     */
    public Screen makeSeletion(Scanner scanner) {
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                return new UserHomeScreen();
            default:
                return new ViewTransactionScreen();
        }
    }
}
