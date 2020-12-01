const searchTermInput = document.getElementById("search-input");
searchTermInput.addEventListener("change", handleChangeToSearchTerm);
const selectionDisplay = document.getElementById("display-selection");
const displayContainer = document.getElementById("display-container");

function handleChangeToSearchTerm(event){
    const searchTerm = parseInt(event.target.value);
    // const url = "https://age-of-empires-2-api.herokuapp.com/api/v1/civilization/";
    // const urlBegin = "https://xkcd.com/";
    // const urlEnd = "/info.0.json";
    const apiURL = 'https://pokeapi.co/api/v2/ability/';

    
    //show selection:
    selectionDisplay.innerHTML = `Your Selection: ${searchTerm}`;

    //make xhttp request:
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = receiveData;
    xhttp.open("GET", apiURL + searchTerm);
    xhttp.send();

    function receiveData(){
        displayContainer.innerHTML = "";

        if(xhttp.readyState === 4){
            if(xhttp.status === 200){
                const responseText = xhttp.responseText;
                const json = JSON.parse(responseText);
                const names = json["names"];

                populateData(names);
            }else{
                console.log("Failure!");
                displayContainer.innerHTML = "Service Unavailable...";
            }
        }
    }



    
}

function populateData(names){
    //update display:
    const list = document.createElement("ul");
    for(let name of names){
        const listItem = document.createElement("li");
        console.log(name);
        listItem.innerHTML = `Language: ${name["language"]["name"]} \n`;
        listItem.innerHTML += name.name;

        list.appendChild(listItem);
    }
    displayContainer.appendChild(list);
}
