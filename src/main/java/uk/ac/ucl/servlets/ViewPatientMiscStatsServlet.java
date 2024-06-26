package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.PatientModel;

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
        PatientModel patientModel = ModelFactory.getPatientModel();
        int numPatients = patientModel.getNumPatients();
        long malesPercent = Math.round((double) patientModel.getNumOfMales() / numPatients * 100);
        long driversPercent = Math.round((double) patientModel.getNumOfDrivers() / numPatients * 100);

        request.setAttribute("numPatients",numPatients);
        request.setAttribute("percentOfMales",malesPercent);
        request.setAttribute("percentOfFemales",100-malesPercent);
        request.setAttribute("percentOfDrivers", driversPercent);
        request.setAttribute("averageAge",patientModel.getAverageAge());
        request.setAttribute("numEthnicities",patientModel.getNumEthnicities());

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/statsMisc.jsp");
        dispatch.forward(request, response);
    }
}
