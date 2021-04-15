<%-- 
    Document   : BookAppointment
    Created on : 3-Mar-2021, 4:15:19 PM
    Author     : Cashton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/main.css">
    <link rel="stylesheet" href="assets/css/BookAppointment.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
    <title>Book Appointment</title>
    <%@include file="testFiles/header.jsp" %>
    <script src="assets/js/addAppt.js"></script> 
</head>
<body>        
              <div class="wrapper">

            <div class="path">
                <div class="link">
                    <a href="">My appointments</a>
                </div>
                <div class="link">
                    <img id="chevronRight" src="assets/img/chevronRight.svg" alt="">
                </div>
                <div class="link">
                    <a href="">New Appointment</a>
                </div>
            </div>

            <div class="appointmentContainer">
                <div class="appointmentInput">
                    <div class="appointmentTitle">
                        <h1>Book an Appointment</h1>
                    </div>
    
    
                    <div class="dateLabel">
                        <label for="date">Date</label>
                    </div>
    
                    <div class="dateInput">
                        <input name="date" type="date" id="submitDate">
                    </div>
    
                    <div class="timeLable">
                        <label for="time">Time</label>
                    </div>
    
                    <div class="timeInput">
                        <select name="time" id="submitTime">
                            <option value="morn">Morning</option>
                            <option value="after">Afternoon</option>
                            <option value="eve">Evening</option>
                        </select>
                    </div>
    
                    <div class="petLabel">
                        <label for="pet">Select Pet</label>
                    </div>
                    <div class="petInput">
                        <select name="pet" id="pet">
                            <option value="Pet 1">Pet 1</option>
                            <option value="Pet 2">Pet 2</option>
                            <option value="Pet 3">Pet 3</option>
                            <option value="Pet 4">Pet 4</option>
                        </select>
                    </div>
    
                    <div class="serviceLabel">
                        <label for="service">Select service</label>
                    </div>
                    
                    <div class="serviceInput">
                        <select name="service" id="service">
                            <option value="Service1">Service 1</option>
                            <option value="Service2">Service 2</option>
                            <option value="Service3">Service 3</option>
                            <option value="Service4">Service 4</option>
                        </select>
                    </div>
    
                    <div class="addServiceBtn">
                        <button onclick="setService()">Add another service</button>
                    </div>
               
                    <div class="addPetToApptBtn">
                        <button onclick="setPet()"> Add pet to the appointment</button>
                    </div>
                </div>
    
                <div class="appointmentSum">
                    <div class="sumTitle">
                        <h3>Appointment Summary:</h3>
                    </div>
                    
                    <form id="bookForm" method="post">                        
                        <div class="sumDate" id="sumDate">
                            <p>Date: </p>
                        </div>
                        <div class="sumTime" id="sumTime">
                            <p>Time: </p>
                        </div>
                        <div class="sumDetails">
                            <p>Details: </p>
                        </div>
    
                        <div class="sumPetName" id="petName">
                        </div>
                        <div class="sumService" id="petService">
                        </div>
                        <div class="notesInput">
                            <textarea name="notes" id="textArea" cols="30" rows="3">Additional Notes</textarea>
                        </div>
                   
                        <div class="bookBtn">
                            <button type="submit">Book appointment</button>
                        </div>
                    </form>
                        
                    <div class="cancelBtn">
                        <button>Cancel</button>
                    </div>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</body>
</html>