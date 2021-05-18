package com.ex.screen;

import com.ex.AbstractApp;
import com.ex.model.Transaction;
import com.ex.services.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ViewTransactionScreen implements Screen {
    public static final Logger logger = LogManager.getLogger(ViewTransactionScreen.class.getName());
    private Scanner scanner = new Scanner(System.in);
    private final Collection<Transaction> displayList = new ArrayList<>();

    /**
     * Displays all transaction by the current user
     */
    private void displayTransaction() {
        System.out.println("---------------------------------------------------------------------------------------");
        for(Transaction t: displayList) {
            System.out.println(
                   t.getMsg()
            );
        }
        System.out.println("---------------------------------------------------------------------------------------");
        logger.info("Displays user's transactions.");
    }

    /**
     * Displays View Transaction Screen
     * @param app reference from the abstractapp class
     * @return a screen that takes user scanner input for selection
     */
    @Override
    public Screen makeScreen(AbstractApp app) {
        Service service = (Service)app.getContext().get("Service");

        System.out.println("=============================");
        System.out.println("TRANSACTION HISTORY");
        System.out.println("Press 1: Return to Home");
        Collection<Transaction> transactions = service.findTransactions(app.getAccountNumber());

        for(Transaction t : transactions) {
            app.setCurrentTransaction(t);
            displayList.add(t);
        }

        displayTransaction();
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
