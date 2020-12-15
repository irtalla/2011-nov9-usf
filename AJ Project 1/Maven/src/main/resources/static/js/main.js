let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
let loggedUser = null;
checkLogin();
setNav();

function setNav() {
    nav.innerHTML = `
            <a href="index.html"><strong>TRMS</strong></a>`;

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
    } else if (loggedUser.department.id === 1 || loggedUser.role.id === 1 || loggedUser.role.id === 2) {
        nav.innerHTML += `
        <a href="myRequests.html">View My Requests</a>
        <a href="newRequest.html">New Request</a>
        <a href="requestDashboard.html">View Request Dashboard</a>
        <span>
            ${loggedUser.username}&nbsp;
            <button type="button" id="loginBtn">Log Out</button>
        </span>`;
    } else if (loggedUser.role.name = 'employee') {
        nav.innerHTML += `
        
        <a href="myRequests.html">View My Requests</a>
        <a href="newRequest.html">New Request</a>
        <span>
            ${loggedUser.username}&nbsp;
            <button type="button" id="loginBtn">Log Out</button>
        </span>`;

    } 
    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else loginBtn.onclick = login;
}

async function login() {
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