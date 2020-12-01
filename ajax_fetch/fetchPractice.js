
var apiURL = 'https://pokeapi.co/api/v2/item/';

document.getElementById('getData').onclick = getData;
document.getElementById('getData').addEventListener('click', changeBackground);

async function getData() {

    let userInput = document.getElementById('dataInput').value; 

    document.getElementById('data').innerHTML = '';

    let response = await fetch(apiURL + userInput);

    if (response.status === 200) {
        let data = await response.json();
        populateData(data);
    } else {
        dataSection.innerHTML = 'IT\'S NOT IN THE BAG!!!!!';
    }

}

function populateData(response) {
    var dataSection = document.getElementById('data');
    
    var nameTag = document.createElement('h2');
    nameTag.innerHTML =  capitalize(response.name);
    var flavorArray = response.flavor_text_entries;
    var flavor = document.createElement('h3');

    for (const flv of flavorArray)
    {
        if (flv.language.name == 'en') {
            flavor.innerHTML = capitalize(flv.text);
            break;
        }
    }

    dataSection.appendChild(nameTag);
    dataSection.appendChild(flavor);

    var spritesObject = response.sprites;
    for (const sprite in spritesObject) {
        if(spritesObject[sprite]) {
            var spriteImg = document.createElement('img');
            spriteImg.src = spritesObject[sprite];
            dataSection.appendChild(spriteImg);
        }
    }
}

function capitalize(str) {
    if (str) {
        let hyphen;

        for(let i = 0; i < str.length; i++) {
            if (str.charAt(i) == '-')
                hyphen = i;
        }

        if (hyphen) {
            if ((str.charAt(0) == 'p' || str.charAt(0) == 'h') && str.charAt(1) == 'p' && str.charAt(2) == '-')
                return str.charAt(0).toUpperCase() + str.charAt(1).toUpperCase() + '-' + str.charAt(3).toUpperCase() + str.slice(4);
            else
                return str.charAt(0).toUpperCase() + str.slice(1, hyphen) + ' ' + str.charAt(hyphen + 1).toUpperCase() + str.slice(hyphen + 2);
        } else if ((str.charAt(0) == 'h' || str.charAt(0) == 't') && str.charAt(1) == 'm') {
            return str.charAt(0).toUpperCase() + str.charAt(1).toUpperCase() + str.slice(2);
        } else {
            return str.charAt(0).toUpperCase() + str.slice(1);
        }
    } else
        return '';
}

function changeBackground() {
    let randomNumber = Math.floor(Math.random() * 7);
    document.body.style.backgroundImage = "url('pictures/pic" + randomNumber + ".jpg'";
}
