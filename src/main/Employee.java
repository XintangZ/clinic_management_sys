package src.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    } // end default constructor

    /**
     * constructor with parameters
     * @param firstName String
     * @param lastName String
     * @param dateOfBirth LocalDate
     * @param gender Gender ("F" for Female, "M" for Male)
     * @param phoneNumber String
     * @param address String
     * @param dateOfEmployment LocalDate
     */
    public Employee(String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber,
            String address, String dateOfEmployment) {
        super(firstName, lastName, dateOfBirth, gender, phoneNumber, address);
        setDateOfEmployment(dateOfEmployment);
    }

    // setter methods 
    /**
     * sets the employed date of the employee
     * @param dateOfEmployment
     */
    public void setDateOfEmployment(String dateOfEmployment) {
        try {
            this.dateOfEmployment = LocalDate.parse(dateOfEmployment);

            if (this.dateOfEmployment.isAfter(LocalDate.now())) {
                throw new Exception("Error: date of employment cannot be a future date");
            }
        } 
        catch (DateTimeParseException dtpe) {
            System.out.println("Error: invalid date format.");
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
