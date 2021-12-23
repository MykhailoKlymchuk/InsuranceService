package com.example.insuranceservice.Date;

import java.util.Date;


public class InsuranceObligation{
    protected int id;
    protected String subjectInsurance;
    protected double levelOfRisk;
    protected double cost;
    protected Date creationDate;
    protected Date dateOfTermination;
    protected int idClient;

    public InsuranceObligation() {
    }

    public InsuranceObligation(int id,
                               String subjectInsurance,
                               double levelOfRisk,
                               double cost,
                               Date creationDate,
                               Date dateOfTermination,
                               int idClient) {
        this.id = id;
        this.subjectInsurance = subjectInsurance;
        this.levelOfRisk = levelOfRisk;
        this.cost = cost;
        this.creationDate = creationDate;
        this.dateOfTermination = dateOfTermination;
        this.idClient = idClient;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectInsurance() {
        return subjectInsurance;
    }

    public void setSubjectInsurance(String subjectInsurance) {
        this.subjectInsurance = subjectInsurance;
    }

    public double getLevelOfRisk() {
        return levelOfRisk;
    }

    public void setLevelOfRisk(double levelOfRisk) {
        this.levelOfRisk = levelOfRisk;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getDateOfTermination() {
        return dateOfTermination;
    }

    public void setDateOfTermination(Date dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

}
