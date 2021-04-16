<%-- 
    Document   : ViewAppointments
    Created on : 28-Mar-2021, 4:56:13 PM
    Author     : Cashton
--%>

<!-- <%@page contentType="text/html" pageEncoding="UTF-8"%> -->
<!-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                    <div>${msg}</div>
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
                                    <td><fmt:formatDate type="date" value="${appt.appointmentDate}" /></td>
                                    <td><fmt:formatDate type="time" value="${appt.appointmentTime}" /></td>
                                    <td><c:forEach items="${appt.appointmentserviceList}" var="pet">${pet.petID.petName}<br></c:forEach></td>
                                    <td><c:forEach items="${appt.appointmentserviceList}" var="service">${service.serviceID.serviceName}<br></c:forEach></td>
                                    <td>
                                        <c:url value="Appointment" var="editurl">
                                            <c:param name="method" value="GET"/>
                                            <c:param name="action" value="edit"/>
                                            <c:param name="apptId" value="${appt.appointmentID}"/>
                                        </c:url>
                                    <td><a href="${editurl}" ><button>Edit</button></a></td>
                                    </td>
                                </tr>
                             </c:forEach> 
                        </tbody>
                    </table>
                </div>
                <div class="addPetLink">
                    <a href="/InfinitePets/BookAppointment"><button>Book an appointment</button></a>
                </div>
            </div>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
    
</html>
