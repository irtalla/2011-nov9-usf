"use strict"

let baseUrl = 'http://localhost:8080/html';
let nav = document.getElementById('navBar');
let loggedUser = null;

checkLogin();
setNav();

function setNav() {
    nav.innerHTML = `
        <a href="index.html"><strong>STMS</strong></a>
        `;
    if (loggedUser) {
        nav.innerHTML += `
            <span>
                ${loggedUser.username}&nbsp;
                <button type="button" id="loginBtn">logout</button>
             </span>
            `;
    } else {
        nav.innerHTML += `
            <form>
                <label for="user">username: </lable>
                <input id="user" name="user" type="text" />
                <label for="pass">password: </lable>
                <input id="pass" name="pass" type="password" />
                <button type="button" id="loginBtn">login</button>
            </form>
        `;
    }

    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) {
        loginBtn.onclick = logout;
    } else {
        loginBtn.onclick = login;
    }
}

async function login() {
    let url = baseUrl + '/users?';
    url += 'user=' + document.getElementById('user').value + '&';
    url += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(url, {method:'PUT'});

    switch (response.status) {
        case 200:
            loggedUser = await response.json();
            setNav();
            break;
        case 400:
            alert('Incorrect password. Try again.');
            document.getElementById('pass').value = '';
            break;
        case 404:
            alert('Username not found.');
            document.getElementById('user').value = '';
            document.getElementById('pass').value = '';
            break;
        default:
            alert('Unexpected error occurred.');
            break;
    }
}

async function logout() {
    let url = baseUrl + '/users';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status !== 200) {
        alert('Failed to logout');
    }
    loggedUser = null;
    setNav();
}

async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) {
        loggedUser = await response.json();
    }
    setNav();
}