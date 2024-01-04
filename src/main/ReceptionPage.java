package src.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.person.Patient;
import src.utils.ObjectIO;
import src.utils.UserInteraction;
import src.clinic.Appointment;

public class ReceptionPage implements UserInteraction {
    private Scanner scanner;

    public ReceptionPage(Scanner scanner) {
        this.scanner = scanner;
    }
    
    // display reception menu and perform corresponding tasks base on user choice
    @Override
    public void displayMenu() {
        ArrayList<Object> patients = ObjectIO.loadData(ObjectIO.PATIENT_FILE_PATH);
        ArrayList<Object> appointments = ObjectIO.loadData(ObjectIO.APPOINTMENT_FILE_PATH);

        // reception menu options
        String[] optionList = { "Create appointment", "Search for an appointment", "All appointments", "Main menu" };
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(optionList));

        while (true) {
            int userChoice = UserInteraction.chooseFromMenu(this.scanner, menu);

            switch (userChoice) {
                case 1:
                    System.out.println("======= NEW PATIENT =======");
                    Patient patient = UserInteraction.createPatient(this.scanner);
                    patients.add(patient);
                    ObjectIO.writeObjects(ObjectIO.PATIENT_FILE_PATH, patients);
                    Appointment appointment = UserInteraction.createAppointment(this.scanner, patient.getName());
                    appointments.add(appointment);
                    ObjectIO.writeObjects(ObjectIO.APPOINTMENT_FILE_PATH, appointments);
                    this.scanner.nextLine();
                    break;
                case 2:
                    System.out.println("Search for an appointment");
                    break;
                case 3:
                    System.out.println("All appointments");
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
