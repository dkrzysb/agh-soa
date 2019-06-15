package pl.agh.kis.soa.guestbook.controller;

import pl.agh.kis.soa.guestbook.model.UserData;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Vector<UserData> usersData = (Vector<UserData>)req.getServletContext().getAttribute("UsersData");
        boolean validationSuccess = true;
        String message = "";

        if(login != null && password != null) {
            if (login.isEmpty()) {
                message = "You have to provide login";
                validationSuccess = false;
            }
            if (validationSuccess && password.isEmpty()) {
                message = "You have to provide password";
                validationSuccess = false;
            }

            UserData userData = usersData.stream().filter(ud -> ud.getLogin().equals(login)).findAny().orElse(null);
            if (validationSuccess && (userData == null || !userData.getPassword().equals(password))) {
                message = "Incorrect login or password";
                validationSuccess = false;
            }

            if (!validationSuccess) {
                try (PrintWriter out = resp.getWriter()) {
                    resp.setContentType("text/html");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
                    out.println("<h3>" + message + "</h3>");
                    requestDispatcher.include(req, resp);
                    return;
                }
            }
        }
        if(login == null || password == null) {
            String loggedUserLogin = (String)((HttpServletRequest)req).getSession().getAttribute("loggedUser");
            if(loggedUserLogin != null) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("guestbook.jsp");
                requestDispatcher.forward(req, resp);
                return;
            }
            else {
                ((HttpServletResponse) resp).sendRedirect("index.jsp");
                return;
            }
        }
        ((HttpServletRequest)req).getSession().setAttribute("loggedUser", login);
        chain.doFilter(req, resp);
    }
}
