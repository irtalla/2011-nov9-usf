"use strict"

let baseUrl = 'http://localhost:8080/html';
let nav = document.getElementById('navBar');
let loggedUser = null;

checkLogin();
setNav();

function setNav() {
    nav.innerHTML = ``;
    if (loggedUser) {
        nav.innerHTML += ``;
    } else {
        nav.innerHTML += ``;
    }

    // let loginBtn = document.getElementById('loginBtn');
    // if (loggedUser) {
    //     loginBtn.onclick = logout;
    // } else {
    //     loginBtn.onclick = login;
    // }
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