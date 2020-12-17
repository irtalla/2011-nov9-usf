"use strict"

import {baseUrl, setLoggedUser} from "./global.js";
let form = document.getElementById("registrationForm");

setRegistration();

function setRegistration() {
    form.innerHTML = `
        <form>
            <label for="firstname">First Name:</label>
            <input id="firstname" name="firstname" type="text" />
            <br>
            <label for="lastname">Last Name:</label>
            <input id="lastname" name="lastname" type="text" />
            <br>
            <label for="email">Email:</label>
            <input id="email" name="email" type="email" placeholder="username@domain.com" />
            <br>
            <label for="username">Username:</label>
            <input id="username" name="username" type="text" />
            <br>
            <label for="password">Pasword:</label>
            <input id="password" name="password" type="password" />
            <br>
            <button type="button" id="registerUser" class="submitBtn">submit</button>
        </form>
    `;
    let submitBtn = document.getElementById('registerUser');
    submitBtn.onclick = registerAuthor;
}

async function registerAuthor() {
    let url = baseUrl + '/users';
    let data = {
        id: 0,
        firstName: document.getElementById("firstname").value,
        lastName: document.getElementById("lastname").value,
        email: document.getElementById("email").value,
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        role: {
            id: 1,
            name: "Author"
        }
    };

    let response = await fetch(url, {
        method: 'POST',
        body: JSON.stringify(data)
    });

    console.log(response);
    if (response.status === 200) {
        alert("Welcome to SPMS! Heading back to the main page...");
        loginRegisteredUser();
    } else if (response.status === 455) {
        alert("Username already taken. Try again.");
    } else if (response.status === 456) {
        alert("Email already used. Try again.");
    } else if (response.status === 457) {
        alert("Invalid email format. Try again.");
    }
}

async function loginRegisteredUser() {
    let url = baseUrl + '/users?';
    url += 'user=' + document.getElementById('username').value + '&';
    url += 'pass=' + document.getElementById('password').value;
    let response = await fetch(url, {method:'PUT'});

    switch (response.status) {
        case 200:
            setLoggedUser(await response.json());
            window.location.replace(baseUrl);
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