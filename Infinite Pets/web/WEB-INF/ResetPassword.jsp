<%-- 
    Document   : RecoverPassword
    Created on : Mar 14, 2021, 9:32:40 PM
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
        <link rel="stylesheet" href="assets/css/ResetPassword.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>Reset Password</title>
        <%@include file="testFiles/header.jsp" %> 
    </head>
    <body>
        <div class="wrapper">
            <form id="resetForm" method="post">
                <div class="resetPasswordContainer">
                    <div class="resetTitle">
                        <h1>Reset Your Password</h1>
                    </div>
                    <div class="instruction">
                        <h3>Please enter your login email:</h3>
                    </div>


                    
                        <div class="resetEmail">
                            <label for="email">Email:</label>
                            <input type="text" name="email" id="email">
                        </div>
                        <div class="send">
                            <button type="submit">Send new password</button>
                        </div>
                    

                    <div class="message">
                        <label>${message}</label>
                    </div>
                </div>
            </form>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %></footer> 
    </body>
     
</html>
