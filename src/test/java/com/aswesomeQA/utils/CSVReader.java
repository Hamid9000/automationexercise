package com.aswesomeQA.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static Object[][] readCsvFromResources(String resourcePath) {

        List<Object[]> rows = new ArrayList<>();

        try (InputStream is =
                     CSVReader.class
                             .getClassLoader()
                             .getResourceAsStream(resourcePath)) {

            if (is == null) {
                throw new RuntimeException(
                        "CSV file NOT found in resources: " + resourcePath);
            }

            Iterable<CSVRecord> records =
                    CSVFormat.DEFAULT
                            .withFirstRecordAsHeader()
                            .parse(new InputStreamReader(is));

            for (CSVRecord record : records) {

                int columnCount = record.size();
                Object[] row = new Object[columnCount];

                for (int i = 0; i < columnCount; i++) {
                    row[i] = record.get(i);
                }

                rows.add(row);
            }

        } catch (Exception e) {
            throw new RuntimeException("CSV read failed: " + e.getMessage(), e);
        }

        // Convert List to Object[][]
        Object[][] data = new Object[rows.size()][];

        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }

        return data;
    }
}
