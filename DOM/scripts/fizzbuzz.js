

function fizzbuzz() {

    var userInput = document.getElementById("input").value;
    console.log(userInput);
    
    for (let i = 1; i <= userInput; i++ ) {
        let para = document.createElement("p");
        if ( i % 3 == 0 && i % 5 == 0) {
        para.innerHTML = "FIZZBUZZ";
        para.appendChild("myDiv");
        } else if (i % 3 == 0)  {
            para.innerHTML = "FIZZ";
            para.appendChild("myDiv");
        } else if  (i % 5 == 0) {
            para.innerHTML = "Buzz";
            para.appendChild("myDiv");
        }  else {
            para.innerHTML = i;
            para.appendChild("myDiv");
        }
    }
}