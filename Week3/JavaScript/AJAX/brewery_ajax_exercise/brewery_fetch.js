/*
    MUST BE FILLED IN TO CUSTOMIZE EXAMPLE
*/
// Endpoint you are sending a GET request to
var apiURL = 'https://api.openbrewerydb.org/breweries?by_city=';

document.getElementById('getData').onclick = getData;

async function getData() {
    let userInput = document.getElementById('dataInput').value; 

    document.getElementById('data').innerHTML = '';

    let response = await fetch(apiURL + userInput);

    if (response.status === 200) {
        let data = await response.json();
        populateData(data);
    } else {
        dataSection.innerHTML = 'Error with request!';
    }
}

function populateData(response) {
    let dataSection = document.getElementById('data');
    
    console.log("success");

    let nameTag = document.createElement('h3');
    console.log(response)
    let breweriesArray = response
    let breweries = document.createElement('ul');
    for (let individual of breweriesArray){
        let breweriesLi = document.createElement('li');
        let entry = "" + individual.name + "<br />" + individual.postal_code + "<br /><br /> " + individual.phone + "<br /> <a href = " + individual.website_url +"> " + individual.website_url + "</a>" + "<br /> <hr />" ;
        breweriesLi.innerHTML = entry;
        breweries.appendChild(breweriesLi);
    }

    dataSection.appendChild(breweries);
}