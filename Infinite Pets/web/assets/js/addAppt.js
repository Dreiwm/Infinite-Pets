        function addAppointment(){
            var div = document.getElementById("tempTableSpace");
            var final = document.getElementById("detailsTable");
            var temp = div.innerHTML; 
            div.innerHTML="";
            final.innerHTML+=temp;

        }

        function removeBtn(parent, selector){
            var toDelete = document.getElementById(parent);
            var select = document.getElementById(selector);
            if(select!=null)
                select.readOnly = false;
            toDelete.remove();
        }

        function sub_form_handler(){
            alert('Submit pressed');
            return true;
        }

        function addService()
        {
            var val = document.getElementById("petSelect");
            var petId = getSelectedOption(val).value;
            addPet(petId);
            getDate();
            getTime();
            val = document.getElementById("serviceSelect");
            var serviceType = getSelectedOption(val).value;
            
            var service = document.getElementById(serviceType+petId);
            if(service != null) return;

            var table = document.getElementById("service"+petId);
            var row = table.insertRow();
            row.setAttribute("id",serviceType+petId);
            var cell = row.insertCell();
           
            content = document.createElement("input");
            content.setAttribute("name",serviceType);
            content.setAttribute("value",serviceType);
            content.readOnly = true;
            content.style = "border: none;margin-left: 10px;";
            cell.appendChild(content);

            cell.appendChild(createRemoveBtn(serviceType+petId));

        }

        function addPet(petId){
            
            var petTable = document.getElementById("service"+petId);
            if(petTable != null) {
                return;}

            var table = document.createElement("TABLE");
            table.setAttribute("id","service"+petId);
            document.getElementById("detailsTable").appendChild(table);

            var row = table.insertRow();
            row.setAttribute("id",petId);
            document.getElementById("service"+petId).appendChild(row);

            var cell = row.insertCell()
            var content = document.createElement("input");
            content.setAttribute("name","petName");
            content.setAttribute("value",petId);
            content.readOnly = true;
            content.style = "border: none";

            cell.appendChild(content);
            cell.appendChild(createRemoveBtn("service"+petId));
            document.getElementById(petId).appendChild(cell);

           
        }


        function getDate(){
            var check = document.getElementById("setDate");
            //check if there is a chosen date
            if(check!=null)
                return;
            //create the input to display
            var chosen = document.getElementById("selectDate");
            var set = document.createElement("input");
            set.setAttribute("id","setDate");
            set.setAttribute("name","setDate");
            //set the values from the date input
            set.value = chosen.value;
            set.readOnly=true;
            chosen.readOnly=true;
            // create a new div so that you can add the remove btn  
            var div = document.createElement("div");
            div.setAttribute("id","dateDiv");
            document.getElementById("displayDate").appendChild(div);
            //add everything to the page
            div.appendChild(set);
            div.appendChild(createRemoveBtn("dateDiv","selectDate"));
        }

        function getTime(){
            var check = document.getElementById("setTime");

            if(check!=null)
                return;
            
            var chosen = getSelectedOption(document.getElementById("selectTime"));
            var set = document.createElement("input");
            set.setAttribute("id","setTime");
            set.setAttribute("name","setTime");
            set.value = chosen.value;
            set.readOnly = true;
            document.getElementById("selectTime").readOnly = true;

            var div = document.createElement("div");
            div.setAttribute("id","timeDiv");
            document.getElementById("displayTime").appendChild(div);

            div.appendChild(set);
            div.appendChild(createRemoveBtn("timeDiv","selectTime"));
        }

        function createRemoveBtn(parent,selector){
            btn = document.createElement("button");
            btn.class = "remove";
            btn.innerHTML = "Remove";
            btn.addEventListener("click",function(event){removeBtn(parent,selector)});
            btn.type = "button";
            return btn;
        }

        function getSelectedOption(sel){
            var opt;
                for( var i = 0, len = sel.options.length; i < len; i++){
                    opt = sel.options[i];
                    if (opt.selected === true)
                        break;
                }
            return opt;
        }