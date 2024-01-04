package src.test;

import java.util.ArrayList;

import src.person.Doctor;
import src.utils.ObjectIO;
import src.utils.UserInteraction;

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
}
