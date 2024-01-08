package src.main;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import src.person.Doctor;
import src.person.Patient;
import src.utils.BusinessHours;
import src.utils.Menu;
import src.utils.ObjectIO;
import src.utils.UserInteraction;
import src.clinic.Appointment;

public class ReceptionPage extends Team6MedicalClinic {
    private static Menu receptionMenu = new Menu("reception", "Display Schedule", "Create new appointment",
            "Search for an appointment", "All appointments","Register new patient", "Search for a patient", "All patients", "Main menu");
    private static ArrayList<Object> patientList = ObjectIO.loadData(ObjectIO.PATIENT_FILE_PATH);
    private static ArrayList<Object> appointmentList = ObjectIO.loadData(ObjectIO.APPOINTMENT_FILE_PATH);
    private static ArrayList<Object> doctorList = ObjectIO.loadData(ObjectIO.DOCTOR_FILE_PATH);

    public static void main(String[] args) {
        // execute reception menu
        receptionMenu.execute(scanner, "Are you sure to leave this page?", displaySchedule, createAppointment,
                searchAppointments, displayAllAppointments, registerPatient, DoctorPage.searchPatients, DoctorPage.displayAllPatients);
        // quit message
        System.out.println("Leaving...");
    } // end method main
    
    // runnables to be passed as params
    // display schedule (14 days from tomorrow) 
    private static Runnable displaySchedule = () -> {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        printSchedule(tomorrow, tomorrow.plusDays(14));
    };

    // create appointment
    private static Runnable createAppointment = () -> {
        String patientName = "";
        boolean noPatientDetails = true;
        while (noPatientDetails) {
            if (UserInteraction.promptForResponse(scanner, "Is the person a new patient?")) {
                System.out.println("\n======= NEW PATIENT =======");
                Patient patient = UserInteraction.createPatient(scanner);
                patientList.add(patient);
                patientName = patient.getName();
                noPatientDetails = false;
                // ObjectIO.writeObjects(ObjectIO.PATIENT_FILE_PATH, patients);
            } else {
                Object patient = UserInteraction.searchForPerson(scanner, patientList);
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
    
    // search appointments
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

    // display all appointments
    private static Runnable displayAllAppointments = () -> {
        UserInteraction.printAll(appointmentList, Appointment.class);
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
     * prints a time table of a day 
     * each time slot is of 30-min length, 
     * the time table will be from 9am to 14pm, 
     * with a lunchbreak from noon to 1pm
     * 
     * @param date a LocalDate of the current day
     * @param time a LocalTime when the time table begins
     */
    static void printDailySchedule(LocalDate date, LocalTime time) {
        if (time.equals(BusinessHours.CLOSE_TIME_PM.getTime())) {
            System.out.println();
            return;
        }

        if (time.equals(BusinessHours.CLOSE_TIME_AM.getTime())) {
            System.out.print("\t");
            printDailySchedule(date, BusinessHours.OPEN_TIME_PM.getTime());
        } else {
            if (checkTimeSlotAvailability(date, time)) {
                System.out.printf("%s\t", time);
                printDailySchedule(date, time.plusMinutes(30));
            } else {
                System.out.printf("\t\t");
                printDailySchedule(date, time.plusMinutes(30));
            }
        }
    } // end method printDailySchedule

    /**
     * prints a schedule of a specified range of days
     * 
     * @param startDate a LocalDate of the date the schedule begins
     * @param endDate a LocalDate of the date the schedule ends
     */
    static void printSchedule(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            return;
        }

        if (startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            System.out.println();
            printSchedule(startDate.plusDays(1), endDate);
        } else {
            System.out.printf("%s (%s)\t", startDate.getDayOfWeek().toString().substring(0, 3), startDate);
            printDailySchedule(startDate, BusinessHours.OPEN_TIME_AM.getTime());
            printSchedule(startDate.plusDays(1), endDate);
        }
    } // end method printSchedule

    /**
     * finds all appointments of a certain date and time
     * 
     * @param date a LocalDate of the appointment date
     * @param time a LocalTime of the appointment time
     * @return an ArrayList of Appointments that matches the date and time
     */
    static ArrayList<Appointment> getAppointmentsOfSameTime(LocalDate date, LocalTime time) {
        ArrayList<Appointment> bookedAppointments = new ArrayList<>();

        for (Object obj : appointmentList) {
            Appointment appointment = (Appointment) obj;
            if (appointment.getDate().equals(date) && appointment.getStartTime().equals(time)
                    && appointment.getStatus().equals("Comfirmed")) {
                bookedAppointments.add(appointment);
            }
        }

        return bookedAppointments;
    } // end method getAppointmentOfSameTime

    /**
     * checks if a doctor is available for a certain time slot
     * 
     * @param doctor a Doctor to check
     * @param bookedAppointments an ArrayList of Appointments of the same date and time
     * @return a boolean indicating if the doctor is available
     */
    static boolean checkDoctorAvailability(Doctor doctor, ArrayList<Appointment> bookedAppointments) {
        boolean isAvailable = true;

        for (Appointment appointment : bookedAppointments) {
            if (appointment.getDoctorName().equals(doctor.getName())) {
                isAvailable = false;
                break;
            }
        }

        return isAvailable;
    } // end method checkDoctorAvailability

    /**
     * checks if there is any doctor is available for a certain appointment time slot
     * 
     * @param date a LocalDate of the appointment date
     * @param time a LocalTime of the appointment time
     * @return a boolean indicating if there is any available doctor
     */
    static boolean checkTimeSlotAvailability(LocalDate date, LocalTime time) {
        boolean isAvailable = false;
        ArrayList<Appointment> bookedAppointments = getAppointmentsOfSameTime(date, time);

        for (Object obj : doctorList) {
            Doctor doctor = (Doctor) obj;
            isAvailable = checkDoctorAvailability(doctor, bookedAppointments);
            if (isAvailable) {
                break;
            }
        } 

        return isAvailable;
    } // end method checkTimeSlotAvailability
} // end class ReceptionPage
