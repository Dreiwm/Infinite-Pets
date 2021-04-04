<%-- 
    Document   : MyProfile
    Created on : Mar 24, 2021, 4:04:47 PM
    Author     : BTran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/MyProfile.css">

        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <script src="assests/js/MyProfile.js"></script> 

         <title>User Profile</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>
        <div id="container"><div id="profile"><h1>My Profile</h1>
        <form method="POST" action="MyProfile">
            <table> 
                <tr><td>First Name:</td><td><input type="text" name="firstName" value=${firstName} readonly></td></tr>
                <tr><td>Last Name:</td><td><input type="text" name="lastName" value=${lastName} readonly></td></tr>
                <tr><td>Street Address:</td><td><input type="text" name="address" value=${address} readonly></td></tr>
                <tr><td>Area:</td><td><input type="text" name="area" value=${area} readonly></td></tr>
                <tr><td>City:</td><td><input type="text" name="city" value=${city} readonly></td></tr>
                <tr><td>Province:</td><td><input type="text" name="prov" value=${prov} readonly></td></tr>
                <tr><td>Country:</td><td><input type="text" name="country" value=${country} readonly></td></tr>               
                <!--NOTE POSTAL IS MISSING LAST 3 DIGITS-->
                <tr><td>Postal Code:</td><td><input type="text" name="postal" value=${postal} readonly></td></tr>
                <tr><td>Email:</td><td><input type="text" name="email" value=${email} readonly></td></tr>
                <tr><td>Password:</td><td><input type="password" name="password" value=${password} readonly></td></tr>
            </table>
            <div id="saveBtn" style="display:none" value="btn">
                <input type="hidden" name="action" value="save">
                <input type="submit" value="Save">
            </div>
            <button onclick="editProfile" id="editBtn">Edit</button>
        </form>
        <form method="get" action="DeleteAccount">
            
            <button type="submit" id="delBtn">Delete Account</button>
        </form>
    </body>
        </div></div><br>
    </body>
    <footer><%@include file="testFiles/footer.jsp" %>  </footer> -->

</html>
