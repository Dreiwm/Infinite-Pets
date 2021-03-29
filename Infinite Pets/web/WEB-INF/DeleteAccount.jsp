<%-- 
    Document   : DeleteAccount
    Created on : 24-Mar-2021, 4:25:44 PM
    Author     : Riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/DeleteAccount.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>Delete Account</title>
        <%@include file="testFiles/header.jsp" %>
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
