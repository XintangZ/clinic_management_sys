package src.utils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * class Menu
 * 
 * @version 1.00
 * @since 2024-01-04
 * @author Team 6
 */

public class Menu {
    private String menuHeader;
    private ArrayList<String> menuOptions;

    /**
     * constructor
     * 
     * @param menuOptions Strings of menu options to be displayed
     */
    public Menu(String menuHeader, String... menuOptions) {
        this.menuHeader = menuHeader;
        this.menuOptions = new ArrayList<>();

        for (String option : menuOptions) {
            this.menuOptions.add(option);
        }
    } // end constructor

    /**
     * prints each menu option in a new line 
     * with a number at the beginning of each line for the user to choose
     */
    public void displayOptions() {
        int index = 0;
        for (String option : this.menuOptions) {
            System.out.printf("%3d. %s %n", ++index, option);
        }
        System.out.println();
    } // end method displayOptions

    /**
     * gets user choice from the menu
     * 
     * @param scanner a Scanner object to read user input
     * @return a String of the number which the user chose
     * @throws Exception when the user input dose not match any menu option number
     */
    public String getUserChoice(Scanner scanner) throws Exception {
        String userChoice = scanner.nextLine();
        for (int i = 0; i < menuOptions.size(); i++) {
            if (userChoice.equals(String.valueOf(i + 1))) {
                return userChoice;
            }
        }
        throw new Exception(
                String.format("Invalid option. Please enter a number between 1 and %d.", this.menuOptions.size()));
    } // end method getUserChoice

    /**
     * executes certain method base on user choice
     * 
     * @param userChoice the String of the option number which the user chose 
     * @param methods Runnable methods to execute for each menu option
     */
    public void proceedToOption(String userChoice, Runnable... methods) {
        for (int i = 0; i < menuOptions.size(); i++) {
            if (userChoice.equals(String.valueOf(i + 1))) {
                methods[i].run();
                return;
            }
        }
    } // end method proceed

    public void execute(Scanner scanner, String exitMsg, Runnable... methods) {
        while (true) {
            String[] userChoice = new String[1];
            try {
                UserInteraction.limitAttempts(() -> {
                    System.out.printf("%n======= %s MENU ======= %n%n", this.menuHeader.toUpperCase());
                    // display main menu options
                    displayOptions();
                    userChoice[0] = getUserChoice(scanner);
                }, 3);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            if (userChoice[0].equals(String.valueOf(this.menuOptions.size()))) {
                if (UserInteraction.promptForResponse(scanner, exitMsg)) {
                    return;
                }
            } else {
                proceedToOption(userChoice[0], methods);
            }
        }
    } // end method execute
}
