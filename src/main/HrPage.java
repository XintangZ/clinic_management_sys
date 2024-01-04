package src.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.person.Doctor;
import src.utils.ObjectIO;
import src.utils.UserInteraction;

public class HrPage implements UserInteraction {
    private Scanner scanner;

    public HrPage(Scanner scanner) {
        this.scanner = scanner;
    }

    // display HR menu and perform corresponding tasks base on user choice
    @Override
    public void displayMenu() {
        ArrayList<Object> doctors = ObjectIO.loadData(ObjectIO.DOCTOR_FILE_PATH);

        // HR menu options
        String[] optionList = {"Add a new doctor", "Search for a doctor", "All doctors", "Main menu"};
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(optionList));

        while (true) {
            int userChoice = UserInteraction.chooseFromMenu(this.scanner, menu);
            
            switch (userChoice) {
                case 1:
                    System.out.println("======= NEW DOCTOR =======");
                    Doctor doctor = UserInteraction.createDoctor(this.scanner);
                    doctors.add(doctor);
                    ObjectIO.writeObjects(ObjectIO.DOCTOR_FILE_PATH, doctors);
                    break;
                case 2:
                    Doctor result = (Doctor) UserInteraction.searchForPerson(scanner, doctors);
                    if (result == null) {
                        System.out.println("Doctor not found.");
                    } else {
                        System.out.println("======= SEARCH RESULT =======");
                        System.out.println(result);
                    }
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
                    if (UserInteraction.promptForResponse(this.scanner, "Are you sure to leave this page?")) {
                        return;
                    }
                    break;
            } // end switch userChoice
        } // end while loop
    } // end method displayMenu
}
