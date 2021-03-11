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

    <title>Book Appointment</title>
</head>
<body>
    <header>


    </header>
    <label for="date">Date</label>
    <input name="date" type="date">

    <br>
    
    <label for="time">Time</label>
    <select name="time" id="time">
        <option value="morn">Morning</option>
        <option value="after">Afternoon</option>
        <option value="eve">Evening</option>
    </select>

    <br>

    <label for="pet">Select Pet</label>
    <select name="pet" id="petName">
        <option value="">Pet 1</option>
        <option value="">Pet 2</option>
        <option value="">Pet 3</option>
        <option value="">Pet 4</option>
    </select>
    
    <br>

    <label for="service">Select service</label>
    <select name="service" id="service">
        <option value="">Service 1</option>
        <option value="">Service 2</option>
        <option value="">Service 3</option>
        <option value="">Service 4</option>
    </select>

    <button>Add another service for this pet</button>

    

    <br>
    <textarea name="notes" id="petNotes" cols="30" rows="3">Additional Notes</textarea>
    
    <h3>Current pet to Add:</h3>

<!--    <table name="tempPetService">
        <tr>
            <td>PET NAME</td>
        </tr>
        <tr>
            <td>SERVICE 1</td>
        </tr>
        <tr>
            <td>SERVICE 2</td>
        </tr>
        <tr>
            <td>SERVICE 3</td>
        </tr>
        <tr>
            <td>SERVICE 4</td>
        </tr>
        <tr>
            <td>Additional notes for the pet</td>
        </tr>
    </table>

    <button>Add pet to the appointment</button>-->


<!--    <ul name="petService">
        <li>pet 1</li>
        <li>service 1</li>
        <li>service 2</li>
        <li>notes about the pet</li>
    </ul>

    <ul name="petService">
        <li>pet 2</li>
        <li>service 1</li>
        <li>service 3</li>
        <li>notes about the pet</li>
    </ul>-->


    <button>Cancel</button>
    <button>Book appointment</button>
</body>
</html>
