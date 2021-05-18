package com.ex.screen;

import com.ex.AbstractApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class TransferScreen implements Screen{
    public static final Logger logger = LogManager.getLogger(TransferScreen.class.getName());
    private Scanner scanner = new Scanner(System.in);
    private String from;
    private String to;
    private int total;

    /**
     * Displays Transfer Screen
     * not implemented yet
     * @param app reference from the abstractapp class
     * @return a screen, enable program to navigate
     */
    @Override
    public Screen makeScreen(AbstractApp app) {
        System.out.println("=============================");
        System.out.println("TRANSFER");
        System.out.println("From:");
        from = scanner.nextLine();
        System.out.println("To:");
        to = scanner.nextLine();
        System.out.println("Enter Amount:");
        total = scanner.nextInt();

        logger.info("Transferring From: {} To: {}", from, to);
        return new UserHomeScreen();
    }
}
