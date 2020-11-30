/*print all the fizz buzz stuff in the html div tag

var createFizzBtn = document.getElementsByTagName('button');
var fuzzDiv = document.getElementById('tableDiv');

createFizzBtn.onclick = createFizz;

function createFizz(){
    createFizzBtn.removeEventListener('click', createFizz);

}

*/

function logMessageFromInput(){
    var number = document.getElementById('messageInput').value; 
    console.log(number);
    for (var i=1; i < number; i++ ){
        if (i % 3 == 0 && i % 5 == 0) document.write("FizzBuzz <br>");
        else if (i % 5 == 0) document.write("Buzz <br>") ;
        else if (i % 3 == 0) document.write("Fizz <br>");
        else document.write(i + "<br>" );
    }
}