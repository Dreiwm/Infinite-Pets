<%-- 
    Document   : CreateAccount
    Created on : Mar 10, 2021, 7:38:40 PM
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
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Create an Account</h1>
        <form>
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
                <tr><td>Confirm Email:</td><td><input required type="text" name="emailConfirm" placeholder="Confirm Email"></td></tr>
                <tr><td>Password:</td><td><input required type="password" name="password" placeholder="Password"></td></tr>
                <tr><td>Confirm Password:</td><td><input required type="passwordConfirm" name="firstName" placeholder="Confirm Password"></td></tr>
            </table>
            <input type="hidden" name="action" value="create">
            <input type="submit" value="Submit">
        </form>
    </body>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</html>
