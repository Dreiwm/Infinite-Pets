<%-- 
    Document   : DeleteAccount
    Created on : 24-Mar-2021, 4:25:44 PM
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
        <title>Delete Account</title> <!-- Overwrite title in header.jsp -->
        <link rel="stylesheet" href="assets/css/deleteAccount.css"/>
    </head>
    <body>

        <div class="wrapper">
            <div class="generalContainer">
            <h1>Delete Account</h1>
            <form>
                    <button type="submit" name="action" value="deleteAccount" class="dangerButton">Delete Account</button>                    
            </form>
            <form method="GET" action="MyProfile">
                    <button type="submit" name="action" value="cancel">Cancel</button>
            </form>
        </div>
        </div>
    </body>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</html>
