package src.main;
import java.util.*;

/**
 * Class Receptionist extends the abstract class Employee
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Receptionist extends Employee {
    private double balanceDue;

    public Receptionist(String firstName, String lastName, String dateOfBirth, Gender gender, String phoneNumber,
    String address, String dateOfEmployment) {
        super(firstName, lastName, dateOfBirth, gender, phoneNumber, address, dateOfEmployment);
    }

    public void updatePatientInfo(Patient patient) {   // create patient would be constructor in patient class
        Patient.setPatient(patient); 
    } // patient class

    public double getPatientBalance() {
        return this.balanceDue;
    } // patient class

    public double chargePatient(double charge) { 
        return Patient.getPatientBalance() + charge;
    }

    public double applyPayment(double payment) {
        return Patient.getPatientBalance() - payment;
    }

    // returns a list of all the doctor's upcoming appointments
    public ArrayList<Appointment> getDoctorScehdule(String doctorName, ArrayList<Appointment> appointmentList) {
        ArrayList<Appointment> filteredList = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getDoctorName().equals(doctorName)) {
                filteredList.add(appointment);
            }
        }
        return filteredList;
    }

    // returns a list of all the patient's upcoming appointments
    public ArrayList<Appointment> getPatientAppointments(String patientName, ArrayList<Appointment> appointmentList) {
        ArrayList<Appointment> filteredList = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getPatientName().equals(patientName)) {
                filteredList.add(appointment);
            }
        }
        return filteredList;
    }

}
