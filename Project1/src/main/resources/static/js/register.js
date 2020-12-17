function generateRegistrationForm(){
    console.log("Generating registration form...");
    let str = `
        <form id="register-form">
            <label for="email">Email: </label>
            <input id="email" name="email" type="text" />
            
            <label for="username">Username: </label>
            <input id="username" name="username" type="text" />
            
            <label for="password"> Password: </label>
            <input id="password" name="password" type="password" />

            <br>
            <label for="firstName">First Name: </label>
            <input id="firstName" name="firstName" type="text" />
            
            <label for="lastName">Last Name: </label>
            <input id="lastName" name="lastName" type="text" />
            
            <label for="bio">Bio: </label>
            <textarea id="bio" name="bio" type="text" /></textarea>
        
            <label for="role"> Role: </label>
            <select id="role" name="role">
                <option value="AUTHOR" selected>Author</option>
                <option value="ASSISTANT_EDITOR">Assistant Editor</option>
                <option value="GENERAL_EDITOR">General Editor</option>
                <option value="SENIOR_EDITOR">Senior Editor</option>
            </select>
        `;

        str += getGenreSelection();

        str += `
            <button type="button" id="registerBtn">Register</button>
        </form>`;
    
    subject.innerHTML += str;
    let registerBtn = document.getElementById("registerBtn");
    registerBtn.onclick = register;
}

async function register() {
    console.log("Registering...");
    let url = baseUrl + '/users';
    let newUser = {};
    userLazyKeys.forEach(key => newUser[key] = document.getElementById(key).value);
    newUser["role"] = document.getElementById("role").value.toUpperCase();
    console.log(newUser);
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    });
    
    switch (response.status) {
        case 200: // successful
            loggedUser = await response.json();
            console.log(loggedUser);
            clearNav();
            generateLogoutForm();
            clearSubject();
            generateUserProfile();
            break;
        case 409: // username taken
            alert('That username has been taken.');
            document.getElementById('user').value = '';
            document.getElementById('pass').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}