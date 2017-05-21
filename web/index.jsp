<%@ page import="HelloWorld.HelloWorld" %><%--
  Created by IntelliJ IDEA.
  User: ricardo
  Date: 5/18/17
  Time: 5:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Simple jsp page</title>
  </head>
  <body>
  <h3 class="message"><%=HelloWorld.getMessage()%></h3>

  <a href="listClients">List clients</a>

  </body>
</html>
