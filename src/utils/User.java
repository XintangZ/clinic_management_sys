package src.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import src.clinic.Appointment;
import src.clinic.Schedule;
import src.clinic.Treatment;
import src.person.Doctor;
import src.person.Patient;
import src.person.Person;

/**
 * class User <p>
 * contains methods to perform user actions, 
 * including creating, searching, and editing objects
 * 
 * @version 1.00
 * @since 2024-01-07
 * @author Team 6
 */

public class User extends InputValidator {
    // constructor
    public User() {
        super();
    }

    // methods to instantiate a new object
    /**
     * sets each property of a Person object
     * by prompting the user to enter attributes in the terminal
     * 
     * @param person a Person object to add attributes to
     * @throws Exception if maxinum number of attempts reached
     */
    public void setPersonalInfo(Person person) throws Exception {
        limitAttempts(() -> {
            System.out.print("First Name: ");
            person.setFirstName(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Last Name: ");
            person.setLastName(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Date of Birth (yyyy-mm-dd): ");
            person.setDateOfBirth(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Gender (F/M): ");
            person.setGender(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Phone Number (xxx-xxx-xxxx): ");
            person.setPhoneNumber(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Address: ");
            person.setAddress(getString());
        }, attempts);
    } // end method setPersonalInfo

    /**
     * instantiates a Doctor object
     * by prompting the user to enter attributes in the terminal
     * 
     * @return a Doctor object
     * @throws Exception if maxinum number of attempts reached
     */
    public Doctor createDoctor() throws Exception {
        Doctor doctor = new Doctor();
        setPersonalInfo(doctor);

        limitAttempts(() -> {
            System.out.print("Date of Employmenth (yyyy-mm-dd): ");
            doctor.setDateOfEmployment(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("SIN Number: ");
            doctor.setSinNumber(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Specialty: ");
            doctor.setSpecialty(getString());
        }, attempts);

        return doctor;
    } // end method createDoctor

    /**
     * instantiates a Patient object
     * by prompting the user to enter attributes in the terminal
     * 
     * @return a Patient object
     * @throws Exception if maxinum number of attempts reached
     */
    public Patient createPatient() throws Exception {
        Patient patient = new Patient();
        setPersonalInfo(patient);

        limitAttempts(() -> {
            System.out.print("Health Card Number: ");
            patient.setHealthCardNumber(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Allergies: ");
            patient.setAllergies(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Insurance Company: ");
            patient.setInsuranceCompany(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Policy Number: ");
            patient.setPolicyNumber(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Covered Percentage: ");
            patient.setCoveredPercentage(getString());
        }, attempts);
        
        return patient;
    } // end method createPatient 
    
    /**
     * instantiates a Treatment object
     * by prompting the user to enter attributes in the terminal
     * 
     * @return a Treatment object
     * @throws Exception if maxinum number of attempts reached
     */
    public Treatment createTreatment(ArrayList<Object> allDoctors, ArrayList<Object> allPatients) throws Exception {
        Treatment treatment = new Treatment();

        limitAttempts(() -> {
            System.out.println("Doctor: ");
            Person doctor = searchForPerson(allDoctors);
            treatment.setDoctorName(doctor.getName());
        }, attempts);

        limitAttempts(() -> {
            System.out.println("Patient: ");
            Person patient = searchForPerson(allPatients);
            treatment.setPatientName(patient.getName());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Medication: ");
            treatment.setMedication(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Description: ");
            treatment.setDescription(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Start Date: ");
            treatment.setStartDate(getString());
        }, attempts);

        limitAttempts(() -> {
            System.out.print("End Date: ");
            treatment.setEndDate(getString());
        }, attempts);

        return treatment;
    } // end method createTreatment

    /**
     * instantiates an Appointment object
     * by prompting the user to enter attributes in the terminal
     * 
     * @return an Appointment object
     * @throws Exception if maxinum number of attempts reached
     */
    public Appointment createAppointment(ArrayList<Appointment> doctorAppointments, String patientName, String doctorName) throws Exception {
        Appointment appointment = new Appointment();

        appointment.setPatientName(patientName);
        appointment.setDoctorName(doctorName);

        limitAttempts(() -> {
            System.out.print("Appointment Date: ");
            appointment.setDate(getString());
        }, attempts);

        String[] timeSlots = { "09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00" };
        ArrayList<String> availableTimeSlots = new ArrayList<>();

        for (String time : timeSlots) {
            if (Schedule.checkAvailability(doctorAppointments, appointment.getDate(), LocalTime.parse(time))) {
                availableTimeSlots.add(time);
            }
        }

        Menu timsSlots = new Menu("available time slots", availableTimeSlots);
        appointment.setStartTime(timsSlots.getChosenOption());

        limitAttempts(() -> {
            System.out.print("Description: ");
            appointment.setDescription(getString());
        }, attempts);

        appointment.setStatus("Confirmed");

        return appointment;
    }


    /**
     * performs linear search to an ArrayList of Objects
     * 
     * @param arrayList an ArrayList of Objects to search from
     * @return a Person object or null if no result found
     * @throws Exception if maxinum number of attempts reached
     */
    public Person searchForPerson(ArrayList<Object> arrayList) throws Exception {
        String[] name = new String[2];
        String[] phone = new String[1];

        limitAttempts(() -> {
            System.out.print("First Name: ");
            name[0] = getString();
        }, attempts);
        
        limitAttempts(() -> {
            System.out.print("Last Name: ");
            name[1] = getString();
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Phone Number (xxx-xxx-xxxx): ");
            phone[0] = getString();
        }, attempts);

        for (Object obj : arrayList) {
            Person person = (Person) obj;
            if (person.getName().equals(name[0] + " " + name[1]) && person.getPhoneNumber().equals(phone[0])) {
                return person;
            }
        }
        throw new NoDataException("Person not found");
    } // end method searchForPerson

    /**
     * performs linear search to an ArrayList of Objects
     * 
     * @param arrayList an ArrayList of Objects to search from
     * @return a Treatment object or null if no result found
     * @throws Exception if maxinum number of attempts reached
     */
    public Treatment searchForTreatment(ArrayList<Object> arrayList) throws Exception { 
        String[] name = new String[2];
        LocalDate[] issueDate = new LocalDate[1];

        limitAttempts(() -> {
            System.out.print("Patient First Name: ");
            name[0] = getString();
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Patient Last Name: ");
            name[1] = getString();
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Issue Date (yyyy-mm-dd): ");
            issueDate[0] = LocalDate.parse(getString());
        }, attempts);

        for (Object obj : arrayList) {
            Treatment treatment = (Treatment) obj;
            if (treatment.getPatientName().equals(name[0] + " " + name[1])) {
                return treatment;
            }
        }
        throw new NoDataException("Treatment not found.");
    } // end method searchForTreatment

    /**
     * performs linear search to an ArrayList of Objects
     * 
     * @param arrayList an ArrayList of Objects to search from
     * @return a Treatment object or null if no result found
     * @throws Exception if maxinum number of attempts reached
     */
    public ArrayList<Object> searchForAppointment(ArrayList<Object> allAppointments) throws Exception {
        ArrayList<Object> filteredAppointments = new ArrayList<>();

        Menu filters = new Menu("choose a filter", "Doctor's Name", "Patient's Name", "Date", "Specific Appointment");
        String chosenFilter = filters.getChosenOption();
        
        switch (chosenFilter) {
            case "Doctor's Name":
                String[] doctorName = new String[1];

                limitAttempts(() -> {
                    System.out.println("Enter Doctor's Name: ");
                    doctorName[0] = getString();
                }, attempts);

                for (Object obj : allAppointments) {
                    if (((Appointment) obj).getDoctorName().equals(doctorName[0])) {
                        filteredAppointments.add(obj);
                    }
                }
                break;
            case "Patient's Name":
                String[] patientName = new String[1];

                limitAttempts(() -> {
                    System.out.println("Enter Patient's Name: ");
                    patientName[0] = getString();
                }, attempts);

                for (Object obj : allAppointments) {
                    if (((Appointment) obj).getPatientName().equals(patientName[0])) {
                        filteredAppointments.add(obj);
                    }
                }
                break;
            case "Date":
                LocalDate[] date = new LocalDate[1];

                limitAttempts(() -> {
                    System.out.println("Enter Appointment Date (yyyy-mm-dd): ");
                    date[0] = LocalDate.parse(getString());
                }, attempts);
                for (Object obj : allAppointments) {
                    if (((Appointment) obj).getDate().equals(date[0])) {
                        filteredAppointments.add(obj);
                    }
                }
                break;
            default:
                LocalDate[] specificDate = new LocalDate[1];
                LocalTime[] specificTime = new LocalTime[1];
                String[] specificDoctorName = new String[1];

                limitAttempts(() -> {
                    System.out.println("Enter Appointment Date (yyyy-mm-dd): ");
                    specificDate[0] = LocalDate.parse(getString());
                }, attempts);

                limitAttempts(() -> {
                    System.out.println("Enter Appointment Time (hh:mm): ");
                    specificTime[0] = LocalTime.parse(getString());
                }, attempts);

                limitAttempts(() -> {
                    System.out.println("Enter Doctor's Name: ");
                    specificDoctorName[0] = getString();
                }, attempts);

                for (Object obj : allAppointments) {
                    if (((Appointment) obj).getDate().equals(specificDate[0])
                            && ((Appointment) obj).getStartTime().equals(specificTime[0])
                            && ((Appointment) obj).getDoctorName().equals(specificDoctorName[0])) {
                        filteredAppointments.add(obj);
                    }
                }
        }
        
        if (filteredAppointments.isEmpty()) {
            throw new NoDataException("Appointment not found.");
        }

        return filteredAppointments;
    } // end method searchForAppointment

    // methods to edit object attributes
    /**
     * takes a String of user input
     * and updates an attribute with the value. 
     * if the input is blank (contains nothing other than white spaces), no change will be made
     * 
     * @param method a setter method to update a certain attribute
     * @throws Exception if maxinum number of attempts reached
     */
    public void updateAttr(RunnableWithParam method) throws Exception {
        String input = this.scanner.nextLine();
        if (!input.isBlank()) {
            method.run(input);
        }
    } // end method updateAttr

    /**
     * displays the attributes of a person line by line, 
     * the user can decide if to enter a new value at the end of each line. 
     * if no new value is entered (the user hit enter without typing anything other than spaces), no changes will be made.
     * if the user entered a new value, updates the coresponding property
     * 
     * @param person the Person object to be modified
     * @throws Exception if maxinum number of attempts reached
     */
    public void editPerson(Person person) throws Exception {
        limitAttempts(() -> {
            System.out.print("First Name: " + person.getFirstName() + " ");
            updateAttr(person::setFirstName); // first name
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Last Name: " + person.getLastName() + " ");
            updateAttr(person::setLastName); // last name
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Date of Birthh: " + person.getDateOfBirth() + " ");
            updateAttr(person::setDateOfBirth); // date of birth
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Gender: " + person.getGender() + " ");
            updateAttr(person::setGender); // gender
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Phone Number: " + person.getPhoneNumber() + " ");
            updateAttr(person::setPhoneNumber); // phone number
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Address: " + person.getAddress() + " ");
            updateAttr(person::setAddress); // address
        }, attempts);
    }
    
    /**
     * displays the attributes of a doctor line by line, 
     * the user can decide if to enter a new value at the end of each line. 
     * if no new value is entered (the user hit enter without typing anything other than spaces), no changes will be made.
     * if the user entered a new value, updates the coresponding property
     * 
     * @param doctor the Doctor object to be modified
     * @throws Exception if maxinum number of attempts reached
     */
    public void editDoctor(Doctor doctor) throws Exception {
        System.out.println("======= EDIT DOCTOR INFO =======");
        System.out.println("Input new information if needed, or skip a field by pressing the enter key.");

        editPerson(doctor);

        limitAttempts(() -> {
            System.out.print("Date of Employment: " + doctor.getDateOfEmployment() + " ");
            updateAttr(doctor::setDateOfEmployment); // date of employment
        }, attempts);

        limitAttempts(() -> {
            System.out.print("SIN Number: " + doctor.getSinNumber() + " ");
            updateAttr(doctor::setSinNumber); // date of employment
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Specialty: " + doctor.getSpecialty() + " ");
            updateAttr(doctor::setSpecialty); // specialty
        }, attempts);
    }
    
    /**
     * displays the attributes of a patient line by line, 
     * the user can decide if to enter a new value at the end of each line. 
     * if no new value is entered (the user hit enter without typing anything other than spaces), no changes will be made.
     * if the user entered a new value, updates the coresponding property
     * 
     * @param patient the Patient object to be modified
     * @throws Exception if maxinum number of attempts reached
     */
    public void editPatient(Patient patient) throws Exception {
        System.out.println("======= EDIT PATIENT INFO =======");
        System.out.println("Input new information if needed, or skip a field by pressing the enter key.");

        editPerson(patient);

        limitAttempts(() -> {
            System.out.print("Health Card Number: " + patient.getHealthCardNumber() + " ");
            updateAttr(patient::setHealthCardNumber); // health card number
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Allergies: " + patient.getAllergies() + " ");
            updateAttr(patient::setAllergies); // allergies
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Insurance Company: " + patient.getInsuranceCompany() + " ");
            updateAttr(patient::setInsuranceCompany); // insurance company
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Policy Number: " + patient.getPolicyNumber() + " ");
            updateAttr(patient::setPolicyNumber); // policy number
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Covered Percentage: " + patient.getCoveredPercentage() * 100 + "% ");
            updateAttr(patient::setCoveredPercentage); // covered percentage
        }, attempts);
    }
    
    /**
     * displays the attributes of a treatment line by line, 
     * the user can decide if to enter a new value at the end of each line. 
     * if no new value is entered (the user hit enter without typing anything other than spaces), no changes will be made.
     * if the user entered a new value, updates the coresponding property
     * 
     * @param treatment the Treatment object to be modified
     * @throws Exception if maxinum number of attempts reached
     */
    public void editTreatment(Treatment treatment) throws Exception {
        System.out.println("======= EDIT TREATMENT INFO =======");
        System.out.println("Input new information if needed, or skip a field by pressing the enter key.");

        limitAttempts(() -> {
            System.out.print("Doctor Name: " + treatment.getDoctorName() + " ");
            updateAttr(treatment::setDoctorName); // doctor name
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Patient Name: " + treatment.getPatientName() + " ");
            updateAttr(treatment::setPatientName); // petient name
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Medication: " + treatment.getMedication() + " ");
            updateAttr(treatment::setMedication); // medication
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Description: " + treatment.getDescription() + " ");
            updateAttr(treatment::setDescription); // description
        }, attempts);

        limitAttempts(() -> {
            System.out.print("Start Date: " + treatment.getStartDate() + " ");
            updateAttr(treatment::setStartDate); // start date
        }, attempts);

        limitAttempts(() -> {
            System.out.print("End Date: " + treatment.getEndDate() + " ");
            updateAttr(treatment::setEndDate); // end date
        }, attempts);
    } // end method editTreatment

    /**
     * prints all objects from an ArrayList
     * 
     * @param <T> a generic type
     * @param objects an ArrayList of Object to print
     * @param type the type of object to cast to
     */
    public <T> void printAll(ArrayList<Object> objects, Class<T> type) {
        if (objects.isEmpty()) {
            System.out.println("No data.");
        } else {
            int index = 1;
            for (Object obj : objects) {
                System.out.printf("------- %s %d ------- %n", type.getSimpleName(), index++);
                System.out.println(type.cast(obj));
            }
        }
    } // end method printAll
} // end class User
