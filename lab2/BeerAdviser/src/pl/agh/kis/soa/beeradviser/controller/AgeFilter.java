package pl.agh.kis.soa.beeradviser.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "AgeFilter")
public class AgeFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        int age = Integer.parseInt(req.getParameter("age"));

        if (age < 18) {
            try(PrintWriter out = resp.getWriter()) {
                resp.setContentType("text/html");
                resp.setCharacterEncoding("windows-1250");
                out.println("<html>");
                out.println("<head><title>Nieuprawniony dostęp</title></head>");
                out.println("<body>");
                out.println("<h1>Masz mniej niż 18 lat. Nie możesz pić piwa.</h1>");
                out.println("</body>");
                out.println("</html>");
                return;
            }
        }
        else
            chain.doFilter(req, resp);
    }
}
