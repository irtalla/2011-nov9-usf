let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
loggedUser = null;
checkLogin();
setNav();
function setNav() {
    nav.innerHTML = ``;
    if (!loggedUser) {
        nav.innerHTML += `
            <div class="topcorner">
                <form >
                    <label for="user">Username: </label>
                    <input id="user" name="user" type="text" />
                    <label for="pass"> Password: </label>
                    <input id="pass" name="pass" type="password" />
                    <button type="button" id="loginBtn" class="btn btn-primary">Log In</button>
                </form>
            </div>
        `;
    } else {
        nav.innerHTML += `
        <div class = "topcorner">
            <span>
                <button type="button" id="profileBtn" class = ".btn-default" onClick = "showProfile('form')">${loggedUser.name}(${loggedUser.role.name})</button>
                <button type="button" id="loginBtn" class="btn btn-primary">Log Out</button>
            </span>
        </div>
    `;
        switch (loggedUser.role.name) {
            case "employee":
                nav.innerHTML += `employee
                <button type="button" onclick="showForm('form' ) "class="btn btn-info">Request Reimbursement</button>
            `;

                break;
            case "supervisor":
                nav.innerHTML += `supervisor
            `;
                break;
            case "benco":
                nav.innerHTML += `BENCO
            `;
                break;
        }

    }

    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else loginBtn.onclick = login;
}
//        

async function login() {
    let url = baseUrl + '/users?';
    url += 'user=' + document.getElementById('user').value + '&';
    url += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(url, { method: 'PUT' });

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
    let response = await fetch(url, { method: 'DELETE' });

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