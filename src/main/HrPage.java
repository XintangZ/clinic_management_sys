package src.main;

import src.person.Doctor;
import src.utils.Menu;
import src.utils.ObjectIO;

/**
 * class HrPage extends Team6MedicalClinic
 * contains the main method to run the HrPage
 * 
 * @version 1.00
 * @since 2024-01-04
 * @author Team 6
 */

public class HrPage extends Team6MedicalClinic {
    private static Menu hrMenu = new Menu("hr menu","Register new doctor", "Search for a doctor", "All doctors", "Main menu");

    public static void main(String[] args) {
        // execute hr menu
        hrMenu.execute("Are you sure to leave this page?", registerDoctor, searchDoctors, displayAllDoctors);
        // quit message
        System.out.println("Leaving...");
    } // end method main

    // runnables to be passed as params
    // register doctor
    private static Runnable registerDoctor = () -> {
        System.out.println("======= NEW DOCTOR =======");
        Doctor doctor;
        try {
            doctor = user.createDoctor();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        allDoctors.add(doctor);
        ObjectIO.writeObjects(ObjectIO.DOCTOR_FILE_PATH, allDoctors);
    };

    // search doctors
    private static Runnable searchDoctors = () -> {
        Doctor result;
        try {
            result = (Doctor) user.searchForPerson(allDoctors);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        
        System.out.println("======= SEARCH RESULT =======");
        System.out.println(result); // print the result

        boolean[] isToEdit = new boolean[1];
        try {
            user.limitAttempts(() -> {
                isToEdit[0] = user.getResponse(
                        "Would you like to edit the doctor's information?");  // ask if the user wants to edit the found object
            }, 3);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        
        if (isToEdit[0]) { 
            try {
                user.editDoctor(result); // edit object attributes
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return;
            }
            ObjectIO.writeObjects(ObjectIO.DOCTOR_FILE_PATH, allDoctors); // write objects to data file
        }
    };
    
    // display all doctors
    private static Runnable displayAllDoctors = () -> {
        System.out.println("======= ALL DOCTORS =======");
        user.printAll(allDoctors, Doctor.class);
    };
} // end class HrPage
