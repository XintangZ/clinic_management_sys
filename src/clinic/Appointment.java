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
    
    public Appointment(LocalDate appointmentDate, LocalTime startTime, String patientName, String doctorName, String status, String description) throws Exception {
        setAppointment(appointmentDate, startTime, patientName, doctorName, status, description);
    }
    
    public void setAppointment(LocalDate appointmentDate, LocalTime startTime, String patientName,
            String doctorName, String status, String description) throws Exception { // same as edit appointment
        setDate(appointmentDate);
        setStartTime(startTime);
        setPatientName(patientName);
        setDoctorName(doctorName);
        setStatus(status);
        setDescription(description);
    }

    /**
     * sets the date of the appointment
     * 
     * @param dateToParse String
     * @throws Exception
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

    public void setDate(LocalDate date) throws Exception {
        if (date.isBefore(creationDate)) {
            throw new Exception("Invalid date. Appointment date cannot be a past date.");
        }
        this.appointmentDate = date;
    }

    public void setStartTime(String timeToParse) throws Exception {
        LocalTime time;
        try {
            time = LocalTime.parse(timeToParse);
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid time format. Format must be \"hh:ss\".");
        }
        setStartTime(time);    
    }

    public void setStartTime(LocalTime startTime) throws Exception {
        if (this.appointmentDate.equals(this.creationDate) && startTime.isBefore(LocalTime.now())) {
            throw new Exception("Invalid time. Appointment time cannot be a past time.");
        }
        this.startTime = startTime;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return this.appointmentDate;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.startTime.plusMinutes(APPOINTMENT_DURATION);
    }

    public String getDoctorName() {
        return this.doctorName;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

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
    }
}
