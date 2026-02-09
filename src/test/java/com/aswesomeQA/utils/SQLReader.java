package com.aswesomeQA.utils;

import com.aswesomeQA.config.DBConnection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

public class SQLReader {

    private static Properties loadSQL() {
        Properties props = new Properties();
        try (FileInputStream fis =
                     new FileInputStream(System.getProperty("user.dir")
                             + "/src/main/resources/sql-queries.properties")) {
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }

    // ======= SQLITE-FRIENDLY GENERIC METHOD =======
    public static Object[][] getTestData(String queryKey) {

        Object[][] data = null;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement(); // simple statement only

            String table = DBConnection.getTableName();
            Properties sql = loadSQL();

            String baseQuery = sql.getProperty(queryKey)
                    .replace("${table}", table);

            System.out.println("FINAL QUERY: " + baseQuery);

            // -------- STEP 1: Get Row Count (SQLite safe) --------
            String countQuery = "SELECT COUNT(*) FROM (" + baseQuery + ") t";

            ResultSet rsCount = stmt.executeQuery(countQuery);
            rsCount.next();
            int rowCount = rsCount.getInt(1);
            rsCount.close();

            // -------- STEP 2: Execute actual query --------
            ResultSet rs = stmt.executeQuery(baseQuery);

            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            // -------- STEP 3: Create pure Object[][] --------
            data = new Object[rowCount][colCount];

            // -------- STEP 4: Fill array generically --------
            int r = 0;
            while (rs.next()) {
                for (int c = 1; c <= colCount; c++) {
                    data[r][c - 1] = rs.getObject(c);
                }
                r++;
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
