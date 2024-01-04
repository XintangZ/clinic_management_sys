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
    private String allergies, insuranceCompany, policyNumber;
    private double coveredPercentage;
    private BloodType bloodType;

    public Patient() {
        super();
    }
    
    public Patient(String aFirstName, String aLastName, LocalDate aDateOfBirth, 
                String aGender, String aPhoneNumber, String anAddress,
                String anAllergies, BloodType aBloodType,
                String anInsuranceCompany, String aPolicyNumber, int aCoveredPercentage) throws Exception {
        super(aFirstName, aLastName, aDateOfBirth, aGender, aPhoneNumber, anAddress);
        setAllergies(anAllergies);
        setBloodType(aBloodType);
        setInsuranceCompany(anInsuranceCompany);
        setPolicyNumber(aPolicyNumber);
        setCoveredPercentage(aCoveredPercentage);
    }

    public void setAllergies(String theAllergies) {
        this.allergies = theAllergies;
    }

    public void setBloodType(BloodType theBloodType) {
        this.bloodType = theBloodType;
    }

    public void setInsuranceCompany(String theInsuranceCompany) {
        this.insuranceCompany = theInsuranceCompany;
    }

    public void setPolicyNumber(String thePolicyNumber) {
        this.policyNumber = thePolicyNumber;
    }

    public void setCoveredPercentage(int theCoveredPercentage) {
        this.coveredPercentage = theCoveredPercentage / 100.0;
    }

    public String getAllergies() {
        return this.allergies;
    }

    public BloodType getBloodType() {
        return this.bloodType;
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
        + "\nBlood type: " + getBloodType()
        + "\nInsurance company: " + getInsuranceCompany()
        + "\nPolicy number: " + getPolicyNumber()
        + "\nCovered percentage: " + getCoveredPercentage() * 100 + "%";
    }  
}
