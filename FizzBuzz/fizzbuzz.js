"use strict";

function clearTable() {
    var table = document.getElementById("table");
    if (table) {
        var rows = table.firstChild;
        while (rows) {
            var cells = rows.firstChild;
            while (cells) {
                rows.removeChild(cells);
                cells = rows.firstChild;
            }
            table.removeChild(rows);
            rows = table.firstChild;
        }
        table.parentElement.removeChild(table);
    }
};

function validateInput(input) {
    var defaultNum = 100;
    if (isNaN(input)) {
        alert("Input is not a number! Defaulting to 100.");
        input = defaultNum;
    }

    if (input <= 0) {
        alert("Negative number detected! Using its absolute value.");
        input *= -1;
    }

    var y = parseInt(input);
    if (input != y) {
        alert("Float detected! Droppiing the floating points.");
        input = y;
    }

    return input;
};

function fizzbuzz(input, table) {  
    var row;
    var cell;
    var str = ""; 

    for (let i = input; i > 0; i--) {
        if (i%10 == 0 || i == input) {
            row = table.insertRow(0);
        }
        cell = row.insertCell(0);
        if (i%3 == 0) {
            str = "Fizz";
            cell.classList.add("fizz");
            if (i%5 == 0) {
                str += "Buzz";
                cell.classList.remove("fizz");
                cell.classList.add("fizzbuzz");
            }
            cell.innerHTML = str;
        } else if (i%5 == 0) {
            str = "Buzz";
            cell.classList.add("buzz");
            cell.innerHTML = str;
        } else {
            str = i;         
            cell.classList.add("num");  
            cell.innerHTML = str;
        } 
    }
};

function main() {
    var div = document.getElementById("divOutput");
    clearTable();
    var table = document.createElement("table");
    table.id = "table";
    div.appendChild(table);
  
    var input = document.getElementById("input").value;
    input = validateInput(input);

    fizzbuzz(input, table);
};

function checkEnter(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        main();
    }
};