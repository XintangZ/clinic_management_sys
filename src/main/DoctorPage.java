package src.main;

import java.util.ArrayList;
import java.util.Arrays;

import src.clinic.Treatment;
import src.utils.ObjectIO;

public class DoctorPage extends Team6MedicalClinic {
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
}
