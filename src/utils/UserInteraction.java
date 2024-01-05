package src.utils;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import src.clinic.Treatment;
import src.person.BloodType;
import src.person.Doctor;
import src.person.Patient;
import src.person.Person;

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
     * displays a menu with numeric options in the terminal
     * and prompts the user to choose one of the menu option by enterning a number
     * 
     * @param scanner a Scanner object to read user input
     * @param menu an ArrayList of Strings containing menu options to be displayed to the user
     * @return the int entered by the user
     */
    public static int chooseFromMenu(Scanner scanner, ArrayList<String> menu) {
		boolean isInvalidChoice = true;
        String input;

        do {
            System.out.printf("Choose one of the following options (enter a number): %n%n");
            for (int i = 1; i <= menu.size(); i++) {
                System.out.printf("%3d. %s %n", i, menu.get(i - 1));
            }
            System.out.println();
            input = scanner.nextLine();

            for (int j = 1; j <= menu.size(); j++) {
                isInvalidChoice = isInvalidChoice && (input.compareTo(String.valueOf(j)) != 0);
            }
            
            if (isInvalidChoice) {
                System.err.printf("Invalid option. Please enter a number between 1 and %d. %n", menu.size());
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
        String specialty;
        LocalDate dateOfEmployment;
        Doctor doctor = new Doctor();

        setPersonalInfo(scanner, doctor);

        while (true) {
            dateOfEmployment = promptForDate(scanner, "Date of Employment");
            try {
                doctor.setDateOfEmployment(dateOfEmployment);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        specialty = promptForString(scanner, "Specialty");
        doctor.setSpecialty(specialty);

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
        String allergies, insuranceCompany, policyNumber;
        int coveredPercentage;
        BloodType bloodType;
        Patient patient = new Patient();

        setPersonalInfo(scanner, patient);

        allergies = promptForString(scanner, "Allergies");
        patient.setAllergies(allergies);

        insuranceCompany = promptForString(scanner, "Insurance Company");
        patient.setInsuranceCompany(insuranceCompany);

        policyNumber = promptForString(scanner, "Policy Number");
        patient.setPolicyNumber(policyNumber);

        coveredPercentage = promptForPositiveInt(scanner, "Covered Percentage");
        patient.setCoveredPercentage(coveredPercentage);

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
        String firstName, lastName, gender, phoneNumber, address;
        LocalDate dateOfBirth;

        firstName = promptForString(scanner, "First Name");
        person.setFirstName(firstName);

        lastName = promptForString(scanner, "Last Name");
        person.setLastName(lastName);

        while (true) {
            dateOfBirth = promptForDate(scanner, "Date of Birth");
            try {
                person.setDateOfBirth(dateOfBirth);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
        while (true) {
            gender = promptForString(scanner, "Gender");
            try {
                person.setGender(gender);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        phoneNumber = promptForString(scanner, "Phone Number");
        person.setPhoneNumber(phoneNumber);

        address = promptForString(scanner, "Address");
        person.setAddress(address);
    } // end method setPersonalInfo
    
    /**
     * instantiates a Treatment object
     * by prompting the user to enter attributes in the terminal
     * 
     * @param scanner a Scanner object to read user input
     * @return a Treatment object
     */
    static Treatment createTreatment(Scanner scanner) {
        String medication, description;
        LocalDate startDate, endDate;
        Treatment treatment = new Treatment();

        medication = promptForString(scanner, "Medication");
        treatment.setMedication(medication);

        description = promptForString(scanner, "Description");
        treatment.setDescription(description);

        while (true) {
            startDate = promptForDate(scanner, "Start date");
            try {
                treatment.setStartDate(startDate);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        while (true) {
            endDate = promptForDate(scanner, "End date");
            try {
                treatment.setEndDate(endDate);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return treatment;
    } // end method createTreatment

    static Object searchForPerson(Scanner scanner, ArrayList<Object> arrayList) {
        String firstName, lastName, name, phoneNumber;

        firstName = UserInteraction.promptForString(scanner, "First Name");
        lastName = UserInteraction.promptForString(scanner, "Last Name");
        name = firstName + " " + lastName;

        phoneNumber = UserInteraction.promptForString(scanner, "Phone Number");

        for (Object obj : arrayList) {
            if (((Person) obj).getName().equals(name) && ((Person) obj).getPhoneNumber().equals(phoneNumber)) {
                return obj;
            }
        }
        
        return null;
    }

}
