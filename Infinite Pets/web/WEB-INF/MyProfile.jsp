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
        <script src="assests/js/MyProfile.js"></script> <!-- this is not fetching for some reason -->

         <title>User Profile</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <script>
    function editProfile() {
    
        var saveBtn = document.getElementById("saveBtn");
        if (saveBtn.style.display === "none") {
            saveBtn.style.display = "block";
            document.getElementById("editBtn").style.display='none';

            //set fields to be editable
//            document.getElementByName("firstName").readOnly = false;
//            document.getElementByName("lastName").readOnly = false;
//            document.getElementByName("address").readOnly = false;
//            document.getElementByName("area").readOnly = false;
//            document.getElementByName("city").readOnly = false;
//            document.getElementByName("prov").readOnly = false;
//            document.getElementByName("country").readOnly = false;
//            document.getElementByName("postal").readOnly = false;
//            document.getElementByName("email").readOnly = false;
//            document.getElementByName("password").readOnly = false;
            
            document.getElementsByClassName("inputFields").readOnly = false;
        } 
        else {
            saveBtn.style.display = "none";
            document.getElementById("editBtn").style.display='block';
        }
    }
    </script>
    <body>
        <div id="container"><div id="profile"><h1>My Profile</h1>
        <form method="POST" action="MyProfile">
            <table> 
                <tr><td>First Name:</td><td><input type="text" name="firstName" class="inputFields" value=${firstName} readOnly></td></tr>
                <tr><td>Last Name:</td><td><input type="text" name="lastName" class="inputFields" value=${lastName} readOnly></td></tr>
                <tr><td>Street Address:</td><td><input type="text" name="address" class="inputFields" value=${address} readOnly></td></tr>
                <tr><td>Area:</td><td><input type="text" name="area" class="inputFields" value=${area} readOnly></td></tr>
                <tr><td>City:</td><td><input type="text" name="city" class="inputFields" value=${city} readOnly></td></tr>
                <tr><td>Province:</td><td><input type="text" name="prov" class="inputFields" value=${prov} readOnly></td></tr>
                <tr><td>Country:</td><td><input type="text" name="country" class="inputFields" value=${country} readOnly></td></tr>               
                <!--NOTE POSTAL IS MISSING LAST 3 DIGITS-->
                <tr><td>Postal Code:</td><td><input type="text" name="postal" class="inputFields" value=${postal} readOnly></td></tr>
                <tr><td>Email:</td><td><input type="text" name="email" class="inputFields" value=${email} readOnly></td></tr>
                <tr><td>Password:</td><td><input type="password" name="password" class="inputFields" value=${password} readOnly></td></tr>
            </table>
            <div id="saveBtn" style="display:none" value="btn">
                <input type="hidden" name="action" value="save">
                <input type="submit" value="Save">
            </div>
        </form>
        <button onclick="editProfile()" id="editBtn" style="display:block">Edit</button>
        <form method="get" action="DeleteAccount">
            <button type="submit" id="delBtn">Delete Account</button>
        </form>
    </body>
        </div></div><br>
    </body>
    <footer><%@include file="testFiles/footer.jsp" %>  </footer>

</html>
