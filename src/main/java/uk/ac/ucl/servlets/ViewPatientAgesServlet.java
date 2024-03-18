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
import java.util.HashSet;

@WebServlet("/statsAge.html")
public class ViewPatientAgesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        model.sort("LAST");
        HashMap<String,ArrayList<String>> idAges = model.getIDsToAges();
        HashMap<String,String> idDOB = model.getIDsToDOB();
        HashMap<String,String> idNames = model.getIDsToNames();
        ArrayList<String> ageGroups = model.getAgeGroups();
        request.setAttribute("idNames", idNames); //dict
        request.setAttribute("idAges", idAges); //dict
        request.setAttribute("idDOB", idDOB); //dict
        request.setAttribute("ageGroups", ageGroups);
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/statsAge.jsp");
        dispatch.forward(request, response);
    }
}
