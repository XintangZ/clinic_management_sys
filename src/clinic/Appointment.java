package src.clinic;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeParseException;

/**
 * class Appointment
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int APPOINTMENT_DURATION = 60;

    private LocalDate appointmentDate;        // format: yyyy-MM-dd
    private LocalTime startTime;              // format: hh:mm
    private String patientName, doctorName, status, description;
    private LocalDate creationDate;

    // constructor
    public Appointment() {
        this.creationDate = LocalDate.now();
    }
    
    /**
     * constructor with params
     * 
     * @param appointmentDate LocalDate
     * @param startTime LocalTime
     * @param patientName String
     * @param doctorName String
     * @param status String
     * @param description String
     * @throws Exception
     */
    public Appointment(LocalDate appointmentDate, LocalTime startTime, String patientName, String doctorName,
            String status, String description) throws Exception {
        setDate(appointmentDate);
        setStartTime(startTime);
        setPatientName(patientName);
        setDoctorName(doctorName);
        setStatus(status);
        setDescription(description);
    } // end constructor with params

    /**
     * sets the date of the appointment
     * 
     * @param dateToParse String
     * @throws Exception if failed to parse the String to LocalDate
     */
    public void setDate(String dateToParse) throws Exception {
        LocalDate date;
        try {
            date = LocalDate.parse(dateToParse);
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid date format. Format must be \"yyyy-mm-dd\".");
        }
        setDate(date);
    } // end method setDate

    /**
     * sets the date of the appointment
     * 
     * @param date LocalDate
     * @throws Exception if the date is a past date
     */
    public void setDate(LocalDate date) throws Exception {
        if (!date.isAfter(creationDate)) {
            throw new Exception("Invalid date. Appointment date must be a future date.");
        }
        this.appointmentDate = date;
    } // end method setDate

    /**
     * sets the start time of the appointment
     * 
     * @param timeToParse String
     * @throws Exception if failed to parse the String to LocalTime
     */
    public void setStartTime(String timeToParse) throws Exception {
        LocalTime time;
        try {
            time = LocalTime.parse(timeToParse);
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid time format. Format must be \"hh:mm\".");
        }
        setStartTime(time);    
    } // end method setStartTime

    /**
     * sets the start time of the appointment
     * 
     * @param startTime LocalTime
     * @throws Exception if the time is a past time
     */
    public void setStartTime(LocalTime startTime) throws Exception {
        if (this.appointmentDate.equals(this.creationDate) && startTime.isBefore(LocalTime.now())) {
            throw new Exception("Invalid time. Appointment time cannot be a past time.");
        }
        this.startTime = startTime;
    } // end method setStartTime

    /**
     * sets the patient name of the appointment
     * 
     * @param patientName String
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    } // end method setPatientName

    /**
     * sets the doctor name of the appointment
     * 
     * @param doctorName String
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    } // end method setDoctorName

    /**
     * sets the status of the appointment
     * 
     * @param status String
     */
    public void setStatus(String status) {
        this.status = status;
    } // end method setStatus

    /**
     * sets the description of the appointment
     * 
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    } // end method setDescription

    /**
     * gets the date of the appointment
     * 
     * @return LocalDate
     */
    public LocalDate getDate() {
        return this.appointmentDate;
    } // end method getDate

    /**
     * gets the start time of the appointment
     * 
     * @return LocalTime
     */
    public LocalTime getStartTime() {
        return this.startTime;
    } // end method getStartTime

    /**
     * gets the estimated end time of the appointment
     * (calculated base on start time)
     * 
     * @return LocalTime
     */
    public LocalTime getEndTime() {
        return this.startTime.plusMinutes(APPOINTMENT_DURATION);
    } // end method getEndTime

    /**
     * gets the doctor name of the appointment
     * 
     * @return String
     */
    public String getDoctorName() {
        return this.doctorName;
    } // end method getDoctorName

    /**
     * gets the patient name of the appointment
     * 
     * @return String
     */
    public String getPatientName() {
        return this.patientName;
    } // end method getPatientName

    /**
     * gets the status of the appointment
     * 
     * @return String
     */
    public String getStatus() {
        return this.status;
    } // end method getStatus

    /**
     * gets the description of the appointment
     * 
     * @return String
     */
    public String getDescription() {
        return this.description;
    } // end method getDescription

    // return string representation of appointment object 
    @Override
    public String toString() {
        return String.format("%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s%n",
        "Date", getDate().toString(),
        "Start Time", getStartTime().toString(),
        "End Time", getEndTime().toString(),
        "Doctor Name", getDoctorName(),
        "Patient Name", getPatientName(),
        "Description", getDescription(),
        "Status", getStatus()
        );
    } // end method toString
} // end class Appointment
