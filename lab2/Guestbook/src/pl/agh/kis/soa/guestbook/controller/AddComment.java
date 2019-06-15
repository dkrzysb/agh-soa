package pl.agh.kis.soa.guestbook.controller;

import pl.agh.kis.soa.guestbook.model.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

@WebServlet(name = "AddComment")
public class AddComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String comment = request.getParameter("comment");

        Vector<Comment> comments = (Vector<Comment>)getServletContext().getAttribute("comments");
        if(comments == null) {
            comments = new Vector<>();
            getServletContext().setAttribute("comments", comments);
        }
        comments.add(new Comment(name, email, comment));
        response.sendRedirect(request.getHeader("referer"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
