/*
    FILLED IN FOR USE WITH POKEAPI
*/
// Endpoint you are sending a GET request to
let apiURL = 'http://numbersapi.com/';
document.getElementById('getData').onclick = getData;
let apiForm = document.querySelector('#form');


async function getData() {
    
  let queryType = apiForm['mathtype'].value;
    // If using input for identifiers, etc.
    // For example, if using PokeAPI, this may be the Pokemon's ID.
    let userInput = document.getElementById('dataInput').value; 

    // Emptying out the div before inserting new data.
    document.getElementById('data').innerHTML = '';
    if(queryType === 'math')
    {
        let response = await fetch(apiURL + userInput+'/math?json');
        if (response.status === 200) {
            let data = await response.json();
            populateData(data);
        } else {
            dataSection.innerHTML = 'It Got Away!';
        }
    }
    else if(queryType === 'trivia')
    {
        let response = await fetch(apiURL + userInput+'/trivia?json');
        if (response.status === 200) {
            let data = await response.json();
            populateData(data);
        } else {
            dataSection.innerHTML = 'It Got Away!';
        }
    }
    else if(queryType === 'date')
    {
        let response = await fetch(apiURL + userInput+'/date?json');
        if (response.status === 200) {
            let data = await response.json();
            populateData(data);
        } else {
            dataSection.innerHTML = 'It Got Away!';
        }
    }
    else{
        dataSection.innerHTML = 'It Got Away!';
    }


}

function populateData(response) {
    let dataSection = document.getElementById('data');
    
    let nameTag = document.createElement('h3');
    nameTag.innerHTML =  capitalize(response.name);
    let abilitiesArray = response.abilities;
    let abilities = document.createElement('ul');

    dataSection.appendChild(nameTag);
    dataSection.innerHTML += 'Trivia Fact: ' + response.text + '<br>';
    dataSection.innerHTML += 'Number: ' + response.number + '<br>';
    dataSection.innerHTML += 'Type: ' + response.type + '<br>';
    dataSection.appendChild(abilities);


}

/*
    The PokeAPI returns Pokemon's information as all lowercase.
    This function is used to fix this when processing data.
*/
function capitalize(str) {
    if (str)
        return str.charAt(0).toUpperCase() + str.slice(1);
    else
        return '';
}