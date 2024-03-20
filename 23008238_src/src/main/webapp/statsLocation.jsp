<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: anthonynkyi
  Date: 18/03/2024
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Patient Data App: Location info</title>
</head>
<body>
    <section class='main'>
        <jsp:include page="topnav.jsp"/>
        <div class="content">
            <% {
                HashMap<String, ArrayList<String>> cityPatients = (HashMap<String, ArrayList<String>>) request.getAttribute("cityPatients");
                HashMap<String, String> patientNames = (HashMap<String, String>) request.getAttribute("idNames");
                ArrayList<String> cities = (ArrayList<String>) request.getAttribute("cities");
                HashMap<String,String> patientAddresses = (HashMap<String, String>) request.getAttribute("idAddresses");
                String state = ", MA";
                for (String city : cities) {
                    out.println("<h2>" + city + state + "</h2>");
                    out.println("<table border='1'>");
                    out.println("<tr>");
                    out.println("<th>Patient Name</th>");
                    out.println("<th>Patient Address</th>");
                    out.println("</tr>");
                    for (String patientID : cityPatients.get(city)) {
                        out.println("<tr>");
                        out.println("<td><a href=patientProfile.html?ID=" + patientID + ">" + patientNames.get(patientID) + "</a></td>");
                        out.println("<td>" + patientAddresses.get(patientID) + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                }
            } %>
        </div>
    </section>
        <jsp:include page="footer.jsp"/>
</body>
</html>
