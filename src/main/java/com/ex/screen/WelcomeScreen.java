package com.ex.screen;

import com.ex.AbstractApp;

import java.util.Scanner;

public class WelcomeScreen implements Screen {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays Welcome Screen
     * @param app reference from the abstractapp class
     * @return a screen that takes user scanner input for selection
     */
    @Override
    public Screen makeScreen(AbstractApp app) {
        System.out.println("=============================");
        System.out.println("Welcome to Bank");
        System.out.println("Please make your selection:");
        System.out.println("Press 1: Customer Login");
        System.out.println("Press 2: Employee Login");
        System.out.println("Press 3: Exit");

        return makeSelection(this.scanner);
    }

    /**
     * make a selection navigation
     * @param scanner gets input from user
     * @return a screen based on the selection
     * Can return null if 1,2,3 is not one of the inputs = exit out of program
     */
    public Screen makeSelection(Scanner scanner) {
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                return new UserLoginScreen();
            case "2":
                return new EmployeeLoginScreen();
            case "3":
                return null;
            default:
                return new WelcomeScreen();
        }
    }
}
