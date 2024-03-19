<%--
  Created by IntelliJ IDEA.
  User: anthonynkyi
  Date: 18/03/2024
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Data App: General stats</title>
    <jsp:include page="header.jsp"/>
</head>
<body class="main">
    <section>
        <jsp:include page="topnav.jsp"/>
        <div class="content">
            <h1 style="text-align: center">Miscellaneous stats</h1>
            <div class="overview-boxes">
                <div class="box">
                    <i class="ic--round-people"></i>
                    <div class="title">Total patients: </div>
                    <div class="number"> <%= request.getAttribute("numPatients") %></div>
                </div>
                <div class="box">
                    <i class="bx bx-male"></i>
                    <div class="title">Percentage male (%):</div>
                    <div class="number"> <%= request.getAttribute("percentOfMales") %></div>
                </div>
                <div class="box">
                    <i class="bx bx-female"></i>
                    <div class="title">Percentage female (%):</div>
                    <div class="number"><%= request.getAttribute("percentOfFemales") %></div>
                </div>
            </div>
            <div class="overview-boxes">
                <div class="box">
                    <i class="bx bx-car"></i>
                    <div class="title">Percentage drivers (%):</div>
                    <div class="number"><%= request.getAttribute("percentOfDrivers") %></div>
                </div>
                <div class="box">
                    <i class="bx bx-calendar-week"></i>
                    <div class="title">Average age:</div>
                    <div class="number"><%= request.getAttribute("averageAge") %></div>
                </div>
                <div class="box">
                    <i class="bx bx-world"></i>
                    <div class="title">Ethnicities represented:</div>
                    <div class="number"><%= request.getAttribute("numEthnicities") %></div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </section>
</body>
</html>
