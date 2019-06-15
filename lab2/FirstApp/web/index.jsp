<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>FirstApp</title>
  </head>
  <body>
    <h1>Hello World!</h1>
    <p>JSP page</p>
    <%
      Date currentDate = new Date();
      out.print("<h2>" + currentDate.toString() + "</h2>");
    %>
  </body>
</html>
