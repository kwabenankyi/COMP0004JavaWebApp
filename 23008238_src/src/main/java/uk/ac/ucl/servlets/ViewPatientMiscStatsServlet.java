package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/statsMisc.html")
public class ViewPatientMiscStatsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        int numPatients = model.getNumPatients();
        long malesPercent = Math.round((double) model.getNumOfMales() / numPatients * 100);
        long driversPercent = Math.round((double) model.getNumOfDrivers() / numPatients * 100);

        request.setAttribute("numPatients",numPatients);
        request.setAttribute("percentOfMales",malesPercent);
        request.setAttribute("percentOfFemales",100-malesPercent);
        request.setAttribute("percentOfDrivers", driversPercent);
        request.setAttribute("averageAge",model.getAverageAge());
        request.setAttribute("numEthnicities",model.getNumEthnicities());

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/statsMisc.jsp");
        dispatch.forward(request, response);
    }
}
