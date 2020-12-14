let baseUrl = 'http://localhost:8080';

let allforms2 = 'http://localhost:8080/form';
//console.log("hrloss");
var chk=document.getElementById("loo");
var reform=document.getElementById('rf');
var allforms=document.getElementById('allforms');
reform.style.display="none";



    
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

var divcost=document.createElement("div");
var inputcost=document.createElement("input");

var divgrading=document.createElement("div");
var inputgrading=document.createElement("select");

var divtoe=document.createElement("div");
var inputtoe=document.createElement("select");



var divtmissed=document.createElement("div");
var inputtm=document.createElement("input");
inputtm.setAttribute("type","time");
inputtm.setAttribute("id","time");


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
    } else if(loggedUser.role=="Employee"){
        console.log("employee");
        nav.innerHTML='';
        nav.innerHTML += `
        <div class="title">
        Menu</div>
        <ul class="list-items">
        
        <li><a href="#"><i class="fas fa-home" onclick="profile()"></i>${loggedUser.role}</a></li>
        
        <li><a href="#"><i class="fas fa-home" onclick="profile()"></i>Max Amount</a></li>
        <li><a href="#"><i class="fas fa-home" onclick="myamount()"></i>My Form</a></li>
        <li><a href="#"><i class="fas fa-sliders-h" onclick="newform()"></i>Submit a Form</a></li>
        <li><a href="#"><i class="fas fa-address-book"></i>CONTACT US</a></li>
        
        <li><a href="#"><i class="fas fa-address-book" onclick="logout()"></i>Logout</a></li>
        `;
        }   else{
            console.log("non employee");
        nav.innerHTML='';
        nav.innerHTML += `
        <div class="title">
        Menu</div>
        <ul class="list-items">
        
        <li><a href="#"><i class="fas fa-home" onclick="profile()"></i>${loggedUser.role}</a></li>
        <li><a href="#"><i class="fas fa-home" onclick="myamount()"></i>Max Amount</a></li>

        <li><a href="#"><i class="fas fa-home" onclick="myform()"></i>My Form</a></li>
        <li><a href="#"><i class="fas fa-home" onclick="getforms()"></i>Check Forms</a></li>
        <li><a href="#"><i class="fas fa-sliders-h" onclick="newform()"></i>Submit a Form</a></li>
        <li><a href="#"><i class="fas fa-address-book"></i>CONTACT US</a></li>
        
        <li><a href="#"><i class="fas fa-address-book" onclick="logout()"></i>Logout</a></li>
        `

        }


    let loginBtn = document.getElementsByName('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else {
        loginBtn.onclick = login;
        console.log("hrlosssaaass");
        console.log(loginBtn.innerHTML);
    }

}
function myamount(){
    console.log(loggedUser.max_claim);
}

async function myform(){
    
    let myforms=allforms2+'/emp/'+loggedUser.emp_id;
    let response = await fetch(myforms,{method: 'GET'});
    switch (response.status) {
        case 200: // successful
            let allforms2 = await response.json();
            console.log("forms in ");
            givemyforms(allforms2);
           // form.innerHTML='';
            
         //   document.getElementById('loo').style.display="none";
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
         //   document.getElementById('pass').value = '';
            break;
        case 404: // user not found
            alert('That  exist.');
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }


    
    // let fors=document.getElementById('myfo');
    // console.log("gormiasnd");
    // let form=document.createElement('form');
    // form.setAttribute('class','file');
    // form.id='muform';
    // let uploda=document.createElement('input');
    // uploda.setAttribute('type','file');
    // uploda.id='inpFile';
    // let filename=document.createElement('button');
    // filename.setAttribute('type','submit');
    // filename.innerHTML=`upload`;
    // form.appendChild(uploda);
    // form.appendChild(filename);
    // fors.appendChild(form);

    // const myform=document.getElementById("muform");
    // const inpFile= document.getElementById("inpFile");

    // //filename.onclick=up;

    
    // myform.addEventListener("submit", e=>{

    //     e.preventDefault();

    //     const formdata= new FormData();
    //     console.log(inpFile.files);

    //     formdata.append("inpFile",inpFile.files[0]);

    //      fetch('load.php',{
    //         method: 'POST',
    //         body: formdata
    //     }).catch(console.error);

    // });

}
async function up(){
        console.log("asdasdasdppppppppppp");
        const formdata= new FormData();
        formdata.append("inpFile",inpFile.files[0]);
        await fetch('load.php',{
            method: 'POST',
            headers: {
                "Content-Type": "text/plain"
            },   body: formdata
        });
    }


