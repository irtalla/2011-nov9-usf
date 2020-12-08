let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
let loggedUser = null;
checkLogin();
setNav();



async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();
    setNav();
}