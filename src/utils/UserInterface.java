package src.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import src.person.*;
import src.clinic.Treatment;

public class UserInterface {
    private Scanner scanner;
    
    // default constructor
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public int chooseFromMenu(ArrayList<String> menu) {
		boolean isInvalidChoice = true;
        String input;

        do {
            System.out.printf("Choose one of the following options (enter a number): %n%n");
            for (int i = 1; i <= menu.size(); i++) {
                System.out.printf("%3d. %s %n", i, menu.get(i - 1));
            }
            System.out.println();
            input = this.scanner.nextLine();

            for (int j = 1; j <= menu.size(); j++) {
                isInvalidChoice = isInvalidChoice && (input.compareTo(String.valueOf(j)) != 0);
            }
            
            if (isInvalidChoice) {
                System.err.println("Invalid option. Please try again.");
            } else {
                isInvalidChoice = false;
            }            
        } while (isInvalidChoice);

        return Integer.parseInt(input);
    }

    public String promptForString(String promptMsg) {
        String str;
        while (true) {
            System.out.print(promptMsg + ": ");
            try {
                str = this.scanner.nextLine().trim();
                if (str.isBlank()) {
                    throw new Exception("Error: input cannot be empty.");
                }
                return str;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public int promptForPositiveInt(String promptMsg) {
        int num;
        while (true) {
            System.out.print(promptMsg + ": ");
            try {
                num = this.scanner.nextInt();
                // check if the user input is greater than 0
                if (num < 0) {
                    throw new Exception("Error: input cannot be negative.");
                } // end if
                return num;
            } // end try
            catch (InputMismatchException e) {
                scanner.nextLine();
                System.err.println("Invalid input, must be an integer.");
            }  // end catch InputMismatchException
            catch (Exception e) {
                scanner.nextLine();
                System.err.println(e.getMessage());
            } // end catch Exception
        } // end while loop
    } // end method promptForPositiveInt

    public LocalDate promptForDate(String promptMsg) {
        String dateToParse;
        LocalDate date;
        while (true) {
            dateToParse = promptForString(promptMsg);
            try {
                date = LocalDate.parse(dateToParse);
                return date;
            } catch (DateTimeParseException dtpe) {
                System.err.println("Error: invalid date format.");
            }
        }
    }

    /**
     * Prompt user a closed question (yes/no question).
     * Return true if the user inputs y or yes,
     * Return false if the uesr inputs n or no.
     * (Cases are ignored.)
     * 
     * @param closedQuestion String
     * @return boolean
     */
    public boolean promptForResponse(String closedQuestion) {
        boolean response;
        while (true) {
            try {
                // print the question
                System.out.println(closedQuestion + " (y/n)");
                // format user input
                String userInput = this.scanner.nextLine().trim().toLowerCase();

                if (userInput.equals("y") || userInput.equals("yes")) {
                    response = true;
                } else if (userInput.equals("n") || userInput.equals("no")) {
                    response = false;
                } else {
                    throw new Exception("Invalid response, please try again.");
                } // end if
                return response;
            } // end try
            catch (Exception e) {
                scanner.nextLine();
                System.err.println(e.getMessage());
            } // end catch Exception
        } // end while loop
    } // end method promptForResponse

    public Doctor createDoctor() {
        String specialty;
        LocalDate dateOfEmployment;
        Doctor doctor = new Doctor();

        createPerson(doctor);

        while (true) {
            dateOfEmployment = promptForDate("Date of Employment");
            try {
                doctor.setDateOfEmployment(dateOfEmployment);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        specialty = promptForString("Specialty");
        doctor.setSpecialty(specialty);

        return doctor;
    }

    public Patient createPatient() {
        String allergies, insuranceCompany, policyNumber;
        int coveredPercentage;
        BloodType bloodType;
        Patient patient = new Patient();

        createPerson(patient);

        allergies = promptForString("Allergies");
        patient.setAllergies(allergies);

        insuranceCompany = promptForString("Insurance Company");
        patient.setInsuranceCompany(insuranceCompany);

        policyNumber = promptForString("Policy Number");
        patient.setPolicyNumber(policyNumber);

        coveredPercentage = promptForPositiveInt("Covered Percentage");
        patient.setCoveredPercentage(coveredPercentage);

        // bloodType = promptForString("Blood Type");
        // patient.setBloodType(bloodType);

        return patient;
    }    
    
    public void createPerson(Person person) {
        String firstName, lastName, gender, phoneNumber, address;
        LocalDate dateOfBirth;

        firstName = promptForString("First Name");
        person.setFirstName(firstName);

        lastName = promptForString("Last Name");
        person.setLastName(lastName);

        while (true) {
            dateOfBirth = promptForDate("Date of Birth");
            try {
                person.setDateOfBirth(dateOfBirth);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
        gender = promptForString("Gender");
        person.setGender(gender);

        phoneNumber = promptForString("Phone Number");
        person.setPhoneNumber(phoneNumber);

        address = promptForString("Address");
        person.setAddress(address);
    }
    
    public Treatment createTreatment() {
        String medication, description;
        LocalDate startDate, endDate;
        Treatment treatment = new Treatment();

        medication = promptForString("Medication");
        treatment.setMedication(medication);

        description = promptForString("Description");
        treatment.setDescription(description);

        while (true) {
            startDate = promptForDate("Start date");
            try {
                treatment.setStartDate(startDate);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        while (true) {
            endDate = promptForDate("End date");
            try {
                treatment.setEndDate(endDate);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return treatment;
    }

    public void nextLine() {
        this.scanner.nextLine();
    }

    public void close() {
        this.scanner.close();
    }
}
