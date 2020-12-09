let baseUrl = 'http://localhost:8080';
//console.log("hrloss");
var chk=document.getElementById("loo");

document.getElementById('loo').style.display="none";
var loadingani=document.getElementById("loadert");

var form=document.getElementById("forms");
var subform=document.getElementById("newform");
console.log(chk.innerHTML);
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
let loggedUser = null;
checkLogin();
setNav();
console.log(loggedUser);

function setNav() {
    if (!loggedUser) {
        nav.innerHTML='';
      //  console.log("navs");
        nav.innerHTML += `
            
        <div class="title">
        Menu</div>
        <ul class="list-items">
        <li><a href="#"><i class="fas fa-home" onclick="register()"></i>REGISTERRR</a></li>
        <li><a href="#"><i class="fas fa-sliders-h" onclick="login()"></i>LOGIN</a></li>
        <li><a href="#"><i class="fas fa-address-book"></i>CONTACT US</a></li>
    
        </ul>
        `;
    } else {
        console.log("hrlosneas");
        nav.innerHTML='';
        nav.innerHTML += `
        <div class="title">
        Menu</div>
        <ul class="list-items">
        
        <li><a href="#"><i class="fas fa-home" onclick="profile()"></i>${loggedUser.emp_name}</a></li>
        <li><a href="#"><i class="fas fa-home" onclick="myform()"></i>Form Status</a></li>
        <li><a href="#"><i class="fas fa-sliders-h" onclick="newform()"></i>Submit a Form</a></li>
        <li><a href="#"><i class="fas fa-address-book"></i>CONTACT US</a></li>
        
        <li><a href="#"><i class="fas fa-address-book" onclick="logout()"></i>Logout</a></li>
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
function profile(){

    

}
function newform(){


}

async function login() {
    console.log("loginsers");
    form.innerHTML= '';
    regisbuton.innerText="LOGIN";
    
    //div.appendChild('Email Address');
    document.getElementById('loo').style.display="none";
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
    
    document.getElementById('loo').style.display="inline-flex";
   // loading.style.visibility="visible";
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/users?';
    console.log(emailinput.value+"npu");
    url += 'user=' + emailinput.value + '&';
    url += 'pass=' + passwordinput.value;
    let response = await fetch(url, {method: 'PUT'});
    console.log(response.json);
    switch (response.status) {
        case 200: // successful
            loggedUser = await response.json();
            console.log("logged in "+loggedUser);
            setNav();
            form.innerHTML='';
            
            document.getElementById('loo').style.display="none";
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
    console.log("loggin out");
    if (response.status != 200) alert('Something wentss wrong.');
    loggedUser = null;
    setNav();
}

async function checkLogin() {
 let url = baseUrl + '/users';
let response = await fetch(url);
console.log(response+"asas");
if (response.status === 200) loggedUser = await response.json();
setNav();
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
    regisbuton.onclick=doreg;

}
async function doreg(){
console.log("registerzzzzed as "+ loggedUser);

  document.getElementById('loo').style.display="inline-flex";
   // loading.style.visibility="visible";
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/users?';
    console.log(emailinput.value+"npu");
    url += 'user=' + emailinput.value + '&';
    url += 'pass=' + passwordinput.value;
    let response = await fetch(url, {method: 'POST'});
    console.log(response.json);
    switch (response.status) {
        case 200: // successful
            loggedUser = await response.json();
            console.log("logged in "+loggedUser);
            setNav();
            form.innerHTML='';
            
            document.getElementById('loo').style.display="none";
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
