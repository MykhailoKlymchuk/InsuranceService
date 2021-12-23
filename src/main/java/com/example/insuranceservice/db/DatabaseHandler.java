package com.example.insuranceservice.db;

import com.example.insuranceservice.Date.Client;
import com.example.insuranceservice.Date.InsuranceObligation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void addNewClients(Client client) {
        String insert = "insert into " + Const.CLIENT_TABLE + "(" +
                Const.FIRST_NAME + "," + Const.LAST_NAME + "," + Const.DATE_BIRTH + "," +
                Const.EMAIL + "," + Const.PHONE_NUMBER + "," + Const.BANK_NUMBER + "," +
                Const.BALANCE + ")" + "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setObject(1, client.getFirstName());
            prSt.setObject(2, client.getLastName());
            prSt.setObject(3, client.getDateBirth());
            prSt.setObject(4, client.getEmail());
            prSt.setObject(5, client.getPhoneNumber());
            prSt.setObject(6, client.getBankAccountNumber());
            prSt.setObject(7, "0");

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addNewObligation(InsuranceObligation obl) {
        String insert = "insert into " + Const.OBLIGATION_TABLE + "(" +
                Const.CLIENT_ID_OBL + "," + Const.SUBJECT_INSURANCE + "," + Const.RISK_LEVEL + "," +
                Const.COST + "," + Const.CREATION_DATE + "," + Const.TERMINATION_DATE + ")" +
                "values(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setObject(1, obl.getIdClient());
            prSt.setObject(2, obl.getSubjectInsurance());
            prSt.setObject(3, obl.getLevelOfRisk());
            prSt.setObject(4, obl.getCost());
            prSt.setObject(5, obl.getCreationDate());
            prSt.setObject(6, obl.getDateOfTermination());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<InsuranceObligation> getAllObl() throws SQLException, ClassNotFoundException {
        ObservableList<InsuranceObligation> obligationData = FXCollections.observableArrayList();
        Statement prSt = getDbConnection().createStatement();
        ResultSet rs = prSt.executeQuery("select * from " + Const.OBLIGATION_TABLE + " order by " + Const.RISK_LEVEL + " desc");
        while (rs.next()) {
            obligationData.add(new InsuranceObligation(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getDate(5),
                    rs.getDate(6),
                    rs.getInt(7))
            );
        }
        return obligationData;
    }

    public ObservableList<Client> getAllClients() throws SQLException, ClassNotFoundException {
        ObservableList<Client> Clients = FXCollections.observableArrayList();
        Statement prSt = getDbConnection().createStatement();
        ResultSet rs = prSt.executeQuery("select * from " + Const.CLIENT_TABLE);
        while (rs.next()) {
            Clients.add(new Client(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getString(7),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getDouble(8))
            );
        }
        return Clients;
    }

    public double getCost() throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        double cost = 0;
        ResultSet rs = prSt.executeQuery("select " + Const.COST + " from " + Const.OBLIGATION_TABLE);
        while (rs.next()) cost += rs.getDouble(1);
        return cost;
    }

    public double getCostRisks() throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        double CostRisks = 0;
        ResultSet rs = prSt.executeQuery("select " + Const.COST + ", " + Const.RISK_LEVEL + " from " + Const.OBLIGATION_TABLE);
        while (rs.next()) CostRisks += (rs.getDouble(1) * rs.getDouble(2));
        return CostRisks;
    }

    public ObservableList<InsuranceObligation> getSearchObl(String Lower, String Upper, String param) throws SQLException, ClassNotFoundException {
        ObservableList<InsuranceObligation> obligationData = FXCollections.observableArrayList();
        Statement prSt = getDbConnection().createStatement();
        ResultSet rs = prSt.executeQuery("select * from " + Const.OBLIGATION_TABLE + " where " + param + " between " + Lower + " and " + Upper);
        while (rs.next()) {
            obligationData.add(new InsuranceObligation(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getDate(5),
                    rs.getDate(6),
                    rs.getInt(7))
            );
        }
        return obligationData;
    }

    public void deleteObl(String id) throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        prSt.execute("delete from insuranceobligations where id = " + id);
    }

    public void deleteClient(String id) throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        prSt.execute("delete from clients where id = " + id);
    }

    public void pay(String id) throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        ResultSet rs = prSt.executeQuery("select (cost*(1+levelOfRisk)) from insuranceobligations where id = " + id);
        double cost = 0, balance = 0, newBalance;
        if (rs.next()) cost = rs.getDouble(1);
        rs = prSt.executeQuery("select balanceInsurance from clients where id = (select clientId from insuranceobligations where id = " + id + ")");
        if (rs.next()) balance = rs.getDouble(1);
        newBalance = cost + balance;
        prSt.execute("update clients set balanceInsurance = " + newBalance + " where id = (select clientId from insuranceobligations where id = " + id + ")");
    }

    public double getNumClient() throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        double num = 0;
        ResultSet rs = prSt.executeQuery("select count(*) from " + Const.CLIENT_TABLE);
        if (rs.next()) num = rs.getDouble(1);
        return num;
    }

    public double getNumObl() throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        double num = 0;
        ResultSet rs = prSt.executeQuery("select count(*) from " + Const.OBLIGATION_TABLE);
        if (rs.next()) num = rs.getDouble(1);
        return num;
    }

    public double getNumUser() throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        double num = 0;
        ResultSet rs = prSt.executeQuery("select count(*) from " + Const.USERS_TABLE);
        if (rs.next()) num = rs.getDouble(1);
        return num;
    }

    public boolean checkUser(String login,String password) throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        ResultSet rs = prSt.executeQuery("select " + Const.LOGIN + ", " + Const.PASSWORD + " from " + Const.USERS_TABLE);
        while (rs.next())
            if (rs.getString(1).equals(login) &&
                    rs.getString(2).equals(password))
                return true;
        return false;
    }

    public boolean checkClient(String id) throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        ResultSet rs = prSt.executeQuery("select " + Const.ID_CLIENT + " from "+ Const.CLIENT_TABLE);
        while (rs.next())
            if (rs.getString(1).equals(id))
                return true;
        return false;
    }

    public boolean checkClientWithObl(String id) throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        ResultSet rs = prSt.executeQuery("select count(*) from "+ Const.OBLIGATION_TABLE+" where " + Const.CLIENT_ID_OBL +" = " + id);
        if (rs.next())
            if (rs.getInt(1)>0)
                return true;
        return false;
    }

    public boolean checkObl(String id) throws SQLException, ClassNotFoundException {
        Statement prSt = getDbConnection().createStatement();
        ResultSet rs = prSt.executeQuery("select " + Const.ID_OBL + " from "+ Const.OBLIGATION_TABLE);
        while (rs.next())
            if (rs.getString(1).equals(id))
                return true;
        return false;
    }
}