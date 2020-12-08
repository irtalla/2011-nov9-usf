let baseUrl = 'http://localhost:8080';
//console.log("hrloss");
var form=document.getElementById("forms");
var subform=document.getElementById("newform");

let nav = document.getElementById('sidebar');

var Login=document.createElement('h2');
var lodiv=document.createElement('div');

var divemail=document.createElement('div');
var divpassword=document.createElement('div');

var emailinput=document.createElement('input');
var passwordinput= document.createElement('input');

var divname= document.createElement('div');
var inputname=document.createElement('input');
//-----------------------form-----------------------
var divevent_id=document.createElement("div");
var eventidinput=document.createElement("input");

var divdates= document.createElement("div");
var datesinput=document.createElement("input");
datesinput.setAttribute("type", "date");
datesinput.setAttribute("value", "2014-02-09");
//subform.appendChild(datesinput);

var divtime= document.createElement("div");
var timeinput=document.createElement("input");
timeinput.setAttribute("type","time");
timeinput.setAttribute("id","time");

var divlocation=document.createElement("div");
var locationinput=document.createElement("input");

var divdes=document.createElement("div");
var inputdesc=document.createElement("input");




var regisbuton=document.createElement('button');    
let loggedUser = false;
checkLogin();
setNav();
console.log("hrloss");

function setNav() {
    if (!loggedUser) {
        console.log("hrloss");

      //  console.log("navs");
        nav.innerHTML += `
            
        <div class="title">
        Menu</div>
        <ul class="list-items">
        <li><a href="#"><i class="fas fa-home" onclick="register()"></i>REGISTERRR</a></li>
        <li><a href="#"><i class="fas fa-sliders-h" onclick="login()"></i>LOGIN</a></li>
        <li><a href="#"><i class="fas fa-address-book"></i>CONTACT US</a></li>
        <li><button type="button" name="loginBtn" onclick="loginaws()">LOFins</button></li>
    
        </ul>
        `;
    } else {
        console.log("hrlosneas");

        nav.innerHTML += `
        <div class="title">
        Menu</div>
        <ul class="list-items">
        <li><a href="#"><i class="fas fa-home" onclick="myform()"></i>Form Status</a></li>
        <li><a href="#"><i class="fas fa-sliders-h" onclick="newform()"></i>Submit a Form</a></li>
        <li><a href="#"><i class="fas fa-address-book"></i>CONTACT US</a></li>
        
        <li><a href="#"><i class="fas fa-address-book"></i>Logout</a></li>
        `;
    }

    let loginBtn = document.getElementsByName('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else {
        loginBtn.onclick = login;
        console.log("hrlosssaaass");
        console.log(loginBtn.innerHTML);
    }

}
function newform(){


}

async function login() {
    console.log("loginsers");
    form.innerHTML= '';
    regisbuton.innerText="LOGIN"
    
    //div.appendChild('Email Address');
    Login.innerHTML="Login<br>"
    lodiv.appendChild(Login);
    divemail.innerHTML='<br>Email address<br>                  ';
    divemail.appendChild(emailinput);
    divpassword.innerHTML='<br>Password<br>   ';
    divpassword.appendChild(passwordinput);
  
    
    
    form.appendChild(lodiv);
 
    form.appendChild(divemail);
    form.appendChild(divpassword);
    form.appendChild(regisbuton);


    ShowLoginFoarm();
    
}
function ShowLoginFoarm(){
    

    regisbuton.onclick=logins;
}

async function logins(){
    console.log("pp");
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/users?';
    console.log(emailinput.value+"npu");
    url += 'user=' + emailinput.value + '&';
    url += 'pass=' + passwordinput.value;
    let response = await fetch(url, {method: 'PUT'});
    
    switch (response.status) {
        case 200: // successful
            loggedUsesr = await response.json();
            setNav();
            break;s
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
//loggedUser=true;
}

function register(){
    console.log("Register");
    form.innerHTML= '';
    regisbuton.innerText="Register"
    
    //div.appendChild('Email Address');
    Login.innerHTML="Register<br>"
    lodiv.appendChild(Login);
    divemail.innerHTML='<br>Email address<br>                  ';
    divemail.appendChild(emailinput);
    divpassword.innerHTML='<br>Password<br>   ';
    divpassword.appendChild(passwordinput);
    divname.innerHTML='<br>Name<br>';
    divname.appendChild(inputname);
    
    
    
    
    form.appendChild(lodiv);
    form.appendChild(divname);
    form.appendChild(divemail);
    form.appendChild(divpassword);
    form.appendChild(regisbuton);


    showreg();
}
function showreg(){

    
}
