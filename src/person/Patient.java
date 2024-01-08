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
    private String allergies, insuranceCompany, policyNumber, healthCardNumber;
    private double coveredPercentage;

    public Patient() {
        super();
    }
    
    public Patient(String aFirstName, String aLastName, LocalDate aDateOfBirth, 
                String aGender, String aPhoneNumber, String anAddress,
                String anAllergies, 
                String anInsuranceCompany, String aPolicyNumber, int aCoveredPercentage) throws Exception {
        super(aFirstName, aLastName, aDateOfBirth, aGender, aPhoneNumber, anAddress);
        setAllergies(anAllergies);
        setInsuranceCompany(anInsuranceCompany);
        setPolicyNumber(aPolicyNumber);
        setCoveredPercentage(aCoveredPercentage);
    }

    public void setAllergies(String theAllergies) {
        this.allergies = theAllergies;
    }

    public void setInsuranceCompany(String theInsuranceCompany) {
        this.insuranceCompany = theInsuranceCompany;
    }

    public void setPolicyNumber(String thePolicyNumber) {
        this.policyNumber = thePolicyNumber;
    }

    public void setCoveredPercentage(String percentageToParse) throws Exception {
        if (!percentageToParse.matches("\\d+")) {
            throw new Exception("Invalid input. Percentage must be all digits.");
        }

        int coveredPercentage = Integer.parseInt(percentageToParse);
        setCoveredPercentage(coveredPercentage);
    }
    
    public void setCoveredPercentage(int theCoveredPercentage) throws Exception {
        if (theCoveredPercentage > 100) {
            throw new Exception("Invalid input. Percentage cannot be greater than 100.");
        }
        this.coveredPercentage = theCoveredPercentage / 100.0;
    }

    public String getAllergies() {
        return this.allergies;
    }

    public String getInsuranceCompany() {
        return this.insuranceCompany;
    }

    public String getPolicyNumber() {
        return this.policyNumber;
    }

    public double getCoveredPercentage() {
        return this.coveredPercentage;
    }

    @Override
    public String toString() {
        return super.toString() + "Allergies: " + getAllergies()
        + "\nInsurance company: " + getInsuranceCompany()
        + "\nPolicy number: " + getPolicyNumber()
        + "\nCovered percentage: " + getCoveredPercentage() * 100 + "%";
    }  
}
