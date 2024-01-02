package src.main;

import java.util.ArrayList;
import java.util.Arrays;

import src.person.Doctor;
import src.person.Patient;
import src.utils.ObjectIO;

public class ReceptionPage extends Team6MedicalClinic {
    // display reception menu and perform corresponding tasks base on user choice
    public static void displayMenu() {
        ArrayList<Object> patients = ObjectIO.loadData(PATIENT_FILE_PATH);

        // reception menu options
        String[] optionList = { "New appointment", "Search for an appointment", "All appointments", "Main menu" };
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(optionList));

        while (true) {
            int userChoice = inputValidator.chooseFromMenu(menu);

            switch (userChoice) {
                case 1:
                    System.out.println("======= NEW PATIENT =======");
                    Patient patient = inputValidator.createPatient();
                    patients.add(patient);
                    ObjectIO.writeObjects(PATIENT_FILE_PATH, patients);
                    break;
                case 2:
                    System.out.println("Search for an appointment");
                    break;
                case 3:
                    System.out.println("All appointments");
                    break;
                default:
                    if (inputValidator.promptForResponse("Are you sure to leave this page?")) {
                        return;
                    }
                    break;
            } // end switch userChoice
        } // end while loop
    } // end method displayReceptionMenu
}
