let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
let loggedUser = null;
checkLogin();
setNav();
function setNav() {
    nav.innerHTML = `
            <a href="index.html"><strong>Cat App</strong></a>
            <a href="viewCats.html">View Cats</a>`;
    if (!loggedUser) {
        nav.innerHTML += `
            <form>
                <label for="user">Username: </label>
                <input id="user" name="user" type="text" />
                <label for="pass"> Password: </label>
                <input id="pass" name="pass" type="password" />
                <button type="button" id="loginBtn">Log In</button> 
            </form>
        `;
    } else {
        nav.innerHTML += `
            <a href="myCats.html">My Cats</a>
            <span>
                ${loggedUser.username}&nbsp;
                <button type="button" id="loginBtn">Log Out</button><br>
                <button type="button" id="updateBtn">Update User</button>
            </span>
        `;
    }

    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else loginBtn.onclick = login;

    let updateBtn = document.getElementById('updateBtn');
    updateBtn.onclick = update;
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

async function update() {
    let url = baseUrl + '/users';
    // let response = await fetch(url);
    // if (response.status === 200) loggedUser = await response.json();
    // setNav();


    nav.innerHTML += `<form>
    <label for="updateUser">Username: </label>
    <input id="updateUser" name="user" type="text" />
    <label for="updatePass">Password: </label>
    <input id="updatePass" name="pass" type="password" />
    <button type="button" id="updateSubmitBtn">Update User</button>
    </form>`;

    let updateSubmitBtn = document.getElementById('updateSubmitBtn');
    updateSubmitBtn.onclick = updateSubmit;
    

    async function updateSubmit(){
        let usr = {};
        usr.username = document.getElementById('updateUser').value;
        usr.password = document.getElementById('updatePass').value;

        const options = {
            method: 'PUT',
            body: JSON.stringify(usr),
            headers: {
                'Content-Type': 'application/json'
            }
        };
            let url = baseUrl + '/users/' + loggedUser.id;
            let response = await fetch(url, options);

            switch (response.status) {
                case 202: // successful
                   //loggedUser = await response.json();
                    setNav();
                    break;

                default: // other error
                    alert('Something went wrong.');
                    break;
            }
    }
}