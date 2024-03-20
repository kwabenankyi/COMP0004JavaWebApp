<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="header.jsp"/>
  <title>Patient Data App: Home</title>
</head>
<body>
  <section class="main">
    <jsp:include page="topnav.jsp"/>
    <div class="content">
      <div class="overview-boxes">
        <div class="box">
          <a href="patientList.html">
            <i class='ic--round-people'></i>
            <div class="box-topic">View Patient List</div>
          </a>
        </div>
        <div class="box">
          <a href="statsMisc.html">
            <i class='bx bx-stats'></i>
            <div class="box-topic">View stats</div>
          </a>
        </div>
      </div><br/>
    </div>
  </section>

</body>
</html>