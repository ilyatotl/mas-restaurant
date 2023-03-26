package org.example.JSONSerializer;

import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

public class TJSONSerializer {
    public static String getString(final String filename) {
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
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }
        return ans;
    }

    public static JsonObject getJson(final String filename) {
        return (JsonObject) new JsonParser().parse(getString(filename));
    }
}
