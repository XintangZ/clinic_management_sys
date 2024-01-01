package src.main;

import java.util.ArrayList;
import java.util.Arrays;

public class HrPage extends Team6MedicalClinic {
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
}
