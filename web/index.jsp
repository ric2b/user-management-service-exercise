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

  <br>

  <form action="getClient">
    Get client (NIF):<br>
    <input type="text" name="NIF"><br>
    <input type="submit" value="Submit">
  </form>

  <br>

  <form action="searchClients">
    Search by name:<br>
    <input type="text" name="name"><br>
    <input type="submit" value="Submit">
  </form>

  </body>
</html>
