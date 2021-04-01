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
        <link rel="stylesheet" href="assets/css/MyProfile.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <%@include file="testFiles/header.jsp" %>
        <title>User Profile</title>
    </head>
    <body>
        <div id="container"><div id="profile"><h1>My Profile</h1>
            <div id="table"><form>
            <table>
                <tr><td>First Name:</td><td><input type="text" name="firstName" placeholder="First Name" readOnly></td></tr>
                <tr><td>Last Name:</td><td><input type="text" name="lastName" placeholder="Last Name" readOnly></td></tr>
                <tr><td>Street Address:</td><td><input type="text" name="address" placeholder="123 Sesame Street" readOnly></td></tr>
                <tr><td>Area:</td><td><input type="text" name="area" placeholder="N, S, W, E" readOnly></td></tr>
                <tr><td>City:</td><td><input type="text" name="city" placeholder="Calgary" readOnly></td></tr>
                <tr><td>Province:</td><td><input type="text" name="prov" placeholder="Alberta" readOnly></td></tr>
                <tr><td>Country:</td><td><input type="text" name="country" placeholder="Canada" readOnly></td></tr>
                <tr><td>Postal Code:</td><td><input type="text" name="postal" placeholder="A1A 1A1" pattern="([A-Z][0-9][A-Z] [0-9][A-Z][0-9])" readOnly></td></tr>
                <tr><td>Email:</td><td><input type="text" name="email" placeholder="Email" readOnly></td></tr>
                <tr><td>Password:</td><td><input required type="password" name="password" placeholder="Password" readOnly></td></tr>
            </table> <br></div>
        <div id="btns">
            <input type="hidden" name="action" value="edit">
            <input type="submit" name="editBtn" value="Edit" onclick="editProfile()">
                </form><br>       
        <form method="get" action="DeleteAccount">
            <button type="submit" id="delBtn">Delete Account</button>
            </form></div></div></div><br>
    </body>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</html>
