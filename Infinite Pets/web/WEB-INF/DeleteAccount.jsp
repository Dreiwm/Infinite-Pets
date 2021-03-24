<%-- 
    Document   : DeleteAccount
    Created on : 24-Mar-2021, 4:25:44 PM
    Author     : Riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="testFiles/header.jsp" %>
        <title>Delete Account</title> <!-- Overwrite title in header.jsp -->
        <link rel="stylesheet" href="assets/css/deleteAccount.css"/>
    </head>
    <body>

        <div class="wrapper">
            <div class="generalContainer">
            <h1>Delete Account</h1>
            <form>
                    <button type="submit" name="action" value="deleteAccount" class="dangerButton">Delete Account</button>
                    <button type="submit" name="action" value="cancel">Cancel</button>
            </form>
        </div>
        </div>
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
</html>
