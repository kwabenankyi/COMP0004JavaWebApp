<%--
  Created by IntelliJ IDEA.
  User: anthonynkyi
  Date: 15/03/2024
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap"%>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App: View profile</title>
</head>
<body>
    <h1>View patient profile</h1>
    <div class="main">
        <p><b>Patient Name:</b> <%=(String) request.getAttribute("FIRST")%> <%=(String) request.getAttribute("LAST")%></p>
    </div>
</body>
</html>
