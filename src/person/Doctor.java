package src.person;

import java.time.LocalDate;

/**
 * class Doctor extends the abstract class Employee
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Doctor extends Employee {
    // data members
    private String specialty;

    // default constructor
    public Doctor() {
        super();
    }
    
    /**
     * constructor with parameters
     * @param firstName String
     * @param lastName String
     * @param dateOfBirth LocalDate
     * @param gender Gender ("F" for Female, "M" for Male)
     * @param phoneNumber String
     * @param address String
     * @param dateOfEmployment LocalDate
     * @param specialty String
     * @throws Exception
     */
    public Doctor(String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber,
    String address, LocalDate dateOfEmployment, String specialty) throws Exception {
        super(firstName, lastName, dateOfBirth, gender, phoneNumber, address, dateOfEmployment);
        setSpecialty(specialty);
    } // end constructor with params

    // setter methods
    /**
     * sets the specialty of the doctor
     * @param specialty String
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    } // end method setSpecialty 

    // getter methods
    /**
     * gets the specialty of the doctor
     * @return String
     */
    public String getSpecialty() {
        return this.specialty;
    } // end method getSpecialty

    // return string representation of doctor object 
    @Override
    public String toString() {
        return super.toString() + String.format("Specialty: %s %n", getSpecialty());
    } // end method toString
}
