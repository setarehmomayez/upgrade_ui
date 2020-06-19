package com.credify.pages;

import com.credify.base.BaseTest;
import com.credify.base.TestUtilities;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginPage extends BasePageObject {
    private By emailElement = By.name("username");
    private By passwordElement = By.name("password");
    private By signInBtn = By.xpath("//button[@type='submit']");


    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void enterEmail(String email) {
        log.info("Entering Email");
        type(email, emailElement);
    }

    public void enterPassword(String pwd) {
        log.info("Entering Password");
        type(pwd, passwordElement);
    }

    public OfferPage clickSignIn(){
        click(signInBtn);
        return new OfferPage(driver,log);
    }

    /** Open WelcomePage with it's url */
    public void openPage() {
        Properties testProperties= TestUtilities.loadTestProperties();
        String loginPageUrl =testProperties.getProperty("loginUrl");
        log.info("Opening page: " + loginPageUrl);
        openUrl(loginPageUrl);
        log.info("Page opened!");
    }


}
