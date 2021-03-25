<%-- 
    Document   : MyProfile
    Created on : Mar 24, 2021, 4:04:47 PM
    Author     : BTran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <%@include file="testFiles/header.jsp" %>
        <title>User Profile</title>
    </head>
    <body>
        <h1>My Profile</h1>
        <form>
            <table>
                <tr><td>First Name:</td><td><input type="text" name="firstName" placeholder="First Name"></td></tr>
                <tr><td>Last Name:</td><td><input type="text" name="lastName" placeholder="Last Name"></td></tr>
                <tr><td>Email:</td><td><input type="text" name="email" placeholder="Email"></td></tr>
                <tr><td>Confirm Email:</td><td><input type="text" name="emailConfirm" placeholder="Confirm Email"></td></tr>
                <tr><td>Password:</td><td><input type="password" name="password" placeholder="Password"></td></tr>
                <tr><td>Confirm Password:</td><td><input type="passwordConfirm" name="firstName" placeholder="Confirm Password"></td></tr>
            </table>
            <input type="hidden" name="action" value="create">
            <input type="submit" value="Submit">
        </form>
    </body>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</html>
