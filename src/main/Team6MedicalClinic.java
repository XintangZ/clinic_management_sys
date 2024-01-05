package src.main;

import java.awt.Font;
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
    protected static Scanner scanner = new Scanner(System.in);
    private static Menu mainMenu = new Menu("main", "Reception", "Doctor", "HR", "Quit");

    public static void main(String[] args) {
        // display logo
        Logo team6logo = new Logo("TEAM6", "Wide Latin", Font.ITALIC, 12);
        team6logo.printLogo();

        mainMenu.execute(scanner, "Are you sure to quit?", displayReceptionMenu, displayDoctorMenu, displayHrMenu);

        System.out.println("Quitting...");
        scanner.close();
    } // end method main

    // runnables to be passed as params
    // reception menu
    static Runnable displayReceptionMenu = () -> {
        ReceptionPage.main(null);
    };
    // doctor menu
    static Runnable displayDoctorMenu = () -> {
        DoctorPage.main(null);
    };
    // hr menu
    static Runnable displayHrMenu = () -> {
        HrPage.main(null);
    };
} // end class Team6MedicalClinic
