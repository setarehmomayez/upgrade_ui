package com.credify.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IncomePage extends BasePageObject {
    private By indvIncome = By.name("borrowerIncome");
    private By addtIncome = By.name("borrowerAdditionalIncome");
    private By continueBtn = By.xpath("//button[@type='submit']");


    public IncomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void enterIndividualIncome(String income) {
        log.info("Entering individual income: " + income);
        type(income, indvIncome);
    }

    public void enterAdditionalIncome(String income) {
        log.info("Entering Additional income: " + income);
        type(income, addtIncome);
    }

    public CreateAccountPage clickContinue() {
        click(By.className("section"));
        click(continueBtn);
        return new CreateAccountPage(driver, log);
    }

}
