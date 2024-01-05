package src.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import src.person.Doctor;
import src.utils.Menu;
import src.utils.ObjectIO;
import src.utils.UserInteraction;

public class HrPage extends Team6MedicalClinic {
    private static Menu hrMenu = new Menu("hr","Add a new doctor", "Search for a doctor", "All doctors", "Main menu");
    private static ArrayList<Object> doctors = ObjectIO.loadData(ObjectIO.DOCTOR_FILE_PATH);;

    public static void main(String[] args) {

        hrMenu.execute(scanner, "Are you sure to leave this page?", addNewDoctor, searchDoctor, displayAllDoctors);

        System.out.println("Leaving...");
    } // end method main

    private static Runnable addNewDoctor = () -> {
        System.out.println("======= NEW DOCTOR =======");
        Doctor doctor = UserInteraction.createDoctor(scanner);
        doctors.add(doctor);
        ObjectIO.writeObjects(ObjectIO.DOCTOR_FILE_PATH, doctors);
    };

    private static Runnable searchDoctor = () -> {
        Doctor result = (Doctor) UserInteraction.searchForPerson(scanner, doctors);
        if (result == null) {
            System.out.println("Doctor not found.");
        } else {
            System.out.println("======= SEARCH RESULT =======");
            System.out.println(result);
            if (UserInteraction.promptForResponse(scanner,
                    "Would you like to edit the doctor's information?")) {
                editDoctor(result);
                ObjectIO.writeObjects(ObjectIO.DOCTOR_FILE_PATH, doctors);
            }
        }
    };

    private static Runnable displayAllDoctors = () -> {
        if (doctors.isEmpty()) {
            System.out.println("No record.");
        } else {
            System.out.println("======= ALL DOCTORS =======");
            int index = 1;
            for (Object obj : doctors) {
                System.out.printf("------- Doctor %d ------- %n", index++);
                System.out.println((Doctor) obj);
            }
        }
    };

    private static void editDoctor(Doctor doctor) {
        System.out.println("======= EDIT DOCTOR INFO =======");
        System.out.println("");

        String input;
        System.out.print("First Name: " + doctor.getFirstName() + " ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            doctor.setFirstName(input);
        }

        System.out.print("Last Name: " + doctor.getLastName() + " ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            doctor.setLastName(input);
        }

        System.out.print("Date of Birth: " + doctor.getDateOfBirth() + " ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            LocalDate newDate;
            while (true) {
                try {
                    newDate = LocalDate.parse(input);
                    break;
                } catch (DateTimeParseException e) {
                    System.err.println("Invalid date format. Format must be \"yyyy-mm-dd\".");
                    input = scanner.nextLine();
                }
            }

            while (true) {                
                try {
                    doctor.setDateOfBirth(newDate);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                }
            }
        }

        System.out.print("Gender: " + doctor.getGender() + " ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            while (true) {
                try {
                    doctor.setGender(input);
                    break;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        
        System.out.print("Phone Number: " + doctor.getPhoneNumber() + " ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            doctor.setPhoneNumber(input);
        }

        System.out.print("Address: " + doctor.getAddress() + " ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            doctor.setAddress(input);
        }

        System.out.print("Date of Employment: " + doctor.getDateOfEmployment() + " ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            LocalDate newDate;
            while (true) {
                try {
                    newDate = LocalDate.parse(input);
                    break;
                } catch (DateTimeParseException e) {
                    System.err.println("Invalid date format. Format must be \"yyyy-mm-dd\".");
                    scanner.nextLine();
                }
            }

            while (true) {                
                try {
                    doctor.setDateOfEmployment(newDate);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }   
        
        System.out.print("Specialty: " + doctor.getSpecialty() + " ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            doctor.setSpecialty(input);
        }        
    }
}
