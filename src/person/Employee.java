package src.person;

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
    private String sinNumber;
    private LocalDate dateOfEmployment;

    // default constructor
    public Employee() {
        super();
    }

    /**
     * constructor with parameters
     * 
     * @param firstName String
     * @param lastName String
     * @param dateOfBirth LocalDate
     * @param gender Gender ("F" for Female, "M" for Male)
     * @param phoneNumber String
     * @param address String
     * @param dateOfEmployment LocalDate
     * @param sinNumber String
     * @throws Exception
     */
    public Employee(String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber,  
            String address, LocalDate dateOfEmployment, String sinNumber) throws Exception {
        super(firstName, lastName, dateOfBirth, gender, phoneNumber, address);
        setDateOfEmployment(dateOfEmployment);
        setSinNumber(sinNumber);
    }

    // setter methods 
    /**
     * sets the sin number of the employee
     * 
     * @param theSinNumber String
     */
	public void setSinNumber(String sinNumber){
		this.sinNumber = sinNumber;
	} // end method setSinNumber

    /**
     * sets the employed date of the employee
     * 
     * @param dateToParse String
     * @throws Exception if failed to parse the String to LocalDate
     */
    public void setDateOfEmployment(String dateToParse) throws Exception {
        LocalDate dateOfEmployment;
        try {
            dateOfEmployment = LocalDate.parse(dateToParse);
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid date format. Format must be \"yyyy-mm-dd\".");
        }
        setDateOfEmployment(dateOfEmployment);
    } // end method setDateOfEmployment

    /**
     * sets the employed date of the employee
     * 
     * @param dateOfEmployment
     * @throws Exception if the date is a future date
     */
    public void setDateOfEmployment(LocalDate dateOfEmployment) throws Exception {
        if (dateOfEmployment.isAfter(LocalDate.now())) {
            throw new Exception("Invalid input. Date of employment cannot be a future date");
        }
        this.dateOfEmployment = dateOfEmployment;
    } // end method setDateOfEmployment

    // getter methods 
    /**
     * gets the sin number of the employee
     * 
     * @return String
     */
	public String getSinNumber() {
		return this.sinNumber;
	} // end method getSinNumber

    /**
     * gets the date of employment of the employee
     * 
     * @return LocalDate
     */
    public LocalDate getDateOfEmployment() {
        return this.dateOfEmployment;
    } // end method getDateOfEmployment

    // return string representation of employee object 
    @Override
    public String toString() {
        return super.toString()
                + String.format("SIN Number: %s %nDate of Employment: %s %n", getSinNumber(), getDateOfEmployment()); 
    } // end method toString
} // end abstract class Employee
