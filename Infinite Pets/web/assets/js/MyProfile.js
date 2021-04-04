/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function editProfile() {
        
    var btn = document.getElementById("editBtn");
    
    if (btn.value === "Edit") {
        //change the values of the buttons to save
        document.getElementById("editBtn").value = "Save";
        document.getElementById("action").value = "save";
        
        //set fields to be editable
        document.getElementById("firstName").readOnly = false;
        document.getElementById("lastName").readOnly = false;
        document.getElementById("address").readOnly = false;
        document.getElementById("area").readOnly = false;
        document.getElementById("city").readOnly = false;
        document.getElementById("prov").readOnly = false;
        document.getElementById("country").readOnly = false;
        document.getElementById("postal").readOnly = false;
        document.getElementById("email").readOnly = false;
        document.getElementById("password").readOnly = false;
    }
    
    else if (btn.value === "Save") {
        //change the values of the buttons to edit
        document.getElementById("editBtn").value = "Edit";
        document.getElementById("action").value = "edit";
        
        //set it back to uneditable
        document.getElementById("firstName").readOnly = true;
        document.getElementById("lastName").readOnly = true;
        document.getElementById("address").readOnly = true;
        document.getElementById("area").readOnly = true;
        document.getElementById("city").readOnly = true;
        document.getElementById("prov").readOnly = true;
        document.getElementById("country").readOnly = true;
        document.getElementById("postal").readOnly = true;
        document.getElementById("email").readOnly = true;
        document.getElementById("password").readOnly = true;
    }
}