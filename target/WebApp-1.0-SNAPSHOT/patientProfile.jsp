<%--
  Created by IntelliJ IDEA.
  User: anthonynkyi
  Date: 15/03/2024
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App: View profile</title>
</head>
<body>
    <jsp:include page="/header.jsp"/>
    <h1>View patient profile</h1>
    <div class="main">
        <p><b>Patient ID:</b> <%=(String) request.getAttribute("ID")%></p>
        <p><b>Patient Name:</b> <%=(String) request.getAttribute("PREFIX")%> <%=(String) request.getAttribute("FIRST")%> <%=(String) request.getAttribute("LAST")%> <%=(String) request.getAttribute("SUFFIX")%></p>
        <p><b>Gender:</b> <%=(String) request.getAttribute("GENDER")%></p>
        <p><b>Date of birth:</b> <%=(String) request.getAttribute("BIRTHDATE")%></p>
        <p><b>Place of birth:</b> <%=(String) request.getAttribute("BIRTHPLACE")%></p>
        <c:choose>
            <c:when test="${empty DEATHDATE}">
            </c:when>
            <c:otherwise>
                <p><b>Date of death:</b> <%=(String) request.getAttribute("DEATHDATE")%></p>
            </c:otherwise>
        </c:choose>

        <p><b>Last known address:</b> <%=(String) request.getAttribute("ADDRESS")%>, <%=(String) request.getAttribute("CITY")%>, <%=(String) request.getAttribute("STATE")%>, <%=(String) request.getAttribute("ZIP")%></p>
        <p><b>Race:</b> <%=(String) request.getAttribute("RACE")%></p>
        <p><b>Ethnicity:</b> <%=(String) request.getAttribute("ETHNICITY")%></p>
        <p><b>Social security number:</b> <%=(String) request.getAttribute("SSN")%></p>

        <c:choose>
            <c:when test="${empty DRIVERS}">
                <p><b>No driving licence.</b></p>
            </c:when>
            <c:otherwise>
                <p><b>Driving licence number:</b> <%=(String) request.getAttribute("DRIVERS")%></p>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${empty PASSPORT}">
                <p><b>No passport.</b></p>
            </c:when>
            <c:otherwise>
                <p><b>Passport number:</b> <%=(String) request.getAttribute("PASSPORT")%></p>
            </c:otherwise>
        </c:choose>

        <p><b>Marital status:</b> <%=(String) request.getAttribute("MARITAL")%></p>

        <c:choose>
            <c:when test="${empty MAIDEN}">
            </c:when>
            <c:otherwise>
                <p><b>Maiden name:</b> <%=(String) request.getAttribute("MAIDEN")%></p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
<jsp:include page="/footer.jsp"/>
</html>
