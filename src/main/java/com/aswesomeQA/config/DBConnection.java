package com.aswesomeQA.config;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static Properties loadProps() {
        Properties props = new Properties();
        try {
            String path = System.getProperty("user.dir")
                    + "/src/main/resources/db.properties";

            FileInputStream fis = new FileInputStream(path);
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }

    public static Connection getConnection() {

        Properties p = loadProps();

        String url = p.getProperty("db.url");
        String user = p.getProperty("db.user");
        String pass = p.getProperty("db.pass");

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ MySQL/SQLite Connected Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static String getTableName() {
        return loadProps().getProperty("db.table");
    }
}
