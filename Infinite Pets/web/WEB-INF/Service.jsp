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
        <link rel="stylesheet" href="assets/css/Service.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pet Service</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
 
    <body>
        <div class="wrapper">
            
            <form method="POST" action="Service">
            <div class="serviceContainer">
                    <div class="title">
                        <h1>Pet Service</h1>
                    </div>
                    <div class="serviceNameLbl">
                        <p>Service Name</p>
                    </div>
                    <div class="serviceName">
                        <input name="serviceName" id="serviceName" value="${serviceName}">
                    </div>
                    <div class="priceLbl">
                        <p>Base Price:</p>
                    </div>
                    <div class="price">
                        <input name="basePrice" id="basePrice" value="${basePrice}">
                    </div>
                    <div class="isActiveLbl">
                        <p>Active:</p>
                    </div>
                    <div class="isActive">
                        <input name="active" id="active" value="${active}">
                    </div>
                    <div class="petLbl">
                        <p>Specify Pet:</p>
                    </div>
                    <div class="pet">
                        <input name="specifyPet" id="specifyPet" value="${specifyPet}">
                    </div>
                    <div class="dateRangeLbl">
                        <p>Date Range:</p>
                    </div>
                    <div class="dateRange">
                        <input name="dateRange" id="dateRange" value="${dateRange}">
                    </div>
                    <div class="submitBtn">
                        <input type="hidden" name="action" value="save">
                        <button type="submit">Submit</button>
                    </div>
                </form>
                <form method="get" action="PetServices"></form>
                    <div class="cancelBtn" id="cancelBtn">
                        <button type="submit" id="cancel">Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <footer><%@include file="testFiles/footer.jsp" %>  </footer>
</html>
