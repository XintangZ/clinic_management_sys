package src.clinic;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * class Appointment
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Appointment implements Serializable {
    private LocalDate appointmentDate;        // format: yyyy-MM-dd
    private LocalTime startTime;   // format: hh:mm a
    private LocalTime endTime;     // format: hh:mm a
    private String patientName;
    private String doctorName;
    private String status;
    private LocalDate creationDate;
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");

    public Appointment() {
        this.creationDate = LocalDate.now();
    }
    
    public Appointment(LocalDate appointmentDate, LocalTime startTime, LocalTime endTime, String patientName, String doctorName, String status) throws Exception {
        setAppointment(appointmentDate, startTime, endTime, patientName, doctorName, status);
    }
    
    public void setAppointment(LocalDate appointmentDate, LocalTime startTime, LocalTime endTime, String patientName, String doctorName, String status) throws Exception {   // same as edit appointment
        setDate(appointmentDate);
        setStartTime(startTime);
        setEndTime(endTime);       
        setPatientName(patientName);
        setDoctorName(doctorName);
        setStatus(status);
    }

    public void setDate(LocalDate date) throws Exception {
        if (date.isBefore(creationDate)) {
            throw new Exception("Invalid date. Appointment date cannot be a past date.");
        }
        this.appointmentDate = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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

    public LocalDate getDate() {
        return this.appointmentDate;
    }

    public String getStartTime() {
        return this.startTime.format(timeFormat);
    }

    public String getEndTime() {
        return this.endTime.format(timeFormat);
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

    @Override
    public String toString() {
        return String.format("%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s%n",
        "Date", getDate().toString(),
        "Start Time", getStartTime(),
        "End Time", getEndTime(),
        "Doctor Name", getDoctorName(),
        "Patient Name", getPatientName(),
        "Status", getStatus()
        );
    }


    
}
