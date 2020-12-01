"use strict";

var apiURL = 'https://ron-swanson-quotes.herokuapp.com/v2/quotes';

document.getElementById("getData").addEventListener('click',getData);
document.getElementById("clearQuotes").addEventListener('click',clearQuotes);

async function getData()
{
    let response = await fetch(apiURL);

    if (response.status === 200)
    {
        displayData(await response.json());
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
