var baseUrl = 'http://localhost:8080';
var user = null;

checkLogin().then(updateClaims);

async function checkLogin(){
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if(response.status === 200){
        user = await response.json();
        console.log(user);
    } else if (response.status === 400){
        console.log('no logged in user');
    }
}

async function updateClaims(){
    //fetch all claims by this user
    //let response = fetch
}