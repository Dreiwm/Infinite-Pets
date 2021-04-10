<%-- 
    Document   : Service
    Created on : Apr 4, 2021, 4:52:13 PM
    Author     : BTran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="assets/css/main.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pet Service</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
 
    <body>
        <h1>Pet Service</h1>
        <form method="POST" action="Service"><div>
            <label for="serviceName">Service Name:</label>
            <input name="serviceName" id="serviceName" value="${serviceName}">
        </div>
        <div>
            <label for="basePrice">Base Price:</label>
            <input name="basePrice" id="basePrice" value="${basePrice}">
        </div>
        <div>
            <label for="active">Active:</label>
            <input name="active" id="active" value="${active}">
        </div>
        <div>
            <label for="specifyPet">Specify Pet:</label>
            <input name="specifyPet" id="specifyPet" value="${specifyPet}">
        </div>
        <div>
            <label for="dateRange">Date Range:</label>
            <input name="dateRange" id="dateRange" value="${dateRange}">
        </div>
            <input type="hidden" name="action" value="save">
            <input type="Submit" value="Submit">
        </form> <br>
        <div id="cancelBtn"><form method="get" action="PetServices">
            <button type="submit" id="cancel">Cancel</button>
        </form></div>
    </body>
    <footer><%@include file="testFiles/footer.jsp" %>  </footer>
</html>
