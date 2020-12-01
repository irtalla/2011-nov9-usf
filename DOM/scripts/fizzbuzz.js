

function fizzbuzz() {

    var userInput = document.getElementById("input").value;
    //  console.log(userInput);
    
    for (let i = 1; i <= userInput; i++ ) {
        var para = document.createElement("p");
        if ( i % 3 == 0 && i % 5 == 0) {
            para.innerHTML = "FIZZBUZZ";
            document.getElementById("myDiv").appendChild(para);
        } else if (i % 3 == 0)  {
            para.innerHTML = "FIZZ";
            document.getElementById("myDiv").appendChild(para);
        } else if  (i % 5 == 0) {
            para.innerHTML = "Buzz";
            document.getElementById("myDiv").appendChild(para);
        }  else {
            para.innerHTML = i;
            document.getElementById("myDiv").appendChild(para);
        }
    }
}