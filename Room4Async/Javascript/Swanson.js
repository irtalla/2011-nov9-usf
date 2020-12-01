"use strict";

var apiURL = 'https://ron-swanson-quotes.herokuapp.com/v2/quotes';

document.getElementById("getData").addEventListener('click',getData);
document.getElementById("clearQuotes").addEventListener('click',clearQuotes);

function getData()
{
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = processData;
    xhttp.open('GET', apiURL);
    xhttp.send();

    function processData()
    {
        if(xhttp.readyState === 4)
        {
            if(xhttp.status === 200)
            {
                let response = xhttp.responseText;
                response = JSON.parse(response);
                displayData(response);
            }
        }
    }

}


function displayData(response)
{
    let section = document.getElementById("data");
    let displayplace = document.createElement("p");
    displayplace.innerHTML = response[0];
    section.appendChild(displayplace);
}

function clearQuotes()
{
    let section = document.getElementById("data");
    while(section.firstChild)
    {
        section.removeChild(section.firstChild);
    }
}
