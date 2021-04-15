<%-- 
    Document   : AddAppointmentService
    Created on : 14-Apr-2021, 3:40:01 PM
    Author     : Riley
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="assets/css/main.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
<%@include file="testFiles/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Service To Appointment</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="path">
                <div class="link">
                    <a href="<c:url value='/Appointment'></c:url>?apptId=${appt.getAppointmentID()}">Appointment</a>
                    </div>
                    <div class="link">
                        <img id="chevronRight" src="assets/img/chevronRight.svg" alt="">
                    </div>
                    <div class="link">
                        <a>Add Service to appointment</a>
                    </div>
                </div>
                <div class="generalContainer">
                <c:if test="${errorMsg != null}">
                    <div id="errorBox">
                        ${errorMsg};
                    </div>
                </c:if>
                    
                    <h1>Add Service to Appointment</h1>

                    <!--GET form, allows user to select service type-->
                    <form action="AddServiceToAppointment" method="GET">
                        <label for="selectServiceType">Choose Service Type: </label>
                        <select name="selectedServiceType" id="selectServiceType" onclick="this.form.submit()">
                        <c:forEach items="${serviceTypesList}" var="serviceType">
                            <c:if test="${selectedServiceTypeID == serviceType.getServiceTypeID()}">
                                <option value="${serviceType.getServiceTypeID()}" selected='true'>${serviceType.getServiceTypeID()} ${serviceType.getServiceType()}</option>
                            </c:if>
                            <c:if test="${selectedServiceTypeID != serviceType.getServiceTypeID()}">
                                <option value="${serviceType.getServiceTypeID()}">${serviceType.getServiceTypeID()} ${serviceType.getServiceType()}</option>
                            </c:if>
                        </c:forEach>
                    </select>

                    <input type="hidden" name="apptId" value="${appt.getAppointmentID()}">
                </form>


                <!--POST form, which submits new appointmentService-->
                <form action="AddServiceToAppointment" method="POST">
                    <label for="selectServices">Choose service: </label> 
                    <select name="selectedServiceId" id="selectServices">
                        <c:forEach items="${services}" var="service">
                            <option value="${service.getServiceID()}">${service.getServiceName()}</option>
                        </c:forEach>
                    </select>
                    
                    <!--Select Pet--> 
                    <label for="selectPet">Choose pet: </label>
                    <select name="selectedPetId" id="selectPet">
                        <c:forEach items="${pets}" var="pet">
                            <option value="${pet.getPetID()}">
                                ${pet.getPetName()}
                            </option>
                        </c:forEach>
                    </select>
                    
                    
                    <br/><br/>
                    <!--Cancel-->
                    <a href="<c:url value='/Appointment'></c:url>?apptId=${appt.getAppointmentID()}">Cancel</a>
                   
                    <!--OK-->
                    <input type="submit" name="action" value="Ok">
                    <input type="hidden" name="apptId" value="${appt.getAppointmentID()}">
                </form>

            </div>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
</html>
