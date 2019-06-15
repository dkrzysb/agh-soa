<%@ page import="pl.agh.kis.soa.guestbook.model.Comment" %>
<%@ page import="java.util.Vector" %><%--
  Created by IntelliJ IDEA.
  User: rafal
  Date: 3/17/2019
  Time: 11:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guestbook</title>
</head>
<body>
    <h1>Please submit your feedback</h1>
    <form action="addcomment" method="post">
        Your name: <input type="text" name="name" /><br />
        Your email: <input type="email" name="email" /><br />
        Comment: <input type="text" name="comment" /><br />
        <button type="submit">Send Feedback</button>
    </form>
    <hr />
    <%
        Vector<Comment> comments = (Vector<Comment>)application.getAttribute("comments");
        if(comments != null) {
            for(Comment comment : comments) {
                out.println("<h3>" + comment.getName() + "(" + comment.getEmail() + "):</h3><br />");
                out.println(comment.getComment() + "<br /><br />");
            }
        }
    %>
</body>
</html>
