<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>




    <html:errors />

    <html:form action="Login.do" > 
      <table width="45%" border="0">
        <tr>
          <td><bean:message key="app.username" />:</td>
          <td><html:text property="username" /></td>
        </tr>
        <tr>
          <td><bean:message key="app.password" />:</td>
          <td><html:password property="password" /></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html:submit /></td>
        </tr>
      </table>
    </html:form> 

