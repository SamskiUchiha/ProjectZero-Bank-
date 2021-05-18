package com.ex.screen;

import com.ex.AbstractApp;
import com.ex.model.User;
import com.ex.services.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WithdrawScreen implements Screen {
    public static final Logger logger = LogManager.getLogger(WithdrawScreen.class.getName());
    private final Scanner scanner = new Scanner(System.in);
    private final List<User> list = new ArrayList<>();
    private final NumberFormat format = new DecimalFormat("#0.00");
    private final LocalDateTime date = LocalDateTime.now();
    private double amount;
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /**
     * Displays Withdrawal Screen
     * displays user's current balance, and take in user's input
     * @param app reference from the abstractapp class
     * @return a screen upon user input
     */
    @Override
    public Screen makeScreen(AbstractApp app) {
        Service service = (Service)app.getContext().get("Service");

        System.out.println("=============================");
        System.out.println("WITHDRAWAL");

        list.add(app.getCurrentUser());
        for(User u: list) {
            System.out.println("Balance: $" + u.getAccounts().getBalance());
            String accountType = u.getAccounts().getType();
            System.out.println("Enter your Amount: ");

            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if(u.getAccounts().withdrawal(amount)) {
                    double newBalance = u.getAccounts().getBalance();
                    int accountNumber = u.getAccounts().getAccountNumber();
                    service.update(u.getId(), newBalance);

                    System.out.println("Withdrawal completed.");
                    String msg = "Withdrawal from: " + accountType + " Amount: -" + format.format(amount) + " Total Balance: " + format.format(newBalance) + " Date: " + date.format(myFormatObj);
                    service.addTransaction("transaction", amount, date, msg, accountNumber);

                    logger.info("Withdrawal from {}, account type: {} ", u.getUsername(), accountType);
                    return new UserHomeScreen();
                } else {
                    System.out.println("You have insufficient funds.");
                    logger.error("User does not have enough funds to withdrawal.");
                    return new WithdrawScreen();
                }
            } else {
                System.out.println("Input is not valid, Please try Again...");
                logger.debug("Input {} is not valid.", amount);
                return new WithdrawScreen();
            }
        }
        return new WithdrawScreen();
    }
}
