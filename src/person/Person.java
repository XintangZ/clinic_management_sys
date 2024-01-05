package src.person;

import java.io.Serializable;
import java.time.*;
import java.util.UUID;

/**
 * abstract class Person
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    // data members
    private final UUID ID;
    private String firstName, lastName, phoneNumber, address;
    private LocalDate dateOfBirth;
    private Gender gender;

    // default constructor
    public Person() {
        this.ID = UUID.randomUUID();
    }

    /**
     * constructor with parameters
     * 
     * @param firstName String
     * @param lastName String
     * @param dateOfBirth LocalDate
     * @param String String ("F" or "f" for Female, "M" or "m" for Male)
     * @param phoneNumber String
     * @param address String
     * @throws Exception
     */
    public Person(String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber,
            String address) throws Exception {
            this.ID = UUID.randomUUID();
            setName(firstName, lastName);
            setDateOfBirth(dateOfBirth);
            setGender(gender);
            setPhoneNumber(phoneNumber);
            setAddress(address);
    } // end constructor with parameters
    
    // setter methods 
    /**
     * sets the first name of the person
     * 
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    } // end method setFirstName

    /**
     * sets the lase name of the person
     * 
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    } // end method setLastName

    /**
     * sets the first and lase name of the person
     * 
     * @param firstName String
     * @param lastName String
     */
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    } // end method setName

    /**
     * sets the date of birth of the person
     * 
     * @param dateOfBirth LocalDate
     * @throws Exception
     */
    public void setDateOfBirth(LocalDate dateOfBirth) throws Exception {
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new Exception("Invalid date. Date of birth cannot be a future date.");
        }
        this.dateOfBirth = dateOfBirth;
    } // end method setDateOfBirth

    /**
     * sets the gender of the person
     * 
     * @param gender Gender ("F" for Female, "M" for Male)
     */
    public void setGender(String gender) throws Exception {
        switch (gender.toUpperCase()) {
            case "F":
                this.gender = Gender.F;
                break;
            case "M":
                this.gender = Gender.M;
                break;
            default:
                throw new Exception("Invalid input. Please enter \"F\" (for Female) or \"M\" (for Male).");
        } 
    } // end method setGender

    /**
     * sets the phone number of the person
     * 
     * @param phoneNumber String
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    } // end method setPhoneNumber

    /**
     * sets the address of the person
     * 
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    } // end method setAddress

    // getter methods 
    public UUID getID() {
        return this.ID;
    }

    /**
     * gets the first name of the person
     * 
     * @return String
     */
    public String getFirstName() {
        return this.firstName;
    } // end method getFirstName

    /**
     * gets the last name of the person
     * 
     * @return String
     */
    public String getLastName() {
        return this.lastName;
    } // end method getLastName

    /**
     * gets the first and last name of the person
     * 
     * @return String
     */
    public String getName() {
        return String.format("%s %s", this.firstName, this.lastName);
    } // end method getName

    /**
     * gets the date of birth of the person
     * 
     * @return LocalDate
     */
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    } // end method getDateOfBirth

    /**
     * gets the age of the person
     * (age is calculated base on date of birth)
     * 
     * @return int
     */
    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).plusDays(1).getYears();
    } // end method getAge

    /**
     * gets the gender of the person
     * 
     * @return String Female or Male 
     */
    public String getGender() {
        return this.gender.getGender();
    } // end method getGender

    /**
     * gets the phone number of the person
     * 
     * @return String
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    } // end method getPhoneNumber

    /**
     * gets the address of the person
     * 
     * @return String
     */
    public String getAddress() {
        return this.address;
    } // end method getAddress

    // return string representation of person object 
    @Override
    public String toString() {
        return String.format("Name: %s %nDate of Birth: %s %nAge: %d %nGender: %s %nPhone Number: %s %nAddress: %s %n",
                            getName(), getDateOfBirth(), getAge(), getGender(), getPhoneNumber(), getAddress());
    } // end method toString
}