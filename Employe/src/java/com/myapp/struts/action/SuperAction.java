/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.model.Model;
import org.apache.struts.action.Action;

/**
 *
 * @author Arles Mathieu
 */
public class SuperAction extends Action {

    private Model model = null;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    
}
