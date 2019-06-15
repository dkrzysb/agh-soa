<%@ page import="java.util.Vector" %>
<%@ page import="pl.agh.kis.soa.guestbook.model.UserData" %><%--
  Created by IntelliJ IDEA.
  User: rafal
  Date: 3/17/2019
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Sign in</title>
  </head>
  <body>
    <%
      Vector<UserData> usersData = new Vector<>();
      usersData.add(new UserData("kowalski", "test123", "Jan", "Kowalski"));
      usersData.add(new UserData("mroczek", "qweasd123", "Mariusz", "Mroczek"));
      application.setAttribute("UsersData", usersData);
    %>
    <form action="book" method="post">
      Login: <input type="text" name="login" /><br />
      Password: <input type="password" name="password" /><br />
      <button type="submit">Sign in</button>
    </form>
  </body>
</html>
