let baseUrl = 'http://localhost:8080';
const storyTypes = ["Article", "Short Story", "Novella", "Novel"];
const keysInOrder = [
    "tentativeTitle", "storyType", "genre", "description", "tagLine", "tentativeCompletionDate", 
     "pseudoFirstName", "pseudoFirstName", "bio"
];
let selectedPitch = null;
populateSections();

async function populateSections(){
    //populate pitches section with viewable pitches
    let response = await fetch("/pitches", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loggedUser)
    });
    let pitches = await response.json();

    document.getElementById("pitchesSection").innerHTML += getPitchesList(pitches);
    
    if(loggedUser.role = "AUTHOR"){
        document.getElementById("addPitchForm").innerHTML += getAddPitchForm();
    }
}

function getPitchesList(pitches){
    // const list = document.createElement("ul");
    let str = `<ul>`;
    pitches.forEach(pitch => str += `<li><a href="pitch.html">${pitch.tentativeTitle} by ${pitch.author.lastName}</a></li>`)
    str += `</ul>`
    return str;
}

function authorRemainingPoints(author){
    return 100 - author.pitches.reduce((sum, pitch) => sum += pitch.storyType.points);
}

function getAddPitchForm(){
    //createdAt handled in db; status and priority handled in controller
    const stringsForKeys = {
        storyType: getSelectForEnum("Story Type", storyTypes),
        genre: getSelectForEnum("Genres", genres),
        tentativeCompletionDate: `
            <input type="date" id="tentativeCompletionDate" name="tentativeCompletionDate"
                value=${Date.now}
                min=${Date.now}
            >`
    };

    let str = `
        <h3>Add Pitch</h3>
        <form id="add-pitch-form">
    `;

    keysInOrder.forEach(key => {
        if(Object.keys(stringsForKeys).includes(key)){
            str += stringsForKeys[key]
        }else{
            str += `
                <label for=${key}>${toPascalCase(str)}: </label>
                <input id=${key} name=${key} type="text" />   
            `
        }
    });

    str += `    <button onclick="addPitch()" id="submit-add-pitch-form" >"Add Pitch"</button>
        </form>
    `;
    return str;
}

async function addPitch(){
    let newPitch = {};
    keysInOrder.forEach(key => newPitch[key] = document.getElementById(key));
    newPitch["status"] = authorRemainingPoints(loggedUser) > 0 ? "PENDING" : "SAVED";

    let response = await fetch(baseUrl + "/pitches", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newPitch)
    });

    if(response.status == 200){
        // successful
        selectedPitch = await response.json();
        window.open("pitch.html","_self");
    }else{
        alert('Pitch could not be added.');
    }
}

function getSelectForEnum(enumName, values){
    let str = getLabelForKey(enumName);
    str += `
        <select id=${values} name=${enumName}>
    `; 
    
    values.forEach(value => str += `
            <option value=${value.toUpperCase()}>${value}</option>
    `); //tried reduce, but wouldn't work...

    str += `</select>`;
    return str;
}

function getLabelForKey(key){
    return `<label for=${key}>${toPascalCase(key)}: </label>`;
}

function toPascalCase(str){
    return str.replace(/(\w)(\w*)/g,
        function(g0,g1,g2){return g1.toUpperCase() + g2.toLowerCase();});
}