/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.model.EmployeModel;
import com.myapp.struts.model.IModel;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Arles Mathieu
 */
public class SuperAction extends DispatchAction{
    
    public static final String MODEL_EMPLOYE = "modelEmploye";
    
    /**
     * Retourne le model
     * @param nom Nom du model
     * @return 
     */
    public IModel getModel(String nom) {
        if(nom.equals(MODEL_EMPLOYE))
            return new EmployeModel();
        return null;
    }
}
