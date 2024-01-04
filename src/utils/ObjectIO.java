package src.utils;

import java.io.*;
import java.util.ArrayList;

/**
 * interface ObjectIO <p>
 * contains constants specifying data file paths
 * and methods to read and write data from/to data files
 * 
 * @version 1.00
 * @since 2023-12-30
 * @author Team 6
 */

public interface ObjectIO {
    // data file paths
    String DOCTOR_FILE_PATH = "data/doctors.bin";
    String PATIENT_FILE_PATH = "data/patients.bin";
    String TREATMENT_FILE_PATH = "data/treatments.bin";
    String APPOINTMENT_FILE_PATH = "data/appointments.bin";

    /**
     * writes objects to an external data file
     * 
     * @param path a String specifying the data file path
     * @param arrayList an ArrayList of any type of objects to be written to the data file
     */
    static void writeObjects(String path, ArrayList<?> arrayList) {
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
    } // end method writeObjects

    /**
     * reads objects from an external data file
     * 
     * @param path a String specifying the data file path
     * @return an ArrayList of Objects containing the objects read from the data file
     */
    static ArrayList<Object> readObjects(String path) {
        ArrayList<Object> arrayList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            while (true) {
                try {
                    arrayList.add(ois.readObject());
                } catch (EOFException e) {
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
        System.err.printf("Failed to read data from \"%s\". Program ended.", path);
        System.exit(-1);
        return null;
    } // end method readObjects

    /**
     * checks a given file path,
     * returns an ArrayList of objects
     * 
     * @param path a String specifying the data file path
     * @return an ArrayList of Objects containing the objects read from the data file
     */
    static ArrayList<Object> loadData(String path) {
        ArrayList<Object> objects;
        // check if the file exists
        File file = new File(path);
        if (file.exists()) {
            // file exists, read data from the file
            objects = readObjects(path);
            return objects;
        } else {
            // file does not exist, return an empty array
            objects = new ArrayList<>();
            return objects;
        }
    } // end method loadData
} // end interface ObjectIO
