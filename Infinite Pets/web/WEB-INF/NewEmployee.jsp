<%-- 
    Document   : NewEmployee
    Created on : Apr 9, 2021, 11:32:10 PM
    Author     : BTran
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/Login.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>New Employee</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>
        <h1>Employee</h1>
        <form action="Employee" method="POST"><table> 
                <tr><td>First Name:</td><td><input type="text" name="firstName" class="inputFields" value="${empAccount.firstName}"></td></tr>
                <tr><td>Last Name:</td><td><input type="text" name="lastName" class="inputFields" value="${empAccount.lastName}"></td></tr>
                <tr><td>Street Address:</td><td><input type="text" name="address" class="inputFields" value="${empAddress.address}"></td></tr>
                <tr><td>Area:</td><td><input type="text" name="area" class="inputFields" value="${empAddress.area}"></td></tr>
                <tr><td>City:</td><td><input type="text" name="city" class="inputFields" value="${empAddress.city}"></td></tr>
                <tr><td>Province:</td><td><input type="text" name="prov" class="inputFields" value="${empAddress.province}"></td></tr>
                <tr><td>Country:</td><td><input type="text" name="country" class="inputFields" value="${empAddress.country}"></td></tr>
                <tr><td>Postal Code:</td><td><input type="text" name="postal" class="inputFields" value="${empAddress.postalCode}"></td></tr>
                <tr><td>Email:</td><td><input type="text" name="email" class="inputFields" value="${empAccount.email}"></td></tr>
                <tr><td>Password:</td><td><input type="password" name="password" class="inputFields" value="${empAccount.passwordHash}"></td></tr>
                <tr><td>Employee:</td><td><select><option value="false">No</option><option value="true" selected="true">Yes</option></select></td></tr>   
                <tr><td>Confirm</td><td><select><option value="false">No</option><option value="true" selected="true">Yes</option></select></td></tr>
            </table>
            <h3>Employee Qualifications</h3>
            <table>
                <tr><th>Name</th><th>Description</th><th>Qualified</th></tr>
                <c:forEach var="service" items="${services}">
                    <tr><td>${service.serviceName}</td><td>${service.serviceDescription}</td><td><select><option value="false">No</option><option value=${service.serviceName} selected="true">Yes</option></select></td></tr>
                </c:forEach>
            </table>               
             <div id="saveBtn" value="btn">
                <input type="hidden" name="action" value="${action}">
                <input type="submit" value="Save">
            </div>
        </form>
        <div id="cancelBtn"><form method="get" action="Employment">
            <button type="submit" id="cancel">Cancel</button>
        </form></div>
    </body>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</html>
