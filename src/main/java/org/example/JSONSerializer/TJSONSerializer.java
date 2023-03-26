package org.example.JSONSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class TJSONSerializer {
    public static String getString(String filename) {
        String ans = "";
        try {
            File file = new File(filename);
            FileInputStream fin = new FileInputStream(file);
            Scanner scanner = new Scanner(fin);
            String temp;
            while (scanner.hasNextLine()) {
                temp = scanner.nextLine();
                ans += temp;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return ans;
    }
}