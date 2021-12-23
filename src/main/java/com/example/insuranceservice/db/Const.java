package com.example.insuranceservice.db;

public class Const {
    //client
    public static final String CLIENT_TABLE = "clients";
    public static final String ID_CLIENT = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String DATE_BIRTH = "dateBirth";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String BANK_NUMBER = "bankAccountNumber";
    public static final String BALANCE = "balanceInsurance";

    //Obligation
    public static final String OBLIGATION_TABLE = "insuranceobligations";
    public static final String ID_OBL = "id";
    public static final String SUBJECT_INSURANCE = "subjectInsurance";
    public static final String RISK_LEVEL = "levelOfRisk";
    public static final String COST = "cost";
    public static final String CREATION_DATE = "creationDate";
    public static final String TERMINATION_DATE = "dateOfTermination";
    public static final String CLIENT_ID_OBL = "clientId";

    //users
    public static final String USERS_TABLE= "users";
    public static final String LOGIN= "login";
    public static final String PASSWORD = "password";
}
