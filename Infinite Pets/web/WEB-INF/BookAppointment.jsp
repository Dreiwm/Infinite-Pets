<%-- 
    Document   : BookAppointment
    Created on : 3-Mar-2021, 4:15:19 PM
    Author     : Cashton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/BookAppointment.css">
    <%@include file="testFiles/header.jsp" %>
    <title>Book Appointment</title>
</head>
<body>
<div class="container">
    <div class="row">
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

            <div class="appointment">
                <div>
                    <h1>Book an Appointment</h1>
                </div>
                <div class="colum">
                    <div class="calendar">
                        <div class="month"></div>
                        <div class="daysHeader"></div>
                        <div class="week">
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                        </div>
                        <div class="week">
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                        </div>
                        <div class="week">
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                        </div>
                        <div class="week">
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                            <div class="day"></div>
                        </div>

                    </div>

                    <img src="assets/img/infinitePetsLogo-black(3).png" width="150" height="200" alt="infinitePetsLogo-black(3)"/>
                    <br>

                    <label for="date">Date</label>
                    <input name="date" type="date" id="selectDate">
                    <br>
                    <label for="time">Time</label>
                    <select name="time" id="selectTime">
                        <option value="morn">Morning</option>
                        <option value="after">Afternoon</option>
                        <option value="eve">Evening</option>
                    </select>
                    <br>
                    <label for="pet">Select Pet</label>
                    <select name="pet" id="petSelect">
                        <option value="Pet 1">Pet 1</option>
                        <option value="Pet 2">Pet 2</option>
                        <option value="Pet 3">Pet 3</option>
                        <option value="Pet 4">Pet 4</option>
                    </select>
                    <br>
                    <label for="service">Select service</label>
                    <select name="service" id="serviceSelect">
                        <option value="Service 1">Service 1</option>
                        <option value="Service 2">Service 2</option>
                        <option value="Service 3">Service 3</option>
                        <option value="Service 4">Service 4</option>
                    </select>

                    <button onclick="addService()" >Add another service for this pet</button>
                    <br>
                    <textarea name="notes" id="notes" cols="30" rows="3" placeholder="Additional Notes"></textarea>

            </div>
                <div class="colum">
                    <h1>Appointment Summary:</h1>

                     
                        <form method ="post">
                        <div id="displayDate">
                            <p id="selectedDate">Date: </p>
                        </div>    
                        <div id="displayTime">
                           <p id="selectedTime">Time: </p> 
                        </div>
                        <p>Details: </p>
                        <div id="detailsTable"></div>
                        <button type="submit" name="action" value="cancel">Cancel</button>
                        <button type="submit" name="action" value="submit">Book appointment</button>
                        </form>        
                    </div>
            </div>
        </div>
</div>
</body>
<footer><%@include file="testFiles/footer.jsp" %></footer>
<script src="assets/js/addAppt.js"></script>
</html>

