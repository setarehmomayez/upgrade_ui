package com.credify.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class BasicInfoPage extends BasePageObject {
    private By firstName = By.name("borrowerFirstName");
    private By lastName = By.name("borrowerLastName");
    private By homeAddress = By.id("borrowerStreet");
    private By suggestedAddr = By.className("geosuggest__item");
    private By state=By.name("borrowerState");
    private By dateOfBirth = By.name("borrowerDateOfBirth");
    private By continueBtn = By.xpath("//button[@type='submit']");


    public BasicInfoPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void enterFirstName(String name) {
        log.info("Entering First Name: " + name);
        type(name, firstName);
    }

    public void enterLastName(String name) {
        log.info("Entering Last Name: " + name);
        type(name, lastName);
    }

    public void enterRandomFirstName() {
        log.info("Entering First Name");
        type(randomName(), firstName);
    }

    public void enterRandomLastName() {
        log.info("Entering Last Name");
        type(randomName(), lastName);
    }

    public void enterHomeAddress(String addr) {
        log.info("Entering Address: " + addr);
        type(addr, homeAddress);
        waitForVisibilityOf(suggestedAddr);
        pressKey(homeAddress, Keys.ARROW_DOWN);
        pressKey(homeAddress, Keys.ENTER);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(state).getAttribute("value").length() != 0;
            }
        });
    }

    public void enterDOB(String date) {
        log.info("Entering Date of birth: " + date);
        type(date, dateOfBirth);
    }

    public IncomePage ClickContinue() {
        click(continueBtn);
        return new IncomePage(driver, log);
    }

    private String randomName() {
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        final int N = 10;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

}
