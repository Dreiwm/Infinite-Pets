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
        <script src="assests/js/MyProfile.js"></script> 
        <%@include file="testFiles/header.jsp" %>
        <title>User Profile</title>
    </head>
    <body>
        <div id="container"><div id="profile"><h1>My Profile</h1>
        <form method="POST" action="MyProfile">
            <table>
                <tr><td>First Name:</td><td><input type="text" name="firstName" value=${firstName} readOnly></td></tr>
                <tr><td>Last Name:</td><td><input type="text" name="lastName" value=${lastName} readOnly></td></tr>
                <tr><td>Street Address:</td><td><input type="text" name="address" value=${address} readOnly></td></tr>
                <tr><td>Area:</td><td><input type="text" name="area" value=${area} readOnly></td></tr>
                <tr><td>City:</td><td><input type="text" name="city" value=${city} readOnly></td></tr>
                <tr><td>Province:</td><td><input type="text" name="prov" value=${prov} readOnly></td></tr>
                <tr><td>Country:</td><td><input type="text" name="country" value=${country} readOnly></td></tr>
                
                <!--NOTE POSTAL IS MISSING LAST 3 DIGITS-->
                <tr><td>Postal Code:</td><td><input type="text" name="postal" value=${postal} readOnly></td></tr>
                <tr><td>Email:</td><td><input type="text" name="email" value=${email} readOnly></td></tr>
                <tr><td>Password:</td><td><input type="password" name="password" value=${password} readOnly></td></tr>
            </table> <br>        
            <div id="saveBtns">
                <input type="hidden" name="action" value="edit">
                <input type="submit" name="editBtn" value="Edit">
            </div>
        </form><br>
        <form method="get" action="DeleteAccount">
            <button type="submit" id="delBtn">Delete Account</button>
        </form></div></div><br>
    </body>
    <footer><%@include file="testFiles/footer.jsp" %>  </footer>
</html>
