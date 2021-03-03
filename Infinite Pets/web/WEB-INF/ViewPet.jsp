<%-- 
    Document   : ViewPet
    Created on : Mar 3, 2021, 12:58:10 PM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Pet</title>
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
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Neutered">Neutered</option>
                    <option value="Spaded">Spaded</option>
                </select>
            <br>
            <label for=""> Select animal type</label>
            <br>
            <input type="text" name="animal"readonly>
            <br>
            <label for=""> Select animal breed</label>
            <br>
            <input type="text" name="breed"readonly>
            </select>
            <br>
            <label for="">Birthday</label>
            <br>
            <input type="date" name="birthday" id="bday">
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
        
        <div></div> 
     
    </body>
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
</html>
