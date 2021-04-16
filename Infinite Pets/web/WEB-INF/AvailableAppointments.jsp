<%-- 
    Document   : StaffAppointments
    Created on : 11-Apr-2021, 4:23:15 PM
    Author     : Riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="assets/css/main.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <%@include file="testFiles/header.jsp" %>
        <title>MyAppointments</title>
    </head>
    <body>
         <div class="wrapper">
            <div class="path">
                <div class="link">
                    <a href="<c:url value='/MyProfile'></c:url>">MyProfile</a>
                    </div>
                    <div class="link">
                        <img id="chevronRight" src="assets/img/chevronRight.svg" alt="">
                    </div>
                    <div class="link">
                        <a>Available Appointments</a>
                    </div>
                </div>
             <div class="generalContainer">
                 
                 <table class="">
                     <thead>
                         <tr>
                             <th>Appointment Services</th>
                             <th>Date</th>
                             <th>Details</th>
                         </tr>
                     </thead>
                     <tbody>
                     <c:forEach items="${availableAppts}" var="availableAppt">
                         <tr>
                             <td>
                                 <c:forEach items="${availableAppt.getAppointmentServiceList()}" var="apptService" varStatus="index">
                                     <c:if test="${index.isLast() == false}">
                                         ${apptService.getServiceID().getServiceName()} with ${apptService.getPetID().getPetName()},
                                     </c:if>
                                     <c:if test="${index.isLast()}">
                                        ${apptService.getServiceID().getServiceName()} with ${apptService.getPetID().getPetName()}
                                     </c:if>
                                 </c:forEach>
                             </td>
                             <td>
                                 <fmt:formatDate type="both" pattern="MMM dd, yyyy" value="${availableAppt.getAppointmentDate()}"/>
                                 at <fmt:formatDate type="time" pattern="ha" value="${availableAppt.getAppointmentTime()}"/>
                             </td>
                             <td>
                                 <!--Btn to view appointment to accept/reject-->
                                 <form action="ViewAvailableAppointment" method="GET">
                                     <input type="submit" value="See details">
                                     <input type="hidden" name="action" value="viewUpcomingAppt">
                                     <input type="hidden" name="apptId" value="${availableAppt.getAppointmentID()}">
                                 </form>
                             </td>
                         </tr>
                     </c:forEach>
                     </tbody>
                 </table>
                 
                 <span>Don't see available appointments you'd like to take? <br/>You can change your <a href='<c:url value="WorkPreferences"></c:url>'>work preferences</a>.</span>
                 
                 <a href='<c:url value="/MyProfile"></c:url>'>Back to MyProfile</a>
             </div>
         </div>
        
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>

    </body>
</html>
