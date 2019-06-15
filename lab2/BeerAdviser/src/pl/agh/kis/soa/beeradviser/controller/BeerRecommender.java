package pl.agh.kis.soa.beeradviser.controller;

import pl.agh.kis.soa.beeradviser.model.BeerExpert;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BeerRecommender")
public class BeerRecommender extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String beerColor = request.getParameter("beerColor");
        BeerExpert beerExpert = new BeerExpert();

        request.setAttribute("recommendedBeer", beerExpert.getRecommendedBeer(beerColor));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View/result.jsp");
        requestDispatcher.forward(request, response);
    }
}
