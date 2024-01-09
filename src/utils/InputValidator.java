package src.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * abstract class InputValidator <p>
 * contains methods to validate user input
 * 
 * @version 1.00
 * @since 2024-01-07
 * @author Team 6
 */

public abstract class InputValidator {
    protected Scanner scanner;
    protected int attempts = 3;

    // constructor
    public InputValidator() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * runs a method for a specified number of times
     * 
     * @param method a method to run for a certain number of times
     * @param attempts an int for the number of times the method will run
     */
    public void limitAttempts(RunnableWithException methodToRun, int attempts) throws Exception {
        do {
            try {
                methodToRun.run();
                return;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                if (attempts > 2) {
                    System.out.printf("%d attempts left. %n", --attempts);
                } else {
                    System.out.printf("%d attempt left. %n", --attempts);
                }
            }
        } while (attempts > 0);
        throw new Exception("Maximum attempts exceeded, action terminated.");
    } // end method limitAttempts

    /**
     * gets a String from user input
     * 
     * @return a String from user input
     * @throws Exception if the user inputs nothing other than white spaces
     */
    public String getString() throws Exception {
        String str = this.scanner.nextLine().trim();
        if (str.isBlank()) {
            throw new Exception("Invalid input. Input cannot be empty.");
        }
        return str;
    }

    /**
     * gets a non-negative number from user input
     *  
     * @return an int entered by the user
     * @throws Exception if the user input is not a number or is less than 0
     */
    public int getPositiveInt() throws Exception {
        int num = scanner.nextInt();
        try {
            if (num < 0) {
                throw new Exception("Invalid input. Input cannot be negative.");
            }
            return num;
        } catch (InputMismatchException e) {
            throw new Exception("Invalid input. Input must be a whole number.");
        }
    } // end method getPositiveInt

    /**
     * prompts the user a closed question (yes/no question) <p>
     * returns true if the user inputs "y" or "yes" <p>
     * returns false if the uesr inputs "n" or "no" <p>
     * (Cases are ignored.)
     * 
     * @return a boolean, the value of which is determined by the user input
     * @throws Exception if the user input is invalid
     */
    public boolean getResponse(String closedQuestion) throws Exception {
        boolean response;

        System.out.println(closedQuestion + " (y/n)");
        String userInput = scanner.nextLine().trim().toLowerCase();

        if (userInput.equals("y") || userInput.equals("yes")) {
            response = true;
        } 
        else if (userInput.equals("n") || userInput.equals("no")) {
            response = false;
        } 
        else {
            throw new Exception("Invalid response. Please try again.");
        }

        return response;
    } // end method promptForResponse

    /**
     * takes a String of user input
     * and updates an attribute with the value. 
     * if the input is blank (contains nothing other than white spaces), no change will be made
     * 
     * @param scanner a Scanner object to read user input
     * @param method a setter method to update a certain attribute
     * @throws Exception if maxinum number of attempts reached
     */
    public void updateAttr(RunnableWithParam method) throws Exception {
        String input = this.scanner.nextLine();
        if (!input.isBlank()) {
            method.run(input);
        }
    }

    // TODO: add regex input validation

    public void close() {
        this.scanner.close();
    }
}
