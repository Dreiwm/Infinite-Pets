<%-- 
    Document   : AdminPetServices
    Created on : Apr 4, 2021, 3:26:21 PM
    Author     : BTran
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/AdminPetServices.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>Admin Pet Services</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>
        <div class="wrapper">
            <div class="serviceContainer">
                <div class="title">
                    <h1>Services List</h1>
                </div>
                <form method="GET" action="Service">
                    <input type="hidden" name="action" value="add">
                    <div class="newService">
                        <button type="submit">Add New Service</button>
                    </div>
                </form>
                <div class="serviceTableContainer">
                    <table class="serviceTable">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Description</th>
                                <th>Edit</th>
                                <!--<th>Delete</th>-->
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="service" items="${services}">
                                <tr>
                                    <td>${service.serviceName}</td>
                                    <td>${service.basePrice}</td>
                                    <td>${service.serviceDescription}</td>
                                    <c:url value="Service" var="editurl">
                                        <c:param name="method" value="GET"/>
                                        <c:param name="action" value="edit"/>
                                        <c:param name="serviceID" value="${service.serviceID}"/>
                                    </c:url>
                                    <td><a href="${editurl}">Edit</a></td>
                                
                                    <c:url value="Service" var="deleteurl">
                                        <c:param name="method" value="GET"/>
                                        <c:param name="action" value="delete"/>
                                        <c:param name="serviceID" value="${service.serviceID}"/>
                                    </c:url >                    
                                    <!--<td><a href="${deleteurl}">Delete</a></td></tr>-->
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <footer><%@include file="testFiles/footer.jsp" %> </footer>
    </body>
    
</html>
