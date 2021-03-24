<%-- 
    Document   : Login
    Created on : Mar 10, 2021, 7:24:28 PM
    Author     : BTran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
</html>
