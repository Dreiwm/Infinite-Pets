/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function editProfile() {
        

        document.getElementById("editBtn").style.display='none';
        //change the values of the buttons to save
        document.getElementById("saveBtn").style.display='block';
        
        //set fields to be editable
        document.getElementById("firstName").readonly = false;
        document.getElementById("lastName").readonly = false;
        document.getElementById("address").readonly = false;
        document.getElementById("area").readonly = false;
        document.getElementById("city").readonly = false;
        document.getElementById("prov").readonly = false;
        document.getElementById("country").readonly = false;
        document.getElementById("postal").readonly = false;
        document.getElementById("email").readonly = false;
        document.getElementById("password").readonly = false;

}