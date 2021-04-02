<%-- 
    Document   : Appointment
    Created on : 27-Mar-2021, 12:20:53 PM
    Author     : Riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <%@include file="testFiles/header.jsp" %>
        <title>Appointment</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="generalContainer">
                <h1>Appointment With ${appt.getPetID().getPetName()}</h1>
                <div class="">

                    <table class="tableData">
                        <tr>
                            <td>Appointment Date: </td>
                            <td>From 
                                <form action="Appointment" method="GET">
                                    <select name="selectMonth" onchange="this.form.submit()">
                                        <c:forEach items="${months}" var="month">
                                            <c:if test="${month == startMonth}">
                                                <option value="${month}" selected="true">
                                                    ${month}
                                                </option>

                                            </c:if>
                                            <c:if test="${month != startMonth}">
                                                <option value="${month}">
                                                    ${month}

                                                </option>

                                            </c:if>
                                        </c:forEach>
                                        ${startMonth} 
                                    </select>

                                    <select name="selectDayOfMonth" onchange="this.form.submit()">
                                        
                                        <c:forEach begin="1" end="${maxNumOfDays}" varStatus="loop">
                                            <!--If day matches attribute of startDayOfMonth, have this option selected-->
                                            <c:if test="${startDayOfMonth == loop.index}">
                                                <option selected="true" value="${loop.index}">${loop.index}</option>
                                            </c:if>
                                            <c:if test="${startDayOfMonth != loop.index}">
                                                <option value="${loop.index}">${loop.index}</option>
                                            </c:if>
                                            <c:if test="${loop.index == startDayOfMonth and startDayOfMonth > loop.index}">
                                                <option value="${loop.index}" selected="true">${loop.index}</option>
                                            </c:if>
                                        </c:forEach>
                                            
                                    </select>

                                        <input type="number" min="${minYearFromAppt}" step="1" name="selectYear" value="${startYear}" onchange="this.form.submit()">
                                        at <select name="selectScheduleBlock" onchange="this.form.submit()">
                                            <c:forEach items="${schBlocks}" var="schb">
                                                <c:if test="${schBlock == schb}">
                                                    <option value="${schb}" selected="true">
                                                        ${schb}
                                                    </option>
                                                </c:if>
                                                
                                                <c:if test="${schBlock != schb}">
                                                    <option value="${schb}">
                                                        ${schb}
                                                    </option>
                                                </c:if>
                                                    
                                            </c:forEach>
                                        </select>
                                </form>
                            </td>
                        </tr>

                        <!--Service-->
                        <tr>
                            <td>Service Name: </td>
                            <td>${appt.getServiceID().getServiceName()}</td>
                        </tr>

                        <!--Vet-->
                        <tr>
                            <td>Vet:</td>
                            <td>${appt.getEmployeeID().getUserID().getFirstName()} ${appt.getEmployeeID().getUserID().getLastName()}</td>
                        </tr>
                    </table>

                    <table class="tableData">
                        <tr>
                            <td>Price:</td>
                            <td>$<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${appt.getServiceID().getBasePrice()}"/></td>
                        </tr>
                    </table>
                    
                    
                    <!--Update-->
                    <form action="Appointment" method="POST" class="miniForms">
                        
                        <input type="submit" value="Update">
                        <input type="hidden" name="action" value="updateAppt">
                        <input type="hidden" name="tempApptDate" value="${tempAppt.getAppointmentDate()}">
                               <span>${tempAppt.getAppointmentID()}</span>
                        
                        
                    </form>
                    <!--Cancel-->
                    <form action="Appointment" method="POST" class="miniForms">
                        <button type="submit" name="action" value="reqCancelAppt" class="dangerButton">Request Appointment Cancellation</button> 
                    </form>
                </div>
            </div>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
</html>
