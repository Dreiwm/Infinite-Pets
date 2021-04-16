<%-- 
    Document   : ConfermReset
    Created on : Mar 21, 2021, 11:51:58 PM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/ForgotPassword.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>Forgot Password</title>
        <!-- <%@include file="testFiles/header.jsp" %> -->

    </head>
    <body>
        <div class="wrapper">

            <form action="">
                <div class="forgotPasswordContainer">
                    <div class="forgotTitle">
                        <h1>Forgot Password</h1>
                    </div>
                
                    <div class="newPassLbl">
                        <p>New Password:</p>
                    </div>
                    <div class="newPass">
                        <input type="text" name="newPassword" id="newPassword" placeholder="New Password">
                    </div>
                    <div class="confirmPassLbl">
                        <p>Confirm Password:</p>
                    </div>
                    <div class="confirmPass">
                        <input type="text" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password">
                    </div>
                    <div class="submitBtn">
                        <button type="submit" value="submit">Submit</button>
                    </div>
                </div>
            </form>
        </div>

        <!-- <footer> <%@include file="testFiles/footer.jsp" %></footer> -->
    </body>
</html>
