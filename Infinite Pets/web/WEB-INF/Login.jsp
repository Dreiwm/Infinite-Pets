<%-- 
    Document   : Login
    Created on : Mar 10, 2021, 7:24:28 PM
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
    <link rel="stylesheet" href="assets/css/Login.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
    <title>Login</title>
    <%@include file="testFiles/header.jsp" %>
</head>
<body>

        <div class="wrapper">
        
            <div class="loginContainer">
                <div class="photo">
                    <img id="loginLogo"  src="assets/img/zacLoginPhotojpg.jpg" alt="">
                </div>

                <form method="POST" action="Login">
                
                    <div class="inputs">
                        <div class="title">
                            <h1>Login</h1>
                        </div>
                        <div class="userLabel">
                            <label for="username">Username</label>
                        </div>
                        <div class="userInput">
                            <input id="username" name="username" type="text">
                        </div>
                        <div class="passLabel">
                            <label for="password">Password</label>
                        </div>
                        <div class="passInput">
                            <input id="password" name="password" type="password">
                        </div>
                        <div class="loginBtn">
                            <button type="submit">Login</button>
                        </div>
                        <div class="createAccount">
                            <a id="SignUp" href="SignUp">Sign Up</a>
                            
                        </div>
                    </div>
                </form>
            </div>
        </div>
  <footer> <%@include file="testFiles/footer.jsp" %> </footer>


<!--                 <div class="inputs">
                    <form action="Login" method="POST">
                    <div class="title">
                        <h1>Login</h1>
                    </div>
                    <div class="userLabel">
                        <label for="username">Email</label>
                    </div>
                    <div class="userInput">
                        <input id="username" name="username" type="email">
                    </div>
                    <div class="passLabel">
                        <label for="password">Password</label>
                    </div>
                    <div class="passInput">
                        <input id="password" name="password" type="password">
                    </div>
                    <div class="btns">
                        <button type="submit" name="action" value="login">Login</button>
                    </div>
                    </form>
                    <div class="createAccount">
                        <a id="createAccount" href="SignUp">Create Account</a>
                    </div>
                </div>
            </div>
    </div>
    </div> -->
  

</body>  

      
</html>
