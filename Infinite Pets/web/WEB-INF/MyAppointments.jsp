<%-- 
    Document   : ViewAppointments
    Created on : 28-Mar-2021, 4:56:13 PM
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
        <link rel="stylesheet" href="assets/css/MyAppointments.css">  
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>My Appointments</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>
        <div class="wrapper">
            <div class="viewApptContainer">
                <div class="title">
                    <h1>My Appointments</h1>
                </div>
                <div class="tableWrapper">
                    <table class="apptTable" border="1" >
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Time</th>
                                <th>Animals</th>
                                <th>Services</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                             <c:forEach items="${appts}" var="appt"> 
                                <tr>
                                    <td>${appt.date}</td>
                                    <td>${appt.time}</td>
                                    <td>${appt.animal}</td>
                                    <td>${appt.service}</td>
                                    <td>
                                        <button>Cancel</button>
                                    </td>
                                </tr>
                             </c:forEach> 
                        </tbody>
                    </table>
                </div>
                <div class="addPetLink">
                    <a href="/InfinitePets/BookAppointment">Book an appointment</a>
                </div>
            </div>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
    
</html>
