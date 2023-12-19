package src.main;

import java.time.LocalDate;

/**
 * Abstract class Employee extends the abstract class Person.
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public abstract class Employee extends Person {
    // data members
    private LocalDate dateOfEmployment;

    /**
     * Constructor with parameters.
     * @param firstName String
     * @param lastName String
     * @param dateOfBirth String (format: yyyy-mm-dd)
     * @param gender Gender ("F" for Female, "M" for Male)
     * @param phoneNumber String
     * @param address String
     * @param dateOfEmployment String (format: yyyy-mm-dd)
     */
    public Employee(String firstName, String lastName, String dateOfBirth, Gender gender, String phoneNumber,
            String address, String dateOfEmployment) {
        super(firstName, lastName, dateOfBirth, gender, phoneNumber, address);
        setDateOfEmployment(dateOfEmployment);
    }

    // setter methods 
    /**
     * Set the employed date of the employee.
     * @param dateOfEmployment
     */
    public void setDateOfEmployment(String dateOfEmployment) {
        try {
            this.dateOfEmployment = LocalDate.parse(dateOfEmployment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date of employment.");
        }
    } // end method setDateOfEmployment

    // getter methods 
    /**
     * Get the date of employment of the employee.
     * @return String (format: yyyy-mm-dd)
     */
    public String getDateOfEmployment() {
        return this.dateOfEmployment.toString();
    } // end method getDateOfEmployment

    // return string representation of employee object 
    @Override
    public String toString() {
        return super.toString() + String.format("Date of Employment: %s %n", getDateOfEmployment());
    } // end method toString
}
