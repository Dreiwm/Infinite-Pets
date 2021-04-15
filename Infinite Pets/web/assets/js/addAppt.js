 function setPet(){
            getDate();
            getTime();
            
            //get the pet and check to see if the value is null or if the element exists already
            var petCheck = getSelectedOption(document.getElementById("pet")).value;
            if(!petCheck||(document.getElementById(petCheck)!=null)){
                return;
            }
            //create the input 
            var content = document.createElement("input");
            content.setAttribute("name","petName");
            content.setAttribute("value",petCheck);
            content.readOnly = true;
            content.style = "border: none";
            //create a div to store the input into
            var div = document.createElement("div");
            div.setAttribute("id",petCheck); 
            //add the input to the div and create a remove button
            div.appendChild(content);
            div.appendChild(createRemoveBtn(petCheck));
            //add everything to the web page
            document.getElementById("petName").appendChild(div);
        }

        function  setService(){
            getDate();
            getTime();
            
            //get the service and check to see if the value is null or if the element exists already
            var serviceCheck = getSelectedOption(document.getElementById("service")).value;
            if(!serviceCheck||(document.getElementById(serviceCheck)!=null)){
                return;
            }
            //create the input 
            var content = document.createElement("input");
            content.setAttribute("name","serviceName");
            content.setAttribute("value",serviceCheck);
            content.readOnly = true;
            content.style = "border: none";
            //create a div to store the input into
            var div = document.createElement("div");
            div.setAttribute("id",serviceCheck); 
            //add the input to the div and create a remove button
            div.appendChild(content);
            div.appendChild(createRemoveBtn(serviceCheck));
            //add everything to the web page
            document.getElementById("petService").appendChild(div);
        }

        function getDate(){
            var check = document.getElementById("setDate");
            //check if there is a chosen date
            if(check!=null)
                return;
            //check to see if the date is null or not
            var chosen = document.getElementById("submitDate").value;
            if(!chosen)
                return;
            //create the input to display
            var set = document.createElement("input");
            set.setAttribute("id","setDate");
            set.setAttribute("name","setDate");
            //set the values from the date input
            set.value = chosen;
            set.readOnly=true;
            chosen.readOnly=true;
            // create a new div so that you can add the remove btn  
            var div = document.createElement("div");
            div.setAttribute("id","dateDiv");
            document.getElementById("sumDate").appendChild(div);
            //add everything to the page
            div.appendChild(set);
            div.appendChild(createRemoveBtn("dateDiv","submitDate"));
        }


        function getTime(){
            var check = document.getElementById("setTime");

            if(check!=null)
                return;
            
            var chosen = getSelectedOption(document.getElementById("submitTime"));
            var set = document.createElement("input");
            set.setAttribute("id","setTime");
            set.setAttribute("name","setTime");
            set.value = chosen.value;
            set.readOnly = true;
            document.getElementById("submitTime").readOnly = true;

            var div = document.createElement("div");
            div.setAttribute("id","timeDiv");
            document.getElementById("sumTime").appendChild(div);

            div.appendChild(set);
            div.appendChild(createRemoveBtn("timeDiv","submitTime"));
        }
        

        function createRemoveBtn(parent,selector){
            btn = document.createElement("button");
            btn.class = "remove";
            btn.innerHTML = "Remove";
            btn.addEventListener("click",function(event){removeBtn(parent,selector)});
            btn.type = "button";
            return btn;
        }

        function removeBtn(parent, selector){
            var toDelete = document.getElementById(parent);
            var select = document.getElementById(selector);
            if(select!=null)
                select.readOnly = false;
            toDelete.remove();
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