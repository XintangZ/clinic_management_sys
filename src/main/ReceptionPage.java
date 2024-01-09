package src.main;

import java.util.ArrayList;

import src.person.Doctor;
import src.person.Patient;
import src.utils.Menu;
import src.utils.ObjectIO;
import src.clinic.Appointment;
import src.clinic.Schedule;

/**
 * class ReceptionPage extends Team6MedicalClinic
 * contains the main method to run the ReceptionPage
 * 
 * @version 1.00
 * @since 2024-01-04
 * @author Team 6
 */

public class ReceptionPage extends Team6MedicalClinic {
    private static Menu receptionMenu = new Menu("reception", "Create new appointment",
            "Search for an appointment", "All appointments", "Register new patient", "Search for a patient", "All patients", "Main menu");
    private static ArrayList<Object> patientList = ObjectIO.loadData(ObjectIO.PATIENT_FILE_PATH);
    private static ArrayList<Object> appointmentList = ObjectIO.loadData(ObjectIO.APPOINTMENT_FILE_PATH);
    private static ArrayList<Object> doctorList = ObjectIO.loadData(ObjectIO.DOCTOR_FILE_PATH);

    public static void main(String[] args) {
        // execute reception menu
        receptionMenu.execute("Are you sure to leave this page?", createAppointment,
                searchAppointments, displayAllAppointments, registerPatient, DoctorPage.searchPatients, DoctorPage.displayAllPatients);
        // quit message
        System.out.println("Leaving...");
    } // end method main
    
    // runnables to be passed as params
    // create appointment
    private static Runnable createAppointment = () -> {
        String[] doctorName = new String[1];

        try {
            user.limitAttempts(() -> {
                doctorName[0] = chooseDoctor();      // choose one of the doctors of a certain specialty
            }, 3);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        } 
        // get all incoming appointments of the chosen doctor
        ArrayList<Appointment> futureAppointments = Schedule.getFutureAppointments(appointmentList);
        ArrayList<Appointment> doctorAppointments = Schedule.getDoctorAppointments(futureAppointments, doctorName[0]); 
        Schedule.displayDoctorSchedule(doctorAppointments);      // display the schedule of the chosen doctor

        // search for a patient
        Patient result;
        try {
            result = (Patient) user.searchForPerson(patientList);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        
        if (result == null) {
            System.out.println("Patient not found.");
            return; // return if no result found
        }

        System.out.println("\n======= ENTER APPOINTMENT DETAILS =======");
        try {
            Appointment appointment = user.createAppointment(result.getName(), doctorName[0]);
            System.out.println("\n======= APPOINTMENT SUCCESSFULLY CREATED =======");
            System.out.println(appointment);

            appointmentList.add(appointment);
            ObjectIO.writeObjects(ObjectIO.APPOINTMENT_FILE_PATH, appointmentList);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
    };
    
    // search appointments
    private static Runnable searchAppointments = () -> {
        // String[] filterMenu = {"Confirmed", "Cancelled"};
        // ArrayList<Object> filter = new ArrayList<>();
        // ArrayList<String> menu = new ArrayList<>();
        // for (String item : filterMenu) {
        //     menu.add(item);
        // }
        // int response = user.chooseFromList(menu);
        // for (Object obj : appointmentList) {
        //     if (((Appointment) obj).getStatus().equals(filterMenu[response - 1])) {
        //         filter.add(obj);
        //     }
        // }
        // ArrayList<Object> smallerFilter = user.chooseFromList(filter);
        // if (smallerFilter.size() == 0) {
        //     System.out.println("\nThere are no appointments that matches the criteria.");
        // } else if (smallerFilter.size() == 1){
        //     boolean[] isToCancel = new boolean[1];
        //     try {
        //         user.limitAttempts(() -> {
        //         isToCancel[0] = user.getResponse(
        //             "There is only one appointment. Do you want to cancel it?");  // ask if the user wants to cancel this appointment
        //     }, 3);
        //     } catch (Exception e) {
        //         System.err.println(e.getMessage());
        //         return;
        //     }
        //      if (isToCancel[0]) { 
        //         ((Appointment)smallerFilter.get(0)).setStatus("Cancelled");
        //         System.out.println("======= APPOINTMENT CANCELLED =======");
        //         System.out.println((Appointment)smallerFilter.get(0));
        //     }
        // } else {
        //     for (Object obj : smallerFilter) {
        //         System.out.println((Appointment) obj);
        //     }
        // }
    };

    // display all appointments
    private static Runnable displayAllAppointments = () -> {
        user.printAll(appointmentList, Appointment.class);
    };

    // register new patient
    private static Runnable registerPatient = () -> {
        System.out.println("======= NEW PATIENT =======");
        Patient patient;
        try {
            patient = user.createPatient();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        patientList.add(patient);
        ObjectIO.writeObjects(ObjectIO.PATIENT_FILE_PATH, patientList);
    };

    // static methods
    /**
     * prompts the user to choose from a list of doctor specialities
     * 
     * @return a String of the chosen doctor specialty
     * @throws Exception if maxinum number of attempts reached
     */
    static String chooseSpecialty() throws Exception {
        int response;
        ArrayList<String> specialties = new ArrayList<>();

        for (Object doctor : doctorList) {
            if (!specialties.contains(((Doctor) doctor).getSpecialty())) {
                specialties.add(((Doctor) doctor).getSpecialty());
            }
        }

        response = user.chooseFromList(specialties);
        return specialties.get(response - 1);
    } // end method chooseSpecialty

    /**
     * gets all doctors of a certain specialty
     * 
     * @param doctorList an ArrayList of Object containing all doctors
     * @param specialty a String specifying the specialty
     * @return an ArrayList of doctor of the specified specialty
     */
    static ArrayList<Doctor> getSpecialtyDoctors(ArrayList<Object> doctorList, String specialty) {
        ArrayList<Doctor> specialists = new ArrayList<>();

        for (Object obj : doctorList) {
            Doctor doctor = (Doctor) obj;
            if (doctor.getSpecialty().equals(specialty)) {
                specialists.add(doctor);
            }
        } 

        return specialists;
    } // end method getSpecialtyDoctors

    /**
     * prompts the user to choose from a list of doctor specialities, 
     * prints out a list of the doctors of the chosen specialty, 
     * then prompts the user to choose one of the listed doctor
     * 
     * @return a String of the chosen doctor's name
     * @throws Exception if maxinum number of attempts reached
     */
    static String chooseDoctor() throws Exception {
        ArrayList<Doctor> doctors = getSpecialtyDoctors(doctorList, chooseSpecialty());
        ArrayList<String> doctorNames = new ArrayList<>();

        for (Doctor doctor : doctors) {
            doctorNames.add(doctor.getName());
        }
        int response = user.chooseFromList(doctorNames);

        return doctorNames.get(response - 1);
    } // end method chooseDoctor
} // end class ReceptionPage
