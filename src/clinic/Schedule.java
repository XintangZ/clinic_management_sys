package src.clinic;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface Schedule {
    /**
     * displays a time table in the terminal showing the availability of a certain doctor
     * 
     * @param doctorAppointments an ArrayList of Appointments of the doctor
     */
    static void displayDoctorSchedule(ArrayList<Appointment> doctorAppointments) {
        LocalDate scheduleStartDate = LocalDate.now().plusDays(1);
        LocalDate scheduleEndDate = scheduleStartDate.plusDays(13);

        System.out.println(
            "+---------------------------------------------------------------------------------------------------------+"); 
        System.out.print("|                    "); 
        printScheduleHeader(BusinessHours.OPEN_TIME_AM.getTime());   
        printWeeklySchedule(scheduleStartDate, scheduleEndDate, doctorAppointments);
    } // end method displayDoctorSchedule

    /**
     * recursively prints a time table of a day.
     * each time slot is of 30-min length, 
     * the time table will be from 9am to 14pm, 
     * with a lunchbreak from noon to 1pm
     * 
     * @param date a LocalDate of the current day
     * @param time a LocalTime when the time table begins
     * @param doctorAppointments an ArrayList of Appointments of a certain doctor
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
     * @param doctorAppointments an ArrayList of Appointments of a certain doctor
     */
    static void printWeeklySchedule(LocalDate startDate, LocalDate endDate, ArrayList<Appointment> doctorAppointments) {
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------+");
        if (startDate.isAfter(endDate)) { // base case
            return;
        }

        if (startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) { // skip weekends
            System.out.println(
                    "|                                                                                                         |");
            printWeeklySchedule(startDate.plusDays(2), endDate, doctorAppointments);
        } else {
            System.out.printf("|  %s (%s)  ", startDate.getDayOfWeek().toString().substring(0, 3), startDate);     // print day of the week and date
            printDailySchedule(startDate, BusinessHours.OPEN_TIME_AM.getTime(), doctorAppointments);    // print the time slots for the day
            printWeeklySchedule(startDate.plusDays(1), endDate, doctorAppointments);
        }
    } // end method printWeeklySchedule

    /**
     * prints the header of the schedule
     * 
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
     * gets all appointments for a certain doctor
     * 
     * @param appointmentList an ArrayList of Object containing all appointments
     * @param doctor the doctor to check
     * @return an ArrayList of Appointment for the specified doctor
     */
    static ArrayList<Appointment> getDoctorAppointments(ArrayList<Appointment> appointmentList, String doctorName) {
        ArrayList<Appointment> doctorAppointments = new ArrayList<>();

        for (Object obj : appointmentList) {
            Appointment appointment = (Appointment) obj;
            if (appointment.getDoctorName().equals(doctorName)) {
                doctorAppointments.add(appointment);
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
                    && appointment.getStatus().equals("Confirmed")) {
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
    static ArrayList<Appointment> getFutureAppointments(ArrayList<Object> appointmentList) {
        ArrayList<Appointment> futureAppointments = new ArrayList<>();

        for (Object obj : appointmentList) {
            Appointment appointment = (Appointment) obj;
            if (!appointment.getDate().isBefore(LocalDate.now())) {
                futureAppointments.add(appointment);
            }
        } 

        return futureAppointments;
    } // end method getFutureAppointments
}
