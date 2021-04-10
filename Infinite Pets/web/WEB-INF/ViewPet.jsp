
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/ViewPet.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
        <title>View Pet</title>
        <%@include file="testFiles/header.jsp" %>
    </head>
    <body>
        <div class="wrapper">
            <form method="post">
                <div class="viewPetContainer">
                    <div class="title">
                        <h1>${pet.name}</h1>
                    </div>

                    <div class="btnEdit">
                        <button onclick="editPet()">Edit</button>
                    </div>
                        
                        <div class="viewPetLeft">
                            <div class="petName">
                                <label for="petName">Name</label>
                                <input type="text" name="petName" id="petName" readonly>
                            </div>

                            <div class="petType">
                                <label for="animal">Animal type</label>
                                <select name="animal" id="animal" disabled>
                                    <option value="">
                                        Animal type
                                    </option>
                                    <c:forEach items="${animalList}" var="anlst">
                                        <option value="${anlst.animalType}">${anlst.animalType}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="petBreed">
                                <label for="breed">Animal breed</label>
                                <select name="breed" id="breed" disabled>
                                    <option value="">
                                        Breed
                                    </option>
                                    <c:forEach items="${breedList}" var="lst">
                                        <option value="${lst.breedName}">${lst.breedName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="petNotes">
                                <label for="medical">Additional Info:</label>                    
                                <textarea name="medical" id="medical" placeholder="Any additional information we should know..." readonly></textarea>
                            </div>
                        </div>

                        

                        
                        <div class="viewPetRight">
                            <div class="petSex">
                                <label for="sex">Sex</label>
                                <select name="sex" id="sex" disabled>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>

                            <div class="petBirthday">
                                <label for="birthday">Birthday</label>                    
                                <input type="date" name="birthday" id="birthday" readonly>
                            </div>

                            <div class="petVet">
                                <label for="vet">Vet</label>
                                <input type="text" name="vet" id="vet" readonly>
                            </div>

                            <div class="petPicture">
                                <label for="picture">Picture</label>
                                <!--<input name="picture" id="picture" type="image" accept="image/*" readonly>-->
                            </div>
                            
                            <div class="choosePicture">
                                <button type="submit" name="action" value="btnChoose" style="display: none;" disabled>Choose Photo...</button>
                                <!-- <input name="choosePhoto" type="submit" value="Choose photo..."> -->
                            </div>

                            <div class="saveBtn">
                                <button type="submit" name="action" value="btnSave" style="display: none;" disabled>Save</button>
                            </div>
                            <div class="cancelBtn">
                                <button type="submit" name="action" value="btnCancel">Cancel</button>
                            </div>
                        </div>
                    
                </div>

            </form>

            <div class="errMsg">
                <!--<p name="errorMsg"></p>-->
            </div>
            
            <p>
                <c:forEach items="${currentPets}" var="pets">
                    ${pets}
                </c:forEach>
            </p>
        </div>
         
        <footer> <%@include file="testFiles/footer.jsp" %> </footer>
    
    
       <script>
            function editPet() {
                document.getElementById("petName").readonly = false;
                document.getElementById("petType").readonly = false;
                document.getElementById("petBreed").readonly = false;



                document.getElementById("petBirthday").readOnly = false;
                document.getElementById("petVet").readOnly = false;
                document.getElementById("petNotes").readOnly = false;
              
                document.getElementById("petSex").disabled = false;

                document.getElementById("btnChoose").style.display='block';
               
                document.getElementById("btnSave").style.display='block';
                document.getElementById("btnSave").disabled= false;
            }
        </script>
        </body>
</html>
