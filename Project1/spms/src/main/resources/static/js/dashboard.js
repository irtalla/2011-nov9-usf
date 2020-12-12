let baseUrl = 'http://localhost:8080';
let loggedUser = null;
let author = null;

getUser();

async function getUser() {
    // userController checkLogin()
    let url = baseUrl + '/users';
    let response = await fetch(url, {credentials: 'include'});
    if (response.status === 200){
        loggedUser = await response.json();
        getAuthorEditor();
    }else{
        window.location.href = "D:/2011-nov9-usf/Project1/spms/src/main/resources/static/login.html"
    }
}

async function getAuthorEditor(){
    let url = baseUrl + '/users/' + loggedUser['id'];
    let response = await fetch(url, {credentials: 'include'});
    if (response.status === 200){
        author = await response.json();
        document.getElementById('welcome_text').innerHTML = 'Welcome ' + author['firstName'] + ".";
    }else{
        alert('Error getting User Data');
    }
}

async function logout() {
    let url = baseUrl + '/users';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedUser = null;
    window.location.href = "D:/2011-nov9-usf/Project1/spms/src/main/resources/static/index.html";
}