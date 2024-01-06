package src.utils;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import src.clinic.Treatment;
import src.person.BloodType;
import src.person.Doctor;
import src.person.Gender;
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
     * runs a method for a specified number of times
     * 
     * @param method a method to run for a certain number of times
     * @param attempts an int for the number of times the method will run
     */
    public static void limitAttempts(RunnableWithException methodToRun, int attempts) throws Exception {
        do {
            try {
                methodToRun.run();
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                if (attempts > 2) {
                    System.out.printf("%d attempts left. %n", --attempts);
                } else {
                    System.out.printf("%d attempt left. %n", --attempts);
                }
            }
        } while (attempts > 0);
        throw new Exception("Maximum attempts exceeded, session ends.");
    } // end method limitAttempts

    /**
     * displays a message in the ternimal
     * and prompts the user to enter a non-empty String
     *  
     * @param scanner a Scanner object to read user input
     * @param promptMsg a String of the prompt message to be displayed to the user
     * @return a String entered by the user
     */
    public static String promptForString(Scanner scanner, String promptMsg) {
        String str;
        while (true) {
            System.out.print(promptMsg + ": ");
            try {
                str = scanner.nextLine().trim();
                if (str.isBlank()) {
                    throw new Exception(String.format("Invalid input. %s cannot be empty.", promptMsg));
                }
                return str;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    } // end method prompForString

    /**
     * displays a message in the ternimal
     * and prompts the user to enter a non-negative number
     *  
     * @param scanner a Scanner object to read user input
     * @param promptMsg a String of the prompt message to be displayed to the user
     * @return an int entered by the user
     */
    public static int promptForPositiveInt(Scanner scanner, String promptMsg) {
        int num;
        while (true) {
            System.out.print(promptMsg + ": ");
            try {
                num = scanner.nextInt();
                // check if the user input is greater than 0
                if (num < 0) {
                    throw new Exception(String.format("Invalid input. %s cannot be negative.", promptMsg));
                } // end if
                return num;
            } // end try
            catch (InputMismatchException e) {
                scanner.nextLine();
                System.err.println((String.format("Invalid input. %s must be a whole number.", promptMsg)));
            }  // end catch InputMismatchException
            catch (Exception e) {
                scanner.nextLine();
                System.err.println(e.getMessage());
            } // end catch Exception
        } // end while loop
    } // end method promptForPositiveInt

    /**
     * displays a message in the ternimal
     * and prompts the user to enter a date in format yyyy-mm-dd,
     * then parses the user input to a LocalDate object
     *  
     * @param scanner a Scanner object to read user input
     * @param promptMsg a String of the prompt message to be displayed to the user
     * @return a localDate object parsed from the String entered by the user
     */
    public static LocalDate promptForDate(Scanner scanner, String promptMsg) {
        String dateToParse;
        LocalDate date;
        while (true) {
            dateToParse = promptForString(scanner, promptMsg);
            try {
                date = LocalDate.parse(dateToParse);
                return date;
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date format. Format must be \"yyyy-mm-dd\".");
            }
        }
    } // end method promptForDate

    /**
     * displays a message in the ternimal
     * and prompts the user to enter a time in format hh:mm,
     * then parses the user input to a LocalTime object
     *  
     * @param scanner a Scanner object to read user input
     * @param promptMsg a String of the prompt message to be displayed to the user
     * @return a localTime object parsed from the String entered by the user
     */
    public static LocalTime promptForTime(Scanner scanner) {
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

    /**
     * displays a menu with numeric options in the terminal
     * and prompts the user to choose one of the menu option by enterning a number
     * 
     * @param scanner a Scanner object to read user input
     * @param menu an ArrayList of Strings containing menu options to be displayed to the user
     * @return the int entered by the user
     */
    public static int chooseFromMenu(Scanner scanner, String[] menu) {
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

    /**
     * prompts the user a closed question (yes/no question) <p>
     * returns true if the user inputs "y" or "yes" <p>
     * returns false if the uesr inputs "n" or "no" <p>
     * (Cases are ignored.)
     * 
     * @param closedQuestion a String of a closed question
     * @return a boolean, the value of which is determined by the user input
     */
    public static boolean promptForResponse(Scanner scanner, String closedQuestion) {
        boolean response;
        while (true) {
            try {
                // print the question
                System.out.println(closedQuestion + " (y/n)");
                // format user input
                String userInput = scanner.nextLine().trim().toLowerCase();

                if (userInput.equals("y") || userInput.equals("yes")) {
                    response = true;
                } else if (userInput.equals("n") || userInput.equals("no")) {
                    response = false;
                } else {
                    throw new Exception("Invalid response. Please try again.");
                } // end if
                return response;
            } // end try
            catch (Exception e) {
                scanner.nextLine();
                System.err.println(e.getMessage());
            } // end catch Exception
        } // end while loop
    } // end method promptForResponse

    /**
     * instantiates a Doctor object
     * by prompting the user to enter attributes in the terminal
     * 
     * @param scanner a Scanner object to read user input
     * @return a Doctor object
     */
    public static Doctor createDoctor(Scanner scanner) {
        Doctor doctor = new Doctor();

        setPersonalInfo(scanner, doctor);

        while (true) {
            try {
                doctor.setDateOfEmployment(promptForDate(scanner, "Date of Employment"));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        doctor.setSpecialty(promptForString(scanner, "Specialty"));

        return doctor;
    } // end method createDoctor

    /**
     * instantiates a Patient object
     * by prompting the user to enter attributes in the terminal
     * 
     * @param scanner a Scanner object to read user input
     * @return a Patient object
     */
    public static Patient createPatient(Scanner scanner) {
        // BloodType bloodType;
        Patient patient = new Patient();

        setPersonalInfo(scanner, patient);
        patient.setAllergies(promptForString(scanner, "Allergies"));
        patient.setInsuranceCompany(promptForString(scanner, "Insurance Company"));
        patient.setPolicyNumber(promptForString(scanner, "Policy Number"));
        patient.setCoveredPercentage(promptForPositiveInt(scanner, "Covered Percentage"));

        // bloodType = promptForString("Blood Type");
        // patient.setBloodType(bloodType);

        return patient;
    } // end method createPatient 
    
    /**
     * initializes each property of a Person object
     * by prompting the user to enter attributes in the terminal
     * 
     * @param scanner a Scanner object to read user input
     * @param person a Person object to add attributes to
     */
    public static void setPersonalInfo(Scanner scanner, Person person) {
        person.setFirstName(promptForString(scanner, "First Name"));
        person.setLastName(promptForString(scanner, "Last Name"));

        while (true) {
            try {
                person.setDateOfBirth(promptForDate(scanner, "Date of Birth"));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
        while (true) {
            try {
                person.setGender(promptForString(scanner, "Gender"));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        person.setPhoneNumber(promptForString(scanner, "Phone Number"));
        person.setAddress(promptForString(scanner, "Address"));
    } // end method setPersonalInfo
    
    /**
     * instantiates a Treatment object
     * by prompting the user to enter attributes in the terminal
     * 
     * @param scanner a Scanner object to read user input
     * @return a Treatment object
     */
    static Treatment createTreatment(Scanner scanner) {
        Treatment treatment = new Treatment();

        treatment.setMedication(promptForString(scanner, "Medication"));
        treatment.setDescription(promptForString(scanner, "Description"));

        while (true) {
            try {
                treatment.setStartDate(promptForDate(scanner, "Start date"));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        while (true) {
            try {
                treatment.setEndDate(promptForDate(scanner, "End date"));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return treatment;
    } // end method createTreatment

    static Object searchForPerson(Scanner scanner, ArrayList<Object> arrayList) {
        String firstName, lastName, name, phoneNumber;

        firstName = promptForString(scanner, "First Name");
        lastName = promptForString(scanner, "Last Name");
        name = firstName + " " + lastName;

        phoneNumber = promptForString(scanner, "Phone Number");

        for (Object obj : arrayList) {
            if (((Person) obj).getName().equals(name) && ((Person) obj).getPhoneNumber().equals(phoneNumber)) {
                return obj;
            }
        } 
        return null;
    }

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
                LocalDate date = promptForDate(scanner, "Appointment Date");
                for (Object obj : filter) {
                    if (((Appointment) obj).getDate().equals(date)) {
                        filter.add(obj);
                    }
                }
                break;
            default:
                LocalDate specificDate = promptForDate(scanner, "Appointment Date");
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
        LocalDate appointmentDate;
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
            appointmentDate = promptForDate(scanner, "Appointment Date");
            try {
                appointment.setDate(appointmentDate);
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
        String[] days = {   "Mon (" + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)) + ")",
                            "Tue (" + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY))+ ")",
                            "Wed (" + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY))+ ")",
                            "Thu (" + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY))+ ")",
                            "Fri (" + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY))+ ")"};
        
        for(int i = 0; i < days.length; i++) {
            System.out.print(days[i] + "\t");
            String[] times = { "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "13:00", "13:30", "14:00", "14:30",
                "15:00", "15:30" };
            for (int j = 0; j < times.length; j++) {
                for (Object obj : filter) {
                    if (((Appointment) obj).getDate().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).equals(days[i].substring(0, 3))) {
                        if ((((Appointment) obj).getStartTime().isBefore(LocalTime.parse(times[j])) || (((Appointment) obj).getStartTime()).equals(LocalTime.parse(times[j])))
                        && ((((Appointment) obj).getEndTime()).equals(LocalTime.parse(times[j])) || (((Appointment) obj).getEndTime()).isAfter(LocalTime.parse(times[j])))) {
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
