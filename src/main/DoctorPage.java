package src.main;

import src.clinic.Treatment;
import src.person.Patient;
import src.utils.Menu;
import src.utils.ObjectIO;

/**
 * class DoctorPage extends Team6MedicalClinic
 * contains the main method to run the DoctorPage
 * 
 * @version 1.00
 * @since 2024-01-04
 * @author Team 6
 */

public class DoctorPage extends Team6MedicalClinic {
    private static Menu doctorMenu = new Menu("doctor menu", "Create new treatment", "Search for a treatment", "All treatments",
            "Search for a patient", "All patients", "Main menu");

    public static void main(String[] args) {
        // execute doctor menu
        doctorMenu.execute("Are you sure to leave this page?", createTreatment, searchTreatments,
                displayAllTreatments, searchPatients, displayAllPatients);
        // quit message
        System.out.println("Leaving...");
    }

    // runnables to be passed as params
    // create treatment
    private static Runnable createTreatment = () -> {
        System.out.println("======= NEW TREATMENT =======");
        Treatment newTreatment;
        try {
            newTreatment = user.createTreatment(allDoctors, allPatients);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        allTreatments.add(newTreatment);
        ObjectIO.writeObjects(ObjectIO.TREATMENT_FILE_PATH, allTreatments);
    };

    // search treatments
    private static Runnable searchTreatments = () -> {
        Treatment result;
        try {
            result = (Treatment) user.searchForTreatment(allTreatments);
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
                        "Would you like to edit the treatment?");  // ask if the user wants to edit the found object
            }, 3);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        
        if (isToEdit[0]) { 
            try {
                user.editTreatment(result); // edit object attributes
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return;
            }
            ObjectIO.writeObjects(ObjectIO.TREATMENT_FILE_PATH, allTreatments); // write objects to data file
        }
    };

    // display all treatments
    private static Runnable displayAllTreatments = () -> {
        System.out.println("======= ALL TREATMENTS =======");
        user.printAll(allTreatments, Treatment.class);
    };

    // search patients
    public static Runnable searchPatients = () -> {
        Patient result;
        try {
            result = (Patient) user.searchForPerson(allPatients);
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
                        "Would you like to edit the patient's information?");  // ask if the user wants to edit the found object
            }, 3);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        
        if (isToEdit[0]) { 
            try {
                user.editPatient(result); // edit object attributes
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return;
            }
            ObjectIO.writeObjects(ObjectIO.PATIENT_FILE_PATH, allPatients); // write objects to data file
        }
    };
    
    // display all patients
    public static Runnable displayAllPatients = () -> {
        System.out.println("======= ALL PATIENTS =======");
        user.printAll(allPatients, Patient.class);
    };
}
