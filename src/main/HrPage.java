package src.main;

import java.util.ArrayList;
import java.util.Arrays;

import src.person.Doctor;
import src.utils.ObjectIO;

public class HrPage extends Team6MedicalClinic {
    // display HR menu and perform corresponding tasks base on user choice
    public static void displayMenu() {
        ArrayList<Object> doctors = ObjectIO.loadData(DOCTOR_FILE_PATH);

        // HR menu options
        String[] optionList = {"Add a new doctor", "Search for a doctor", "All doctors", "Main menu"};
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(optionList));

        while (true) {
            int userChoice = inputValidator.chooseFromMenu(menu);
            
            switch (userChoice) {
                case 1:
                    System.out.println("======= NEW DOCTOR =======");
                    Doctor doctor = inputValidator.createDoctor();
                    doctors.add(doctor);
                    ObjectIO.writeObjects(DOCTOR_FILE_PATH, doctors);
                    break;
                case 2:
                    System.out.println("Search for a doctor");
                    break;
                case 3:
                    if (doctors.isEmpty()) {
                        System.out.println("No record.");
                    } else {
                        System.out.println("======= ALL DOCTORS =======");
                        int index = 1;
                        for (Object obj : doctors) {
                            System.out.printf("------- Doctor %d ------- %n", index++);
                            System.out.println((Doctor) obj);
                        }
                    }
                    break;
                default:
                    if (inputValidator.promptForResponse("Are you sure to leave this page?")) {
                        return;
                    }
                    break;
            } // end switch userChoice
        } // end while loop
    } // end method displayHrMenu
}
