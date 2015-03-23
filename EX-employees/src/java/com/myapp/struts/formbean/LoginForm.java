package com.myapp.struts.formbean;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm {

  private String password = null;

  private String username = null;

  // Accesseurs pour le mot de passe
  public String getPassword() {

    return (this.password);
  }

  public void setPassword(String password) {

    this.password = password;
  }

  // Accesseurs pour le nom d'utilisateur
  public String getUsername() {

    return (this.username);
  }

  public void setUsername(String username) {

    this.username = username;
  }

  // Cette methode est appelee a chaque requete. Elle r�initialise le formulaire
  // avant d'enregistrer les valeurs correspondant a la nouvelle requete.
  public void reset(ActionMapping mapping, HttpServletRequest request) {

    this.password = null;
    this.username = null;
  }

  public ActionErrors validate(ActionMapping mapping,
    HttpServletRequest request) {

    ActionErrors errors = new ActionErrors();

    if ( (username == null ) || (username.length() == 0) ) {

      errors.add("username", new ActionMessage("errors.username.required"));
    }
    if ( (password == null ) || (password.length() == 0) ) {

      errors.add("password", new ActionMessage("errors.password.required"));
    }
    return errors;
  }
}
