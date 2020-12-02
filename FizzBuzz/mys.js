/*
    MUST BE FILLED IN TO CUSTOMIZE EXAMPLE
*/
// Endpoint you are sending a GET request to
let apiURL = 'https://catfact.ninja/fact?max_length=';

document.getElementById('getData').onclick = getData;

async function getData() {
	let userInput = document.getElementById('dataInput').value;
	document.getElementById('data').innerHTML = '';
		
    let response = await fetch(apiURL + userInput);
		
    if (response.status === 200) {
        let data = await response.json();
        populateData(data);
    } else {
        dataSection.innerHTML = 'Not working!';
    }
}


function populateData(response) {
    let dataSection = document.getElementById('data');
    
    let nameTag = document.createElement('h3');
    nameTag.innerHTML = response.fact;
	
	dataSection.appendChild(nameTag);
	dataSection.innerHTML += 'Fact<br>';
}

