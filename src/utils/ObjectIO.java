package src.utils;

import java.io.*;
import java.util.ArrayList;

public class ObjectIO {
    // public static void writeObject(String path, Object obj) {
    //     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path, true))) {
    //         oos.writeObject(obj);
    //     } catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    
    public static void writeObjects(String path, ArrayList<?> arrayList) {
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

    public static ArrayList<Object> readObjects(String path) {
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
    }

    public static ArrayList<Object> loadData(String path) {
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
    }
}
