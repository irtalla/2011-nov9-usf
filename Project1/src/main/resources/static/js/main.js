const baseUrl = "http://localhost:8080";
let loggedUser = null;
let selectedPitch = null;
let selectedDraft = null;

const titleMessage = document.getElementById('titleMessage');
const nav = document.getElementById('navBar');
const subject = document.getElementById('subject');
    
const userLazyKeys = ["email", "username", "password", "firstName", "lastName", "bio"];
const pitchKeysInOrder = [
    "tentativeTitle", "storyType", "genre", "description", "tagLine", "tentativeCompletionDate", 
     "pseudoFirstName", "pseudoLastName", "pseudoBio"
];

const genres = ["YA", "Sci Fi", "Mystery", "Romance", "Drama", "Adventure"];
const storyTypes = ["Article", "Short Story", "Novella", "Novel"];

document.addEventListener('DOMContentLoaded', (event) => {
    clearNav();
    clearSubject();
    generateLoggedOutWelcomeMessage();
    checkLogin();
});

function generateLoggedOutWelcomeMessage(){
    titleMessage.innerHTML = `
        <h1>Welcome to the Story Pitch Management App!</h1>
        <i>Streamlining the Dream-To-Reality Pipeline</i>
    `;
}

function generateLoggedInWelcomeMessage(){
    titleMessage.innerHTML = `
        <h1>Story Pitch Management App</h1>
        <i>Streamlining the Dream-To-Reality Pipeline</i>
    `;
}

function getGenreSelection(){
    let roleSelect = document.getElementById("role");
    if(!roleSelect) {
        let str = "";
        return str;
    }

    let role = roleSelect.value;    
    if(role != null && role.value != "Author"){
        let str = `<select>`;
        genres.forEach(genre => str += `<option value=${genre.toUpperCase()}>${genre}</option>`)
        str += `</select>`
        return str;
    }
}

function clearNav(){
    document.getElementById("navBar").innerHTML = "";
}

function clearSubject(){
    document.getElementById("subject").innerHTML = "";
}

function getSelectForEnum(enumName, values){
    let str = getLabelForKey(enumName);
    str += `
        <select id=${enumName} name=${enumName}>
    `; 
    
    values.forEach(value => str += `
            <option value=${value.toUpperCase()}>${value}</option>
    `); //tried reduce, but wouldn't work...

    str += `</select><br>`;
    return str;
}

function getLabelForKey(key){
    return `<label for=${key}>${toSentenceCase(key)}: </label>`;
}

function toPascalCase(str){
    return str.toLowerCase().replace(/[^a-zA-Z0-9]+(.)/g, (m, chr) => chr.toUpperCase());
    // return str.replace(/(\w)(\w*)/g,
    //     function(g0,g1,g2){return g1.toUpperCase() + g2.toLowerCase();});
}

function toSentenceCase(str){
    str = str.replace( /([A-Z])/g, " $1" ); 
    return str.charAt(0).toUpperCase() + str.slice(1);
}

