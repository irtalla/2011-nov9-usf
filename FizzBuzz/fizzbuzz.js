//  let n = window.prompt("Enter a number between 1 - 100");

var createTableBtn = document.getElementsByTagName('button')[0];
var tableDiv = document.getElementById('tableDiv');

createTableBtn.onclick = createTable;

function createTable() {
    createTableBtn.removeEventListener('click', createTable);

    var table = document.createElement('table');
    var rows = (Math.floor(Math.random() * 99)) + 1;
    var cols = 1;
    for (var i = 0; i < rows; i++) {
        var tr;
        tr = document.createElement('tr');
        for (var j = 0; j < cols; j++) {
            var td;
            if (i === 0) {
                td = document.createElement('th');
            }
            else td = document.createElement('td');
            td.innerHTML = `<div class=${i % 3 === 0 && i % 5 === 0 ? "fizzbuzz" : i % 3 === 0 ? "fizz" : i % 5 === 0 ? 'buzz' : ''}>
            ${i}: ${i % 3 === 0 && i % 5 === 0 ? 'FizzBuzz': i % 3 === 0 ? 'Fizz' : 
            i % 5 === 0 ? 'Buzz' : ''} </div>`;
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



// function fizzBuzz(n) {
//     for (let i = 1; i <= n; i++) {
//         if (i % 3 === 0 && i % 5 === 0) { 
//             document.write(`<div class="fizzbuzz">${i}: FizzBuzz </div>`); 
//         } else if (i % 3 === 0) {
//             document.write(`<div class="fizz">${i}: Fizz </div>`);
//         } else if (i % 5 === 0) { 
//             document.write(`<div class="buzz">${i}: Buzz <div>`); 
//         }
//     }
// } 

// fizzBuzz(n);

      