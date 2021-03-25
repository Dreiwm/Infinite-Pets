<%-- 
    Document   : RecoverPassword
    Created on : Mar 14, 2021, 9:32:40 PM
    Author     : Chris
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
        <title>Reset Password</title>
    </head>
    <body>
        <div>
            <h2>Reset Your Password</h2>
            <p>
            Please enter your login email:
            </p>

            <form id="resetForm" action="reset_password" method="post">
                <table class="center">
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email" id="email" size="20"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <button type="submit">Send me new password</button>
                        </td>
                    </tr>    
                </table>
            </form>
        </div>
        
        
</script>    
    </body>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
</html>
