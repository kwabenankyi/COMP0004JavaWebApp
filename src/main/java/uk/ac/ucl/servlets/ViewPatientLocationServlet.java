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
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/statsLocation.html")
public class ViewPatientLocationServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PatientModel patientModel = ModelFactory.getPatientModel();
        patientModel.sort("CITY");
        HashMap<String,String> idNames = patientModel.getIDsToNames();
        HashMap<String,String> idAddresses = patientModel.getIDsToAddresses();
        ArrayList<String> cities = new ArrayList<>(new HashSet<>(patientModel.getCities()));
        cities.sort(String.CASE_INSENSITIVE_ORDER);
        request.setAttribute("cityPatients", patientModel.splitPatientsByCity()); //dict
        request.setAttribute("idNames", idNames); //dict
        request.setAttribute("cities", cities);
        request.setAttribute("idAddresses", idAddresses); //dict
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/statsLocation.jsp");
        dispatch.forward(request, response);
    }
}
