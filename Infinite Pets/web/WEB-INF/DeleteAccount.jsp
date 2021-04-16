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
            <div class="path">
                <div class="link1">
                    <a href="<c:url value='/MyProfile'></c:url>">My Profile</a>
                </div>
                <div class="chevron">
                    <img id="chevronRight" src="assets/img/chevronRight.svg" alt="">
                </div>
                <div class="link2">
                    <a>Delete Account</a>
                </div>
            </div>

        
            <div class="deleteAccountContainer">                
            
                
                <c:if test="${deleteAccountVerified == false}">
                        <div class="confirmDelete">
                        <div class="confirmTitle">
                            <h1>Delete Account</h1>
                        </div>
                        <div class="confirmMessage">
                            <p>We will send an email to you to confirm your account deletion.</p>
                        </div>
                        <!-- <form name="deleteAccountForm" method="POST"> -->
                            <div class="deleteBtn">
                                <button type="submit" name="action" value="deleteAccount">Delete Account</button>
                            </div>
                            <div class="cancelBtn">
                                <button type="submit" name="action" value="cancel">Cancel</button>
                            </div>
                        <!-- </form> -->
                    </div>
                </c:if>
                

                <!-- If deleteAccount parameter is not null, then present 
                the successful account deletion. Otherwise, present button to send an email-->
                <c:if test="${deleteAccountVerified == true}">
                    <div class="deleted">
                        <div class="deletedTitle">
                            <h1>Your account has been deleted.</h1>
                        </div>
                        <div class="deletedMessage">
                            <a href="/Login">Return to Login page</a>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer> 
    </body>
    
</html>
