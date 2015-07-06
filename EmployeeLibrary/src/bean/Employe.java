package bean;

import java.io.Serializable;

public class Employe implements Serializable{

    protected String username;
    protected String name;
    protected String department;
    protected String rolename;
    protected String phone;
    protected String email;
    protected Integer depid;
    protected Integer roleid;
    protected String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getUsername() {

        return username;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setDepartment(String department) {

        this.department = department;
    }

    public String getDepartment() {

        return this.department;
    }

    public void setRolename(String rolename) {

        this.rolename = rolename;
    }

    public String getRolename() {

        return rolename;
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

    public void setDepid(Integer depid) {

        this.depid = depid;
    }

    public Integer getDepid() {

        return depid;
    }

    public void setRoleid(Integer roleid) {

        this.roleid = roleid;
    }

    public Integer getRoleid() {

        return roleid;
    }
}
