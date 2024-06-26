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
import java.util.HashSet;

@WebServlet("/statsAge.html")
public class ViewPatientAgesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PatientModel patientModel = ModelFactory.getPatientModel();
        patientModel.sort("LAST");
        HashMap<String,ArrayList<String>> idAges = patientModel.getIDsToAges();
        HashMap<String,String> idDOB = patientModel.getIDsToDOB();
        HashMap<String,String> idNames = patientModel.getIDsToNames();
        ArrayList<String> ageGroups = patientModel.getAgeGroups();
        request.setAttribute("idNames", idNames); //dict
        request.setAttribute("idAges", idAges); //dict
        request.setAttribute("idDOB", idDOB); //dict
        request.setAttribute("ageGroups", ageGroups);
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/statsAge.jsp");
        dispatch.forward(request, response);
    }
}
