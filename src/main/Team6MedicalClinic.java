package src.main;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import src.person.Doctor;
import src.person.Patient;
import src.clinic.Treatment;
import src.utils.*;

public class Team6MedicalClinic {
    protected static UserInterface userInterface = new UserInterface();
    // data file paths
    protected static final String DOCTOR_FILE_PATH = "data/doctors.bin";
    protected static final String PATIENT_FILE_PATH = "data/patients.bin";
    protected static final String TREATMENT_FILE_PATH = "data/treatments.bin";
    protected static final String APPOINTMENT_FILE_PATH = "data/appointments.bin";

    public static void main(String[] args) {
        // display logo
        Logo team6logo = new Logo("TEAM6","Wide Latin",Font.ITALIC,12);
        team6logo.printLogo();

        // main menu options
        String[] optionList = { "Reception", "Doctor", "HR", "Quit" };
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(optionList));

        while (true) {
            // display main menu and prompt the user to choose 
            int userChoice = userInterface.chooseFromMenu(menu);

            switch (userChoice) {
                case 1:
                    // reception menu
                    displayReceptionMenu();
                    break;
                case 2:
                    // doctor menu
                    displayDoctorMenu();
                    break;
                case 3:
                    // HR menu
                    displayHrMenu();
                    break;
                default:
                    // confirm if the user wants to quit
                    if (userInterface.promptForResponse("Are you sure to quit?")) {
                        userInterface.close();
                        System.exit(0);
                    }
                    break;
            } // end switch userChoice
        } // end while loop
    } // end method main

    // display reception menu and perform corresponding tasks base on user choice
    public static void displayReceptionMenu() {
        // reception menu options
        String[] optionList = { "New appointment", "Search for an appointment", "All appointments", "Main menu" };
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(optionList));

        while (true) {
            int userChoice = userInterface.chooseFromMenu(menu);

            switch (userChoice) {
                case 1:
                    System.out.println("new appointment");
                    break;
                case 2:
                    System.out.println("Search for an appointment");
                    break;
                case 3:
                    System.out.println("All appointments");
                    break;
                default:
                    if (userInterface.promptForResponse("Are you sure to leave this page?")) {
                        return;
                    }
                    break;
            } // end switch userChoice
        } // end while loop
    } // end method displayReceptionMenu

    // display doctor menu and perform corresponding tasks base on user choice
    public static void displayDoctorMenu() {
        // doctor menu options
        String[] optionList = { "New treatment", "Search for a treatment", "All treatments", "Search for a patient", "All patients", "Main menu" };
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(optionList));

        while (true) {
            // load all treatments to memory
            ArrayList<Object> treatments = ObjectIO.readObjects(TREATMENT_FILE_PATH);

            int userChoice = userInterface.chooseFromMenu(menu);

            switch (userChoice) {
                case 1:
                    Treatment newTreatment = userInterface.createTreatment();
                    treatments.add(newTreatment);
                    ObjectIO.writeObjects(TREATMENT_FILE_PATH, treatments);
                    break;
                case 2:
                    System.out.println("Search for a treatment");
                    break;
                case 3:
                    for (Object obj : treatments) {
                        System.out.println((Treatment) obj);
                    }
                    break;
                case 4:
                    System.out.println("Search for a patient");
                    break;
                case 5:
                    System.out.println("All patients");
                    break;
                default:
                    if (userInterface.promptForResponse("Are you sure to leave this page?")) {
                        return;
                    }
                    break;
            } // end switch userChoice
        } // end while loop
    } // end method displayDoctorMenu

    // display HR menu and perform corresponding tasks base on user choice
    public static void displayHrMenu() {
        // HR menu options
        String[] optionList = {"Add a new doctor", "Search for a doctor", "All doctors", "Main menu"};
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(optionList));

        while (true) {
            int userChoice = userInterface.chooseFromMenu(menu);
            
            switch (userChoice) {
                case 1:
                    System.out.println("Add a new doctor");
                    break;
                case 2:
                    System.out.println("Search for a doctor");
                    break;
                case 3:
                    System.out.println("All doctors");
                    break;
                default:
                    if (userInterface.promptForResponse("Are you sure to leave this page?")) {
                        return;
                    }
                    break;
            } // end switch userChoice
        } // end while loop
    } // end method displayHrMenu
} // end class Team6MedicalClinic
