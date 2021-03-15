
function addAppointment(){
    //moves the appointments from temp table to the final space  
    var div = document.getElementById("tempTableSpace");
    var final = document.getElementById("detailsTable");
    var temp = div.innerHTML;      
    div.innerHTML="";
    final.innerHTML+=temp;
}

function removeService(serviceId){
    var toDelete = document.getElementById(serviceId);
    toDelete.remove();
}

function removePet(petId){
    var toDelete = document.getElementById(petId);
    toDelete.remove();
}

function addService()
{
    var val = document.getElementById("petSelect");
    var petId = getSelectedOption(val).value;
    //check if the pet is there or not
    addPet(petId);
    val = document.getElementById("serviceSelect");
    var serviceType = getSelectedOption(val).value;
    
    //check if the action would create a dup service
    var service = document.getElementById(serviceType+petId);
    if(service != null) return;

    //insert into table service[PetID] the new service
    var table = document.getElementById("service"+petId);
    var row = table.insertRow();
    row.setAttribute("id",serviceType+petId);
    var cell = row.insertCell();
   
    //create a new input and then set the required attributes
    content = document.createElement("input");
    content.setAttribute("name",serviceType);
    content.setAttribute("value",serviceType);
    content.disabled = true;
    content.style = "border: none;margin-left: 10px;";
    cell.appendChild(content);

    //create the removal button and set its attributes
    btn = document.createElement("button");
    btn.id = "serviceRemove";
    btn.innerHTML = "Remove";
    btn.onclick = function(){removeService(serviceType+petId)};
    cell.appendChild(btn);

}
function addPet(petId){
    //checking to see if the pet has been made or not
    var petTable = document.getElementById("service"+petId);
    if(petTable != null) {
        return;}

    //creating a new table and assinging it the id service[PetID]
    var table = document.createElement("TABLE");
    table.setAttribute("id","service"+petId);
    document.getElementById("tempTableSpace").appendChild(table);

    //adding a row and assinging it the id id[PetID]
    var row = table.insertRow();
    row.setAttribute("id",petId);
    document.getElementById("service"+petId).appendChild(row);

    //creating a col and then creating a text box that will have the pet name as its value
    var cell = row.insertCell()
    var content = document.createElement("input");
    content.setAttribute("name","petName");
    content.setAttribute("value",petId);
    content.disabled = true;
    content.style = "border: none";
    
    //creating and setting the atttributes for the removal button
    btn = document.createElement("button");
    btn.id = "serviceRemove";
    btn.innerHTML = "Remove";
    btn.onclick = function(){removePet("service"+petId)};
    
    //setting the input box and button
    cell.appendChild(content);
    cell.appendChild(btn);
    document.getElementById(petId).appendChild(cell);
}


function getSelectedOption(sel){
    var opt;
        //for each option check if it is selected and if so break from the loop and return that option
        for( var i = 0, len = sel.options.length; i < len; i++){
            opt = sel.options[i];
            if (opt.selected === true)
                break;
        }
    return opt;
}
