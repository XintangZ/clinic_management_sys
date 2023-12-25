package src.main;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class Appointment
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Appointment {
    private Date appointmentStartTime;  // format: mm/dd/yyyy hh:mm
    private Date appointmentEndTime;    // format: mm/dd/yyyy hh:mm
    private String patientName;
    private String doctorName;
    private String status;
    

    public Appointment(Date appointmentStart, Date appointmentEnd, String patientName, String doctorName){
        setAppointment(appointmentStart, appointmentEnd, patientName, doctorName);
    }
    
    public void setAppointment(Date appointmentStartTime, Date appointmentEndTime, String patientName, String doctorName) {   // same as edit appointment
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
        this.patientName = patientName;
        this.doctorName = doctorName;
    } // receptionist has access to this method

    public String getDoctorName() {
        return this.doctorName;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public String getStatus() {
        return this.status;
    }

    public ArrayList<Appointment> getAppointmentList(ArrayList<Appointment> appointments) {
        ArrayList<Appointment> upcomingAppointments = new ArrayList<>();
        for (Appointment appointment: appointments) {
            if (!appointment.getStatus().equals("Complete") || !appointment.getStatus().equals("Cancelled")) {
                upcomingAppointments.add(appointment);
            }
        }
        return upcomingAppointments;
    }

}
