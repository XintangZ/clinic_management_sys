package src.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.person.Doctor;
import src.utils.ObjectIO;
import src.utils.UserInteraction;

public class HrPage implements UserInteraction {
    private Scanner scanner;

    public HrPage(Scanner scanner) {
        this.scanner = scanner;
    }

    // display HR menu and perform corresponding tasks base on user choice
    @Override
    public void displayMenu() {
        ArrayList<Object> doctors = ObjectIO.loadData(ObjectIO.DOCTOR_FILE_PATH);

        // HR menu options
        String[] optionList = {"Add a new doctor", "Search for a doctor", "All doctors", "Main menu"};
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(optionList));

        while (true) {
            // display HR menu and prompt the user to choose 
            System.out.println("======= HR MENU =======");
            int userChoice = UserInteraction.chooseFromMenu(this.scanner, menu);
            // perform corresponding task base on user chioce
            switch (userChoice) {
                case 1:     // add a new doctor
                    System.out.println("======= NEW DOCTOR =======");
                    Doctor doctor = UserInteraction.createDoctor(this.scanner);
                    doctors.add(doctor);
                    ObjectIO.writeObjects(ObjectIO.DOCTOR_FILE_PATH, doctors);
                    break;
                case 2:     // search for a doctor
                    Doctor result = (Doctor) UserInteraction.searchForPerson(scanner, doctors);
                    if (result == null) {
                        System.out.println("Doctor not found.");
                    } else {
                        System.out.println("======= SEARCH RESULT =======");
                        System.out.println(result);
                        if (UserInteraction.promptForResponse(this.scanner,
                                "Would you like to edit the doctor's information?")) {
                            editDoctor(result);
                            ObjectIO.writeObjects(ObjectIO.DOCTOR_FILE_PATH, doctors);
                        }
                    }
                    break;
                case 3:     // print all doctors
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
                    break;
                default:    // back to main menu
                    if (UserInteraction.promptForResponse(this.scanner, "Are you sure to leave this page?")) {
                        return;
                    }
                    break;
            } // end switch userChoice
        } // end while loop
    } // end method displayMenu

    private void editDoctor(Doctor doctor) {
        System.out.println("======= EDIT DOCTOR INFO =======");
        System.out.println("");

        String input;
        System.out.print("First Name: " + doctor.getFirstName() + " ");
        input = this.scanner.nextLine();
        if (!input.isEmpty()) {
            doctor.setFirstName(input);
        }

        System.out.print("Last Name: " + doctor.getLastName() + " ");
        input = this.scanner.nextLine();
        if (!input.isEmpty()) {
            doctor.setLastName(input);
        }

        System.out.print("Date of Birth: " + doctor.getDateOfBirth() + " ");
        input = this.scanner.nextLine();
        if (!input.isEmpty()) {
            LocalDate newDate;
            while (true) {
                try {
                    newDate = LocalDate.parse(input);
                    break;
                } catch (DateTimeParseException e) {
                    System.err.println("Invalid date format. Format must be \"yyyy-mm-dd\".");
                    input = this.scanner.nextLine();
                }
            }

            while (true) {                
                try {
                    doctor.setDateOfBirth(newDate);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    this.scanner.nextLine();
                }
            }
        }

        System.out.print("Gender: " + doctor.getGender() + " ");
        input = this.scanner.nextLine();
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
        input = this.scanner.nextLine();
        if (!input.isEmpty()) {
            doctor.setPhoneNumber(input);
        }

        System.out.print("Address: " + doctor.getAddress() + " ");
        input = this.scanner.nextLine();
        if (!input.isEmpty()) {
            doctor.setAddress(input);
        }

        System.out.print("Date of Employment: " + doctor.getDateOfEmployment() + " ");
        input = this.scanner.nextLine();
        if (!input.isEmpty()) {
            LocalDate newDate;
            while (true) {
                try {
                    newDate = LocalDate.parse(input);
                    break;
                } catch (DateTimeParseException e) {
                    System.err.println("Invalid date format. Format must be \"yyyy-mm-dd\".");
                    this.scanner.nextLine();
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
        input = this.scanner.nextLine();
        if (!input.isEmpty()) {
            doctor.setSpecialty(input);
        }        
    }
}
