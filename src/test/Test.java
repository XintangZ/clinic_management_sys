package src.test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import src.main.Person;
import src.main.Utils;

public class Test implements Utils {
    public static void main(String[] args) {
        LocalDate date;
        boolean isNotValid;
        Scanner scanner = new Scanner(System.in);

        do {
            date = Utils.parseDate(scanner);

            isNotValid = date.isAfter(LocalDate.now());

            if (isNotValid) {
                System.out.println("Cannot be a future date.");
            }
        } while (isNotValid);

        scanner.close();
    }
}
