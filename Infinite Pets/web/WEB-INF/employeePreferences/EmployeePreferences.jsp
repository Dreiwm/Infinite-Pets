<%-- 
    Document   : EmployeePreferences
    Created on : 9-Apr-2021, 10:17:38 AM
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
        <%@include file="../testFiles/header.jsp" %>
        <title>Employee Preferences</title>
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
                        <a>Work Preferences</a>
                    </div>
                </div>
                <div class="generalContainer">
                <c:if test="${errorMsg != null}">  
                    <div id="errorBox">
                        ${errorMsg}
                    </div>
                </c:if>
                    
                    <!--Table of sorts here, showing what preferences currently have-->
                    <!--Also will contain buttons: delete and add-->
                    <!--delete will be for per row-->
                    <!--add button will be at bottom row (empty)-->
                    <h1>Work Preferences</h1>
                    <table>
                        <thead>
                            <tr>
                                <th>Work Preference</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--This is where JSTL comes in--> 
                        <c:forEach items="${empPreferenceList}" var="empPreference">

                        <tr>
                            <td>${empPreference.getServiceTypeID().getServiceType()}</td>
                        
                        <td>
                            <!--// delete button-->
                        <form action="WorkPreferences" method="GET">
                            <input type="submit" name="action" value="Delete">
                            <input type="hidden" name="empPrefID" value="${empPreference.getEmpServicePreferenceID()}">
                        </form>
                        </td>
                    </c:forEach>
                        </tr>
                        <tr>
                            <td>service tyep 1</td>
                            <td><input type="submit" value="Delee"> </td>
                        </tr>
                        
                    <tr>
                    <form action="WorkPreferences" method="POST">

                        <td>
                            <label for="serviceTypeSelection">Service Type: </label>
                            <select name="selectServiceType">
                                <c:forEach items="${serviceTypes}" var="serviceType">
                                    <option value="${serviceType.getServiceTypeID()}">${serviceType.getServiceType()}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="Add">
                            <input type="hidden" name="action" value="add">
                        </td>
                    </form>
                    </tr>
                    </tbody>
                </table>
                    
                <!--Btn to go back to MyProfile-->
                <a href="<c:url value='/MyProfile'></c:url>">Return to MyProfile</a>
            </div>
        </div>
    </body>
</html>
