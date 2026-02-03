package com.aswesomeQA.pages;
import static com.aswesomeQA.driver.DriverManager.getDriver;
import com.aswesomeQA.base.CommonToAllPage;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;


public class SignUpPage extends CommonToAllPage {

    //----------------------- LOCATERS -------------------------
    private By name = By.xpath("//input[@name='name']");
    private By emailAddress = By.xpath("//div[@class='signup-form' ]//input[@name='email']");
    private By signUp = By.xpath("//button[text()='Signup']");
    private By actualMessage = By.xpath("//h2[normalize-space()='Enter Account Information']");

    // ---------------------- Navigation Page --------------------

    public void  goToSignUpPage() {
        getDriver().get(PropertiesReader.readKeys("baseUrl") + PropertiesReader.readKeys("signUpUrl"));
    }

    //
    public void enterUsername(String user) {
        waitForVisible(name).clear();
        waitForVisible(name).sendKeys(user);
    }


    public  void enterEmailAddress(String emailAddress ){
        waitForVisible(this.emailAddress).clear();
        waitForVisible(this.emailAddress).sendKeys(emailAddress);
    }

    public void clickSignUp(){
        waitForClickable(signUp).click();
    }

    public  String getActualMessage (){
        return waitForVisible(actualMessage).getText();
    }


}
