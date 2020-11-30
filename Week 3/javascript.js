var fizzbtn= document.getElementById("mybtn");
//console.log(fizzbtn.value);
var el = document.getElementById("demo");
var list=document.getElementById("list");
     
fizzbtn.onclick= myFunction;
function myFunction() {
fizzbtn.removeEventListener('click',myFunction);
    // }
  //  document.getElementById("mybtn").value="Close Curtain";
  
    var start = 1;
      var end = document.getElementById("end").value;
      var i;
      var output = "";
      var lit=document.createElement('ol');
      for (i = start; i <= end; i++) {
        var li;
        if (i % 3 == 0 && i % 5 == 0){
          
        li=document.createElement('li');
       li.style.background='Red';
       li.innerHTML='FizzBuxzz';
         // output += 'FizzBuzz </br>';
        }
          //Display number as "FizzBuzz"
        else if (i % 3 == 0 && i % 5 != 0){
                
        li=document.createElement('li');
        li.style.background='SlateGrey';
        li.innerHTML='Fizz';
         // output += 'Fizz </br>';
        }
        else if (i % 3 != 0 && i % 5 == 0){
                
        li=document.createElement('li');
        li.style.background='DimGrey';
        li.innerHTML='Buzz';
        // output += 'Buzz </br>';
        }
        else{
                
        li=document.createElement('li');
        li.style.background='DarkGrey';
        li.innerHTML=i;
         // output += i+' </br>';      
        }
        lit.appendChild(li);
      }
      list.appendChild(lit);
     // fizzbtn.innerHTML='Remove fizz';
      el.innerHTML = output;
      fizzbtn.innerText='Remove FizzBuzz';
      fizzbtn.onclick=removeBuzz;

    
    
    }
    function myf2(){

        var li=document.createElement('ol');
        var ele=document.createElement('li');



    }
    function removeBuzz()
    {
        fizzbtn.removeEventListener('click',removeBuzz);
        list.innerHTML='';
        fizzbtn.innerText='Create Buzz';
        fizzbtn.addEventListener('click',myFunction);


    }