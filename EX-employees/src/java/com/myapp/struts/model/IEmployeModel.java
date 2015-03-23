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
     */
    public void insertEmploye(ActionForm form);
    
    
    /**
     * Supprime un employé
     * @param username Nom de l'employé à supprimer
     */
    public void deleteEmploye(String username);
    
    
    /**
     * Mets à jours les données d'un user
     * @param form 
     */
      public void updateUser(ActionForm form);
      
      
      /**
       * 
       * @param username
       * @return
       * @throws Exception 
       */
        public ActionForm buildEmployeForm(String username) throws Exception;


        /**
         * 
         * @param username
         * @param password
         * @return 
         */
        public  String getUser(String username, String password);
        
        
        /**
         * 
         * @return 
         */
        public  ArrayList getEmployes();

}
