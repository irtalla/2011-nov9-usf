let baseUrl = 'http://localhost:8080';

let nav = document.getElementById('navBar');
loggedUser = null;
reimbursements = {};
attachments = {};
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

async function getReimbursements(callback) {
    let url = baseUrl + '/reimbursements';
    let response = await fetch(url, { method: 'GET' });

    switch (response.status) {
        case 200: // successful
            reimbursements = await response.json();
            break;
        default: // other error
            alert('Something went wrong retrieving reimbursements.');
            break;
    }
    callback(reimbursements);
}

async function getReimbursementsById(id, callback) {
    let url = baseUrl + '/reimbursements/' + id;
    let response = await fetch(url, { method: 'GET' });

    switch (response.status) {
        case 200: // successful
            reimbursements = await response.json();
            break;
        default: // other error
            alert('Something went wrong retrieving reimbursements.');
            break;
    }
    callback(reimbursements);
}
async function getAttachmentsById(id, callback) {
    let url = baseUrl + '/reimbursements/attachments/' + id;
    let response = await fetch(url, { method: 'GET' });

    switch (response.status) {
        case 200: // successful
            attachments = await response.json();
            callback(attachments);
            return attachments;
            break;
        default: // other error
            alert('Something went wrong retrieving attachments.');
            break;
    }
}

async function logout() {
    let url = baseUrl + '/users';
    let response = await fetch(url, { method: 'DELETE' });

    if (response.status != 200) alert('Something went wrong.');
    loggedUser = null;
    setNav();
    document.getElementById("form").innerHTML = ``;
}

async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();
    setNav();
}
function coverageBasedOnType(type, cost){
    switch (type) {
        case '1'://univ 80
            return cost * 0.8;
            break;

        case '2'://semin 60 
            return cost * 0.6;  
            break;

        case '3'://cert prep 75
            return cost * 0.75;
            break;

        case '4'://cert 100 
            return cost;
            break;

        case '5'://tech training 90
         return cost * 0.9;
            break;

        case '6'://other 30
            return cost * 0.3;
            break; 
    }
}