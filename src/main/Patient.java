package src.main;

import java.util.ArrayList;

/**
 * Class Patient extends the abstract class Person
 * 
 * @version 1.00
 * @since 2023-12-25
 * @author Team 6
 */

public class Patient extends Person {
    protected ArrayList<String> allergies = new ArrayList<String>();
    protected String insuranceCompany,policyNumber;
    protected double coveredPercentage;
    protected BloodType bloodType;
    
    public Patient(String aFirstName, String aLastName, String aDateOfBirth, 
		     Gender aGender, String aPhoneNumber, String anAddress,
		     ArrayList<String> anAllergies, BloodType aBloodType,
		     String anInsuranceCompany, String aPolicyNumber, double aCoveredPercentage){
        super(aFirstName, aLastName, aDateOfBirth, aGender, aPhoneNumber, anAddress);
        setAllergies(anAllergies);
        setBloodType(aBloodType);
        setInsuranceCompany(anInsuranceCompany);
        setPolicyNumber(aPolicyNumber);
        setCoveredPercentage(aCoveredPercentage);
    }

    public void updatePatientInfo(String aFirstName, String aLastName, String aDateOfBirth, 
		     Gender aGender, String aPhoneNumber, String anAddress,
		     ArrayList<String> anAllergies, BloodType aBloodType,
		     String anInsuranceCompany, String aPolicyNumber, double aCoveredPercentage) {
        super.setName(aFirstName, aLastName);
        super.setDateOfBirth(aDateOfBirth);
        super.setGender(aGender);
        super.setPhoneNumber(aPhoneNumber);
        super.setAddress(anAddress);
        setAllergies(anAllergies);
        setBloodType(aBloodType);
        setInsuranceCompany(anInsuranceCompany);
        setPolicyNumber(aPolicyNumber);
        setCoveredPercentage(aCoveredPercentage);
    } // updates patient information

    public void setAllergies(ArrayList<String> theAllergies){
	this.allergies = theAllergies;
    }

    public void setBloodType(BloodType theBloodType){
	this.bloodType = theBloodType;
    }

    public void setInsuranceCompany(String theInsuranceCompany){
	this.insuranceCompany = theInsuranceCompany;
    }

    public void setPolicyNumber(String thePolicyNumber){
	this.policyNumber = thePolicyNumber;
    }

    public void setCoveredPercentage(double theCoveredPercentage){
	this.coveredPercentage = theCoveredPercentage;
    }

    public ArrayList<String> getAllergies(){
	return this.allergies;
    }

    public BloodType getBloodType(){
	return this.bloodType;
    }

    public String getInsuranceCompany(){
	return this.insuranceCompany;
    }

    public String getPolicyNumber(){
	return this.policyNumber;
    }

    public double getCoveredPercentage(){
	return this.coveredPercentage;
    }

@Override
public String toString(){
    return super.toString() + "Allergies: " +getAllergies()
	+ "\nBlood type: " + getBloodType()
	+ "\nInsurance company: " + getInsuranceCompany()
	+ "\nPolicy number: " + getPolicyNumber()
	+ "\nCovered percentage: " + getCoveredPercentage()
    + "\n";
}
    
    
}
