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
        <link rel="stylesheet" href="assets/css/AdminAcctServices.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>Employment</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>
        <div class="wrapper">

            <div class="employmentContainer">
                <div class="title">
                    <h1>Employment</h1>
                </div>
                <div class="newEmp">
                    <form method="GET" action="Employee">
                        <input type="hidden" name="action" value="create">
                        <button type="submit">Add New Employee</button>
                    </form>
                </div>
                <div class="empTableContainer">
                    <table class="empTable">
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="employee" items="${employees}">
                                <tr>
                                    <td>${employee.firstName}</td>
                                    <td>${employee.lastName}</td>
                                    <td>${employee.email}</td>
                                    
                                    <c:url value="Employee" var="editurl">
                                        <c:param name="method" value="GET"/>
                                        <c:param name="action" value="edit"/>
                                        <c:param name="empEmail" value="${employee.email}"/>
                                    </c:url>
                                    <td><a href="${editurl}">Edit</a></td>
                                        
                                    <c:url value="Employee" var="deleteurl">
                                        <c:param name="method" value="GET"/>
                                        <c:param name="action" value="delete"/>
                                        <c:param name="empEmail" value="${employee.email}"/>
                                    </c:url>                     
                                    <td><a href="${deleteurl}">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
    
</html>
