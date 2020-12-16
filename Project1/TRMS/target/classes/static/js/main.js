let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
// nav.classList.add("navbar");
// nav.classList.add("navbar-expand-lg");
// nav.classList.add("navbar-light");
// nav.classList.add("bg-light");

let loggedUser = null;
// checkLogin();
setNav();

function setNav() {
   
    nav.innerHTML = `
            <a href="index.html"><strong>Tuition Reimbursement Management System (TRMS)</strong></a>
            <a href="viewEvtReqs.html">View Event Requests</a>`;
    if (!loggedUser) {
        nav.innerHTML += ` 
            <form>
                <label for="user">Username: </label>
                <input id="user" name="user" type="text" value="pokemon"/>
                <label for="pass"> Password: </label>
                <input id="pass" name="pass" type="password" value="pokemon"/>
                <button type="button" id="loginBtn">Log In</button>
            </form>    
             <a href="register.html">Register User</a>      
        `;
    } else {
        nav.innerHTML += `
			<a href="EvtReqToApprove.html">Event Requests To Approve</a>            
			<a href="myEvtReqs.html">My Event Requests</a>
			<a href="addEvtReqs.html">Add Event Requests</a>
            <span>
                <a id="usernameLink" href="profile.html">${loggedUser.username}&nbsp;</a>
                <button type="button" id="loginBtn">Log Out</button>
            </span>
        `;
    }
    
    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else loginBtn.onclick = login;
   
}

async function login() {
	
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/users?';
    url += 'user=' + document.getElementById('user').value + '&';
    url += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(url, {method: 'PUT'});
    
    switch (response.status) {
        case 200: // successful
            loggedUser = await response.json(); 
            setNav();
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

async function logout() {
    let url = baseUrl + '/users';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedUser = null;
    setNav();
}

async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();
    setNav();
}
