package src.main;
import java.util.*;

public class Tester {
    public static void main(String[] args) {
        // instance of receptionist class
        Receptionist receptionist = new Receptionist("Mike","Carls","1990-01-20",
						Gender.M,"111-222-3333","1 Lane St.",
                        "2023-09-12");

        ArrayList<Appointment> appointments = new ArrayList<>();
        ArrayList<Appointment> filteredList = new ArrayList<>();
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Patient> patients = new ArrayList<>();

        String filter = "All";

        // instance of a doctor
        Doctor doctor1 = new Doctor("Mary","Jones","1987-05-20",
						Gender.F,"123-456-7890","123 Main St.",
                        "2005-04-23", "General");

        doctors.add(doctor1);

        // another instance of a doctor
        Doctor doctor2 = new Doctor("Sarah","Wilson","1983-12-20",
						Gender.F,"123-456-7890","123 Main St.",
                        "2015-04-23", "Cardiology");

        doctors.add(doctor2);

        // instance of a patient
        BloodType bloodType = BloodType.AB;
        String[] allergiesList = {"Bean","Peanut Butter","Flour","Meat","Penicillin"};
	    ArrayList<String> allergiesArray = new ArrayList<>(Arrays.asList(allergiesList));

        Patient patient1 = new Patient("John","Smith","1988-08-18",
						Gender.M,"514-345-7890","123 rue de Dame",
                        allergiesArray, bloodType, "Forever Inc.", 
					   "G123456", 80);

        patients.add(patient1);

        patient1.updatePatientInfo("John","Smith","1988-08-18",
						Gender.M,"514-345-7890","90 King St.",
                        allergiesArray, bloodType, "Forever Inc.", 
					   "G123456", 80);

        // instance of an appointment
        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentDate();  // sets the date information
        appointment1.setAppointment(patient1.getName(), doctor1.getName(), "Confirmed"); // sets the other information for appointments
        appointments.add(appointment1);
        System.out.println(appointment1);

        // update existing appointment time
        appointment1.setAppointmentDate();
        System.out.println(appointment1);

        // instance of another appointment
        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentDate();  // sets the date information
        appointment2.setAppointment(patient1.getName(), doctor1.getName(), "Confirmed"); // sets the other information for appointments
        appointments.add(appointment2);

        // update status of appointment
        appointment2.setAppointment(patient1.getName(), doctor2.getName(), "Cancelled");

        filter = "Confirmed";
        filteredList = receptionist.getDoctorSchedule(appointments, doctor1.getName(), filter);
        if (filteredList.size() > 0) {
            for (Appointment appointment : filteredList) {
                System.out.println(appointment);
            }
        } else {
            System.out.println("This doctor has no appointments.");
        }

        filteredList = receptionist.getPatientAppointments(appointments, patient1.getName(), filter);
        if (filteredList.size() > 0) {
            for (Appointment appointment : filteredList) {
                System.out.println(appointment);
            }
        } else {
            System.out.println("This patient has no appointments.");
        }
    }
}
