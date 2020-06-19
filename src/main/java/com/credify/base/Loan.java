package com.credify.base;

import lombok.Data;

@Data
public class Loan {

    private String loanAmount;
    private String monthlyPayment;
    private String term;
    private String interestRate;
    private String arp;

}
