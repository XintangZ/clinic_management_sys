package src.utils;

import java.util.ArrayList;

/**
 * class Menu
 * 
 * @version 1.00
 * @since 2024-01-04
 * @author Team 6
 */

public class Menu extends InputValidator {
    private String menuHeader;
    private ArrayList<String> menuOptions;

    /**
     * constructor
     * 
     * @param menuOptions Strings of menu options to be displayed
     */
    public Menu(String menuHeader, String... menuOptions) {
        super();
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
     * @return a String of the number which the user chose
     * @throws Exception when the user input dose not match any menu option number
     */
    public String getUserChoice() throws Exception {
        String userChoice = scanner.nextLine().trim();
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

    /**
     * displays the menu, 
     * prompts the user to choose one of the menu options, 
     * and executes certain method base on user choice 
     * (the last option will always be quit/leave the menu)
     * 
     * @param exitMsg a String of message to be displayed when the user chooses to quit/leave the menu
     * @param methods Runnable methods to execute for each menu option
     */
    public void execute(String exitMsg, Runnable... methods) {
        while (true) {
            String[] userChoice = new String[1];
            try {
                limitAttempts(() -> {
                    System.out.printf("%n======= %s MENU ======= %n%n", this.menuHeader.toUpperCase());
                    // display main menu options
                    displayOptions();
                    // get user choice
                    userChoice[0] = getUserChoice();
                }, 3);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return;
            }

            if (userChoice[0].equals(String.valueOf(this.menuOptions.size()))) {
                boolean[] isToQuit = new boolean[1];
                try {
                    limitAttempts(() -> {
                        isToQuit[0] = getResponse(exitMsg);
                    }, attempts);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                if (isToQuit[0]) {
                    return;
                }
            } else {
                proceedToOption(userChoice[0], methods);
            }
        }
    } // end method execute
}
