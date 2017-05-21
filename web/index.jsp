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

  <form action="addClient" method="post">
    <h4>Add a client:</h4><br>
    Name: <input type="text" name="name">
    NIF: <input type="text" name="NIF">
    address: <input type="text" name="address">
    phone number: <input type="text" name="phone_number">
    <input type="submit" value="Submit">
  </form>

  <br>

  <form action="removeClient" method="post">
    <h4>Remove client (NIF):</h4><br>
    <input type="text" name="NIF"><br>
    <input type="submit" value="Submit">
  </form>

  <br>

  <a href="listClients"><h4>List clients</h4></a>

  <br>

  <form action="getClient" method="get">
    <h4>Get client (NIF):</h4><br>
    <input type="text" name="NIF"><br>
    <input type="submit" value="Submit">
  </form>

  <br>

  <form action="searchClients" method="get">
    <h4>Search by name:</h4><br>
    <input type="text" name="name"><br>
    <input type="submit" value="Submit">
  </form>

  </body>
</html>
