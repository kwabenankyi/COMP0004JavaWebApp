<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Patients:</h2>
  <ul>
    <%
      int count = 0;
      List<String> patients = (List<String>) request.getAttribute("patientNames");
      List<String> IDs = (List<String>) request.getAttribute("patientIDs");
      for (String patient : patients)
      {
        String href = "patientProfile.html?ID=" + IDs.get(count);
        count++;
    %>
    <li><a href="<%=href%>"><%=patient%></a>
    </li>
    <% } %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
