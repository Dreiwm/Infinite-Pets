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
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="/AddPet">Add a new pet</a>
        
    </body>
</html>
