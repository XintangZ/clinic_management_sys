package src.clinic;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * class Treatment
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Treatment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String doctorName, patientName, medication, description;
    private LocalDate issueDate, startDate, endDate;

    // default constructor
    public Treatment() {
        setIssueDate();
    }

    /**
     * constructor with parameter
     * @param medication String
     * @param description String
     * @param startDate LocalDate
     * @param endDate LocalDate
     * @throws Exception
     */
    public Treatment(String doctorName, String patientName, String medication, String description, LocalDate startDate,
            LocalDate endDate) throws Exception {
        setDoctorName(doctorName);
        setPatientName(patientName);
        setMedication(medication);
        setDescription(description);
        setIssueDate();
        setStartDate(startDate);
        setEndDate(endDate);
    } // end constructor with parameter

    // setters
    /**
     * sets the doctor name of the treatment
     * 
     * @param medication String
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    } // end method setDoctorName
    
    /**
     * sets the patient name of the treatment
     * 
     * @param medication String
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    } // end method setPatientName

    /**
     * sets the medication of the treatment
     * 
     * @param medication String
     */
    public void setMedication(String medication) {
        this.medication = medication;
    } // end method setMedication

    /**
     * sets the description of the treatment
     * 
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    } // end method setDescription

    /**
     * sets the issue date to be today
     */
    private void setIssueDate() {
        this.issueDate = LocalDate.now();
    } // end method setIssueDate

    /**
     * sets the start date of the treatment
     * 
     * @param dateToParse String
     * @throws Exception if failed to parse the String to LocalDate
     */
    public void setStartDate(String dateToParse) throws Exception {
        LocalDate startDate;
        try {
            startDate = LocalDate.parse(dateToParse);
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid date format. Format must be \"yyyy-mm-dd\".");
        }
        setStartDate(startDate);
    } // end method setStartDate

    /**
     * sets the start date of the treatment
     * 
     * @param startDate LocalDate
     * @throws Exception if the date is a past date
     */
    public void setStartDate(LocalDate startDate) throws Exception {
        if (startDate.isBefore(issueDate)) {
            throw new Exception("Invalid date. Start date cannot be a past date.");
        }
        this.startDate = startDate;
    } // end method setStartDate

    /**
     * sets the end date of the treatment
     * 
     * @param dateToParse String
     * @throws Exception if failed to parse the String to LocalDate
     */
    public void setEndDate(String dateToParse) throws Exception {
        LocalDate endDate;
        try {
            endDate = LocalDate.parse(dateToParse);
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid date format. Format must be \"yyyy-mm-dd\".");
        }
        setEndDate(endDate);
    } // end method setEndDate

    /**
     * sets the end date of the treatment
     * 
     * @param endDate LocalDate
     * @throws Exception if the date is before start date
     */
    public void setEndDate(LocalDate endDate) throws Exception {
        if (endDate.isBefore(startDate)) {
            throw new Exception("Invalid date. End date cannot be earlier than the start date.");
        }
        this.endDate = endDate;
    } // end method setEndDate

    // getterse
    /**
     * gets the doctor name of the treatment
     * 
     * @return String
     */
    public String getDoctorName() {
        return this.doctorName;
    }

    /**
     * gets the patient name of the treatment
     * 
     * @return String
     */
    public String getPatientName() {
        return this.patientName;
    }

    /**
     * gets the medication of the treatment
     * 
     * @return String
     */
    public String getMedication() {
        return this.medication;
    }

    /**
     * gets the description of the treatment
     * 
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * gets the issue date of the treatment
     * 
     * @return LocalDate
     */
    public LocalDate getIssueDate() {
        return this.issueDate;
    }

    /**
     * gets the start date of the treatment
     * 
     * @return LocalDate
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * gets the end date of the treatment
     * 
     * @return LocalDate
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    // return string representation of treatment object 
    @Override
    public String toString() {
        return String.format("Doctor Name: %s %nPatient Name: %s %nMedication: %s %nDescription: %s %nIssue Date: %s %nStart Date: %s %nEnd date: %s %n",
                                getDoctorName(), getPatientName(), getMedication(), getDescription(), getIssueDate(), getStartDate(), getEndDate());
    } // end method toString
} // end class Treatment
