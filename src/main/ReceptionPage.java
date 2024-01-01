package src.main;

import java.util.ArrayList;
import java.util.Arrays;

public class ReceptionPage extends Team6MedicalClinic {
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
}
