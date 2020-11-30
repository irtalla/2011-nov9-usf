let outSpan = document.getElementById("output");
let fizzButton = document.getElementById("fizzButton");

fizzButton.onclick = fizzbuzz;

function fizzbuzz(){

 let input = document.getElementById("fbInput").value;
 let result = "";
 for(let i = 1; i <= input; i++){
     if(i % 3 == 0 && i % 5 == 0){
         result += "FizzBuzz, ";
     }else if(i % 3 == 0){
         result += "Fizz, ";
     }else if(i % 5 == 0){
         result += "Buzz, ";
     }else{
     result += (i + ", ");
     }
 }

 result = result.substring(0, result.length-2);
 console.log(result);
 outSpan.innerHTML = `<strong>
 ${result}!
</strong>`;

}

