
// let x = 0;
// function logMessageFromInput() {
//     let message = document.getElementById('messageInput').value;
//     if (message < 1 || message > 100) {
//         console.log ("Please input a number between 1 and 100")
//     } else {
//         x =message +1
//         for (let i = 1; i <=message; i++) {
//             if (i % 3 == 0 && i % 5 == 0) {
//                 console.log ("Fizzbuzz");
//             } else if (i % 3 == 0) {
//                 console.log ("Fizz");
//             } else if (i % 5 == 0) {
//                 console.log ("Buzz");
//             } else {
//                 console.log(i);
//             }
//         }
//     }

// }




var createTableBtn = document.getElementsByTagName('button')[0];
var tableDiv = document.getElementById('tableDiv');

createTableBtn.onclick = createTable;


function createTable() {
    createTableBtn.removeEventListener('click', createTable);
    let message = document.getElementById('messageInput').value;

    var table = document.createElement('table');

    for (let i = 0; i <=message; i++) {
        var tr;
        tr = document.createElement('tr');
        var td;
        if (i==0) {
            td = document.createElement('th');
            td.innerHTML = `FizzBuzz up to ${message}`;
            tr.appendChild(td);
        }
        else if (i % 3 == 0 && i % 5 == 0)  {
            td = document.createElement('td');
            td.innerHTML = 'FizzBuzz';
            tr.appendChild(td);
        }else if (i % 3 == 0) {
            td = document.createElement('td');
            td.innerHTML = 'Fizz';
            tr.appendChild(td);
        }else if (i % 5 == 0) {
            td = document.createElement('td');
            td.innerHTML = 'Buzz';
            tr.appendChild(td);
        }else {
            td = document.createElement('td');
            td.innerHTML = i;
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



// console.log('hello world')