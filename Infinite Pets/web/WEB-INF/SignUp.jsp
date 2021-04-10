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
        <form method="POST" action="SignUp">
            <div class="accountContainer">
                <div class="photo">
                    <img src="assets/img/SignUp.jpg" alt="">
                </div>
                <div class="title">
                    <h1>Create a new Account</h1>
                </div>
<!--                <div class="firstname">
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
                </div>-->
                <div class="firstname">
                    <table>
                            <tr><td>First Name:</td><td><input type="text" name="firstName" placeholder="First Name" required></td></tr>
                            <tr><td>Last Name:</td><td><input type="text" name="lastName" placeholder="Last Name" required></td></tr>
                            <tr><td>Street Address:</td><td><input type="text" name="address" placeholder="123 Sesame Street" required></td></tr>
                            <tr><td>Area:</td><td><input type="text" name="area" placeholder="N, S, W, E" required></td></tr>
                            <tr><td>City:</td><td><input type="text" name="city" placeholder="Calgary" required></td></tr>
                            <tr><td>Province:</td><td><input type="text" name="prov" placeholder="Alberta" required></td></tr>
                            <tr><td>Country:</td><td><input type="text" name="country" placeholder="Canada" required></td></tr>
                            <tr><td>Postal Code:</td><td><input type="text" name="postal" placeholder="A1A 1A1" pattern="([A-Z][0-9][A-Z] [0-9][A-Z][0-9])" required></td></tr>
                            <tr><td>Email:</td><td><input type="text" name="email" placeholder="Email" required></td></tr>
                            <tr><td>Confirm Email:</td><td><input type="text" name="emailConf" placeholder="Email" required></td></tr>
                            <tr><td>Password:</td><td><input required type="password" name="password" placeholder="Password" required></td></tr>
                            <tr><td>Confirm Password:</td><td><input required type="password" name="passwordConf" placeholder="Password" required></td></tr>
                        </table>
                </div>
                <div class="createbtn">
                    <input type="hidden" name="action" value="create">
                    <button name="createAccount" id="createAccount" type="submit">Create Account</button>
                </div>
            </div>
        </form>
    </div>

<!-- <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/MyProfile.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <
        <title>Sign Up</title>
    </head>
    <body>
        <div id="container"><div id="profile"><h1>Create an Account</h1>
            <div id="table"><form method="POST" action="SignUp">
            <table>
                <tr><td>First Name:</td><td><input type="text" name="firstName" placeholder="First Name" required></td></tr>
                <tr><td>Last Name:</td><td><input type="text" name="lastName" placeholder="Last Name" required></td></tr>
                <tr><td>Street Address:</td><td><input type="text" name="address" placeholder="123 Sesame Street" required></td></tr>
                <tr><td>Area:</td><td><input type="text" name="area" placeholder="N, S, W, E" required></td></tr>
                <tr><td>City:</td><td><input type="text" name="city" placeholder="Calgary" required></td></tr>
                <tr><td>Province:</td><td><input type="text" name="prov" placeholder="Alberta" required></td></tr>
                <tr><td>Country:</td><td><input type="text" name="country" placeholder="Canada" required></td></tr>
                <tr><td>Postal Code:</td><td><input type="text" name="postal" placeholder="A1A 1A1" pattern="([A-Z][0-9][A-Z] [0-9][A-Z][0-9])" required></td></tr>
                <tr><td>Email:</td><td><input type="text" name="email" placeholder="Email" required></td></tr>
                <tr><td>Confirm Email:</td><td><input type="text" name="emailConf" placeholder="Email" required></td></tr>
                <tr><td>Password:</td><td><input required type="password" name="password" placeholder="Password" required></td></tr>
                <tr><td>Confirm Password:</td><td><input required type="password" name="passwordConf" placeholder="Password" required></td></tr>
            </table> <br></div>
            <input type="hidden" name="action" value="create">
            <input type="submit" value="Create Account">
        </form><br></div></div><br>
    </body> -->

    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</body>
</html>