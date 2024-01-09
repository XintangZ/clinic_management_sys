package src.main;

import java.awt.Font;
import java.util.ArrayList;

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
    protected static ArrayList<Object> allPatients = ObjectIO.loadData(ObjectIO.PATIENT_FILE_PATH);
    protected static ArrayList<Object> allAppointments = ObjectIO.loadData(ObjectIO.APPOINTMENT_FILE_PATH);
    protected static ArrayList<Object> allDoctors = ObjectIO.loadData(ObjectIO.DOCTOR_FILE_PATH);
    protected static ArrayList<Object> allTreatments = ObjectIO.loadData(ObjectIO.TREATMENT_FILE_PATH);

    protected static User user = new User();
    private static Menu mainMenu = new Menu("main menu", "Reception", "Doctor", "HR", "Quit");

    public static void main(String[] args) {
        // display logo
        Logo team6logo = new Logo("TEAM6", "Wide Latin", Font.ITALIC, 16);
        team6logo.printLogo();
        // execute main menu
        mainMenu.execute("Are you sure to quit?", displayReceptionMenu, displayDoctorMenu, displayHrMenu);
        // quit message
        System.out.println("Quitting...");
        user.close();
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
