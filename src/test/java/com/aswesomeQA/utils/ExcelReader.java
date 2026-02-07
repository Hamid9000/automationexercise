package com.aswesomeQA.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {
    public static Object[][] readSheet(String filePath, String sheetName) {

        Object[][] data;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            int totalRows = sheet.getPhysicalNumberOfRows() - 1; // safer

            // 🔥 FIX: Calculate REAL used columns (ignore blank ones at the end)
            int totalCols = 0;
            Row headerRow = sheet.getRow(0);

            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                Cell cell = headerRow.getCell(j);
                if (cell != null && !cell.toString().trim().isEmpty()) {
                    totalCols = j + 1;
                }
            }

            data = new Object[totalRows][totalCols];

            int dataRowIndex = 0;

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;   // skip empty rows

                for (int j = 0; j < totalCols; j++) {

                    Cell cell = row.getCell(j);
                    data[dataRowIndex][j] =
                            (cell == null) ? "" : cell.toString().trim();
                }

                dataRowIndex++;
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Excel read failed for file: " + filePath, e);
        }

        return data;
    }
}
