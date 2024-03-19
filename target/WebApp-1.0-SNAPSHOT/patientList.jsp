<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="header.jsp"/>
  <title>Patient Data App: Patient list</title>
</head>
<body>
  <section class="main">
    <jsp:include page="topnav.jsp"/>
    <div class="content">
        <h1 style="text-align: center">All patients</h1>
        <div class="overview-boxes">
            <%
              int count = 0;
              List<String> patients = (List<String>) request.getAttribute("patientNames");
              List<String> IDs = (List<String>) request.getAttribute("patientIDs");
              for (String patient : patients)
              {
                String href = "patientProfile.html?ID=" + IDs.get(count);
                count++;
            %>
            <div class="box">
              <a href="<%=href%>">
                <i class='gg--profile'></i>
                <div class="title"><%=patient%></div>
              </a>
            </div>
            <% } %>
        </div>
    </div>
    <jsp:include page="/footer.jsp"/>
  </section>
</body>
</html>
