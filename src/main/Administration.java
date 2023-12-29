package src.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * interface Administration
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Administration {
    public static void main(String[] args) throws DateTimeParseException, Exception {
        // String firstName, lastName, gender, phoneNumber, address;
        // String dateOfBirth;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> persons = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Person person = new Person();

            System.out.print("First Name: ");
            person.setFirstName(scanner.next()) ;

            System.out.print("Last Name: ");
            person.setLastName(scanner.next());

            // do {
            //     isNotValid = true;
            //     System.out.print("Date of Birth (yyyy-mm-dd): ");
            //     try {
            //         dateOfBirth = LocalDate.parse(scanner.next());

            //         isNotValid = dateOfBirth.isAfter(LocalDate.now());
            //         if (isNotValid) {
            //             throw new Exception("Error: date of birth cannot be a future date.");
            //         }
            //     } catch (DateTimeParseException dtpe) {
            //         System.out.println("Error: invalid date format.");
            //     } catch (Exception e) {
            //         System.out.println(e.getMessage());
            //     }
            // } while (isNotValid);      
            System.out.print("Date of Birth (yyyy-mm-dd): ");
            person.setDateOfBirth(scanner.next());

            System.out.print("Gender: ");
            person.setGender(scanner.next());

            System.out.print("Phone Number: ");
            person.setPhoneNumber(scanner.next());

            System.out.print("Address: ");
            scanner.nextLine();
            person.setAddress(scanner.nextLine());

            persons.add(person);
            
        } // end for loop
        
        Utils.writeData("data/persons.bin", persons);
        scanner.close();
    } // end method main

    public static Doctor createDoctor() {
        Doctor doctor = new Doctor();

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                
                System.out.print("Specialty: ");
                doctor.setSpecialty(scanner.next());
                
                System.out.print("Date of Employment (yyyy-mm-dd): ");
                doctor.setDateOfEmployment(scanner.next());
            } while (!Utils.getAnswer(scanner, "Is the information above correct?"));
        } // end try-with-resources 

        try (PrintWriter outFile = new PrintWriter("data/doctors.txt")) {
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return doctor;
    };

    public static void createPerson(Scanner scanner, String option) {
        Person person = new Person();
        boolean isNotValid;

        System.out.print("First Name: ");
        person.setFirstName(scanner.next()) ;

        System.out.print("Last Name: ");
        person.setLastName(scanner.next());

        do {
            isNotValid = true;
            System.out.print("Date of Birth (yyyy-mm-dd): ");
            try {
                dateOfBirth = LocalDate.parse(scanner.next());

                isNotValid = dateOfBirth.isAfter(LocalDate.now());
                if (isNotValid) {
                    throw new Exception("Error: date of birth cannot be a future date.");
                }
            } catch (DateTimeParseException dtpe) {
                System.out.println("Error: invalid date format.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (isNotValid);      
        // System.out.print("Date of Birth (yyyy-mm-dd): ");
        // person.setDateOfBirth(scanner.next());

        System.out.print("Gender: ");
        person.setGender(scanner.next());

        System.out.print("Phone Number: ");
        person.setPhoneNumber(scanner.next());

        System.out.print("Address: ");
        scanner.nextLine();
        person.setAddress(scanner.nextLine());
        switch (option) {
            case "doctor":
                
                break;
            case "patient":
                
                break;        
            default:
                break;
        }
    }
} // end class Administration
