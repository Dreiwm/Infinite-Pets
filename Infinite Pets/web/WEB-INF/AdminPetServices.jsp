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
        <link rel="stylesheet" href="assets/css/main.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Pet Services</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>
        <div id="container">
            <h1>Services List</h1>
            <div id="newService"><form method="GET" action="Service">
                <input type="Submit" value="Add New Service">
            </form></div>
            <div><table>
                <tr><th>Name</th><th>Price</th><th>Description</th><th>Active</th><th>Service Type</th><th>Edit</th><th>Delete</th></tr>
                <c:forEach var="service" items="${services}">
                    <tr><td>${service.serviceName}</td><td>${service.basePrice}</td><td>${service.serviceDescription}</td><td>service.active</td><td>11</td>
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
                    </c:url>                     
                    <td><a href="${deleteurl}">Delete</a></td></tr>
                </c:forEach>
            </table></div>
        </div>
    </body>
    <footer><%@include file="testFiles/footer.jsp" %> </footer>
</html>
