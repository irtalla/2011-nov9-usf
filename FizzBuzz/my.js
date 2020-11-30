
var createTableBtn = document.getElementsByTagName('button')[1];
var tableDiv = document.getElementById('tableDiv');

createTableBtn.onclick=createTable;

function createTable() {
    createTableBtn.removeEventListener('click', createTable);

    var table = document.createElement('table');
    var rows = document.getElementById('limit').value;
    var cols = 1;
    for (var i = 0; i <= rows; i++) {
        var tr = i;
        tr = document.createElement('tr');
        for (var j = 0; j < cols; j++) {
            var td;
            if (i === 0) td = document.createElement('th'); // Interesting interaction- because we are looping from 0,
            else td = document.createElement('td');			// the above line actually creates a header element which 
				if (i % 15 == 0) td.innerHTML = "FizzBuzz"; // we fill as bold 'FizzBuzz' because it is a header
				else if (i % 3 == 0) td.innerHTML = "Fizz"; // and the td.innerHTML for %15 == 0 is 'FizzBuzz'.
				else if (i % 5 == 0) td.innerHTMl = "Buzz";
				else td.innerHTML = i;
            tr.appendChild(td);
        }
        
        table.appendChild(tr);
    }
    tableDiv.appendChild(table);

    createTableBtn.innerHTML = 'Remove Table';
    createTableBtn.onclick = removeTable;
}

function removeTable() {
	createTableBtn.removeEventListener('click', removeTable);
	tableDiv.innerHTML = '';
	createTableBtn.innerHTML = 'Create Table';
	createTableBtn.onclick = createTable;
}

