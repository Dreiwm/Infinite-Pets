<%-- 
    Document   : ViewPet
    Created on : Mar 3, 2021, 12:58:10 PM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/SignUp.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>View Pet</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>
        <button onclick="editPet()">EDIT</button>
        <form method="post">
            <label for="">Pet Name</label>
            <br>
            <input type="text" name="petName" id="petName" readonly>
            <br>
            <label for="">Sex</label>
                <select name="sex" id="sex" disabled="true">
                    <c:forEach items="${sex}" var="sexList">
                        <option value="${sexList}">${sexList}</option>
                    </c:forEach>
                </select>
            <br>
            <label for=""> Select animal type</label>
            <br>
            <input type="text" name="animal" readonly>
            <br>
            <label for=""> Select animal breed</label>
            <br>
            <input type="text" name="breed" readonly>
            </select>
            <br>
            <label for="">Birthday</label>
            <br>
            <input type="date" name="birthday" id="bday" readonly>
            <br>
            <label for="">Additional Info:</label>
            <br>
            <textarea name="medical" id="medical" cols="30" rows="10" placeholder="Any additional information we should know..."readonly></textarea>
            <br>
            <label for="">Vet</label>
            <br>
            <input type="text" name="vet" id="vet"readonly>
            <br>
            <label for="">Add a picture</label>
            <br>
            <input type="image" src="" alt="">
        
            <label for=""> an image:</label>
            <input type="image" alt="" accept="image/*">
            <input type="submit" value="Choose photo...">
            <br>
            <button type="submit" name="action" value="save" style="display:none" disabled="true" id="btnSave">Save</button>
            <button type="submit" name="action" value="cancel">Cancel</button>
            
        </form>
        
         
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    
    
       <script>
            function editPet() {
                document.getElementById("bday").readOnly = false;
                document.getElementById("vet").readOnly = false;
                document.getElementById("medical").readOnly = false;
              
                document.getElementById("sex").disabled = false;
               
                document.getElementById("btnSave").style.display='block';
                document.getElementById("btnSave").disabled= false;
            }
        </script>
        </body>
</html>
