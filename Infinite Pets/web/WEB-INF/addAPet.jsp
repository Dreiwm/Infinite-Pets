<%-- 
    Document   : addAPet
    Created on : 8-Feb-2021, 3:38:37 PM
    Author     : Cashton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a Pet</title>
    </head>
        <header>
        infinite pets header goes here...
    </header>
    <body>
        <h1>Add a Pet</h1>
        <label for="">Pet Name</label>
        <br>
        <input type="text" name="petName" id="">
        <br>
        <label for="">Sex</label>
        <select name="sex" id="">
            <option value="male">male</option>
            <option value="female">female</option>
        </select>
        <br>
        <label for=""> Select animal type</label>
        <br>
        <select name="animal" id="">
            <option value="">
                animal type
            </option>
        </select>
        <br>
        <label for=""> Select animal breed</label>
        <br>
        <select name="animal" id="">
            <option value="">
                animal breed
            </option>
        </select>
        <br>
        <label for="">Birthday</label>
        <br>
        <input type="date" name="birthday" id="">
        <br>
        <label for="">Additional Info:</label>
        <br>
        <textarea name="info" id="" cols="30" rows="10">Any additional information we should know...</textarea>
        <br>
        <label for="">Add a picture</label>
        <br>
        <input type="image" src="" alt="">
        <form action="">
            <label for="">Upload an image:</label>
            <input type="image" alt="" accept="image/*">
            <input type="submit" value="Choose photo...">
        </form>
    </body>
    <br>
    <br>
    <br>
    <footer>
        infinite pets footer goes here...
    </footer>
</html>
