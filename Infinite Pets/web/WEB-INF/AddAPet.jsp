<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/main.css">
    <link rel="stylesheet" href="assets/css/AddAPet.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
    <title>Add a Pet</title>
    <%@include file="testFiles/header.jsp" %>
</head>
    <body>
        <div class="wrapper">
            
            <form method="post" enctype="multipart/form-data">
                <div class="addPetContainer">
                    <div class="title">
                        <h1>Add a Pet</h1>
                    </div>
                    
                        
                        <div class="addPetLeft">
                            <div class="petName">
                                <label for="petName">Pet Name</label>
                                <input type="text" name="petName" id="petName">
                            </div>

                            <div class="petType">
                                <label for="animal"> Select animal type</label>
                                <select name="animal" id="animal">
                                    <option value="">
                                        Animal type
                                    </option>
                                    
                                    <c:forEach items="${animalList}" var="anlst">
                                        <option value="${anlst.animalType}" selected>${anlst.animalType}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            
                            
                            <div class="petBreed">
                                <label for="breed"> Select animal breed</label>
                                <select name="breed" id="breed">
                                    <option value="" id='default'>
                                        Animal breed
                                    </option>
                                    <c:choose>
                                        <c:when test="${anlst.animalType == 'Dog'}">
                                            <c:forEach items="${dogBreeds}" var="breed">
                                                <option value="${dogBreed.breedName}">${dogBreed.breedName}</option>
                                            </c:forEach>
                                            
                                        </c:when>
                                        <c:when test="${anlst.animalType == 'Cat'}">
                                            <c:forEach items="${catBreeds}" var="catBreed">
                                                <option value="${catBreed.breedName}">${catBreed.breedName}</option>
                                            </c:forEach>
                                            
                                        </c:when>
                                        <c:otherwise>    
                                        <c:forEach items="${exoticBreeds}" var="exoticBreed">
                                                <option value="${exoticBreed.breedName}">${exoticBreed.breedName}</option>
                                        </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                    <%--</c:forEach>--%>                                  
                                </select>
                            </div>

                            
                            <div class="petNotes">
                                <label for="medical">Additional Info:</label>                    
                                <textarea name="medical" id="medical" placeholder="Any additional information we should know..."></textarea>
                            </div>
                        </div>

                        
                        <div class="addPetRight">
                            <div class="petSex">
                                <label for="sex">Sex</label>
                                <select name="sex" id="sex">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>

                            <div class="petBirthday">
                                <label for="birthday">Birthday</label>                    
                                <input type="date" name="birthday" id="birthday">
                            </div>

                            <div class="petVet">
                                <label for="vet">Vet</label>
                                <input type="text" name="vet" id="vet">
                            </div>

                            <div class="petPicture">
                                <label for="picture">Add a picture</label>
                                <!--<input name="picture" id="picture" type="image" alt="image" accept="image/*">-->
                                 <input name="picture" id="picture" type="file" > 
                                <!-- <input type="submit" value="Choose photo..."> -->
                            </div>
                         

                            <div class="saveBtn">
                                <button type="submit" name="action" value="save">Save</button>
                            </div>
                            <div class="cancelBtn">
                                <button type="submit" name="action" value="cancel">Cancel</button>
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
<!--        <script>
    function setBreed(var breed) {
        console.log(breed);
    if (breed === "Dog"){
        document.getElementByClassName("dog").style.display = "block";
        document.getElementByClassName("cat").style.display = "none";
        document.getElementByClassName("exotic").style.display = "none";
    }
    else if (breed === "Cat"){
        document.getElementByClassName("dog").style.display = "none";
        document.getElementByClassName("cat").style.display = "block";
        document.getElementByClassName("exotic").style.display = "none";
    }
    else if (breed === "Bird"){
        document.getElementByClassName("dog").style.display = "none";
        document.getElementByClassName("cat").style.display = "none";
        document.getElementByClassName("exotic").style.display = "block";
    }}
</script>-->
    </body>
</html>
