package src.main;

import java.time.LocalDate;

/**
 * Abstract class Person.
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public abstract class Person {
    // data members
    private String firstName, lastName, phoneNumber, address;
    private LocalDate dateOfBirth;
    private int age;
    private Gender gender;

    /**
     * Constructor with parameters.
     * @param firstName String
     * @param lastName String
     * @param dateOfBirth String (format: yyyy-mm-dd)
     * @param gender Gender ("F" for Female, "M" for Male)
     * @param phoneNumber String
     * @param address String
     */
    public Person(String firstName, String lastName, String dateOfBirth, Gender gender, String phoneNumber, String address) {
        setName(firstName, lastName);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        } // end constructor with parameters
    
        // setter methods 
        /**
         * Set the name of the person.
         * @param firstName String
         * @param lastName String
         */
        public void setName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        } // end method setName

        /**
         * Set the date of birth of the person.
         * @param dateOfBirth String (format: yyyy-mm-dd)
         */
        public void setDateOfBirth(String dateOfBirth) {
            try {
                this.dateOfBirth = LocalDate.parse(dateOfBirth);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid date of birth.");
            }
            setAge(); // update age when date of birth has changed
        } // end method setDateOfBirth

        /**
         * Set the age of the person.
         * (Calculated base on the date of birth)
         */
        private void setAge() {
            // get today's date
            LocalDate today = LocalDate.now();
            // calculate age based on birth year
            this.age = today.getYear() - this.dateOfBirth.getYear();
            // if the person has not passed the birthday this year, age -1
            if (today.getMonthValue() < this.dateOfBirth.getMonthValue()) {
                this.age--;
            } else if (today.getMonthValue() == this.dateOfBirth.getMonthValue()
                    && today.getDayOfMonth() < this.dateOfBirth.getDayOfMonth()) {
                this.age--;
            } // end if
            
            // age cannot be negative
            if (this.age < 0) {
                throw new IllegalArgumentException("Invalid date of birth.");
            } // end if
        } // end method setAge

        /**
         * Set the gender of the person.
         * @param gender Gender ("F" for Female, "M" for Male)
         */
        public void setGender(Gender gender) {
            this.gender = gender;
        } // end method setGender

        /**
         * Set the phone number of the person.
         * @param phoneNumber String
         */
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        } // end method setPhoneNumber

        /**
         * Set the address of the person.
         * @param address String
         */
        public void setAddress(String address) {
            this.address = address;
        } // end method setAddress
    
        // getter methods 
        /**
         * Get the first and last name of the person.
         * @return String
         */
        public String getName() {
            return String.format("%s %s", this.firstName, this.lastName);
        } // end method getName

        /**
         * Get the date of birth of the person.
         * @return String (format: yyyy-mm-dd)
         */
        public String getDateOfBirth() {
            return this.dateOfBirth.toString();
        } // end method getDateOfBirth
    
        /**
         * Get the age of the person.
         * @return int
         */
        public int getAge() {
            return this.age;
        } // end method getAge

        /**
         * Get the gender of the person.
         * @return String 
         */
        public String getGender() {
            return this.gender.getGender();
        } // end method getGender

        /**
         * Get the phone number of the person.
         * @return String
         */
        public String getPhoneNumber() {
            return this.phoneNumber;
        } // end method getPhoneNumber

        /**
         * Get the address of the person.
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