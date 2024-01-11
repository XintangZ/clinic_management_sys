package src.main;

import java.util.ArrayList;

import src.person.Doctor;
import src.person.Patient;
import src.utils.Menu;
import src.utils.NoDataException;
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
    private static Menu receptionMenu = new Menu("reception menu", "Create new appointment",
            "Search for an appointment", "All appointments", "Register new patient", "Search for a patient", "All patients", "Main menu");

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
                doctorName[0] = chooseDoctor(); // choose one of the doctors of a certain specialty
            }, 3);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        } 
        // get all incoming appointments of the chosen doctor
        ArrayList<Appointment> futureAppointments = Schedule.getFutureAppointments(allAppointments);
        ArrayList<Appointment> doctorAppointments = Schedule.getDoctorAppointments(futureAppointments, doctorName[0]); 
        Schedule.displayDoctorSchedule(doctorAppointments);      // display the schedule of the chosen doctor

        // search for a patient
        System.out.println("======= RETRIEVE PATIENT =======");
        Patient result;
        try {
            result = (Patient) user.searchForPerson(allPatients);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("\n======= ENTER APPOINTMENT DETAILS =======");
        try {
            Appointment appointment = user.createAppointment(doctorAppointments, result.getName(), doctorName[0]);
            System.out.println("\n======= APPOINTMENT SUCCESSFULLY CREATED =======");
            System.out.println(appointment);

            allAppointments.add(appointment);
            ObjectIO.writeObjects(ObjectIO.APPOINTMENT_FILE_PATH, allAppointments);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
    };
    
    // search appointments
    private static Runnable searchAppointments = () -> {
        ArrayList<Object> results;

        try {
            results = user.searchForAppointment(allAppointments);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        
        System.out.println("======= SEARCH RESULTS =======");
        user.printAll(results, Appointment.class);

        if (results.size() == 1) {
            boolean[] isToEdit = new boolean[1];
            try {
                user.limitAttempts(() -> {
                    isToEdit[0] = user.getResponse(
                            "Would you like to edit the appointment?");  // ask if the user wants to edit the found object
                }, 3);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return;
            }
            
            if (isToEdit[0]) { 
                try {
                    user.editAppointment((Appointment) results.get(0)); // edit object attributes
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }
                ObjectIO.writeObjects(ObjectIO.APPOINTMENT_FILE_PATH, allAppointments); // write objects to data file
            }
        }
    };

    // display all appointments
    private static Runnable displayAllAppointments = () -> {
        System.out.println("======= ALL APPOINTMENTS =======");
        user.printAll(allAppointments, Appointment.class);
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
        allPatients.add(patient);
        ObjectIO.writeObjects(ObjectIO.PATIENT_FILE_PATH, allPatients);
    };

    // static methods
    /**
     * prompts the user to choose from a list of doctor specialities
     * 
     * @return a String of the chosen doctor specialty
     * @throws Exception if maxinum number of attempts reached
     */
    static String chooseSpecialty() throws Exception {
        String chosenSpecialty;
        ArrayList<String> specialties = new ArrayList<>();

        for (Object doctor : allDoctors) {
            if (!specialties.contains(((Doctor) doctor).getSpecialty())) {
                specialties.add(((Doctor) doctor).getSpecialty());
            }
        }

        if (specialties.isEmpty()) {
            throw new NoDataException("No registered doctor.");
        }

        Menu specialtyList = new Menu("select a specialty", specialties);
        chosenSpecialty = specialtyList.getChosenOption();

        return chosenSpecialty;
    } // end method chooseSpecialty

    /**
     * gets all doctors of a certain specialty
     * 
     * @param allDoctors an ArrayList of Object containing all doctors
     * @param specialty a String specifying the specialty
     * @return an ArrayList of doctor of the specified specialty
     */
    static ArrayList<Doctor> getSpecialtyDoctors(ArrayList<Object> allDoctors, String specialty) {
        ArrayList<Doctor> specialists = new ArrayList<>();

        for (Object obj : allDoctors) {
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
        String chosenDoctor;

        ArrayList<Doctor> doctors = getSpecialtyDoctors(allDoctors, chooseSpecialty());
        ArrayList<String> doctorNames = new ArrayList<>();

        for (Doctor doctor : doctors) {
            doctorNames.add(doctor.getName());
        }

        Menu doctorNameList = new Menu("select a doctor", doctorNames);
        chosenDoctor = doctorNameList.getChosenOption();

        return chosenDoctor;
    } // end method chooseDoctor
} // end class ReceptionPage
