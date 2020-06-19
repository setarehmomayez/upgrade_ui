package com.credify.pages;

import com.credify.base.Loan;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class OfferPage extends BasePageObject {
    SoftAssert softAssert = new SoftAssert();

    private By loadAmount = By.cssSelector("[data-auto='userLoanAmount']");
    private By monthlyPayment = By.cssSelector("[data-auto='defaultMonthlyPayment']");
    private By term = By.cssSelector("[data-auto='defaultLoanTerm']");
    private By interestRate = By.cssSelector("[data-auto='defaultLoanInterestRate']");
    private By arp = By.cssSelector("[data-auto='defaultMoreInfoAPR']");
    private By menu = By.xpath("//div[@class='header-nav']/label[1]");
    private By signOutBtn = By.linkText("Sign Out");

    public OfferPage(WebDriver driver, Logger log) {
        super(driver, log);
        waitForVisibilityOf(loadAmount);
    }

    public void signOut() {
        log.info("Signing out");
        hoverOverElement(menu.findElement(driver));
        click(menu);
        click(signOutBtn);
    }

    public Loan storeLoanData() {
        Loan loan = new Loan();
        loan.setLoanAmount(driver.findElement(loadAmount).getText());
        loan.setInterestRate(driver.findElement(interestRate).getText());
        loan.setMonthlyPayment(driver.findElement(monthlyPayment).getText());
        loan.setTerm(driver.findElement(term).getText());
        loan.setArp(driver.findElement(arp).getText());
        return loan;
    }

    public void validateLoanData(Loan loan) {
        softAssert.assertEquals(loan.getLoanAmount(), driver.findElement(loadAmount).getText());
        softAssert.assertEquals(loan.getInterestRate(), driver.findElement(interestRate).getText());
        softAssert.assertEquals(loan.getMonthlyPayment(), driver.findElement(monthlyPayment).getText());
        softAssert.assertEquals(loan.getTerm(), driver.findElement(term).getText());
        softAssert.assertEquals(loan.getArp(), driver.findElement(arp).getText());
        softAssert.assertAll();

    }

}
