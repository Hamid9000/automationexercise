package com.aswesomeQA.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtilsExcel {

    public static final String SHEET_PATH =
            System.getProperty("user.dir")
                    + "/src/test/resources/Signup_TestData_Atutomation.xlsx";

    // -------- CORE READER (returns ALL columns except TestType) --------

    private static Object[][] readFilteredData(String sheetName, String filterType) {

        try (FileInputStream file = new FileInputStream(SHEET_PATH);
             Workbook book = WorkbookFactory.create(file)) {

            Sheet sheet = book.getSheet(sheetName);

            int rows = sheet.getLastRowNum();              // excludes header
            int cols = sheet.getRow(0).getLastCellNum();  // total columns

            List<Object[]> tempData = new ArrayList<>();

            for (int i = 0; i < rows; i++) {

                String testType = getCellValue(sheet, i + 1, cols - 1);

                if (testType.equalsIgnoreCase(filterType)) {

                    Object[] rowData = new Object[cols - 1]; // all except TestType

                    for (int j = 0; j < cols - 1; j++) {
                        rowData[j] = getCellValue(sheet, i + 1, j);
                    }

                    tempData.add(rowData);
                }
            }

            return tempData.toArray(new Object[0][]);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Excel read failed for sheet: " + sheetName + " | " + e.getMessage(), e
            );
        }
    }

    // -------- DATA PROVIDERS (aligned with your SignUpTest) --------

    @DataProvider(name = "signupInvalidData")
    public static Object[][] getSignupInvalidData() {
        // Returns: TC_ID, Name, Email, Expected_Result
        return readFilteredData("SignupData", "Negative");
    }

    @DataProvider(name = "signupExistingEmailData")
    public static Object[][] getSignupExistingEmailData() {
        // Returns: TC_ID, Name, Email, Expected_Result (dummy param for test)
        return readFilteredData("SignupData", "Existing_Email");
    }

    @DataProvider(name = "signupValidData")
    public static Object[][] getSignupValidData() {

        Object[][] raw = readFilteredData("SignupData", "Positive");

        // Convert 4 columns → 3 columns (remove Expected_Result for positive test)
        Object[][] trimmed = new Object[raw.length][3];

        for (int i = 0; i < raw.length; i++) {
            trimmed[i][0] = raw[i][0]; // TC_ID
            trimmed[i][1] = raw[i][1]; // Name
            trimmed[i][2] = raw[i][2]; // Email
        }

        return trimmed;
    }

    // -------- NULL-SAFE CELL READER --------

    private static String getCellValue(Sheet sheet, int row, int col) {

        if (sheet.getRow(row) == null) {
            return "";
        }

        if (sheet.getRow(row).getCell(col) == null) {
            return "";
        }

        return sheet.getRow(row).getCell(col).toString().trim();
    }
}
