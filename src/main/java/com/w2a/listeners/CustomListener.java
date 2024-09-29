package com.w2a.listeners;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.Page;
import com.w2a.utilities.TestUtil;
import org.testng.*;

public class CustomListener extends Page implements ITestListener, ISuiteListener {

    @Override
    public void onTestStart(ITestResult result) {
        test = rep.startTest(result.getName().toUpperCase());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(LogStatus.PASS, result.getName().toUpperCase()+" PASS");
        rep.endTest(test);
        rep.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.setProperty("org.uncommons.reportng.escape-output","false");
        try {
            TestUtil.captureScreen();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        test.log(LogStatus.FAIL, result.getName().toUpperCase()+ "Failed Case Exception: "+ result.getThrowable());
        test.log(LogStatus.FAIL, test.addScreencast(TestUtil.scrName));

        Reporter.log("Click to see Screenshot");
        Reporter.log("<a target=\"_blank\" href="+TestUtil.scrName+">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href="+TestUtil.scrName+"><img src="+TestUtil.scrName+" height=200 width=200></img></a>");
        rep.endTest(test);
        rep.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(LogStatus.SKIP, result.getName().toUpperCase()+" Skipped the test as the Run mode is NO");
        rep.endTest(test);
        rep.flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished: " + context.getName());
    }

    @Override
    public void onStart(ISuite suite) {
        ISuiteListener.super.onStart(suite);
    }

    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
    }
}

