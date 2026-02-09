package com.aswesomeQA.utils;

import com.aswesomeQA.config.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;

public class SQLReader {

    private static Properties loadSQL() {
        Properties props = new Properties();
        try (FileInputStream fis =
                     new FileInputStream("src/main/resources/sql-queries.properties")) {
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }

    public static Object[][] getSignupPositiveData() {

        ArrayList<Object[]> data = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String table = DBConnection.getTableName();

            Properties sql = loadSQL();
            String query = sql.getProperty("signup.positive.query")
                    .replace("${table}", table);

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                data.add(new Object[]{
                        rs.getString("TC_ID"),
                        rs.getString("Name"),
                        rs.getString("Email")
                });
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][0]);
    }
}
