package com.credify.pages;

import com.credify.base.TestUtilities;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Properties;

public class WelcomePage extends BasePageObject{
    private By dropdown = By.xpath("//*[@id=\"root\"]/div/main/div/div/div/div/div[2]/div[2]/form/div/div/div[2]/div/select");
    private By desiredAmount = By.name("desiredAmount");
    private By checkRateBtn = By.xpath("//button[@type='submit']");




    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Open WelcomePage with it's url */
    public void openPage() {
        Properties testProperties= TestUtilities.loadTestProperties();
        String baseUrl =testProperties.getProperty("baseUrl");
        log.info("Opening page: " + baseUrl);
        openUrl(baseUrl);
        log.info("Page opened!");
    }
    public void typeAmount(String amount){
        log.info("Entering amount: " + amount);
        type(amount, desiredAmount);

    }
    /** This method selects given option from dropdown */
    public void selectPurpose(int i) {
        log.info("Selecting option " + i + " from dropdown");
        WebElement dropdownElement = find(dropdown);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(i);
    }

    public BasicInfoPage clickCheckRate(){
        log.info("Clicking on check your rate button");
        click(checkRateBtn);
        return new BasicInfoPage(driver, log);


    }


}
