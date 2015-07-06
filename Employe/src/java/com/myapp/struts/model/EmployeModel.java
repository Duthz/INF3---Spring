/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.model;

import bean.Employe;
import ejb.ModelException;
import ejb.EmployeModelRemote;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author Arles Mathieu
 */
public class EmployeModel implements IEmployeModel {

    EmployeModelRemote employeModel = lookupEmployeModelRemote();

    private DataSource datasource;

    public DataSource getDatasource() {
        return this.datasource;
    }

    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
        employeModel.setDatasource(datasource);
    }

    private Connection getConnection() throws SQLException {
        return this.getDatasource().getConnection();
    }

    @Override
    public void deleteEmploye(String username) throws ModelException {
        employeModel.deleteEmployee(username);
    }

    @Override
    public void insertEmploye(Employe e) throws ModelException {
        employeModel.insertEmployee(e);
    }

    @Override
    public void updateUser(Employe e) throws ModelException {
        employeModel.updateEmployee(e);
    }

    @Override
    public Employe getEmployeByUserName(String username) throws ModelException {
        return employeModel.getEmployeByUserName(username);
    }

    @Override
    public String getUser(String username, String password) throws ModelException {
        return employeModel.getUser(username, password);
    }

    @Override
    public List getEmployes() throws ModelException {
        return employeModel.getEmployes();
    }

    private EmployeModelRemote lookupEmployeModelRemote() {
        try {
            Context c = new InitialContext();
            return (EmployeModelRemote) c.lookup("java:global/EJBEmployee/EmployeModel!ejb.EmployeModelRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
