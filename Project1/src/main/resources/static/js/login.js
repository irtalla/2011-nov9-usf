async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) {
        loggedUser = await response.json();
        console.log(loggedUser);
        clearNav();
        clearSubject();
        generateLogoutForm();
        generateLoggedInWelcomeMessage();
        generateUserProfile();
    }else{
        console.log("Not Logged In");
        clearNav();
        clearSubject();
        generateLoginForm();
        generateRegistrationForm();
        generateLoggedOutWelcomeMessage();
    }
}

function generateLoginForm(){
    console.log("Generating login form...");
    let str = `
        <h3>Login:</h3>
        <form id="login-form">
            <label for="user">Username: </label>
            <input id="user" name="user" type="text" />
            
            <label for="pass"> Password: </label>
            <input id="pass" name="pass" type="password" />
            
            <button type="button" id="loginBtn">Log In</button>
        </form>

        <br>
        OR
        <h3>Register</h3>
    `;
    subject.innerHTML += str;
    let loginBtn = document.getElementById("loginBtn");
    loginBtn.onclick = login;
    // console.log(loginBtn.onclick);
}

async function login() {
    console.log("Logging in...");
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/users';
    url += '?user=' + document.getElementById('user').value + '&';
    url += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(url, {method: 'PUT'});
    
    switch (response.status) {
        case 200: // successful
            loggedUser = await response.json();
            console.log(loggedUser);
            clearNav();
            clearSubject();
            generateLogoutForm();
            generateLoggedInWelcomeMessage();
            generateUserProfile();
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