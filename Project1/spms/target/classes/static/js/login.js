let baseUrl = 'http://localhost:8080';
let loggedUser = null;

checkLogin()
awaiting()

function awaiting(){
    console.log("In awaiting function");
    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else loginBtn.onclick = login;
}

async function login() {
    console.log("In login function");
    let url = baseUrl + '/users?';
    url += 'user=' + document.getElementById('username').value + '&';
    url += 'pass=' + document.getElementById('pwd').value;
    console.log(url);
    let response = await fetch(url, {method: 'PUT'});
    
    switch (response.status) {
        case 200: // successful
            alert('logged in');
            loggedUser = await response.json();
            break;
        case 400: // incorrect password
            document.getElementById('pwd').value = '';
            document.getElementById('pwd').className = document.getElementById('pwd').className + " error";
            document.getElementById('passwordHelp').innerHTML = 'Incorrect password. Please try again.'
            break;
        case 404: // user not found
            document.getElementById('username').value = '';
            document.getElementById('pwd').value = '';
            document.getElementById('username').className = document.getElementById('username').className + " error";
            document.getElementById('usernameHelp').innerHTML = 'The user does not exist. Please register an account.'
            break;
        default: // other error
            document.getElementById('passwordHelp').innerHTML = 'Something went wrong. Please try again later.'
            break;
    }
}

async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();
    awaiting();
}