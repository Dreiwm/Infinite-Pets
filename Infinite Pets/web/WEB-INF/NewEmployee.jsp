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
        <link rel="stylesheet" href="assets/css/NewEmployee.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>New Employee</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>
        <div class="wrapper">
            <form action="Employee" method="POST">
            <input type="hidden" name="oldEmail" value="${oldEmail}" id="oldEmail">
            <div class="employeeContainer">
                <div class="employee">
                    <h1>Employee</h1>
                </div>
                <table class="empTable"> 
                    <tr>
                        <td>First Name:</td>
                        <td><input type="text" name="firstName" class="inputFields" value="${empAccount.firstName}"></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><input type="text" name="lastName" class="inputFields" value="${empAccount.lastName}"></td>
                    </tr>
                    <tr>
                        <td>Street Address:</td>
                        <td><input type="text" name="address" class="inputFields" value="${empAddress.address}"></td>
                    </tr>
                    <tr>
                        <td>Area:</td>
                        <td><input type="text" name="area" class="inputFields" value="${empAddress.area}"></td>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td><input type="text" name="city" class="inputFields" value="${empAddress.city}"></td>
                    </tr>
                    <tr>
                        <td>Province:</td>
                        <td><input type="text" name="prov" class="inputFields" value="${empAddress.province}"></td>
                    </tr>
                    <tr>
                        <td>Country:</td>
                        <td><input type="text" name="country" class="inputFields" value="${empAddress.country}"></td>
                    </tr>
                    <tr>
                        <td>Postal Code:</td>
                        <td><input type="text" name="postal" class="inputFields" value="${empAddress.postalCode}"></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email" class="inputFields" value="${empAccount.email}"></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" class="inputFields" value="${empAccount.passwordHash}"></td>
                    </tr>
                    <tr>
                        <td>Employee:</td>
                        <td>
                            <select>
                                <option value="false">No</option>
                                <option value="true" selected="true">Yes</option>
                            </select>
                        </td>
                    </tr>   
                    <tr>
                        <td>Confirm</td>
                        <td>
                            <select>
                                <option value="false">No</option>
                                <option value="true" selected="true">Yes</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <div class="qualifications">
                    <h1>Employee Qualifications</h1>
                </div>
                <table class="qualTable">
                    <tr><th>Name</th><th>Qualified</th></tr>
                <c:forEach var="service" items="${services}">
                    <tr><td>${service.key}</td><td>                
                <c:choose>       
                    <c:when test = "${service.value == true}">
			<select name="${service.key}" id="${service.key}">
                            <option value="true" selected>Yes</option>
                            <option value="false" >No</option>
			</select>
                    </c:when>
                    <c:otherwise>
                        <select name="${service.key}" id="${service.key}">
                            <option value="true" >Yes</option>
                            <option value="false" selected>No</option>
			</select>
                    </c:otherwise>
                </c:choose> </td></tr>                  
                </c:forEach>
                </table>          
                <div class="saveBtn" id="saveBtn" value="btn">
                    <input type="hidden" name="action" value="${action}">
                    <button type="submit">Save</button>
                </div>
            
                <div class="cancelBtn" id="cancelBtn">
                    <form method="get" action="Employment">
                        <button type="submit" id="cancel">Cancel</button>
                    </form>
                </div>
            </div>
        </form>
        </div>
        
    
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
    <script>
        
    </script>
</html>
