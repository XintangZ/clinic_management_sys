package src.clinic;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * class Treatment
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Treatment implements Serializable {
    private static final long serialVersionUID = 1L;

    private final UUID ID;
    private UUID doctorId, patientId;
    private String medication, description;
    private LocalDate issueDate, startDate, endDate;

    // default constructor
    public Treatment() {
        ID = UUID.randomUUID();
        setIssueDate();
    }

    /**
     * constructor with parameter
     * @param medication String
     * @param description String
     * @param startDate LocalDate
     * @param endDate LocalDate
     * @throws Exception when a string is blank, or when start date is a past date, or when end date is before start date
     */
    public Treatment(String medication, String description, LocalDate startDate, LocalDate endDate) throws Exception {
        ID = UUID.randomUUID();
        setMedication(medication);
        setDescription(description);
        setIssueDate();
        setStartDate(startDate);
        setEndDate(endDate);
    } // end constructor with parameter

    // setters
    /**
     * sets the medication of the treatment
     * @param medication String
     */
    public void setMedication(String medication) {
        this.medication = medication;
    } // end method setMedication

    /**
     * sets the description of the treatment
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
     * @param startDate LocalDate
     * @throws Exception when the date is a past date
     */
    public void setStartDate(LocalDate startDate) throws Exception {
        if (startDate.isBefore(issueDate)) {
            throw new Exception("Error: start date cannot be a past date.");
        }
        this.startDate = startDate;
    } // end method setStartDate

    /**
     * sets the end date of the treatment
     * @param endDate LocalDate
     * @throws Exception when the date is before start date
     */
    public void setEndDate(LocalDate endDate) throws Exception {
        if (endDate.isBefore(startDate)) {
            throw new Exception("Error: end date cannot be earlier than the start date.");
        }
        this.endDate = endDate;
    } // end method setEndDate

    // getters
    public UUID getID() {
        return this.ID;
    }

    /**
     * gets the medication of the treatment
     * @return String
     */
    public String getMedication() {
        return this.medication;
    }

    /**
     * gets the description of the treatment
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * gets the issue date of the treatment
     * @return LocalDate
     */
    public LocalDate getIssueDate() {
        return this.issueDate;
    }

    /**
     * gets the start date of the treatment
     * @return LocalDate
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * gets the end date of the treatment
     * @return LocalDate
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    // return string representation of treatment object 
    @Override
    public String toString() {
        return String.format("Medication: %s %nDescription: %s %nIssue Date: %s %nStart Date: %s %nEnd date: %s %n",
                                getMedication(), getDescription(), getIssueDate(), getStartDate(), getEndDate());
    } // end method toString
}
