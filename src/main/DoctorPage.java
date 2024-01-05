package src.main;

import java.util.ArrayList;
import java.util.Arrays;

import src.clinic.Treatment;
import src.utils.Menu;
import src.utils.ObjectIO;
import src.utils.UserInteraction;

public class DoctorPage extends Team6MedicalClinic {
    private static Menu doctorMenu = new Menu("doctor", "New treatment", "Search for a treatment", "All treatments",
            "Search for a patient", "All patients", "Main menu");
    private static ArrayList<Object> patients = ObjectIO.loadData(ObjectIO.PATIENT_FILE_PATH);
    private static ArrayList<Object> treatments = ObjectIO.loadData(ObjectIO.TREATMENT_FILE_PATH);


    public static void main(String[] args) {

        doctorMenu.execute(scanner, "Are you sure to leave this page?", createTreatment, searchTreatment,
                displayAllTreatments, searchPatient, displayAllPatients);

        System.out.println("Leaving...");
    }
    
    private static Runnable createTreatment = () -> {
        System.out.println("======= NEW TREATMENT =======");
        Treatment newTreatment = UserInteraction.createTreatment(scanner);
        treatments.add(newTreatment);
        ObjectIO.writeObjects(ObjectIO.TREATMENT_FILE_PATH, treatments);
    };

    private static Runnable searchTreatment = () -> {

    };

    private static Runnable displayAllTreatments = () -> {
        if (treatments.isEmpty()) {
            System.out.println("No record.");
        } else {
            System.out.println("======= ALL TREATMENTS =======");
            int index = 1;
            for (Object obj : treatments) {
                System.out.printf("------- Treatment %d ------- %n", index++);
                System.out.println((Treatment) obj);
            }
        }
    };

    private static Runnable searchPatient = () -> {

    };

    private static Runnable displayAllPatients = () -> {

    };
}
