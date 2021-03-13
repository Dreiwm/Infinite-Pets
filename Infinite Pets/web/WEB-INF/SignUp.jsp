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
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Create an Account</h1>
        <form>
            <table>
                <tr><td><c:if test="${errorFirst != null}"><p>${errorFirst}</p></c:if></td></tr> <!--helper tag for first name -->
                <tr><td>First Name:</td><td><input type="text" name="firstName" placeholder="First Name" required="true"></td></tr>
                <tr><td><c:if test="${errorLast != null}"><p>${errorLast}</p></c:if></td></tr> <!--helper tag for last name-->
                <tr><td>Last Name:</td><td><input type="text" name="lastName" placeholder="Last Name" required="true"></td></tr>
                <tr><td><c:if test="${errorEmail != null}"><p>${errorEmail}</p></c:if></td></tr> <!--helper tag for email-->
                <tr><td>Email:</td><td><input type="text" name="email" placeholder="Email" required="true"></td></tr>
                <tr><td><c:if test="${errorEmail != null}"><p>${errorEmail}</p></c:if></td></tr> <!--helper tag for email-->
                <tr><td>Confirm Email:</td><td><input type="text" name="emailConfirm" placeholder="Confirm Email" required="true"></td></tr>
                <tr><td><c:if test="${errorPass != null}"><p>${errorPass}</p></c:if></td></tr> <!--helper tag for password-->
                <tr><td>Password:</td><td><input type="password" name="password" placeholder="Password" required="true"></td></tr>
                <tr><td><c:if test="${errorPass != null}"><p>${errorPass}</p></c:if></td></tr> <!--helper tag for password-->
                <tr><td>Confirm Password:</td><td><input type="password" name="passwordConf" placeholder="Confirm Password" required="true"></td></tr>
                <tr><td><c:if test="${errorAddress != null}"><p>${errorAddress}</p></c:if></td></tr> <!--helper tag for address-->
                <tr><td>Address:</td><td><input type="text" name="address" placeholder="123 Street NW" required="true"></td></tr>
                <tr><td><c:if test="${errorPostal != null}"><p>${errorPostal}</p></c:if></td></tr> <!--helper tag for postal code-->
                <tr><td>Postal Code:</td><td><input type="text" name="postalCode" placeholder="A1A 1A1" required="true" pattern="([A-Z][0-9][A-Z] [0-9][A-Z][0-9])"></td></tr>
                
                <!--Assuming they are all local they will be from Alberta, Canada-->
<!--                <tr><td><c:if test="${errorProv != null}"><p>${errorProv}</p></c:if></td></tr> //helper tag for password
                <tr><td>Province:</td><td><input type="text" name="province" placeholder="Confirm Password" required="true"></td></tr>
                <tr><td><c:if test="${errorCountry != null}"><p>${errorCountry}</p></c:if></td></tr> //helper tag for password
                <tr><td>Country:</td><td><input type="text" name="country" placeholder="Confirm Password" required="true"></td></tr>-->
                
            </table> <br>
            <input type="hidden" name="action" value="create">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
