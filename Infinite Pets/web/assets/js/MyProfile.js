/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function editProfile() {
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
    
    var btn = document.getElementById("editBtn");
    
    if (btn.value == "Edit") {
        document.getElementById("editBtn").value = "Save";
        document.getElementById("action").value = "save";
    }
    else if (btn.value == "Save") {
        document.getElementById("editBtn").value = "Edit";
        document.getElementById("action").value = "edit";
    }
}