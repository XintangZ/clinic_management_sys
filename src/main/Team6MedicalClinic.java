package src.main;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import src.utils.*;

public class Team6MedicalClinic {
    protected static InputValidator inputValidator = new InputValidator();
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
            int userChoice = inputValidator.chooseFromMenu(menu);

            switch (userChoice) {
                case 1:
                    // reception menu
                    ReceptionPage.displayMenu();
                    break;
                case 2:
                    // doctor menu
                    DoctorPage.displayMenu();
                    break;
                case 3:
                    // HR menu
                    HrPage.displayMenu();
                    break;
                default:
                    // confirm if the user wants to quit
                    if (inputValidator.promptForResponse("Are you sure to quit?")) {
                        inputValidator.close();
                        System.exit(0);
                    }
                    break;
            } // end switch userChoice
        } // end while loop
    } // end method main
} // end class Team6MedicalClinic
