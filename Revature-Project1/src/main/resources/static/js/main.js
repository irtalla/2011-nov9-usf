let baseUrl = "http://localhost:8081";
let currentUser = null;

checkLogin();

let fakeNav = document.getElementsByClassName("queen-navbaria");
let otherPart = document.getElementsByClassName("soul-of-spms");



/*document.getElementById("author-link").style.display = none;
document.getElementById("editor-link").style.display = none;*/

/*Not going to change it just yet, just want to try retrieving the thing*/
if (document.getElementById("login")){
	document.getElementById("login").onclick = login;
}

async function login(){
	//http://localhost:8081/users?username=hitormiss&password=iguesstheynevermisshuh
	let url = baseUrl + "/users?";
	url += "username=" + document.getElementById("uenm").value + "&";
	url += "password=" + document.getElementById("asod").value;
	
	console.log(url);
	
	let response = await fetch(url, {method: 'POST'});
    
    switch (response.status) {
        case 200:
        	console.log("At least it went through nicely.");
            currentUser = await response.json();
            if (currentUser){
            	console.log(currentUser);
            	alert(`Logged into ${currentUser.userInfo.username}`);
            	
            	if(currentUser.userInfo.role === "author"){
            		document.getElementById("author-link").style.display = "block";
            	}
            	else if (currentUser.userInfo.role === "editor"){
            		document.getElementById("editor-link").style.display = "block";
            	}
            }
            break;
        case 404: // user not found
        	console.log("At least we know I can't type well.")
            alert('That user does not exist.');
            document.getElementById('uenm').value = '';
            document.getElementById('asod').value = '';
            break;
        default: // other error
        	console.log("There is no upside at this point")
            alert('An unknown error happened, which sucks because we can\'t hint at it.');
            break;

    }
}


async function checkLogin(){
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200){ 
    	currentUser = await response.json() 
    };

}

