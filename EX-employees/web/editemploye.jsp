<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>



    <html:errors />

    <html:form action="/EditEmploye" > 
      <table width="500" border="0">
        <tr>
          <td><bean:message key="app.username" />:</td>
          <td><html:text property="username" /></td>
          <td><bean:message key="app.password" />:</td>
          <td><html:password property="password" /></td>
        </tr>
        <tr>
          <td><bean:message key="app.name" />:</td>
          <td><html:text property="name" /></td>
          <td><bean:message key="app.phone" />:</td>
          <td><html:text property="phone" /></td>
        </tr>
        <tr>
          <td><bean:message key="app.email" />:</td>
          <td><html:text property="email" /></td>
          <td><bean:message key="app.department" />:</td>
    <td>

      <html:select property="depid" size="1">
              <html:option value="1">
          <bean:message key="app.administration" />
        </html:option>
              <html:option value="2">
          <bean:message key="app.computer" />
        </html:option>
              <html:option value="3">
          <bean:message key="app.sales" />
        </html:option>
              <html:option value="4">
          <bean:message key="app.production" />
        </html:option>
            </html:select>
          
          </td>
        </tr>
        <tr>
          <td><bean:message key="app.role" />:</td>
    <td>

      <html:select property="roleid" size="1">
              <html:option value="1">
          <bean:message key="app.manager" />
        </html:option>
              <html:option value="2">
          <bean:message key="app.employe" />
        </html:option>
            </html:select>
          
          </td>
          <td colspan="2" align="center">
      <html:submit />
      <html:cancel />
    </td>
        </tr>
      </table>
    </html:form> 

