var registerbuton= document.getElementById('register');
var logi= document.getElementById('login');
var form= document.getElementById('data');
registerbuton.onclick= ShowRegisterForm;

function ShowRegisterForm(){
    //console.log("regis");
    form.innerHTML= '';

    var Login=document.createElement('h2');
    var lodiv=document.createElement('div');
    
    var divname=document.createElement('div');
    var nameinput=document.createElement('input');
    var divemail=document.createElement('div');
    var divpassword=document.createElement('div');
    var emailinput=document.createElement('input');
    var passwordinput= document.createElement('input');
  //  passwordinput.placeholder="Password";
    var regisbuton=document.createElement('button');
    regisbuton.innerText="REGISTER"
    //div.appendChild('Email Address');
   
    Login.innerHTML="Register<br>"
    lodiv.appendChild(Login);
    divname.innerHTML="Name <br>";
    divname.appendChild(nameinput);
    divemail.innerHTML='<br>Email address<br>                  ';
    divemail.appendChild(emailinput);
    divpassword.innerHTML='<br>Password <br>  ';
    divpassword.appendChild(passwordinput);

    
    form.appendChild(lodiv);
    
    
    form.appendChild(divname);
    form.appendChild(divemail);
    form.appendChild(divpassword);
    form.appendChild(regisbuton);

    regisbuton.onclick=register;
}
function register(){ 
    
//     logi.innerHTML=''
// logi.outerHTML=''
 form.innerHTML='';
// registerbuton.innerHTML=''
// registerbuton.outerHTML=''

}

