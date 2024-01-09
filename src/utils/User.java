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
    public Appointment createAppointment(ArrayList<Appointment> doctorAppointments, String patientName, String doctorName) throws Exception {
        Appointment appointment = new Appointment();

        appointment.setPatientName(patientName);
        appointment.setDoctorName(doctorName);

        System.out.print("Appointment Date: ");
        limitAttempts(() -> {
            appointment.setDate(getString());
        }, attempts);

        String[] timeSlots = { "09:00", "10:00", "11:00", "13:00", "14:00", "15:00" };
        ArrayList<String> availableTimeSlots = new ArrayList<>();

        for (String time : timeSlots) {
            if (Schedule.checkAvailability(doctorAppointments, appointment.getDate(), LocalTime.parse(time))) {
                availableTimeSlots.add(time);
            }
        }

        Menu timsSlots = new Menu("available time slots", availableTimeSlots);
        appointment.setStartTime(timsSlots.getChosenOption());

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
        LocalDate[] issueDate = new LocalDate[1];

        System.out.print("Patient First Name: ");
        limitAttempts(() -> {
            name[0] = getString();
        }, attempts);

        System.out.print("Patient Last Name: ");
        limitAttempts(() -> {
            name[1] = getString();
        }, attempts);

        System.out.print("Issue Date (yyyy-mm-dd): ");
        limitAttempts(() -> {
            issueDate[1] = LocalDate.parse(getString());
        }, attempts);

        for (Object obj : arrayList) {
            Treatment treatment = (Treatment) obj;
            if (treatment.getPatientName().equals(name[0] + " " + name[1])) {
                return treatment;
            }
        }
        return null;
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

        if (chosenFilter == null) {
            throw new Exception("");
        }

        switch (chosenFilter) {
            case "Doctor's Name":
                String[] doctorName = new String[1];

                System.out.println("Enter Doctor's Name: ");
                limitAttempts(() -> {
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

                System.out.println("Enter Patient's Name: ");
                limitAttempts(() -> {
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

                System.out.println("Enter Appointment Date (yyyy-mm-dd): ");
                limitAttempts(() -> {
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

                System.out.println("Enter Appointment Date (yyyy-mm-dd): ");
                limitAttempts(() -> {
                    specificDate[0] = LocalDate.parse(getString());
                }, attempts);

                System.out.println("Enter Appointment Time (hh:ss): ");
                limitAttempts(() -> {
                    specificTime[0] = LocalTime.parse(getString());
                }, attempts);

                System.out.println("Enter Doctor's Name: ");
                limitAttempts(() -> {
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

        System.out.print("Health Card Number: " + patient.getHealthCardNumber() + " ");
        limitAttempts(() -> {
            updateAttr(patient::setHealthCardNumber); // health card number
        }, attempts);

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
     * displays the attributes of an appointmet line by line, 
     * the user can decide if to enter a new value at the end of each line. 
     * if no new value is entered (the user hit enter without typing anything other than spaces), no changes will be made.
     * if the user entered a new value, updates the coresponding property
     * 
     * @param treatment the Appointment object to be modified
     * @throws Exception if maxinum number of attempts reached
     */
    public void editAppointment(Appointment appointment) throws Exception {
        System.out.println("======= EDIT APPOINTMENT INFO =======");
        System.out.println("Input new information if needed, or skip a field by pressing the enter key.");

        System.out.print("Date: " + appointment.getDate() + " ");
        limitAttempts(() -> {
            updateAttr(appointment::setDate); // date
        }, attempts);

        System.out.print("Start Time: " + appointment.getStartTime() + " ");
        limitAttempts(() -> {
            updateAttr(appointment::setStartTime); // start time
        }, attempts);

        System.out.print("Doctor Name: " + appointment.getDoctorName() + " ");
        limitAttempts(() -> {
            updateAttr(appointment::setDoctorName); // doctor name
        }, attempts);

        System.out.print("Patient Name: " + appointment.getPatientName() + " ");
        limitAttempts(() -> {
            updateAttr(appointment::setPatientName); // petient name
        }, attempts);

        System.out.print("Description: " + appointment.getDescription() + " ");
        limitAttempts(() -> {
            updateAttr(appointment::setDescription); // description
        }, attempts);

        System.out.print("Status: " + appointment.getStatus() + " ");
        limitAttempts(() -> {
            updateAttr(appointment::setStatus); // status
        }, attempts);
    } // end method editAppointment

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
            int index = 1;
            for (Object obj : objects) {
                System.out.printf("------- %s %d ------- %n", type.getSimpleName(), index++);
                System.out.println(type.cast(obj));
            }
        }
    } // end method printAll
} // end class User
