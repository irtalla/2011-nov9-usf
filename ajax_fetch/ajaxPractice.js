
var apiURL = 'https://pokeapi.co/api/v2/item/';

document.getElementById('getData').onclick = getData;
document.getElementById('getData').addEventListener('click', changeBackground);

function getData() {
    
    var userInput = document.getElementById('dataInput').value;

    // 4 steps to making an AJAX call
    // STEP 1: Create an XML Http Request object
    var xhttp = new XMLHttpRequest();

    // STEP 2: Set a callback function for the readystatechange event
    xhttp.onreadystatechange = receiveData;

    // STEP 3: Open the request
    xhttp.open('GET', apiURL + '' + userInput);

    // STEP 4: Send the request
    xhttp.send();

    // This needs to be an inner function so that it has closure to xhttp.
    function receiveData() {
        /*
            Different ready states of an XMLHttpRequest object
            0: UNSENT
            1: OPENED
            2: HEADERS RECEIVED
            3: LOADING
            4: DONE
        */
        // Emptying out the div before inserting new data.
        var dataSection = document.getElementById('data');
        dataSection.innerHTML = '';
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
                // Ready state is DONE, HTTP status code is "OK"
                var response = xhttp.responseText;
                response = JSON.parse(response);
                populateData(response);
            } else {
                // Ready state is DONE but status code is not "OK"
                dataSection.innerHTML = 'IT\'S NOT IN THE BAG!!!!!';
            }
        } else {
            // Ready state is not DONE
            /*
                Can have some sort of "loading" action
            */
        }
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
            let spriteImg = document.createElement('img');
            spriteImg.src = spritesObject[sprite];
            dataSection.appendChild(spriteImg);
        }
    }
}

function capitalize(str) {
    if (str) {
        let ws;
        let hyphen;

        for(let i = 0; i < str.length; i++) {
            if (str.charAt(i) == ' ')
                ws = i;
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
