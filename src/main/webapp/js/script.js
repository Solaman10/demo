var fromOption=null;
var toOption=null;//aed
var frmcrnc = null;
var tocrnc = null;
const dropList=document.querySelectorAll(".drop-list select");
fetch("https://api.exchangerate.host/latest").then((data) => data.json()).then((data) => {display(data.rates);});

function display(data) {
    console.log(data);
    entries = Object.entries(data);
    for(var i=0;i<entries.length;i++)
    {
        dropList[0].innerHTML += `<option value="${entries[i][0]}">${entries[i][0]}</option>`;
        dropList[1].innerHTML += `<option value="${entries[i][0]}">${entries[i][0]}</option>`;
    }
}

function removeToCurrency(){
    var fromDropdown=document.getElementById("fromCurrency");

    var toDropdown=document.getElementById("toCurrency");
    if(toOption!=null) {
        toDropdown.add(toOption);
    }
    for(var i=0;i<fromDropdown.options.length;i++){
        if(fromDropdown.options[i].selected)
        {
            toOption = document.createElement("option");
            toOption.text=fromDropdown.options[i].text;

            var removeOption = Array.from(toDropdown.options).find(item=> item.text ===toOption.text);
            toOption = removeOption;

            toDropdown.removeChild(removeOption);

            break;
        }
    }
}

function removeFromCurrency(){
    var toDropdown=document.getElementById("toCurrency");
    var fromDropdown=document.getElementById("fromCurrency");

    if(fromOption!=null) {
        fromDropdown.add(fromOption);
    }
    for(var i=0;i<toDropdown.options.length;i++){
        if(toDropdown.options[i].selected){
            var tempOption = document.createElement("option");
            tempOption.text=toDropdown.options[i].text;

            var removeOption = Array.from(fromDropdown.options).find(item=> item.text ===tempOption.text);
            fromOption=removeOption;
            fromDropdown.removeChild(removeOption);
            break;
        }
    }
}

























/*function swap() {
console.log("swap fnctn");
var frmCrncy = document.getElementById("fromCurrency");
var toCrncy = document.getElementById("toCurrency");
var frmOption = document.createElement("option");
var tOption = document.createElement("option");
for(var i=0;i<frmCrncy.options.length;i++)
{
   if(frmCrncy.options[i].selected)
   {
       var temOpt = document.createElement("option");
       temOpt.text = frmCrncy.options[i].text

       var rmvOpt = Array.from(frmCrncy.options).find(item=> item.text ===temOpt.text);
       frmcrnc = rmvOpt;
       console.log(frmcrnc);
       frmCrncy.removeChild(rmvOpt);
//           tempCrnc = "AED";
//           frmCrncy.add(tempCrnc);
   }

  if(toCrncy.options[i].selected)
  {
      var temOptn = document.createElement("option");
      temOptn.text = toCrncy.options[i].text;

      var rmveOpt = Array.from(toCrncy.options).find(item=> item.text ===temOptn.text);
      tocrnc = rmveOpt;
      console.log(tocrnc);
      toCrncy.removeChild(rmveOpt);
  }
  console.log(frmCrncy);
  console.log(toCrncy);
  *//*frmCrncy.add(tocrnc);
      toCrncy.add(frmcrnc);*//*
    }
    }*/








/*toDropdown.append($("#toCurrency option").remove().sort(function(a, b) {
                var at = $(a).text(),
                    bt = $(b).text();
                return (at < bt) ? -1 : ((at > bt) ? 1 : 0);
            }));
            console.log(fromDropdown);*/
