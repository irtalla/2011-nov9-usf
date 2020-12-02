
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
            if (i === 0) td = document.createElement('th'); 
            else td = document.createElement('td');			
				if ((i % 3 == 0) && (i % 5 == 0)) td.innerHTML = "FizzBuzz";
				else if (i % 3 == 0) td.innerHTML = "Fizz"; 
				else if (i % 5 == 0) td.innerHTML = "Buzz";
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

