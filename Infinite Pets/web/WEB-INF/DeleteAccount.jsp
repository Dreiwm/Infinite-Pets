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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/DeleteAccount.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>Delete Account</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>

        <div class="wrapper">
            <div class="generalContainer">
                <!-- If deleteAccount parameter is not null, then present 
                the successful account deletion. Otherwise, present button to send an email-->
                <c:if test="${deleteAccountVerified == true}">
                    <h1>
                        Your account had been deleted.
                    </h1>
                    <p>
                        <a href="/Login">Return to Login page</a>
                    </p>
                </c:if>
                
                <c:if test="${deleteAccountVerified == false}">
                    <h1>Delete Account</h1>
                    <span>We will send an email to you to confirm your account deletion.</span>
                    <br/>
                    <form name="deleteAccountForm" method="POST">
                            <button type="submit" name="action" value="deleteAccount" class="dangerButton">Delete Account</button>
                            <button type="submit" name="action" value="cancel">Cancel</button>
                    </form>
                </c:if>
            </div>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
    
</html>
