/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.model;

import bean.Employe;
import java.util.List;

/**
 *
 * @author Arles Mathieu
 */
public interface IEmployeModel extends Model{
    
    /**
     * Insère un employé dans la base de données
     * @param e employé à insérer
     * @throws com.myapp.struts.model.ModelException
     */
    public void insertEmploye(Employe e) throws ModelException;
    
    
    /**
     * Supprime un employé
     * @param username Nom de l'employé à supprimer
     */
    public void deleteEmploye(String username) throws ModelException;
    
    
    /**
     * Mets à jours les données d'un user
     * @param e employé à mettre à jours 
     */
      public void updateUser(Employe e) throws ModelException;
      
      
      /**
       * 
       * @param username
       * @return 
       */
        public Employe getEmployeByUserName(String username)  throws ModelException;


        /**
         * 
         * @param username
         * @param password
         * @return 
         */
        public  String getUser(String username, String password) throws ModelException;
        
        
        /**
         * 
         * @return 
         */
        public  List getEmployes() throws ModelException;

}
