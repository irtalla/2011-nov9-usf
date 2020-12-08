"use strict";

var baseUrl = 'http://localhost:8080';
var user = null;
//checkLogin();

async function checkLogin(callback = null)
{
    console.log("Checking Login");
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200)
    {
        user = await response.json();
        console.log("logged in as " + user.name);
    } 
    else
        console.log("Not Logged In");
    
    if (callback) callback();
}

async function login()
{
    console.log("login attempt");
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    console.log('password is' + password);
    let url = baseUrl + '/users?';
    url += 'user=' + username + '&';
    url += 'pass=' + password;
    let response = await fetch(url, {method: 'PUT'});
    switch (response.status) {
        case 200: // successful
            user = await response.json();
            window.location.replace('homepage.html');
            break;
        case 400: // incorrect login
            alert('Invalid username or password, please try again.');
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}