<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<logic:notPresent name="USER">
  <logic:forward name="login" />
</logic:notPresent>

    <html:errors />

    <table width="750"
      border="0" cellspacing="0" cellpadding="0">
      <tr align="left">
        <th><bean:message key="app.username" /></th>
        <th><bean:message key="app.name" /></th>
        <th><bean:message key="app.phone" /></th>
        <th><bean:message key="app.email" /></th>
        <th><bean:message key="app.department" /></th>
        <th><bean:message key="app.role" /></th>
      </tr>
      <!-- iteration sur les résultats de la requête -->
      <logic:iterate id="employe" name="employes">
  <tr align="left">
    <td>
            <bean:write name="employe" property="username" />
    </td>
    <td>
            <bean:write name="employe" property="name" />
    </td>
    <td>
            <bean:write name="employe" property="phone" />
    </td>
    <td>
            <bean:write name="employe" property="email" />
    </td>
    <td>
            <bean:write name="employe" property="department" />
    </td>
    <td>
            <bean:write name="employe" property="rolename" />
    </td>
    <td>
            <a href="Edit.do?username=<bean:write name="employe"
        property="username" />">Modifier</a>
      <a href="Delete.do?username=<bean:write name="employe"
        property="username" />">Supprimer</a>
    </td>
  </tr>
      </logic:iterate>
      <tr>
        <td colspan="7">
    <hr>
  </td>
      </tr>
    </table>
    <font size="-1" face="arial">
      <a href="addemploye.do">Ajouter un employé</a>
    </font>
