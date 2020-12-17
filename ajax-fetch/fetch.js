const searchTermInput = document.getElementById("search-input");
searchTermInput.addEventListener("change", handleChangeToSearchTerm);
const selectionDisplay = document.getElementById("display-selection");
const displayContainer = document.getElementById("display-container");

async function handleChangeToSearchTerm(event){
    const searchTerm = parseInt(event.target.value);
    // const url = "https://age-of-empires-2-api.herokuapp.com/api/v1/civilization/";
    // const urlBegin = "https://xkcd.com/";
    // const urlEnd = "/info.0.json";
    const apiURL = 'https://pokeapi.co/api/v2/ability/';

    
    //show selection:
    selectionDisplay.innerHTML = `Your Selection: ${searchTerm}`;

    //make fetch request:
    let response = await fetch(apiURL + searchTerm);

   if(response.status === 200){
        let data = await response.json();
        let names = data["names"];
        populateData(names);
    }else{
        console.log("Failure!");
        displayContainer.innerHTML = "Service Unavailable...";
    }    
}

function populateData(names){
    //update display:
    const list = document.createElement("ul");
    for(let name of names){
        const listItem = document.createElement("li");
        console.log(name);
        listItem.innerHTML = `Language: ${name["language"]["name"]}: \n`;
        listItem.innerHTML += name.name;

        list.appendChild(listItem);
    }
    displayContainer.appendChild(list);
}
