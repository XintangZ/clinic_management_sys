package src.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.person.Patient;
import src.person.BloodType;
import src.utils.Menu;
import src.utils.ObjectIO;
import src.utils.UserInteraction;
import src.clinic.Appointment;

public class ReceptionPage extends Team6MedicalClinic {
    private static Menu receptionMenu = new Menu("reception", "Create appointment", "Search for an appointment",
            "All appointments", "Main menu");
    // private static ArrayList<Object> patients = ObjectIO.loadData(ObjectIO.PATIENT_FILE_PATH);
    private static ArrayList<Object> patients = new ArrayList<>();
    private static ArrayList<Object> appointmentList = new ArrayList<>();
    // private static ArrayList<Object> appointmentList = ObjectIO.loadData(ObjectIO.APPOINTMENT_FILE_PATH);

    public static void main(String[] args) {

        receptionMenu.execute(scanner, "Are you sure to leave this page?", createAppointment, searchAppointments, displayAllAppointments);

        System.out.println("Leaving...");
    }
    
    private static Runnable createAppointment = () -> {
        String patientName = "";
        boolean noPatientDetails = true;
        while (noPatientDetails) {
            if (UserInteraction.promptForResponse(scanner, "Is the person a new patient?")) {
                System.out.println("\n======= NEW PATIENT =======");
                Patient patient = UserInteraction.createPatient(scanner);
                patients.add(patient);
                patientName = patient.getName();
                noPatientDetails = false;
                // ObjectIO.writeObjects(ObjectIO.PATIENT_FILE_PATH, patients);
            } else {
                Object patient = UserInteraction.searchForPerson(scanner, patients);
                if (patient == null) {
                    System.out.println("\nThis person does not exist.");
                } else {
                    patientName = ((Patient)patient).getName();
                    noPatientDetails = false;
                }
            }
        }
        scanner.nextLine();
        System.out.println("\n======= ENTER APPOINTMENT DETAILS =======");
        Appointment appointment = UserInteraction.createAppointment(scanner, patientName);
        System.out.println("\n======= APPOINTMENT SUCCESSFULLY CREATED =======");
        System.out.println(appointment);
        scanner.nextLine();
    };
    
    private static Runnable searchAppointments = () -> {
        String[] filterMenu = {"Confirmed", "Cancelled"};
        ArrayList<Object> filter = new ArrayList<>();
        int response = UserInteraction.chooseFromMenu(scanner, filterMenu);
        for (Object obj : appointmentList) {
            if (((Appointment) obj).getStatus().equals(filterMenu[response - 1])) {
                filter.add(obj);
            }
        }
        ArrayList<Object> smallerFilter = UserInteraction.searchForAppointments(scanner, filter);
        if (smallerFilter.size() == 0) {
            System.out.println("\nThere are no appointments that matches the criteria.");
        } else if (smallerFilter.size() == 1){
            if (UserInteraction.promptForResponse(scanner, "There is only one appointment. Do you want to cancel it?")) {
                ((Appointment)smallerFilter.get(0)).setStatus("Cancelled");
                System.out.println("======= APPOINTMENT CANCELLED =======");
                System.out.println((Appointment)smallerFilter.get(0));
            }

        } else {
            for (Object obj : smallerFilter) {
                System.out.println((Appointment) obj);
            }
        }

    };

    private static Runnable displayAllAppointments = () -> {
         if (appointmentList.size() == 0) {
            System.out.println("\nThere are no appointments.");
        } else {
            System.out.println("======= ALL APPOINTMENTS =======");
            for (Object obj : appointmentList) {
                System.out.println((Appointment) obj);
            }
        }
    };
}
