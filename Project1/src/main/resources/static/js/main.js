"use strict"

import {baseUrl, loggedUser, setLoggedUser} from "./global.js";

let nav = document.getElementById('navBar');

checkLogin();
setNav();

function setNav() {
    nav.innerHTML = `
        <a href="index.html"><strong>STMS</strong></a>
        `;
    if (loggedUser) {
        nav.innerHTML += `
            <br>
            <span>
                ${"Welcome " + loggedUser.firstName + " " + loggedUser.lastName}&nbsp;
                <button type="button" id="loginBtn">logout</button>
             </span>
             <br>
             <a href="viewPitch.html"><strong>Pitches</strong></a>
            `;
    } else {
        nav.innerHTML += `
            <form>
                <label for="user">username: </label>
                <input id="user" name="user" type="text" />
                <label for="pass">password: </label>
                <input id="pass" name="pass" type="password" />
                <button type="button" id="loginBtn">login</button>
                <button type="button" id="registerBtn">register</button>
            </form>
        `;
    }

    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) {
        loginBtn.onclick = logout;
    } else {
        loginBtn.onclick = login;
        let registerBtn = document.getElementById('registerBtn');
        registerBtn.onclick = registerUser;
    }

}

async function login() {
    let url = baseUrl + '/users?';
    url += 'user=' + document.getElementById('user').value + '&';
    url += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(url, {method:'PUT'});

    switch (response.status) {
        case 200:
            setLoggedUser(await response.json());
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
    setLoggedUser(null);
    if (window.location != baseUrl) {
        window.location.replace(baseUrl);
    } else {
        setNav();
    }
}

async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) {
        setLoggedUser(await response.json());
    } 
    setNav();
}

async function registerUser() {
    window.location.replace(baseUrl + "/register.html");
}