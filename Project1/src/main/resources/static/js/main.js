let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
let loggedUser = null;
checkLogin();
setNav();

function setNav() {
    nav.innerHTML = `<a href="index.html"><strong>Home</strong></a>`;
    if (localStorage.getItem('username') == null) {
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
        console.log(localStorage.getItem('roleId'));

        if (localStorage.getItem('roleId') == 1) {
            nav.innerHTML += `
                <a href="myStories.html">My Stories</a>
                <a href="addStory.html">Add Story</a>
            `;
            //console.log(localStorage.getItem('username'));
        }
        else {
            nav.innerHTML += `
                <a href="pendingStories.html">Pending Stories</a>
            `;
        }

        nav.innerHTML += `
            <span>
                <span>Logged in as ${localStorage.getItem('username')}&nbsp;</span>
                <button type="button" id="loginBtn">Log Out</button>
            </span>
        `;
    }

    let loginBtn = document.getElementById('loginBtn');
    if (localStorage.getItem('username')) {
        loginBtn.addEventListener("click", logout);
        loginBtn.addEventListener("click", () => {
            window.location.replace("index.html");
        });
    } else loginBtn.onclick = login;
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
            console.log(loggedUser);
            localStorage.setItem('username', loggedUser.username);
            localStorage.setItem('firstName', loggedUser.firstName);
            localStorage.setItem('lastName', loggedUser.lastName);
            localStorage.setItem('id', loggedUser.id);
            localStorage.setItem('roleId', loggedUser.role.id);
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
    /*let url = baseUrl + '/users';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedUser = null;*/
    localStorage.clear();
    setNav();

}

async function checkLogin(callback = null) {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200){
         loggedUser = await response.json();
    }
    setNav();
}