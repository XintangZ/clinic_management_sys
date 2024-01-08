package src.main;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import src.person.Doctor;
import src.person.Patient;
import src.utils.Menu;
import src.utils.ObjectIO;
import src.utils.UserInteraction;
import src.clinic.Appointment;
import src.clinic.BusinessHours;

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

    private static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        // all incoming appointments
        ArrayList<Object> futureAppointment = getFutureAppointments(appointmentList);
        // execute reception menu
        receptionMenu.execute("Are you sure to leave this page?", createAppointment,
                searchAppointments, displayAllAppointments, registerPatient, DoctorPage.searchPatients, DoctorPage.displayAllPatients);
        // quit message
        System.out.println("Leaving...");
    } // end method main
    
    // runnables to be passed as params
    // create appointment
    private static Runnable createAppointment = () -> {
        LocalDate scheduleStartDate = LocalDate.now().plusDays(1);
        LocalDate scheduleEndDate = scheduleStartDate.plusDays(13);

        // TODO:
        // ask for specialty (call getSpecialtyDoctors
        // select doctor (doctor = chooseFromMenu
        // display doctor schedule (printSchedule   
        System.out.println(
                "+---------------------------------------------------------------------------------------------+"); 
        System.out.print("|                    "); 
        printScheduleHeader(BusinessHours.OPEN_TIME_AM.getTime());   
        printSchedule(scheduleStartDate, scheduleEndDate, appointments);
        // ask if to create an appointment
        // ask if the patient is new
        // search for patient || create new patient
        // select date
        // select time
        // enter the rest of attrs for the appointment
        // String patientName = "";
        // boolean noPatientDetails = true;
        // while (noPatientDetails) {
        //     if (user.getResponse("Is the person a new patient?")) {
        //         System.out.println("\n======= NEW PATIENT =======");
        //         Patient patient = user.createPatient();
        //         patientList.add(patient);
        //         patientName = patient.getName();
        //         noPatientDetails = false;
        //         // ObjectIO.writeObjects(ObjectIO.PATIENT_FILE_PATH, patients);
        //     } else {
        //         Object patient = user.searchForPerson(patientList);
        //         if (patient == null) {
        //             System.out.println("\nThis person does not exist.");
        //         } else {
        //             patientName = ((Patient)patient).getName();
        //             noPatientDetails = false;
        //         }
        //     }
        // }
        // scanner.nextLine();
        // System.out.println("\n======= ENTER APPOINTMENT DETAILS =======");
        // Appointment appointment = UserInteraction.createAppointment(scanner, patientName);
        // System.out.println("\n======= APPOINTMENT SUCCESSFULLY CREATED =======");
        // System.out.println(appointment);
        // scanner.nextLine();
    };
    
    // search appointments
    private static Runnable searchAppointments = () -> {
        // String[] filterMenu = {"Confirmed", "Cancelled"};
        // ArrayList<Object> filter = new ArrayList<>();
        // int response = UserInteraction.chooseFromMenu(scanner, filterMenu);
        // for (Object obj : appointmentList) {
        //     if (((Appointment) obj).getStatus().equals(filterMenu[response - 1])) {
        //         filter.add(obj);
        //     }
        // }
        // ArrayList<Object> smallerFilter = UserInteraction.searchForAppointments(scanner, filter);
        // if (smallerFilter.size() == 0) {
        //     System.out.println("\nThere are no appointments that matches the criteria.");
        // } else if (smallerFilter.size() == 1){
        //     if (user.getResponse("There is only one appointment. Do you want to cancel it?")) {
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
     * recursively prints a time table of a day.
     * each time slot is of 30-min length, 
     * the time table will be from 9am to 14pm, 
     * with a lunchbreak from noon to 1pm
     * 
     * @param date a LocalDate of the current day
     * @param time a LocalTime when the time table begins
     */
    static void printDailySchedule(LocalDate date, LocalTime time, ArrayList<Appointment> doctorAppointments) {
        if (time.equals(BusinessHours.CLOSE_TIME_PM.getTime())) { // base case
            System.out.println("|");
            return;
        }

        if (time.equals(BusinessHours.CLOSE_TIME_AM.getTime())) {
            System.out.print("|");
            printDailySchedule(date, BusinessHours.OPEN_TIME_PM.getTime(), doctorAppointments); // skip lunch break
        } else {
            if (checkAvailability(doctorAppointments, date, time)) {
                System.out.print("|           ");    // if the doctor is available, print nothing
                printDailySchedule(date, time.plusMinutes(60), doctorAppointments);
            } else {
                System.out.print("|     x     ");      // if the doctor is not available, print x
                printDailySchedule(date, time.plusMinutes(60), doctorAppointments);
            }
        }
    } // end method printDailySchedule

    /**
     * recursively prints a schedule table of a specified range of days
     * 
     * @param startDate a LocalDate of the date the schedule begins
     * @param endDate a LocalDate of the date the schedule ends
     */
    static void printSchedule(LocalDate startDate, LocalDate endDate, ArrayList<Appointment> doctorAppointments) {
        System.out.println(
                "+---------------------------------------------------------------------------------------------+");
        if (startDate.isAfter(endDate)) { // base case
            return;
        }

        if (startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) { // skip weekends
            System.out.println(
                    "|                                                                                             |");
            printSchedule(startDate.plusDays(2), endDate, doctorAppointments);
        } else {
            System.out.printf("|  %s (%s)  ", startDate.getDayOfWeek().toString().substring(0, 3), startDate);     // print day of the week and date
            printDailySchedule(startDate, BusinessHours.OPEN_TIME_AM.getTime(), doctorAppointments);    // print the time slots for the day
            printSchedule(startDate.plusDays(1), endDate, doctorAppointments);
        }
    } // end method printSchedule

    /**
     * recursively prints a time table of a day.
     * each time slot is of 30-min length, 
     * the time table will be from 9am to 14pm, 
     * with a lunchbreak from noon to 1pm
     * 
     * @param date a LocalDate of the current day
     * @param time a LocalTime when the time table begins
     */
    static void printScheduleHeader(LocalTime time) {
        if (time.equals(BusinessHours.CLOSE_TIME_PM.getTime())) { // base case
            System.out.println("|");
            return;
        }

        if (time.equals(BusinessHours.CLOSE_TIME_AM.getTime())) {
            System.out.print("|");
            printScheduleHeader(BusinessHours.OPEN_TIME_PM.getTime()); // skip lunch break
        } else {
            System.out.printf("|   %s   ", time);      // if the doctor is not available, print x
            printScheduleHeader(time.plusMinutes(60));
        }
    } // end method printScheduleHeader    

    /**
     * gets all doctors of a certain specialty
     * 
     * @param doctorList an ArrayList of Object containing all doctors
     * @param specialty a String specifying the specialty
     * @return  an ArrayList of doctor of the specified specialty
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
     * gets all appointments for a certain doctor
     * 
     * @param appointmentList an ArrayList of Object containing all appointments
     * @param doctor the doctor to check
     * @return an ArrayList of Appointment for the specified doctor
     */
    static ArrayList<Appointment> getDoctorAppointments(ArrayList<Object> appointmentList, Doctor doctor) {
        ArrayList<Appointment> doctorAppointments = new ArrayList<>();

        for (Object obj : appointmentList) {
            Appointment appointment = (Appointment) obj;
            if (doctor.getName().equals(appointment.getDoctorName())) {
                appointmentList.add(appointment);
            }
        } 

        return doctorAppointments;
    } // end method getDoctorAppointments 

    /**
     * gets a certain doctor's availability for a certain date and time
     * 
     * @param doctorAppointments an ArrayList of Appointments of a certain doctor
     * @param date a LocalDate to check
     * @param time a LocalTime to check
     * @return a boolean indicating if the doctor is available
     */
    static boolean checkAvailability(ArrayList<Appointment> doctorAppointments, LocalDate date, LocalTime time) {
        for (Object obj : doctorAppointments) {
            Appointment appointment = (Appointment) obj;
            if (appointment.getDate().equals(date) && appointment.getStartTime().equals(time)
                    && appointment.getStatus().equals("Comfirmed")) {
                return false;
            }
        }
        return true;
    } // end method checkAvailability

    /**
     * gets all incoming appointments
     * 
     * @param appointmentList an ArrayList of Object containing all appointments
     * @return an ArrayList of Object containing the appointments from today and after
     */
    static ArrayList<Object> getFutureAppointments(ArrayList<Object> appointmentList) {
        ArrayList<Object> futureAppointments = new ArrayList<>();

        for (Object obj : appointmentList) {
            Appointment appointment = (Appointment) obj;
            if (!appointment.getDate().isBefore(LocalDate.now())) {
                futureAppointments.add(obj);
            }
        } 

        return futureAppointments;
    } // end method getFutureAppointments
} // end class ReceptionPage
