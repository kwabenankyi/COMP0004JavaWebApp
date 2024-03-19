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
  <jsp:include page="/topnav.jsp"/>
  <div class="content">
    <h1>Search Result for "<%=(String) request.getAttribute("searchstring")%>"</h1>
    <br/>
    <%
      List<String> results = (List<String>) request.getAttribute("result");
      List<String> resultsid = (List<String>) request.getAttribute("resultids");
      if (!results.isEmpty())
      {
    %>
    <ul>
      <%
        int i = 0;
        String patient, currentid, href;
        for (i=0; i<results.size(); i++)
        {
          patient = results.get(i);
          href = "patientProfile.html?ID=" + resultsid.get(i);
      %>
      <a href="<%=href%>"><li><%=patient%></li></a>
      <% }
      } else
      {%>
      <p>No results found.</p>
      <%}%>
    </ul>
  </div>
  <jsp:include page="/footer.jsp"/>
</div>
</body>
</html>
