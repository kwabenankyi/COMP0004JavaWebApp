<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: anthonynkyi
  Date: 18/03/2024
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Data App: View by age</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<section class="main">
    <jsp:include page="topnav.jsp"/>
    <h1>Age info</h1>
    <div class="content">
        <h1 style="text-align: center">All patients grouped by age</h1>
        <div class="overview-boxes">
            <% {
                HashMap<String, ArrayList<String>> patientAges = (HashMap<String, ArrayList<String>>) request.getAttribute("idAges");
                HashMap<String, String> patientNames = (HashMap<String, String>) request.getAttribute("idNames");
                HashMap<String, String> patientDOB = (HashMap<String, String>) request.getAttribute("idDOB");
                ArrayList<String> ageGroups = (ArrayList<String>) request.getAttribute("ageGroups");
                String name;
                ArrayList<String> groupIDs;
                for (String group : ageGroups) {
                    out.println("<div class='box'><h2>" + group + "</h2>");
                    out.println("<table border='1'>");
                    out.println("<tr>");
                    out.println("<th>Patient Name</th>");
                    out.println("<th>Date of birth</th>");
                    out.println("</tr>");
                    groupIDs = patientAges.get(group);
                    for (String id : groupIDs) {
                        out.println("<tr>");
                        out.println("<td><a href=patientProfile.html?ID=" + id + ">" + patientNames.get(id) + "</a></td>");
                        out.println("<td>" + patientDOB.get(id) + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table></div>");
                }
            } %>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</section>



</body>
</html>
