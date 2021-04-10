<%-- 
    Document   : AdminAccntServices
    Created on : Apr 9, 2021, 1:15:23 PM
    Author     : BTran
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/Login.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>Employment</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>
        <div id="mainContainer"><h1>Employment</h1>
            <div id="newEMP"><form method="GET" action="NewEmployee">
                <input type="Submit" value="Add New Employee">
            </form></div>
            <div id="empTable"><table>
            <tr><th>First Name</th><th>Last Name</th><th>Email</th><th>Edit</th><th>Delete</th></tr>
            <c:forEach var="employee" items="${employees}">
                <tr><td>${employee.firstName}</td><td>${employee.lastName}</td><td>${employee.email}</td>
                    <c:url value="Employment" var="editurl">
                        <c:param name="method" value="GET"/>
                        <c:param name="action" value="edit"/>
                        <c:param name="email" value="${employee.email}"/>
                    </c:url>
                    <td><a href="${editurl}">Edit</a></td>
                    <c:url value="Employment" var="deleteurl">
                        <c:param name="method" value="GET"/>
                        <c:param name="action" value="delete"/>
                        <c:param name="email" value="${employee.email}"/>
                    </c:url>                     
                <td><a href="${deleteurl}">Delete</a></td></tr>
            </c:forEach>
        </table></div>
        </div>
    </body>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</html>
