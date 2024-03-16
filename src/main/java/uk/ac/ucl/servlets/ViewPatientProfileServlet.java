package uk.ac.ucl.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

@WebServlet("/patientProfile.html")
public class ViewPatientProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        String patientID = request.getParameter("ID");
        HashMap<String, String> patientProfile = model.getPatientProfile(patientID);
        for (String key : patientProfile.keySet()) {
            request.setAttribute(key, patientProfile.get(key));
        }
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientProfile.jsp");
        dispatch.forward(request, response);
    }
}
