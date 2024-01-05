package src.person;
import java.time.LocalDate;
import java.util.*;

import src.clinic.Appointment;

/**
 * Class Receptionist extends the abstract class Employee
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Receptionist extends Employee {
    public Receptionist(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber,
    String address, LocalDate dateOfEmployment) throws Exception {
        super(firstName, lastName, dateOfBirth, lastName, phoneNumber, address, dateOfEmployment);
    }

    public ArrayList<Appointment> getPatientAppointments(ArrayList<Appointment> appointments, String patientName, String filter) {
        ArrayList<Appointment> filteredList = new ArrayList<>();
        if (filter.equals("All")) {
            for (Appointment appointment : appointments) {
                if (appointment.getPatientName().equals(patientName)) {
                    filteredList.add(appointment);
                }
            }
        } else {
            for (Appointment appointment : appointments) {
                if (appointment.getPatientName().equals(patientName) && appointment.getStatus().equals(filter)) {
                    filteredList.add(appointment);
                }
            }
        }
        return filteredList;
    }

    public ArrayList<Appointment> getDoctorSchedule(ArrayList<Appointment> appointments, String doctorName, String filter) {
        ArrayList<Appointment> filteredList = new ArrayList<>();
        if (filter.equals("All")) {
            for (Appointment appointment : appointments) {
                if (appointment.getDoctorName().equals(doctorName)) {
                    filteredList.add(appointment);
                }
            }
        } else {
            for (Appointment appointment : appointments) {
                if (appointment.getDoctorName().equals(doctorName) && appointment.getStatus().equals(filter)) {
                    filteredList.add(appointment);
                }
            }
        }
        return filteredList;
    }

    // public double getPatientBalance() {
    //     return this.balanceDue;
    // } // patient class

    // public double chargePatient(double charge) { 
    //     return Patient.getPatientBalance() + charge;
    // }

    // public double applyPayment(double payment) {
    //     return Patient.getPatientBalance() - payment;
    // }
    /* ONLY IF WE WANT TO HAVE A BALANCE FOR PATIENT */

}
