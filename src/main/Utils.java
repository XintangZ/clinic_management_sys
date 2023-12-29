package src.main;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public interface Utils {
    /**
     * Prompt user a closed question (yes/no question).
     * Return true if the user inputs y or yes,
     * Return false if the uesr inputs n or no.
     * (Cases are ignored.)
     * 
     * @param scanner Scanner
     * @param closedQuestion String
     * @return boolean
     */
    static boolean getAnswer(Scanner scanner, String closedQuestion) {
        boolean answer;
        while (true) {
            try {
                // prompt the user for an answer
                System.out.println(closedQuestion + " (y/n)");
                String userInput = scanner.next().trim().toLowerCase();
                if (userInput.equals("y") || userInput.equals("yes")) {
                    answer = true;
                } else if (userInput.equals("n") || userInput.equals("no")) {
                    answer = false;
                } else {
                    throw new Exception("Invalid response, please try again.");
                } // end if
                return answer;
            } // end try
            catch (Exception e) {
                scanner.nextLine();
                System.err.println(e.getMessage());
            } // end catch Exception
        } // end while loop
    } // end method getAnswer

    static LocalDate parseDate(Scanner scanner) {
        LocalDate date;

        while (true) {
            System.out.println("Enter a date:");
            try {
                date = LocalDate.parse(scanner.next());

                return date;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Error: invalid date format.");
                scanner.nextLine();
            }
        }
    } // end method 

    static void writeData(String path, ArrayList<?> arrayList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            for (Object obj : arrayList) {
                oos.writeObject(obj);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Object> readData(String path) {
        ArrayList<Object> arrayList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            while (true) {
                try {
                    arrayList.add(ois.readObject());
                } catch (EOFException e) {
                    // TODO: handle exception
                    break;
                }
            }
            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.printf("Failed to read data from \"%s\". Program ended.", path);
        System.exit(-1);
        return null;
    }
}
