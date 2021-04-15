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
    <script type="text/javascript">
        console.log("test js");
        function testing(){
            console.log("TEST FUNCTION");
            let answer = confirm("Are you sure? This action cannot be undone.<br/> Note: You can only cancel an appointment 24 hours prior.");

            if (answer === true) {
                this.form.submit();
            }
        }
    </script>
    <body>
        <div class="wrapper">
             <div class="path">
                <div class="link">
                    <a href="<c:url value='/MyAppointments'></c:url>">My appointments</a>
                </div>
                <div class="link">
                    <img id="chevronRight" src="assets/img/chevronRight.svg" alt="">
                </div>
                <div class="link">
                    <a>Edit Appointment</a>
                </div>
            </div>
            <div class="generalContainer">
                <h1>Appointment</h1>
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

                                    <input type="number" step="1" name="selectYear" value="${startYear}">
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
                                <td>
                                    <!--Need to loop through services-->
                                    <table>
                                    <c:forEach items="${apptServices}" var="apptService" varStatus="status">
                                        <tr>
                                            <td>${apptService.getServiceID().getServiceName()} with with ${apptService.getPetID().getPetName()} - $${apptService.getServiceID().getBasePrice()}</td>
                                            <!--Delete service-->
                                            <td>
                                                <a href="<c:url value='Appointment'></c:url>?action=deleteService&apptServiceId=${apptService.getAppServID()}&apptId=${appt.getAppointmentID()}">X</a>
                                            </td>
                                        </tr>
                                        <c:if test="${status.isLast()}">
                                            <tr>
                                                <td>
                                                    <a href="<c:url value='AddServiceToAppointment'></c:url>?apptId=${appt.getAppointmentID()}">Add Service</a>
                                                </td>
                                            </tr>
                                            </table>
                                        </c:if>
                                        <!--last item-->
                                    </c:forEach>
                                </td>
                            </tr>

                            <!--Vet-->
                            <tr>
                                <td>Employee:</td>
                                <td>
                                    <c:if test="${appt.getEmployeeID() != null}">
                                        ${appt.getEmployeeID().getUserID().getFirstName()}
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                                                    
                        <!--Update-->

                        <input type="submit" value="Update">
                        <input type="hidden" name="action" value="updateAppt">
                        <input type="hidden" name="apptId" value="${appt.getAppointmentID()}">
                        
                        <!--View Client Contract-->
                        <!--Will open a tab with plain html with print button and close button-->
                        <!--<a href="<c:url value='/ClientContract'></c:url>" target="_blank">View Contract</a>-->          

                    </form>
                    <br/>
                    <form action="Appointment" method="POST" id="cancelForm">
                        <!--Cancel-->
                        
                        <script type="text/javascript">
                            function confirmDelete() {
                                let answer = confirm("Are you sure? This action cannot be undone.<br/> Note: You can only cancel an appointment 24 hours prior.");
                                console.log(answer);
                                if (answer === true) {
                                    document.getElementById("cancelForm").submit();
                                }
                            }
                        </script>
                        
                        <!--<input type="submit" value="Cancel Appointment" class="dangerButton">-->
                        <button type="button" onclick="confirmDelete()">Cancel Appointment</button>

                        <!--<input type="submit" value="Cancel Appointment" class="dangerButton">-->
<!--                         <button onclick="testing()">Cancel Appointment</button> -->

                        <input type="hidden" name="action" value="reqCancelAppt">
                        <input type="hidden" name="apptId" value="${appt.getAppointmentID()}">
                    </form>
                </div>
            </div>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
</html>
