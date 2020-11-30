//This is a fizzbuzz app!!!!!!!

function logMessageFromInput(){
    var number = document.getElementById('messageInput').value; 
    console.log(number);
    for (var i=1; i < number; i++ ){
        if (i % 3 == 0 && i % 5 == 0) console.log("FizzBuzz");
        else if (i % 5 == 0) console.log("Buzz");
        else if (i % 3 == 0) console.log("Fizz");
        else console.log(i);
    }
}