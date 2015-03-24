/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.model;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author Arles Mathieu
 */
public interface IEmployeModel extends IModel{
    
    /**
     * Insère un employé dans la base de données
     * @param form
     * @throws com.myapp.struts.model.ModelException
     */
    public void insertEmploye(ActionForm form) throws ModelException;
    
    
    /**
     * Supprime un employé
     * @param username Nom de l'employé à supprimer
     * @throws com.myapp.struts.model.ModelException
     */
    public void deleteEmploye(String username) throws ModelException;
    
    
    /**
     * Mets à jours les données d'un user
     * @param form 
     * @throws com.myapp.struts.model.ModelException 
     */
      public void updateUser(ActionForm form) throws ModelException;
      
      
      /**
       * 
       * @param username
       * @return 
     * @throws com.myapp.struts.model.ModelException 
       */
        public ActionForm buildEmployeForm(String username)  throws ModelException;


        /**
         * 
         * @param username
         * @param password
         * @return 
     * @throws com.myapp.struts.model.ModelException 
         */
        public  String getUser(String username, String password) throws ModelException;
        
        
        /**
         * 
         * @return 
     * @throws com.myapp.struts.model.ModelException 
         */
        public  ArrayList getEmployes() throws ModelException;

}
