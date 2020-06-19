package com.credify;

import com.credify.base.Account;
import com.credify.base.Loan;
import com.credify.base.TestUtilities;
import com.credify.pages.*;
import org.testng.annotations.Test;

import java.util.Random;

public class WebTests extends TestUtilities {

    @Test
    public void firstTest() {
        log.info("Starting the Test");

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Enter loan amount as 2,000
        welcomePage.typeAmount("2000");

        // select any purpose randomly
        Random rn = new Random();
        welcomePage.selectPurpose(rn.nextInt(6) + 1);

        // Click "Check your Rate"
        BasicInfoPage basicInfoPage = welcomePage.clickCheckRate();

        //In the "Let's get started with some basic information" page, enter first name
        basicInfoPage.enterRandomFirstName();

        //b.Enter last name
        basicInfoPage.enterRandomLastName();

        //c.Enter home address
        basicInfoPage.enterHomeAddress("1");

        //d.Enter DOB
        basicInfoPage.enterDOB("01012000");
        IncomePage incomePage = basicInfoPage.ClickContinue();

        //Annual Income: More than $120,000
        incomePage.enterIndividualIncome("130000");

        //Additional Income : More than $5,000
        incomePage.enterAdditionalIncome("5000");
        CreateAccountPage createAccountPage = incomePage.clickContinue();

        //Create account
        Account account = new Account();
        account.setEmail(TestUtilities.createEmail());
        createAccountPage.enterEmail(account.getEmail());
        account.setPwd(TestUtilities.createPassword());
        createAccountPage.enterPassword(account.getPwd());
        createAccountPage.clickCheckBox();
        OfferPage offerPage = createAccountPage.clickCheckRateBtn();

        //Store Loan details
        Loan loan = offerPage.storeLoanData();
        offerPage.signOut();

        //login
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.openPage();

        loginPage.enterEmail(account.getEmail());
        loginPage.enterPassword(account.getPwd());
        OfferPage myOfferPage=loginPage.clickSignIn();

        myOfferPage.validateLoanData(loan);




    }

}
