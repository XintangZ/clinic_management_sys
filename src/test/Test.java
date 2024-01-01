package src.test;

import java.util.ArrayList;

import src.clinic.Treatment;
import src.utils.ObjectIO;
import src.utils.UserInterface;

public class Test {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();

        ArrayList<Treatment> treatments = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Treatment treatment = userInterface.createTreatment();
            treatments.add(treatment);
        }

        ObjectIO.writeObjects("treatments.bin", treatments);

        ArrayList<Object> treatements = ObjectIO.readObjects("treatments.bin");

        for (Object obj : treatements) {
            System.out.println((Treatment) obj);
        }
    }
}
