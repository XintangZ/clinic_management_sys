package src.utils;
import java.util.*;

import src.clinic.Appointment;
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
        System.out.print("First Name: ");
        limitAttempts(() -> {
            person.setFirstName(getString());
        }, attempts);

        System.out.print("Last Name: ");
        limitAttempts(() -> {
            person.setLastName(getString());
        }, attempts);

        System.out.print("Date of Birth: ");
        limitAttempts(() -> {
            person.setDateOfBirth(getString());
        }, attempts);

        System.out.print("Gender: ");
        limitAttempts(() -> {
            person.setGender(getString());
        }, attempts);

        System.out.print("Phone Number: ");
        limitAttempts(() -> {
            person.setPhoneNumber(getString());
        }, attempts);

        System.out.print("Address: ");
        limitAttempts(() -> {
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

        System.out.print("Date of Employment: ");
        limitAttempts(() -> {
            doctor.setDateOfEmployment(getString());
        }, attempts);

        System.out.print("SIN Number: ");
        limitAttempts(() -> {
            doctor.setSinNumber(getString());
        }, attempts);

        System.out.print("Specialty: ");
        limitAttempts(() -> {
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

        System.out.print("Allergies: ");
        limitAttempts(() -> {
            patient.setAllergies(getString());
        }, attempts);

        System.out.print("Insurance Company: ");
        limitAttempts(() -> {
            patient.setInsuranceCompany(getString());
        }, attempts);

        System.out.print("Policy Number: ");
        limitAttempts(() -> {
            patient.setPolicyNumber(getString());
        }, attempts);

        System.out.print("Covered Percentage: ");
        limitAttempts(() -> {
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
    public Treatment createTreatment() throws Exception {
        Treatment treatment = new Treatment();

        System.out.print("Doctor Name: ");
        limitAttempts(() -> {
            treatment.setDoctorName(getString());
        }, attempts);

        System.out.print("Patient Name: ");
        limitAttempts(() -> {
            treatment.setPatientName(getString());
        }, attempts);

        System.out.print("Medication: ");
        limitAttempts(() -> {
            treatment.setMedication(getString());
        }, attempts);

        System.out.print("Description: ");
        limitAttempts(() -> {
            treatment.setDescription(getString());
        }, attempts);

        System.out.print("Start Date: ");
        limitAttempts(() -> {
            treatment.setStartDate(getString());
        }, attempts);

        System.out.print("End Date: ");
        limitAttempts(() -> {
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
    public Appointment createAppointment(String patientName, String doctorName) throws Exception {
        Appointment appointment = new Appointment();

        appointment.setPatientName(patientName);
        appointment.setDoctorName(doctorName);

        System.out.print("Appointment Date: ");
        limitAttempts(() -> {
            appointment.setDate(getString());
        }, attempts);

        String[] menuTimes = {"09:00", "10:00", "11:00", "13:00", "14:00", "15:00"};    // TODO: validate time availability
        ArrayList<String> menu = new ArrayList<>();
        for (String item : menuTimes) {
            menu.add(item);
        }
        int response = chooseFromList(menu);
        appointment.setStartTime(menuTimes[response - 1]);

        System.out.print("Description: ");
        limitAttempts(() -> {
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

        System.out.print("First Name: ");
        limitAttempts(() -> {
            name[0] = getString();
        }, attempts);
        System.out.print("Last Name: ");
        limitAttempts(() -> {
            name[1] = getString();
        }, attempts);

        for (Object obj : arrayList) {
            Person person = (Person) obj;
            if (person.getName().equals(name[0] + " " + name[1])) {
                return person;
            }
        }
        return null;
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

        System.out.print("Patient First Name: ");
        limitAttempts(() -> {
            name[0] = getString();
        }, attempts);
        System.out.print("Patient Last Name: ");
        limitAttempts(() -> {
            name[1] = getString();
        }, attempts);

        for (Object obj : arrayList) {
            Treatment treatment = (Treatment) obj;
            if (treatment.getPatientName().equals(name[0] + " " + name[1])) {
                return treatment;
            }
        }
        return null;
    } // end method searchForTreatment


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
        System.out.print("First Name: " + person.getFirstName() + " ");
        limitAttempts(() -> {
            updateAttr(person::setFirstName); // first name
        }, attempts);

        System.out.print("Last Name: " + person.getLastName() + " ");
        limitAttempts(() -> {
            updateAttr(person::setLastName); // last name
        }, attempts);

        System.out.print("Date of Birth: " + person.getDateOfBirth() + " ");
        limitAttempts(() -> {
            updateAttr(person::setDateOfBirth); // date of birth
        }, attempts);

        System.out.print("Gender: " + person.getGender() + " ");
        limitAttempts(() -> {
            updateAttr(person::setGender); // gender
        }, attempts);

        System.out.print("Phone Number: " + person.getPhoneNumber() + " ");
        limitAttempts(() -> {
            updateAttr(person::setPhoneNumber); // phone number
        }, attempts);

        System.out.print("Address: " + person.getAddress() + " ");
        limitAttempts(() -> {
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

        System.out.print("Date of Employment: " + doctor.getDateOfEmployment() + " ");
        limitAttempts(() -> {
            updateAttr(doctor::setDateOfEmployment); // date of employment
        }, attempts);

        System.out.print("SIN Number: " + doctor.getSinNumber() + " ");
        limitAttempts(() -> {
            updateAttr(doctor::setSinNumber); // date of employment
        }, attempts);

        System.out.print("Specialty: " + doctor.getSpecialty() + " ");
        limitAttempts(() -> {
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

        System.out.print("Allergies: " + patient.getAllergies() + " ");
        limitAttempts(() -> {
            updateAttr(patient::setAllergies); // allergies
        }, attempts);

        System.out.print("Insurance Company: " + patient.getInsuranceCompany() + " ");
        limitAttempts(() -> {
            updateAttr(patient::setInsuranceCompany); // insurance company
        }, attempts);

        System.out.print("Policy Number: " + patient.getPolicyNumber() + " ");
        limitAttempts(() -> {
            updateAttr(patient::setPolicyNumber); // policy number
        }, attempts);

        System.out.print("Covered Percentage: " + patient.getCoveredPercentage() + " ");
        limitAttempts(() -> {
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

        System.out.print("Doctor Name: " + treatment.getMedication() + " ");
        limitAttempts(() -> {
            updateAttr(treatment::setDoctorName); // doctor name
        }, attempts);

        System.out.print("Patient Name: " + treatment.getMedication() + " ");
        limitAttempts(() -> {
            updateAttr(treatment::setPatientName); // petient name
        }, attempts);

        System.out.print("Medication: " + treatment.getMedication() + " ");
        limitAttempts(() -> {
            updateAttr(treatment::setMedication); // medication
        }, attempts);

        System.out.print("Description: " + treatment.getDescription() + " ");
        limitAttempts(() -> {
            updateAttr(treatment::setDescription); // description
        }, attempts);

        System.out.print("Start Date: " + treatment.getStartDate() + " ");
        limitAttempts(() -> {
            updateAttr(treatment::setStartDate); // start date
        }, attempts);

        System.out.print("End Date: " + treatment.getEndDate() + " ");
        limitAttempts(() -> {
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
            System.out.println("No record.");
        } else {
            System.out.println(String.format("======= ALL %sS =======", type.getSimpleName().toUpperCase()));
            int index = 1;
            for (Object obj : objects) {
                System.out.printf("------- %s %d ------- %n", type.getSimpleName(), index++);
                System.out.println(type.cast(obj));
            }
        }
    } // end method printAll
} // end class User
