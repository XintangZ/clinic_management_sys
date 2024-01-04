package src.main;

/**
 * Class Doctor extends the abstract class Employee.
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Doctor extends Employee {
    // data members
    private String specialty;

    // constructor with params
    /**
     * Constructor with parameters.
     * @param firstName String
     * @param lastName String
     * @param dateOfBirth String (format: yyyy-mm-dd)
     * @param gender Gender ("F" for Female, "M" for Male)
     * @param phoneNumber String
     * @param address String
     * @param dateOfEmployment String (format: yyyy-mm-dd)
     * @param specialty String
     */
    public Doctor(String firstName, String lastName, String dateOfBirth, Gender gender, String phoneNumber,
    String address, String dateOfEmployment, String specialty) {
        super(firstName, lastName, dateOfBirth, gender, phoneNumber, address, dateOfEmployment);
        setSpecialty(specialty);
    } // end constructor with params

    // setter methods
    /**
     * Set the specialty of the doctor.
     * @param specialty String
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    } // end method setSpecialty 

    // getter methods
    /**
     * Get the specialty of the doctor.
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
