var button = document.getElementById("run");
button.addEventListener('click', fizzbuzz);

var tableDiv = document.getElementById('outputDiv');

function fizzbuzz() {
    let max = -1;

    let input = document.getElementById("maxInput");
    max = input.value;

    //dont know how to cast yet
    /*if(typeof max != typeof 1){
        console.log(typeof max);
        console.log(typeof 1);
        console.log("invalid input");
    }*/

    if (max < 0) {
        console.log("invalid input");
        return;
    }

    for (let i = 1; i < max; i++) {
        if (i % 3 == 0 && i % 5 == 0) {
            console.log("fizzbuzz");
        } else if (i % 3 == 0) {
            console.log("fizz");
        } else if (i % 5 == 0) {
            console.log("buzz");
        } else {
            console.log(i);
        }
    }

    tableDiv.innerHTML = '';
    let table = document.createElement('table');
    let rows = max;
    let tr;
    let td;
    for (let i = 1; i < rows; i++) {
        tr = document.createElement('tr');

        td = document.createElement('td');
        td.innerHTML = i;
        tr.appendChild(td);

        td = document.createElement('td');
        if (i % 3 == 0 && i % 5 == 0) {
            td.innerHTML = 'fizzbuzz';
        } else if (i % 3 == 0) {
            td.innerHTML = 'fizz';
        } else if (i % 5 == 0) {
            td.innerHTML = 'buzz';
        } else {
            td.innerHTML = i;
        }

        tr.appendChild(td);

        table.appendChild(tr);
    }

    tableDiv.appendChild(table);
}