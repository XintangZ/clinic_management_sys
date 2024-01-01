package src.clinic;

import java.io.Serializable;
import java.time.*;

/**
 * class Appointment
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String appointmentId, doctorId, patientId;
    private LocalDate appointmentDate;
    private LocalTime startTime, endTime;

    public Appointment() {

    }

    public void setDoctor(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatient(String patientId) {
        this.patientId = patientId;
    }

    public void setAppointmentDate(LocalDate appointmentDate) throws Exception {
        if (appointmentDate.isBefore(LocalDate.now())) {
            throw new Exception("Invalid input: appointment date cannot be a past date");
        }
        this.appointmentDate = appointmentDate;
    }

    public void setStartTime(LocalTime startTime) {

    }
}
