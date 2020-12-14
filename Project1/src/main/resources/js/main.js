let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
let loggedUser = null;
const genres = ["YA", "Sci Fi", "Mystery", "Romance", "Drama", "Adventure"];

checkLogin();
setNav();

function setNav() {
    nav.innerHTML = `
            <a href="index.html"><strong>Story Pitch Management App</strong></a>
            <a href="viewPitches.html">View Pitches</a>`;
    if (!loggedUser) {
        nav.innerHTML += `
            <form id="login-form">
                <label for="user">Username: </label>
                <input id="user" name="user" type="text" />
                
                <label for="pass"> Password: </label>
                <input id="pass" name="pass" type="password" />
                
                <button type="button" id="loginBtn">Log In</button>
            </form>

            Or Register:
            <form id="register-form">
                <label for="email">Email: </label>
                <input id="email" name="email" type="text" />
                
                <label for="username">Username: </label>
                <input id="username" name="username" type="text" />
                
                <label for="password"> Password: </label>
                <input id="password" name="password" type="password" />

                <label for="firstName">First Name: </label>
                <input id="firstName" name="firstName" type="text" />
                
                <label for="lastName">Last Name: </label>
                <input id="lastName" name="lastName" type="text" />
                
                <label for="bio">Bio: </label>
                <textarea id="bio" name="bio" type="text" /></textarea>
            `;

            str += getGenreSelection();

            str += `
                <label for="role"> Role: </label>
                <select id="role" name="role">
                    <option value="AUTHOR">Author</option>
                    <option value="ASSISTANT_EDITOR">Assistant Editor</option>
                    <option value="GENERAL_EDITOR">General Editor</option>
                    <option value="SENIOR_EDITOR">Senior Editor</option>
                </select>

                <button type="button" id="registerBtn" onClick="register">Log In</button>
            </form>
        `;
    } else {
        nav.innerHTML += `
            <a href="pitches.html">Pitches</a>
            <span>
                <a href="profile.html">${loggedUser.username}&nbsp;</a>
                <button type="button" id="loginBtn" onclick="login">Log Out</button>
            </span>
        `;
    }

    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else loginBtn.onclick = login;
}

function getGenreSelection(){
    let role = document.getElementById("role").value;
    if(role != null && role.value != "Author"){
        let str = `<select>`;
        genres.forEach(genre => str += `<option value=${genre.toUpperCase()}>${genre}</option>`)
        str += `</select>`
    }
}

async function login() {
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/users?';
    url += 'user=' + document.getElementById('user').value + '&';
    url += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(url, {method: 'PUT'});
    
    switch (response.status) {
        case 200: // successful
            loggedUser = await response.json();
            setNav();
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('pass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            document.getElementById('user').value = '';
            document.getElementById('pass').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function register() {
    let url = baseUrl + '/users?';
    const keys = ["username", "password", "firstName", "lastName", "bio"];
    let newUser = {};
    keys.forEach(key => newUser[key] = document.getElementById(key));
    newUser["role"] = document.getElementById("role").value.toUpperCase();

    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    });
    
    switch (response.status) {
        case 200: // successful
            loggedUser = await response.json();
            setNav();
            break;
        case 409: // username taken
            alert('That username has been taken.');
            document.getElementById('user').value = '';
            document.getElementById('pass').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function logout() {
    let url = baseUrl + '/users';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedUser = null;
    setNav();
}

async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();
    setNav();
}