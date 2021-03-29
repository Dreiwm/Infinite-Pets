<%-- 
    Document   : CreateAccount
    Created on : Mar 10, 2021, 7:38:40 PM
    Author     : BTran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/main.css">
    <link rel="stylesheet" href="assets/css/SignUp.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
    <title>Sign Up</title>
    <%@include file="testFiles/header.jsp" %>
</head>
<body>
    <div class="wrapper">
        <form>
            <div class="accountContainer">
                <div class="photo">
                    <img src="assets/img/SignUp.jpg" alt="">
                </div>
                <div class="title">
                    <h1>Create a new Account</h1>
                </div>
                <div class="firstname">
                    <label for="fname">First Name:</label>
                    <input name="firstName" id="firstName" type="text">
                </div>
                <div class="lastname">
                    <label for="lastName">Last Name:</label>
                    <input name="lastName" id="lastName" type="text">
                </div>
                <div class="email">
                    <label for="email">Email:</label>
                    <input name="email" id="email" type="email">
                </div>
                <div class="pass1">
                    <label for="pass1">Password:</label>
                    <input name="password" id="password" type="password">
                </div>
                <div class="pass2">
                    <label for="pass2">Confirm Password:</label>
                    <input name="passwordConfirm" id="passwordConfirm" type="password">
                </div>
                <div class="createbtn">
                    <input type="hidden" name="action" value="create">
                    <button name="createAccount" id="createAccount" type="submit">Create Account</button>
                </div>
            </div>
        </form>
    </div>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</body>
</html>