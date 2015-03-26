/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.model;

import com.myapp.struts.bean.Employe;
import java.util.List;
import org.apache.struts.action.ActionForm;

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
     * @throws com.myapp.struts.model.ModelException
     */
    public void deleteEmploye(String username) throws ModelException;
    
    
    /**
     * Mets à jours les données d'un user
     * @param e employé à mettre à jours
     * @throws com.myapp.struts.model.ModelException 
     */
      public void updateUser(Employe e) throws ModelException;
      
      
      /**
       * 
       * @param username
       * @return 
     * @throws com.myapp.struts.model.ModelException 
       */
        public Employe getEmployeByUserName(String username)  throws ModelException;


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
        public  List getEmployes() throws ModelException;

}