function givemyforms(allforms2){
    allforms.innerHTML = '';
    allforms.style.width='70%';
    
    allforms.style.height='70%';

    if (allforms2.length > 0) {
        console.log("all formssss"+ allforms2.length);
        let table = document.createElement('table');
        table.id = 'formTable';
       

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>location</th>
                <th>Start Time</th> 
                <th>Description</th>
                <th>Cost</th>
                <th>FormStatus</th>
                <th>TimeMissed</th>
                <th>Grading format</th>
            </tr>
        `;

        for (let form of allforms2  ) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${form.from_id}</td>
                <td>${form.dates}</td>
                <td>${form.location}</td>
                <td>${form.timet}</td>
                <td>${form.description}</td>
                <td>${form.cost}</td>
                <td>${form.form_status}</td>
                <td>${form.work_time_missed}</td>
                <td>${form.grading_format}</td>
                
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');
            td.id='forms'
           // td.appendChild(ul);
          //  tr.appendChild(td);
         
            let adoptBtn = document.createElement('button');
            adoptBtn.type = 'button';
            adoptBtn.id = 'acp_'+form.from_id;
            adoptBtn.textContent = 'Upload Passing grade';
            adoptBtn.disabled = !loggedUser;
            
            // let rejBtn = document.createElement('button');
            
            // rejBtn.type = 'button';
            // rejBtn.id = 'acp_'+form.form_id;
            // rejBtn.textContent = 'Reject';
            // rejBtn.disabled = !loggedUser;
            // let askBtn = document.createElement('button');
            
            // askBtn.type = 'button';
            // askBtn.id = 'ask_'+form.form_id ;
            // askBtn.textContent = 'Ask for more info';
            // askBtn.disabled = !loggedUser;
            // // <button type="button" id="Howard_6"
            // //  disabled="false">Adopt</button>
            
             let btnTd = document.createElement('td');
             btnTd.appendChild(adoptBtn);
              tr.appendChild(btnTd);
             if(!(form.toa_id.toa_id===4)){
                 adoptBtn.disabled=true;
             }
            table.appendChild(tr);
            
            adoptBtn.addEventListener('click', uploadfile);
            
        }

        allforms.appendChild(table);
    } else {
        allforms.innerHTML = 'No cats are available.';
    }
}
function uploadfile(){
    console.log("uploading file");
}


async function getforms(){

    let response = await fetch(allforms2,{method: 'GET'});
    switch (response.status) {
        case 200: // successful
            let allforms2 = await response.json();
            console.log("forms in ");
            showall(allforms2);
           // form.innerHTML='';
            
         //   document.getElementById('loo').style.display="none";
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
         //   document.getElementById('pass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }

}
function showall(allforms2){
    

    allforms.innerHTML = '';

    if (allforms2.length > 0) {
        console.log("all formssss"+ allforms2.length);
        let table = document.createElement('table');
        table.id = 'formTable';
       

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Employee id</th>
                <th>Date</th>
                <th>location</th>
                <th>Start Time</th> 
                <th>Description</th>
                <th>Cost</th>
                <th>FormStatus</th>
                <th>TimeMissed</th>
                <th>Grading format</th>
                <th>TypeApproval</th>
            </tr>
        `;

        for (let form of allforms2  ) {
            let tr = document.createElement('tr');
            
            tr.innerHTML = `
                <td>${form.from_id}</td>
                <td>${form.emp_id.emp_id}</td>
                <td>${form.dates}</td>
                <td>${form.location}</td>
                <td>${form.timet}</td>
                <td>${form.description}</td>
                <td>${form.cost}</td>
                <td>${form.form_status}</td>
                <td>${form.work_time_missed}</td>
                <td>${form.grading_format}</td>
                <td>${form.toa_id.toa_id}</td>
                
            `;
          //  console.log(form.form_id+"forms");
            let td = document.createElement('td');
            let ul = document.createElement('ul');
            td.id='forms'
           // td.appendChild(ul);
          //  tr.appendChild(td);

            let adoptBtn = document.createElement('button');
            adoptBtn.type = 'button';
            adoptBtn.id = 'acp_'+form.from_id;
            adoptBtn.textContent = 'Accept';
            adoptBtn.disabled = !loggedUser;
            
            let rejBtn = document.createElement('button');
            
            rejBtn.type = 'button';
            rejBtn.id = 'acp_'+form.form_id;
            rejBtn.textContent = 'Reject';
            rejBtn.disabled = !loggedUser;
            let askBtn = document.createElement('button');
            
            askBtn.type = 'button';
            askBtn.id = 'ask_'+form.form_id ;
            askBtn.textContent = 'Ask for more info';
            askBtn.disabled = !loggedUser;
            // <button type="button" id="Howard_6"
            //  disabled="false">Adopt</button>
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(askBtn);
            btnTd.appendChild(adoptBtn);
            btnTd.appendChild(rejBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);
            
            askBtn.disabled=true;
            adoptBtn.disabled=true;
            rejBtn.disabled=true;
            console.log(loggedUser.role +"  " +form.toa_id.toa_id);
            if((loggedUser.role==="Direct" && form.toa_id.toa_id===1)){
                console.log("herer");
                askBtn.disabled=false;
                adoptBtn.disabled=false;
                rejBtn.disabled=false;
            }else if((loggedUser.role==="DepartHead" && form.toa_id.toa_id===2)){
             
                askBtn.disabled=false;
                adoptBtn.disabled=false;
                rejBtn.disabled=false;
            }else if((loggedUser.role==="benco" && form.toa_id.toa_id===3)){
             
                askBtn.disabled=false;
                adoptBtn.disabled=false;
                rejBtn.disabled=false;
            }


            
            adoptBtn.addEventListener('click', updateform);
        }

        allforms.appendChild(table);
    } else {
        allforms.innerHTML = 'No cats are available.';
    }
}
async function updateform(){
        let btnId = event.target.id;
        console.log("btn id "+btnId);
        let index = btnId.indexOf('_'); // find underscore (see line 46)
        let id = btnId.slice(index+1); // get text after underscore
        let name = btnId.replace('_', ''); // remove underscore
        if (confirm('You want to adopt ' + name + '?')) {
            console.log(allforms2+'/'+id);
            let url = allforms2 +'/'+ id;
            let response = await fetch(url,{method: 'GET'});
            let form= await response.json();
            console.log("form toa id:"+form.toa_id.toa_id);
            let toa2={};
            toa2.stage="";
            if(form.toa_id.toa_id===1){
                console.log("tis 1");
                toa2.toa_id=form.toa_id.toa_id+1;
            }else if(form.toa_id.toa_id===2){
                console.log("tis 2");
                toa2.toa_id=form.toa_id.toa_id+1;

            }else if(form.toa_id.toa_id===3){
                toa2.toa_id=form.toa_id.toa_id+1;
                // if(toa2.toa_id===4){
                //     updatecost(form);
                // }
                //updatecost(form);
            }else if(form.toa_id.toa_id===4){
               // updatecost(form);
            }
            form.toa_id=toa2;

            console.log("form toa id"+JSON.stringify(form));

            let newres=await fetch(url,{method: 'PUT',body:JSON.stringify(form)});
            if (newres.status === 200) {
                alert('Updated successfully.');
            } else {
                alert('Something went wrong.');
            }   
        }
        
    }

