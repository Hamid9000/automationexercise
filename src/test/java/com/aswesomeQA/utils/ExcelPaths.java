package com.aswesomeQA.utils;

public class ExcelPaths {

    private static final String BASE =
            System.getProperty("user.dir")
                    + "/src/test/resources/testdata";

    // ============ SIGNUP TEST DATA ============
    public static final String SIGNUP_NEG_FILE =
            BASE + "/signup/Signup_Negative_TestData_Excel.xlsx";

    public static final String SIGNUP_POS_FILE =
            BASE + "/signup/Signup_Positive_TestData_Excel.xlsx";

    // ============ REGISTRATION TEST DATA ============
    public static final String REG_NEG_FILE =
            BASE + "/registration/Registration_Negative_TestData_Excel.xlsx";

    public static final String REG_POS_FILE =
            BASE + "/registration/Registration_Positive_TestData_Excel.xlsx";

    // ============ E2E TEST DATA (NEW) ============
    public static final String E2E_FLOW_FILE =
            BASE + "/E2E/E2E_Signup_Registration_TestData.xlsx";
}
