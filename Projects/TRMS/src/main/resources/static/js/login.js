var loginbutton= document.getElementById('login');
var regi=document.getElementById('register');
var form= document.getElementById('data');
loginbutton.onclick= ShowLoginFoarm;

function ShowLoginFoarm(){
    form.innerHTML= '';
    var Login=document.createElement('h2');
    var lodiv=document.createElement('div');
    var divemail=document.createElement('div');
    var divpassword=document.createElement('div');
    var emailinput=document.createElement('input');
    var passwordinput= document.createElement('input');
    
    var regisbuton=document.createElement('button');
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

    regisbuton.onclick=login;
}
function login(){

    loginbutton.innerHTML=''
    loginbutton.outerHTML=''
    form.innerHTML='';
    regi.innerHTML=''
    regi.outerHTML=''

    logout();

}
function logout(){

    var logbtn=document.createElement('button');

}
