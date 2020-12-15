let baseUrl = 'http://localhost:8080'
let nav = document.getElementById('navBar');
let loggedUser = null;
checkLogin();
setNav();
function setNav() {
	nav.innerHTML = `
		<a href="index.html"><strong>Book app</strong></a>
		<a href="viewPitches.html">View Pitches</a>
		<a href="viewApprovals.html">View Approvals</a>
		<a href="viewDrafts.html">View Drafts</a>`
	if (!loggedUser) {
		nav.innerHTML += `
		<form>
			<label for="user">usrname: </label>
			<input id="user" name="user" type="text" />
			<label for="pass"> Password: </label>
			<input id="pass" name="pass" type="password" />
			<button type="button" id="loginBtn">Log in</button>
		</form>
		`;
	} else {
		nav.innerHTML += `
		<a href="viewPitches.html">View Pitches</a>
		<span>
			${loggedUser.usrname}&nbsp;
			<button type="button" id="loginBtn">Log out</button>
		</span>
			`;
	}
	
	let loginBtn = document.getElementById('loginBtn');
	if (loggedUser) loginBtn.onclick = logout;
	else loginBtn.onclick = login;
}

async function login() {
	let url = baseUrl + '/users?';
	url += 'user=' + document.getElementById('user').value + '&';
	url += 'pass=' + document.getElementById('pass').value;
	let response = await fetch(url, {method: 'PUT'});
	
	switch (response.status) {
		case 200: 
			loggedUser = await response.json();
			console.log(loggedUser.usr_id)
			setNav();
			break;
		case 400:
			alert('Incorrect password, try again.');
			document.getElementById('pass').value = '';
			break;
		case 404:
			alert('usr not found.');
			document.getElementById('user').value = '';
			document.getElementById('pass').value = '';
			break;
		default:
			alert('Something went wrong.');
			break;
	}
}

async function logout() {
	let url = baseUrl + '/users';
	let response = await fetch(url,{method:'DELETE'});
	
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