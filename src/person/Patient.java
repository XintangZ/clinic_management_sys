package src.person;

import java.time.LocalDate;

/**
 * Class Patient extends the abstract class Person
 * 
 * @version 1.00
 * @since 2023-12-25
 * @author Team 6
 */

public class Patient extends Person {
    private String healthCardNumber, allergies, insuranceCompany, policyNumber;
    private double coveredPercentage;

    // constructor
    public Patient() {
        super();
    }
    
    /**
     * constructor with params
     * 
     * @param aFirstName String
     * @param aLastName String
     * @param aDateOfBirth LocalDate
     * @param aGender String ("F" or "f" for Female, "M" or "m" for Male)
     * @param aPhoneNumber String
     * @param anAddress String
     * @param aHealthCardNumber String
     * @param anAllergies String
     * @param anInsuranceCompany String
     * @param aPolicyNumber String
     * @param aCoveredPercentage int
     * @throws Exception
     */
    public Patient(String aFirstName, String aLastName, LocalDate aDateOfBirth, String aGender, String aPhoneNumber,
            String anAddress, String aHealthCardNumber, String anAllergies, String anInsuranceCompany, String aPolicyNumber,
            int aCoveredPercentage) throws Exception {
        super(aFirstName, aLastName, aDateOfBirth, aGender, aPhoneNumber, anAddress);
        setHealthCardNumber(aHealthCardNumber);
        setAllergies(anAllergies);
        setInsuranceCompany(anInsuranceCompany);
        setPolicyNumber(aPolicyNumber);
        setCoveredPercentage(aCoveredPercentage);
    } // end constructor with params

    /**
     * sets the allergies of the patient
     * 
     * @param theAllergies String
     */
    public void setAllergies(String theAllergies) {
        this.allergies = theAllergies;
    } // end method setAllergies

    /**
     * sets the insurance company of the patient
     * 
     * @param theInsuranceCompany String
     */
    public void setInsuranceCompany(String theInsuranceCompany) {
        this.insuranceCompany = theInsuranceCompany;
    } // end method serInsuranceCompany

    /**
     * sets the insurance policy number of the patient
     * 
     * @param thePolicyNumber String
     */
    public void setPolicyNumber(String thePolicyNumber) {
        this.policyNumber = thePolicyNumber;
    } // end method setPolicyNumber

    /**
     * sets the health card number of the patient
     * 
     * @param theHealthCardNumber String
     */
	public void setHealthCardNumber(String theHealthCardNumber){
		this.healthCardNumber = theHealthCardNumber;
    } // end method setHealthCardNumber

    /**
     * sets the insurance covered percentage of the patient
     * 
     * @param percentageToParse String
     * @throws Exception is the String contains non-digits
     */
    public void setCoveredPercentage(String percentageToParse) throws Exception {
        if (!percentageToParse.matches("\\d+")) {
            throw new Exception("Invalid input. Percentage must be all digits.");
        }

        int coveredPercentage = Integer.parseInt(percentageToParse);
        setCoveredPercentage(coveredPercentage);
    } // end method setCoveredPercentage
    
    /**
     * sets the insurance covered percentage of the patient
     * 
     * @param theCoveredPercentage int
     * @throws Exception if the int is greater than 100
     */
    public void setCoveredPercentage(int theCoveredPercentage) throws Exception {
        if (theCoveredPercentage > 100) {
            throw new Exception("Invalid input. Percentage cannot be greater than 100.");
        }
        this.coveredPercentage = theCoveredPercentage / 100.0;
    } // end method setCoveredPercentage

    /**
     * gets the allergies of the patient
     * 
     * @return String
     */
    public String getAllergies() {
        return this.allergies;
    } // end method getAllergies

    /**
     * gets the insurance company of the patient
     * 
     * @return String
     */
    public String getInsuranceCompany() {
        return this.insuranceCompany;
    } // end method getInsuranceCompany

    /**
     * gets the insurance policy number of the patient
     * 
     * @return String
     */
    public String getPolicyNumber() {
        return this.policyNumber;
    } // end method getPolicyNumber

    /**
     * gets the health card number of the patient
     * 
     * @return String
     */
	public String getHealthCardNumber(){
		return this.healthCardNumber;
    } // end method getHealthCardNumber

    /**
     * gets the insurance covered percentage of the patient
     * 
     * @return double
     */
    public double getCoveredPercentage() {
        return this.coveredPercentage;
    } // end method getCoveredPercentage

    // return string representation of patient object 
    @Override
    public String toString() {
        return super.toString() + "Health card number: " + getHealthCardNumber() 
        + "\nAllergies: " + getAllergies() 
        + "\nInsurance company: " + getInsuranceCompany() 
        + "\nPolicy number: " + getPolicyNumber() 
        + "\nCovered percentage: " + getCoveredPercentage() * 100 + "%"; 
    } // end method toString
} // end class Patient
