package src.test;

import java.time.LocalDate;
import java.util.Scanner;

import src.person.Doctor;
import src.utils.RunnableWithException;


public class TestRunnable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LocalDate[] dateHolder = new LocalDate[1];
        Doctor doctor = new Doctor();

        RunnableWithException function = () -> {
            doctor.setGender(scanner.nextLine());
        };


        try {
            limitAttempts(function, 3);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (dateHolder[0] != null) {
            System.out.println(dateHolder[0]);
        }

        scanner.close();
    }


    // Method to run the passed-in method for a specific number of times
    public static void limitAttempts(RunnableWithException methodToRun, int attempts) throws Exception {
        do {
            try {
                methodToRun.run();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                if (attempts > 2) {
                    System.out.println(--attempts + " attempts left.");
                } else {
                    System.out.println(--attempts + " attempt left.");
                }
            }
        } while (attempts > 0);
        throw new Exception("Maximum attempts exceeded, session ends.");
    } // end method limitAttempts
}
