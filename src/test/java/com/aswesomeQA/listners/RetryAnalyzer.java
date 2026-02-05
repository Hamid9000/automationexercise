package com.aswesomeQA.listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("🔁 Retrying test: " + result.getMethod().getMethodName() + " | Attempt: " + retryCount);
            return true;
        } else {
            System.out.println("❌ Final failure for test: " + result.getMethod().getMethodName());
            return false;
        }
    }

}
