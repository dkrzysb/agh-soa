package pl.agh.kis.soa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;

@WebServlet(name = "PostNumbers")
public class PostNumbers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("windows-1250");

        Enumeration<String> parameterNames = request.getParameterNames();
        ArrayList<Integer> numbers = new ArrayList<>();

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<header><title>PostNumbers</title></header>");
            out.println("<body>");
            while (parameterNames.hasMoreElements()) {
                Object obj = parameterNames.nextElement();
                String parameterName = (String) obj;
                String value = request.getParameter(parameterName);
                int number;
                try {
                    number = Integer.parseInt(value);
                } catch (NumberFormatException ex) {
                    out.println("<h1>Data in request didn't contain only valid numbers</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }

                numbers.add(number);
            }

            Collections.sort(numbers);
            out.println("<h1>Sorted numbers:</h1>");
            for(int number : numbers)
            {
                out.println(number);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
