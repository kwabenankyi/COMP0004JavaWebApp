
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Patient Data App: Stats index</title>
</head>
<body>
    <section class="main">
        <jsp:include page="topnav.jsp"/>
        <div class="content">
            <div class="overview-boxes">
                <div class="box">
                    <a href="statsLocation.html">
                        <i class='bx bx-map-pin'></i>
                        <div class="box-topic">View stats by location</div>
                    </a>
                </div>
                <div class="box">
                    <a href="statsAge.html">
                        <i class='bx bx-calendar-check'></i>
                        <div class="box-topic">View stats by age</div>
                    </a>
                </div>
                <div class="box">
                    <a href="statsMisc.html">
                        <i class='bx bx-stats'></i>
                        <div class="box-topic">View miscellaneous stats</div>
                    </a>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </section>
</body>
</html>