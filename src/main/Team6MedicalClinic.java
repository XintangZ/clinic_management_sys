package src.main;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.utils.*;

/**
 * class Team6MedicalClinic
 * contains the main method to run the program
 * 
 * @version 1.00
 * @since 2023-12-26
 * @author Team 6
 */

public class Team6MedicalClinic {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // define main menu options
        String[] optionList = { "Reception", "Doctor", "HR", "Quit" };
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(optionList));

        // display logo
        Logo team6logo = new Logo("TEAM6","Wide Latin",Font.ITALIC,12);
        team6logo.printLogo();

        while (true) {
            // display main menu and prompt the user to choose 
            System.out.println("======= MAIN MENU =======");
            int userChoice = UserInteraction.chooseFromMenu(scanner, menu);
            // display sub menu base on user chioce
            switch (userChoice) {
                case 1:     // reception menu
                    ReceptionPage receptionPage = new ReceptionPage(scanner);
                    receptionPage.displayMenu();
                    break;
                case 2:     // doctor menu
                    DoctorPage doctorPage = new DoctorPage(scanner);
                    doctorPage.displayMenu();
                    break;
                case 3:     // HR menu
                    HrPage hrPage = new HrPage(scanner);
                    hrPage.displayMenu();
                    break;
                default:    // quit
                    // confirm if the user wants to quit
                    if (UserInteraction.promptForResponse(scanner, "Are you sure to quit?")) {
                        scanner.close();
                        System.exit(0);
                    }
                    break;
            } // end switch userChoice
        } // end while loop
    } // end method main
} // end class Team6MedicalClinic
