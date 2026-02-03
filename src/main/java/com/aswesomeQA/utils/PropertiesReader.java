package com.aswesomeQA.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public static String readKeys(String key) {

        try {
            FileInputStream fis =
                    new FileInputStream(
                            System.getProperty("user.dir")
                                    + "/src/main/resources/data.properties"
                    );

            Properties p = new Properties();
            p.load(fis);

            return p.getProperty(key);   // ✅ CORRECT

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
