//                            var fromOption=null;
//                            var toOption=null;//aed
//                            const dropList=document.querySelectorAll(".drop-list select");
//                            fetch("https://api.exchangerate.host/latest")
//                            .then((data) => data.json())
//                            .then((data) => {
//                                display(data.rates);
//                            });
//
//                            function display(data) {
//
//
//                              entries = Object.entries(data);
//                              for(var i=0;i<entries.length;i++)
//                              {
//
//                              dropList[0].innerHTML += `<option value="${entries[i][0]}">${entries[i][0]}</option>`;
//                              dropList[1].innerHTML += `<option value="${entries[i][0]}">${entries[i][0]}</option>`;
//
//                              }
//                            }

                           /* function removeToCurrency(){
                                var dropdown=document.getElementById("fromCurrency");
                                var todropdown=document.getElementById("toCurrency");
                                for(var i=0;i<dropdown.options.length;i++){
                                if(dropdown.options[i].disabled){
                                console.log(dropdown.options[i]);
                                todropdown.removeChild(todropdown.options[i]);
                                break;
                                }

                                }
                                console.log(dropdown);
                            }*/

                            function removeToCurrency(){


                             var toDropdown=document.getElementById("toCurrency");

                             var option1=document.createElement("option");
                             option1.text="Sola";

                             toDropdown.add(option1);
                             var fromDropdown=document.getElementById("fromCurrency");
                             option1=document.createElement("option");
                                                          option1.text="Sola";

                                                          fromDropdown.add(option1);
//                            if(toOption!=null) {
//                                   console.log("added"+toOption.text);
//                                   toDropdown.add(toOption);
//                                   console.log(toDropdown);
//                                }
//                            for(var i=0;i<fromDropdown.options.length;i++){
//                            if(fromDropdown.options[i].selected){
//                            toOption = document.createElement("option");
//                            toOption.text=fromDropdown.options[i].text;
////                            var optionToDel = toDropdown
//                            //console.log(option2.text);
//                              var removeOption = Array.from(toDropdown.options).find(item=> item.text ===toOption.text);
//                              toOption = removeOption;
////                            console.log(toDropdown.text);
//                            toDropdown.removeChild(removeOption);
//                            console.log("removed"+removeOption.text);
//                            break;
//                            }
                        //}

                                                    }

                            function removeFromCurrency(){
                            var option1=document.createElement("option");
                                                         option1.text="Sola";
                                                         var option2=document.createElement("option");
                                                          option1.text="Sola";
//                            console.log(option.text);
                                var fromDropdown=document.getElementById("fromCurrency");
//                                 console.log("toDropDown "+Array.from(fromDropdown.options));
                                                               fromDropdown.add(option1);

//                                if(fromOption!=null) {
//                                       console.log("added"+fromOption.text);
//                                       fromDropdown.add(fromOption);
////                                       console.log(fromOption);
//
//                                }
//                                for(var i=0;i<toDropdown.options.length;i++){
//                                if(toDropdown.options[i].selected){
//                                fromOption = document.createElement("option");
//                                fromOption.text=toDropdown.options[i].text;
//                                fromOption.value =toDropdown.options[i].text;
////                                console.log(fromOption);
//                                //console.log(option1.text);
//                                var removeOption = Array.from(fromDropdown.options).find(item=> item.text ===fromOption.text);
//                                fromOption=removeOption;
//                                fromDropdown.removeChild(removeOption);
//                                console.log("removed"+removeOption.text);
//                                break;
//                                }
//                                                            }

                                                        }
