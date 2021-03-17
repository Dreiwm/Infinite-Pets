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
    <%@include file="testFiles/header.jsp" %>
    <script src="assets/js/addAppt.js"></script>
    <link rel="stylesheet" type="text/css" href="./assets/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="./assets/css/BookAppointment.css"/>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
    <title>Book Appointment</title>
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
                        <input name="date" type="date" id="selectDate">
                    </div>
    
                    <div class="timeLable">
                        <label for="time">Time</label>
                    </div>
    
                    <div class="timeInput">
                        <select name="time" id="selectTime">
                            <option value="morn">Morning</option>
                            <option value="after">Afternoon</option>
                            <option value="eve">Evening</option>
                        </select>
                    </div>
    
                    <div class="petLabel">
                        <label for="pet">Select Pet</label>
                    </div>
                    <div class="petInput">
                        <select name="pet" id="petSelect">
                            <option value="">Pet 1</option>
                            <option value="">Pet 2</option>
                            <option value="">Pet 3</option>
                            <option value="">Pet 4</option>
                        </select>
                    </div>
    
                    <div class="serviceLabel">
                        <label for="service">Select service</label>
                    </div>
                    
                    <div class="serviceInput">
                        <select name="service" id="serviceSelect">
                            <option value="">Service 1</option>
                            <option value="">Service 2</option>
                            <option value="">Service 3</option>
                            <option value="">Service 4</option>
                        </select>
                    </div>
    
                    <div class="addServiceBtn">
                        <button onclick="addService()">Add another service</button>
                    </div>
    
                    
    
                    <div class="notesInput">
                        <textarea name="notes" id="notes" cols="30" rows="3">Additional Notes</textarea>
                    </div>
                    
                    <div class="addPetToApptBtn">
                        <button>Add pet to the appointment</button>
                    </div>
    
                </div>
    
                <div class="appointmentSum">
                    <div class="sumTitle">
                        <h3>Appointment Summary:</h3>
                    </div>
                        <div class="sumDate">
                            <p>Date: </p>
                        </div>
                        <div class="sumTime">
                            <p>Time: </p>
                        </div>
                        <div class="sumDetails">
                            <p>Details: </p>
                        </div>
    
                        <div class="sumPetName">
                            <input id="sumPetName" type="text">
                        </div>
                        
                        <div class="sumService">
                            <input id="sumService" type="text">
                        </div>

                        <div class="cancelBtn">
                            <button>Cancel</button>
                        </div>
                        <div class="bookBtn">
                            <button>Book appointment</button>
                        </div>
                </div>
            </div>
        </div>

</body>



  <!-- <div class="calendar">
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
                
            </div> -->

<%@include file="testFiles/footer.jsp" %>
</html>

