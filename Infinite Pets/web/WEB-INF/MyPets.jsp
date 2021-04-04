<%-- 
    Document   : myPets
    Created on : 24-Feb-2021, 6:43:58 PM
    Author     : Cashton
--%>

<!-- <%@page contentType="text/html" pageEncoding="UTF-8"%> -->
<!-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/MyPets.css">  
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>My Pets</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>

        <div class="wrapper">
            <div class="viewPetsContainer">
                <div class="title">
                    <h1>My Pets</h1>
                </div>
                <div class="tableWrapper">
                    <table class="petTable" border="1" >
                        <thead>
                            <tr>
                                <th>Pet Name</th>
                                <th>Animal</th>
                                <th>Breed</th>
                                <th>Sex</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                             <c:forEach items="${pets}" var="pet"> 
                                <tr>
                                    <td>${pet.petName}</td>
                                    <td>${pet.species}</td>
                                    <td>${pet.breed}</td>
                                    <td>${pet.sex}</td>
                                    <td>
                                        <button>Remove</button>
                                    </td>
                                </tr>
                             </c:forEach> 
                        </tbody>
                    </table>
                </div>
                <div class="addPetLink">
                    <a href="/InfinitePets/AddPet">Add a new pet</a>
                </div>
            </div>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
  
<!--         <h1>My Pets</h1>
        
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
                        <td>${pet.sex}</td> -->
                        <!--make another link to go to the pet and an image as well
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="/InfinitePets/AddPet">Add a new pet</a>
         -->

    </body>
</html>
