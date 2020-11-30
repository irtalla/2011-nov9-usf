// Line Comments

/*
    Block Comments
*/

//"use strict"; // See line 83.

/* Functions */




function logMessageFromInput() {
    // JavaScript is dynamically-typed. 
    var message = 5;
    message = true;
    message = document.getElementById('messageInput').value;
    console.log(message);
}


function fizzBuzzFunction() {
    // JavaScript is dynamically-typed. 
    max = document.getElementById('messageInput').value;
	let i = 1;
	while(i <= max){
		let localVar = i;
		if(i % 3 == 0 && i % 5 == 0){
			localVar = "FizzBuzz";
		}
		else if(i % 3 == 0){
			localVar = "Fizz";
		}
		else if(i % 5 == 0){
			localVar = "Buzz";
		} else{
			localVar = i;
		}
		if(i > 100){
			break;
		}
		console.log(localVar);
		i++;
	}
}


var elementsWithClassRev = document.getElementsByClassName('rev');
var helloSpan = elementsWithClassRev[0];

var spanElements = document.getElementsByTagName('span');
helloSpan = spanElements[0];

helloSpan = document.getElementById('hello');

var clickMeButton = document.getElementsByTagName('button')[0];

/*
    Manipulating the DOM: Changing Elements
*/
// Adding event listener.
var div1 = document.getElementById('tableDiv');

function addListenersToDivs() {
    div1.addEventListener('mouseover', selectDiv, capturing);
    div1.addEventListener('mouseout', deselectDiv, capturing);
}

function removeListenersFromDivs() {
    div1.removeEventListener('mouseover', selectDiv, capturing);
    div1.removeEventListener('mouseout', deselectDiv, capturing);
}

function selectDiv() {
    this.style.borderTop = '2px solid orange';
    console.log(this.id);
}

function deselectDiv() {
    this.style.border = 'none';
}


var createTableBtn = document.getElementsByTagName('button')[0];
var tableDiv = document.getElementById('tableDiv');
createTableBtn.onclick = createTable;

function createTable() {
    createTableBtn.removeEventListener('click', createTable);

    message = document.getElementById('messageInput').value;
	
    var table = document.createElement('table');
    var rows = message;
	
	let i = 1;
	while(i <= rows){
		let localVar = i;
        tr = document.createElement('tr');
		
		if(i % 3 == 0 && i % 5 == 0){
			localVar = "FizzBuzz";
		}
		else if(i % 3 == 0){
			localVar = "Fizz";
		}
		else if(i % 5 == 0){
			localVar = "Buzz";
		} else{
			localVar = i;
		}
		if(i > 100){
			break;
		}
        var td;
		td = document.createElement('td');
		td.innerHTML = localVar;
        tr.appendChild(td);
        table.appendChild(tr);
		i++;
	}
	/*
    for (var i = 0; i < rows; i++) {
        var tr;
        tr = document.createElement('tr');
        for (var j = 0; j < cols; j++) {
            var td;
            if (i === 0) td = document.createElement('th');
            else td = document.createElement('td');
            td.innerHTML = j;
            tr.appendChild(td);
        }
        
        table.appendChild(tr);
    }
	*/
	
    tableDiv.appendChild(table);

    createTableBtn.innerHTML = 'Undo';
    createTableBtn.onclick = removeTable;
}

function removeTable() {
    createTableBtn.removeEventListener('click', removeTable);
    tableDiv.innerHTML = '';
    createTableBtn.innerHTML = 'FizzBuzz';
    createTableBtn.onclick = createTable;
}
