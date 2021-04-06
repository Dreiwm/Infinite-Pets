<%-- 
    Document   : myPets
    Created on : 24-Feb-2021, 6:43:58 PM
    Author     : Cashton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <%@include file="testFiles/header.jsp" %>
        <title>My Pets</title>
    </head>
    <body>
        <h1>My Pets</h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Pet Name</th>
                    <th>Animal</th>
                    <th>Breed</th>
                    <th>Sex</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pets}" var="pet">
                    <tr>
                        <td>${pet.petName}</td>
                        <td>${pet.species}</td>
                        <td>${pet.breed}</td>
                        <td>${pet.sex}</td>
                        <!--make another link to go to the pet and an image as well-->
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="/InfinitePets/AddPet">Add a new pet</a>
        
    </body>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</html>
