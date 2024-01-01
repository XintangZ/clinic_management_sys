package src.person;

import java.time.LocalDate;

/**
 * abstract class Employee extends the abstract class Person
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public abstract class Employee extends Person {
    // data members
    private LocalDate dateOfEmployment;

    // default constructor
    public Employee() {
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
     * @throws Exception
     */
    public Employee(String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber,
            String address, LocalDate dateOfEmployment) throws Exception {
        super(firstName, lastName, dateOfBirth, gender, phoneNumber, address);
        setDateOfEmployment(dateOfEmployment);
    }

    // setter methods 
    /**
     * sets the employed date of the employee
     * @param dateOfEmployment
     * @throws Exception
     */
    public void setDateOfEmployment(LocalDate dateOfEmployment) throws Exception {
        if (dateOfEmployment.isAfter(LocalDate.now())) {
            throw new Exception("Error: date of employment cannot be a future date");
        }
        this.dateOfEmployment = dateOfEmployment;
    } // end method setDateOfEmployment

    // getter methods 
    /**
     * gets the date of employment of the employee
     * @return LocalDate
     */
    public LocalDate getDateOfEmployment() {
        return this.dateOfEmployment;
    } // end method getDateOfEmployment

    // return string representation of employee object 
    @Override
    public String toString() {
        return super.toString() + String.format("Date of Employment: %s %n", getDateOfEmployment());
    } // end method toString
}
