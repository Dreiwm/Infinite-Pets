<%-- 
    Document   : Login
    Created on : Mar 10, 2021, 7:24:28 PM
    Author     : BTran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form>
            <table>
                <tr><td>Login</td><td><input type="text" name="userName" placeholder="Email"></td></tr>
                <tr><td>Password</td><td><input type="password" name="password" placeholder="Password"></td></tr>
            </table>
            <input type="hidden" name="action" value="login">
            <input type="Submit" value="Log In">
        </form>
        <p>Need an account?</p><p>Sign-Up</p>
    </body>
</html>-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/main.css">
    <link rel="stylesheet" href="assets/css/Login.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
   
    <title>Login</title>
</head>
<body>
    
        
        <div class="navbar">
            <div class="navItem">
                <img id="menu" src="assets/img/menu.svg">
            </div>
            <div id="navItem" class="navItem">
                <img id="logo" src="assets/img/infinitePetsLogo-White(4).png">
            </div>
            <div id="navItem" class="navItem">
                <img id="account" src="assets/img/account.svg">
            </div>
        </div>

        <div class="wrapper">
        
            <div class="loginContainer">
                <div class="photo">
                    <img id="loginLogo"  src="assets/img/zacLoginPhotojpg.jpg" alt="">
                </div>
                <div class="inputs">
                    <div class="title">
                        <h1>Login</h1>
                    </div>
                    <div class="userLabel">
                        <label for="username">Username</label>
                    </div>
                    <div class="userInput">
                        <input id="username" name="username" type="username">
                    </div>
                    <div class="passLabel">
                        <label for="password">Password</label>
                    </div>
                    <div class="passInput">
                        <input id="password" name="password" type="password">
                    </div>
                    <div class="createAccount">
                        <a id="createAccount" href="CreateAccount">Create Account</a>
                    </div>
                </div>
            </div>
    </div>

    </div>
        
</body>
</html>