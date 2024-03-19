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
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/statsLocation.html")
public class ViewPatientLocationServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        model.sort("CITY");
        HashMap<String,String> idNames = model.getIDsToNames();
        HashMap<String,String> idAddresses = model.getIDsToAddresses();
        ArrayList<String> cities = new ArrayList<>(new HashSet<>(model.getCities()));
        cities.sort(String.CASE_INSENSITIVE_ORDER);
        request.setAttribute("cityPatients", model.splitPatientsByCity()); //dict
        request.setAttribute("idNames", idNames); //dict
        request.setAttribute("cities", cities);
        request.setAttribute("idAddresses", idAddresses); //dict
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/statsLocation.jsp");
        dispatch.forward(request, response);
    }
}
