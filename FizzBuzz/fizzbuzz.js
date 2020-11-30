let input = document.getElementById('maxnum').value;
let outputDisplay ="";
for (let i = 1; i <= input; i++){
 if (i%15==0) outputDisplay += "FizzBuzz ";
 else if (i%5==0) outputDisplay +="Buzz ";
 else if (i%3==0) outputDisplay +="Fizz ";
 else outputDisplay += i +" ";
 document.getElementById("output").innerHTML = outputDisplay; 
}
    
