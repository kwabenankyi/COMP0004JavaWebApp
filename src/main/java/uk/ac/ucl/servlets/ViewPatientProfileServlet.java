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
        try {
            for (String key : patientProfile.keySet()) {
                if ((key.equals("MARITAL"))) {
                    switch (patientProfile.get(key)) {
                        case "S":
                            request.setAttribute(key, "Single");
                            break;
                        case "M":
                            request.setAttribute(key, "Married");
                            break;
                        default:
                            request.setAttribute(key, "Unknown");
                            break;
                    }
                } else if (key.equals("PREFIX") || key.equals("SUFFIX")) {
                    request.setAttribute(key, patientProfile.get(key));
                } else {
                    request.setAttribute(key, patientProfile.get(key));
                }
            }
        } catch (NullPointerException e) {
            request.setAttribute("error", "Patient not found");
        }
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientProfile.jsp");
        dispatch.forward(request, response);
    }
}
