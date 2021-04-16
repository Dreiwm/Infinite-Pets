<%-- 
    Document   : NewServices
    Created on : Apr 15, 2021, 11:58:28 PM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service</title>
    </head>
    <body>
        <h1>Service</h1>
        <form action="Service" method="POST">
            <table>
                <tr><td>Name:</td><td><input type="text" name="name" class="inputFields" value="${service.serviceName}"></td></tr>
                <tr><td>Price:</td><td><input type="number" name="price" class="inputFields" value="${service.basePrice}"></td></tr>
                <tr><td>Description:</td><td><input type="text" name="description" class="inputFields" value="${service.serviceDiscription}"></td></tr>
                <tr><td>Active:</td><td><select name="active" class="inputFields"><option value="${service.active}" selected>${service.active}</option>
                        <option value="${!service.active}" selected>${!service.active}</option></select></td></tr>
            </table>
            <input type="hidden" name="serviceID" value="${serviceID}">
            <div id="saveBtn" value="btn">
                <input type="hidden" name="action" value="${action}">
                <input type="submit" value="Save">
            </div>
        </form>
        <div id="cancelBtn"><form method="get" action="Employment">
            <button type="submit" id="cancel">Cancel</button>
        </form></div>
    </body>
    <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    </body>
</html>
