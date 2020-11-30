"use strict";

var printBtn = document.getElementById("beginButton");
var butnNum;
printBtn.addEventListener('click',printFizzBuzz);
var listDiv = document.getElementById("listDiv");
var newList = null;

function findFizzBuzz(number)
{
    if (number % 3 == 0 && number % 5 == 0)
    {
        return "FizzBuzz";
    }
    else if(number % 3 == 0)
    {
        return "Fizz";
    }
    else if (number % 5 == 0)
    {
        return "Buzz";
    }
    else
    {
        return number;
    }
}

function printFizzBuzz ()
{
    if(newList != null)
    {
        removeList(newList);
    }
    butnNum = document.getElementById("numInput").value;
    let number = butnNum;
    newList = document.createElement('ul');
    if (number < 1 || number > 100)
    {
        console.log("Invalid Number Range!");
    }
    else{
        for(let i = 1; i <= number; i++)
        {
            let entry = document.createElement('li');
            entry.innerHTML = findFizzBuzz(i);
            newList.appendChild(entry);
        }
    }

    listDiv.appendChild(newList);
    listDiv.style.width = "125px";
    listDiv.style.backgroundColor = "#cc2168";
    listDiv.style.color = "#4621cc";
}

function removeList(alist)
{
    listDiv.removeChild(alist);
}


