function generateLogoutForm(){
    nav.innerHTML += `
        <span>
            <a href="profile.html">${loggedUser.username}&nbsp;</a>
            <button type="button" id="logoutBtn">Log Out</button>
        </span>`;
    let logoutBtn = document.getElementById("logoutBtn");
    logoutBtn.onclick = logout;
}

async function logout() {
    let url = baseUrl + '/users';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedUser = null;
    clearNav();
    generateRegistrationForm();
    generateLoginForm();
    generateLoggedOutWelcomeMessage();
}