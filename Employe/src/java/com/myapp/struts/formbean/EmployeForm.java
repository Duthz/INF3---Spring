package com.myapp.struts.formbean;

import bean.Employe;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

public class EmployeForm extends ActionForm {

  protected String username;
  protected String password;
  protected String name;
  protected String phone;
  protected String email;
  protected String depid;
  protected String roleid;

    public EmployeForm() {
    }

  
  
  
  public EmployeForm(Employe e) {
      this.username = e.getUsername();
      this.depid = String.valueOf(e.getDepid());
      this.email = e.getEmail();
      this.name = e.getName();
      this.password = e.getPassword();
      this.phone = e.getPhone();
      this.roleid = String.valueOf(e.getRoleid());
  }
  
  public void setUsername(String username) {

    this.username = username;
  }

  public String getUsername() {

    return username;
  }

  public void setPassword(String password) {

    this.password = password;
  }

  public String getPassword() {

    return password;
  }

  public void setName(String name) {

    this.name = name;
  }

  public String getName() {

    return name;
  }

  public void setPhone(String phone) {

    this.phone = phone;
  }

  public String getPhone() {

    return phone;
  }

  public void setEmail(String email) {

    this.email = email;
  }

  public String getEmail() {

    return email;
  }

  public void setDepid(String depid) {

    this.depid = depid;
  }

  public String getDepid() {

    return depid;
  }

  public void setRoleid(String roleid) {

    this.roleid = roleid;
  }

  public String getRoleid() {

    return roleid;
  }

  // Cette methode est appelee par chaque requete. Elle reinitialise les
  // attributs du formulaire avant de copier les donnees de la nouvelle requete.
  public void reset(ActionMapping mapping, HttpServletRequest request) {

    this.username = "";
    this.password = "";
    this.name = "";
    this.phone = "";
    this.email = "";
    this.depid = "1";
    this.roleid = "1";
  }

  public ActionErrors validate(ActionMapping mapping,
    HttpServletRequest request) {

    ActionErrors errors = new ActionErrors();

    // Teste si l'utilisateur est connu
      HttpSession session = request.getSession();
      if ( session.getAttribute("USER") == null ) {

        // retourner null force l'action a traiter l'erreur de login
        return null;
      }
    

    if ( (roleid == null ) || (roleid.length() == 0) ) {

      errors.add("roleid", new ActionMessage("errors.roleid.required"));
    }
    if ( (depid == null ) || (depid.length() == 0) ) {

      errors.add("depid", new ActionMessage("errors.depid.required"));
    }
    if ( (email == null ) || (email.length() == 0) ) {

      errors.add("email", new ActionMessage("errors.email.required"));
    }
    if ( (phone == null ) || (phone.length() == 0) ) {

      errors.add("phone", new ActionMessage("errors.phone.required"));
    }
    if ( (name == null ) || (name.length() == 0) ) {

      errors.add("name", new ActionMessage("errors.name.required"));
    }
    if ( (password == null ) || (password.length() == 0) ) {

      errors.add("password", new ActionMessage("errors.password.required"));
    }
    if ( (username == null ) || (username.length() == 0) ) {

      errors.add("username", new ActionMessage("errors.username.required"));
    }
    return errors;
  }
  
  
  public Employe formToEmploye() {
      Employe e = new Employe();
      
      e.setDepid(Integer.parseInt(depid));
      e.setEmail(this.email);
      e.setName(this.name);
      e.setPassword(password);
      e.setPhone(phone);
      e.setUsername(username);
      e.setRoleid(Integer.parseInt(roleid));
      
      return e;
  }
}
