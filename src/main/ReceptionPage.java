package src.main;

import java.util.ArrayList;
import java.util.Arrays;

import src.person.Patient;
import src.utils.Menu;
import src.utils.ObjectIO;
import src.utils.UserInteraction;
import src.clinic.Appointment;

public class ReceptionPage extends Team6MedicalClinic {
    private static Menu receptionMenu = new Menu("reception", "Create appointment", "Search for an appointment",
            "All appointments", "Main menu");
    private static ArrayList<Object> patients = ObjectIO.loadData(ObjectIO.PATIENT_FILE_PATH);

    public static void main(String[] args) {

        receptionMenu.execute(scanner, "Are you sure to leave this page?", createAppointment, searchAppointment, displayAllAppointments);

        System.out.println("Leaving...");
    }
    
    private static Runnable createAppointment = () -> {

    };
    
    private static Runnable searchAppointment = () -> {

    };

    private static Runnable displayAllAppointments = () -> {

    };
}
