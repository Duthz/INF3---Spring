/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.model.IModel;
import org.apache.struts.action.Action;

/**
 *
 * @author Arles Mathieu
 */
public class SuperAction extends Action {

    private IModel iModel = null;

    public void setIModel(IModel m) {
        this.iModel = m;
    }

    public IModel getIModel() {
        return this.iModel;

    }
}
