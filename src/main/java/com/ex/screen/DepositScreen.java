package com.ex.screen;

import com.ex.AbstractApp;
import com.ex.model.User;
import com.ex.services.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepositScreen implements Screen {
    private final Scanner scanner = new Scanner(System.in);
    private final List<User> list = new ArrayList<>();
    private final LocalDateTime date = LocalDateTime.now();
    private final NumberFormat format = new DecimalFormat("#0.00");
    public static final Logger logger = LogManager.getLogger(DepositScreen.class.getName());
    private String accountType;
    private ObjectId userID;
    private double amount;

    /**
     * Displays User Deposit Screen
     * @param app reference from the abstractapp class, to get current user
     * @return screen, enable program to navigate
     */
    @Override
    public Screen makeScreen(AbstractApp app) {
        Service service = (Service) app.getContext().get("Service");
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("=============================");
        System.out.println("DEPOSIT");
        logger.info("Start of depositing...");

        list.add(app.getCurrentUser());
        for(User u: list) {
            userID = u.getId();
            System.out.println("Balance: $" + u.getAccounts().getBalance());
            accountType = u.getAccounts().getType();
            System.out.println("Enter your Amount: ");

            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                u.getAccounts().deposit(amount);
                double newBalance = u.getAccounts().getBalance();
                int accountNumber = u.getAccounts().getAccountNumber();
                service.update(u.getId(), newBalance);

                System.out.println("Deposit completed.");

                String msg = "Deposit to: " + accountType + " Amount: +" + format.format(amount)  + " Total Balance: " + format.format(newBalance)  + " Date: " + date.format(myFormatObj);
                service.addTransaction("transaction", amount, date, msg, accountNumber);
                logger.info("Depositing to {}, account type: {} ", userID, accountType);
                return new UserHomeScreen();
            } else {
                System.out.println("Input is not valid, Please try Again...");
                logger.error("Input {} is not valid.", amount);
                return new DepositScreen();
            }
        }
        return new DepositScreen();
    }
}
