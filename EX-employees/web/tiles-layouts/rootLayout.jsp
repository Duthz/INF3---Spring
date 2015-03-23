<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!DOCTYPE html>
<html>
  <head>
    <title><bean:message key="app.title" /></title>
  </head>

  <body>
      <tiles:insert attribute="top_banner"/>
      <tiles:insert attribute="header"/>

      <tiles:insert attribute="article"/>
      <tiles:insert attribute="footer"/>

  </body>
</html>
