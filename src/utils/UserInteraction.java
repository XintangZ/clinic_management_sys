package src.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import src.clinic.Treatment;
import src.person.Doctor;
import src.person.Patient;
import src.person.Person;
import src.clinic.Appointment;

/**
 * interface UserInteraction <p>
 * contains methods that prompt, validate and return user input <p>
 * also contains methods to instantiate different types of objects by prompting the user to input attributes
 * 
 * @version 1.00
 * @since 2023-12-30
 * @author Team 6
 */

public interface UserInteraction {
    /**
     * displays a message in the ternimal
     * and prompts the user to enter a time in format hh:mm,
     * then parses the user input to a LocalTime object
     *  
     * @param scanner a Scanner object to read user input
     * @param promptMsg a String of the prompt message to be displayed to the user
     * @return a localTime object parsed from the String entered by the user
     */
    static LocalTime promptForTime(Scanner scanner) {
        while (true) {
            int hour = promptForPositiveInt(scanner, "Enter the starting time in the format hh mm: ");
            String str = Integer.toString(hour);
            if (hour < 10) {
                str = "0" + Integer.toString(hour);
            }
            String timeToParse = String.format("%s:%s", str, scanner.next());
            try {
                LocalTime time = LocalTime.parse(timeToParse);
                return time;
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date format. Format must be \"yyyy-mm-dd\".");
            }
        }
    } // end method promptForTime

    // deprecated. use Menu class instead
    static int chooseFromMenu(Scanner scanner, String[] menu) {
		boolean isInvalidChoice = true;
        String input;

        do {
            System.out.printf("Choose one of the following options (enter a number): %n%n");
            for (int i = 1; i <= menu.length; i++) {
                System.out.printf("%3d. %s %n", i, menu[i - 1]);
            }
            System.out.println();
            input = scanner.nextLine();

            for (int j = 1; j <= menu.length; j++) {
                isInvalidChoice = isInvalidChoice && (input.compareTo(String.valueOf(j)) != 0);
            }
            
            if (isInvalidChoice) {
                System.err.printf("Invalid option. Please enter a number between 1 and %d. %n", menu.length);
            } else {
                isInvalidChoice = false;
            }            
        } while (isInvalidChoice);

        return Integer.parseInt(input);
    } // end method chooseFromMenu
    

    static ArrayList<Object> searchForAppointments(Scanner scanner, ArrayList<Object> arrayList) {
        
        ArrayList<Object> filter = new ArrayList<>();

        String[] menu = {"Doctor's Name", "Patient's Name", "Date", "Specific Appointment"};
        int response = chooseFromMenu(scanner, menu);

        switch (response) {
            case 1:
                String doctorName = promptForString(scanner, "Doctor's Name");
                for (Object obj : filter) {
                    if (((Appointment) obj).getDoctorName().equals(doctorName)) {
                        filter.add(obj);
                    }
                }
                break;
            case 2:
                String patientName = promptForString(scanner, "Patient's Name");
                for (Object obj : filter) {
                    if (((Appointment) obj).getPatientName().equals(patientName)) {
                        filter.add(obj);
                    }
                } 
                break;
            case 3:
                LocalDate date = promptForString(scanner, "Appointment Date");
                for (Object obj : filter) {
                    if (((Appointment) obj).getDate().equals(date)) {
                        filter.add(obj);
                    }
                }
                break;
            default:
                LocalDate specificDate = promptForString(scanner, "Appointment Date");
                LocalTime specificTime =  promptForTime(scanner);
                scanner.nextLine();
                String specificDoctorName = promptForString(scanner, "Doctor's Name");
                for (Object obj : filter) {
                    if (((Appointment) obj).getDate().equals(specificDate) && ((Appointment) obj).getStartTime().equals(specificTime) 
                    && ((Appointment) obj).getDoctorName().equals(specificDoctorName)) {
                        filter.add(obj);
                    }
                }
        }
        return filter;
    }
    
    public static Appointment createAppointment(Scanner scanner, String patientName) {
        LocalTime startTime, endTime;
        String doctorName;
        int minutes;
        Appointment appointment = new Appointment();
        // ArrayList<Object> appointmentList = ObjectIO.loadData(ObjectIO.APPOINTMENT_FILE_PATH);
        ArrayList<Object> appointmentList = new ArrayList<>();
        ArrayList<Object> filter = new ArrayList<>();
        appointment.setPatientName(patientName);
        doctorName = promptForString(scanner, "Doctor Name");
        appointment.setDoctorName(doctorName);

        for (Object obj : appointmentList) {
            if (((Appointment) obj).getDoctorName().equals(doctorName)) {
                filter.add(obj);
            }
        }
        showDoctorAvailabilitySchedule(doctorName, filter);
        while (true) {
            try {
                appointment.setDate(promptForString(scanner, "Appointment Date"));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        startTime = promptForTime(scanner);
        appointment.setStartTime(startTime);
        minutes = promptForPositiveInt(scanner, "Length of Appointment (in mins)");
        endTime = startTime.plusMinutes(minutes);
        appointment.setEndTime(endTime);
        appointment.setStatus("Confirmed");
        return appointment;
    }

    public static void showDoctorAvailabilitySchedule(String doctorName, ArrayList<Object> appointmentList) {
        ArrayList<Object> filter = new ArrayList<>();
        for (Object obj : appointmentList) {
            if (!((Appointment) obj).getStatus().equals("Cancelled")) {
                filter.add(obj);
            }
        }
        LocalDate today = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        String[] days = { "Mon (" + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)) + ")",
                "Tue (" + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY)) + ")",
                "Wed (" + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY)) + ")",
                "Thu (" + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY)) + ")",
                "Fri (" + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)) + ")" };

        for (int i = 0; i < days.length; i++) {
            System.out.print(days[i] + "\t");
            String[] times = { "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "13:00", "13:30", "14:00", "14:30",
                    "15:00", "15:30" };
            for (int j = 0; j < times.length; j++) {
                for (Object obj : filter) {
                    if (((Appointment) obj).getDate().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                            .equals(days[i].substring(0, 3))) {
                        if ((((Appointment) obj).getStartTime().isBefore(LocalTime.parse(times[j]))
                                || (((Appointment) obj).getStartTime()).equals(LocalTime.parse(times[j])))
                                && ((((Appointment) obj).getEndTime()).equals(LocalTime.parse(times[j]))
                                        || (((Appointment) obj).getEndTime()).isAfter(LocalTime.parse(times[j])))) {
                            times[j] = "     ";
                        }
                    }
                }
            }
            for (int timesCounter = 0; timesCounter < times.length; timesCounter++) {
                System.out.print(times[timesCounter] + "    ");
            }
            System.out.println();
        }
    }
}
