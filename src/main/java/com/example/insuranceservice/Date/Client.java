package com.example.insuranceservice.Date;

import java.util.Date;

public class Client{
    private int id;
    private String firstName;
    private String lastName;
    private Date dateBirth;
    private String bankAccountNumber;
    private String email;
    private String phoneNumber;
    private double balanceInsurance;

    public Client() {
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Client(int id, String firstName, String lastName, Date dateBirth, String bankAccountNumber, String email, String phoneNumber, double balanceInsurance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.bankAccountNumber = bankAccountNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balanceInsurance = balanceInsurance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalanceInsurance() {
        return balanceInsurance;
    }

    public void setBalanceInsurance(double balanceInsurance) {
        this.balanceInsurance = balanceInsurance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
