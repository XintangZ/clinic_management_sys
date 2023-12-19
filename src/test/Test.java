package src.test;

import src.main.Doctor;
import src.main.Gender;

public class Test {
    public static void main(String[] args) {
        Doctor doctor = new Doctor("John", "Smith", "2000-12-31", Gender.M, "123-456-7890", "123 Cat St.",
                "2020-01-01", "General");
        System.out.println(doctor);
    }
}