async function updatecost(form){

    console.log("form cosrt"+form.cost)
    let percent=form.event_id.event_coverage;
    let max=form.emp_id.max_claim;
    let cost=form.cost;
    let awarded=cost*(percent/100);
    let empl=form.emp_id.emp_id;
    let emplink=baseUrl+'/users/'+empl;
    console.log(emplink+"    :LINK");
    let emplo=await fetch(emplink);
    let emp=await emplo.json();
    if(emplo.status===200){
       // alert('asdads');
        console.log("employee"+JSON.stringify(emp));
        console.log("cost:"+cost+" awarded: "+awarded+"max: "+max);

    }else{
        alert('Something went wrong.');

    }
    if(cost>max_claim){
        emp.awardedclaim=max_claim;
    }else{
    emp.awardedclaim=awarded;}
    emp.max_claim=max-awarded;
    // form.emp_id.awardedclaim=awarded;
    // form.emp_id.max_claim=max-awarded;
    let url=baseUrl+'/users/'+emp.emp_id;
    let updateduser=await fetch(url,{method: 'PUT',body:JSON.stringify(emp)});

    if(updateduser.status===200){
        alert('Updated successfully.');

    }else{
        alert('Something went wrong.');

    }
    setNav();

}

function profile(){

    

}
function newform(){

    reform.innerHTML='Reimbursment Form:<br><br>'
    reform.style.display="inline-flex";

    var fromdiv= document.createElement('div');
   
    divevent_id.innerHTML='<br>Event Id<br>';
    divevent_id.appendChild(eventidinput);
    fromdiv.appendChild(divevent_id);
    divdes.innerHTML='Description<br>';
    divdes.appendChild(inputdesc);
    fromdiv.appendChild(divdes);

   

    divlocation.innerHTML='Location<br>';
    divlocation.appendChild(locationinput);
    fromdiv.appendChild(divlocation);

    divtime.innerHTML='<br>Start Time';
    divtime.appendChild(timeinput);
    fromdiv.appendChild(divtime);

    divdates.innerHTML='Date<br>';
    divdates.appendChild(datesinput);
    fromdiv.appendChild(divdates);

    divcost.innerHTML=`Cost<br>`;
    divcost.appendChild(inputcost);
    fromdiv.appendChild(divcost);
    var label =document.createElement('label');
    label.innerHTML='Grading format';
    divgrading.innerHTML=`Grading format<br>`;

    inputgrading.innerHTML+=` <select name="cars" id="cars"
<option value="Letters">Letters</option>
<option value="Letters">Letters</option>
<option value="Seminars">Pass/Fail</option>
<option value="Percentage">Percentage</option>
</select>`;
    divgrading.appendChild(inputgrading);
    fromdiv.appendChild(divgrading);

    divtmissed.innerHTML=`End Time :    `;
    divtmissed.appendChild(inputtm);
    fromdiv.appendChild(divtmissed);


    var label=document.createElement("label");
    label.innerHTML=`Type of event:`;
    
    inputtoe.innerHTML+=` <select name="cars" id="cars"
                        <option value="volvo">Volvo</option>
    <option value="Courses">Courses</option>
    <option value="Seminars">Seminars</option>
    <option value="Certification Classes">Certification Classes</option>
    <option value="Certification">Certification</option>
    <option value="Technical Training">Technical Training</option>
    <option value="Others">Others</option>
  </select>`;
    divtoe.appendChild(label);
    divtoe.appendChild(inputtoe);

    fromdiv.appendChild(divtoe);
    
    var subbtn=document.createElement("button");
    subbtn.innerHTML=`Submit Request`;

    fromdiv.appendChild(subbtn);
    reform.appendChild(fromdiv);

    subbtn.onclick=submitform;
    

}
async function submitform()
{
    let form={};
    console.log(loggedUser.emp_name+"submit");
    //form.from_id=0;
    form.emp_id=loggedUser;
    var value=eventidinput.value;
    console.log("vakue+"+value);
    form.event_id={};
    let event={};
    
    
    if(value == 1){
        event.event_id=1;

    }else if(value == 2){
        event.event_id=2;
    }else if(value ==3){
        event.event_id=3;
    }else if(value==4){
        event.event_id=4;
    }else if(value ==5){
        event.event_id=5;
    }else if(value==6){
        event.event_id=6;
    }

    event.event_name='';
    event.event_coverage=0;
    //event=eventidinput.value;
    form.event_id=event;
    form.dates=datesinput.value+'';
    form.timet=timeinput.value+'';
    form.location=locationinput.value;
    form.description=inputdesc.value;
    form.cost=inputcost.value;
    form.grading_format=inputgrading.value;
    //form.event_id=inputtoe.value;
    form.form_status="Pending";
    var utc = new Date().toJSON().slice(0,10);
    console.log("toady date:"+utc+"event time :"+datesinput.value);
    if(datediff(parseDate(utc), parseDate(datesinput.value))<14){
        form.priority='Urgent';
    }else{
        form.priority='Non Urgent';

    }
    let toa={};
    console.log("role"+loggedUser.role);
    if(!(loggedUser.role=='Direct')){
            console.log("id=1");
            toa.toa_id=1;
        }else{
            console.log("id=2");

            toa.toa_id=2;
             }
    toa.stage="";
    form.toa_id=toa;    
    form.work_time_missed=String(inputtm.value);
    let url=baseUrl+'/form';
    let response = await fetch(url,{method:'POST',body:JSON.stringify(form)});
    if(response.status===201){
        alert('Form submitted');
        reform.innerHTML=``;
        reform.outerHTML=``;
        setNav();
    }else{
        alert('Form was not submitted');
    }
}
function parseDate(str) {
    var mdy = str.split('-');
    return new Date(mdy[1], mdy[0]-1, mdy[2]);
}
function datediff(first, second) {
    // Take the difference between the dates and divide by milliseconds per day.
    // Round to nearest whole number to deal with DST.
    return Math.round((second-first)/(1000*60*60*24));
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
    console.log("chekcoing");
 let url = baseUrl + '/users';
let response = await fetch(url);
if (response.status === 200){ loggedUser = await response.json();
    setNav();

}
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
