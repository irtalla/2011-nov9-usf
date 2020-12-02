var baseUrl = 'http://localhost:8080';

var loginBtn = document.getElementById('loginBtn')
loginBtn.onclick = login;

async function login() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let url = baseUrl + '/users?';
    url += 'username=' + username + '&';
    url += 'password=' + password;

    let response = await fetch(url, {method:'PUT'});

    switch (response.status){
        case 200:
            console.log('login');
            break;
        default:
            console.log('error');
    }
}