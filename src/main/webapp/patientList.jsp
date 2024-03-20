<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
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
              HashMap<String,String> idNames = (HashMap<String, String>) request.getAttribute("idNames");
              for (String id : idNames.keySet())
              {
                String href = "patientProfile.html?ID=" + id;
            %>
            <div class="box">
              <a href="<%=href%>">
                <i class='gg--profile'></i>
                <div class="title"><%=idNames.get(id)%></div>
              </a>
            </div>
            <% } %>
        </div>
    </div>
    <jsp:include page="/footer.jsp"/>
  </section>
</body>
</html>
