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
                    <c:if test="${errorMsg != null}">
                        <div id="errorBox">${errorMsg}</div>
                    </c:if>
                   
                    <form action="Appointment" method="POST">
                        <table class="tableData">

                            <tr>
                                <td>Appointment Date: </td>
                                <td> 
                                    <select name="selectMonth">
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

                                    <input type="number" step="1" max="31" min="1" name="selectDayOfMonth" value="${startDayOfMonth}">

                                    <input type="number" min="${minYearFromAppt}" step="1" name="selectYear" value="${startYear}">
                                    at <select name="selectScheduleBlock">
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

                        <input type="submit" value="Update">
                        <input type="hidden" name="action" value="updateAppt">
                                                <input type="hidden" name="apptId" value="${appt.getAppointmentID()}">
                    </form>
                            <br/>
                    <form action="Appointment" method="POST">
                        <!--Cancel-->
                        <input type="submit" value="Cancel Appointment" class="dangerButton">
                        <input type="hidden" name="action" value="reqCancelAppt">
                        <input type="hidden" name="apptId" value="${appt.getAppointmentID()}">
                    </form>
                </div>
            </div>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
</html>
