package src.test;

import java.util.ArrayList;

import src.person.Doctor;
import src.utils.ObjectIO;

public class Test {
    public static void main(String[] args) {
        boolean[][] calendar = new boolean[5][12];
        String[] days = { "MON", "TUE", "WED", "THU", "FRI" };
        String[] times = { "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "13:00", "13:30", "14:00", "14:30",
                "15:00", "15:30" };

        for (int i = 0; i < calendar.length; i++) {
            System.out.print(days[i] + "\t");
            for (int j = 0; j < calendar[i].length; j++) {
                if (!calendar[i][j]) {
                    System.out.print(times[j] + "    ");
                } else {
                    System.out.print("         ");
                }
            }
            System.out.println();
        }
    }
    
    // static void create(String type) {
    //     switch (type) {
    //         case "doctor":
    //             ArrayList<Doctor> doctors = ObjectIO.loadData(DOCTOR_FILE_PATH);
    //             System.out.println("======= NEW DOCTOR =======");
    //             Doctor doctor = inputValidator.createDoctor();
    //             doctors.add(doctor);
    //             ObjectIO.writeObjects(DOCTOR_FILE_PATH, doctors);
    //             break;
    //         case "patient":
    //             ArrayList<Patient> patients = ObjectIO.loadData(PATIENT_FILE_PATH);
    //             System.out.println("======= NEW PATIENT =======");
    //             Patient patient = inputValidator.createPatient();
    //             patients.add(patient);
    //             ObjectIO.writeObjects(PATIENT_FILE_PATH, patients);
    //             break;
    //         case "treatment":
    //             ArrayList<Treatment> treatments = ObjectIO.loadData(TREATMENT_FILE_PATH);
    //             System.out.println("======= NEW TREATMENT =======");
    //             Treatment treatment = inputValidator.createTreatment();
    //             treatments.add(treatment);
    //             ObjectIO.writeObjects(TREATMENT_FILE_PATH, treatments);       
    //             break;
    //         default:
    //             break;
    //     }
    // }
}
