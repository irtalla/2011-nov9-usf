/*
    MUST BE FILLED IN TO CUSTOMIZE EXAMPLE
*/
// Endpoint you are sending a GET request to
var apiURL = 'https://api.openbrewerydb.org/breweries?by_city=';

document.getElementById('getData').onclick = getData;

async function getData() {
    let userInput = document.getElementById('dataInput').Value;

    document.createElement('data').innerHTML = '';

    let response = await fetch(apiURL + userInput);

    if(response.status >= 200 && response.status < 300){
        let data = await response.json();
        populateData(data);
    }else{
        dataSection.innerHTML = "Not 200 status";
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
        let entry = "" + individual.name + "<br />" + individual.postal_code + "<br /> <a id = "+ "site" +"href = " + individual.website_url +"> " + individual.website_url + "</a>" + "<br /> <hr />" ;
        breweriesLi.innerHTML = entry;
        breweries.appendChild(breweriesLi);
    }

    dataSection.appendChild(breweries);

    let webPrev = document.getElementById('window-two');
    let 

}