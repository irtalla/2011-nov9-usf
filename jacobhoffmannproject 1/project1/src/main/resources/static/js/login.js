let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
let loggedUser;
let loggedAuth;
let loggedEmp;
let loggedEdit;
let isemp;
let iseditor;
checkLogin();
setNav();
//getProps();
function setNav() {
    //nav bar?
    nav.innerHTML = `
            <a href="index.html"><strong>Cat App</strong></a>`;
           // <a href="startscreen.html">View Cats</a>`;
    if (!loggedUser) {
        nav.innerHTML += `
            <form>
                <label for="user">Username: </label>
                <input id="user" name="user" type="text" />
                <label for="pass"> Password: </label>
                <input id="pass" name="pass" type="password" />
                <button type="button" id="loginBtn">Log In</button>
            </form>
        `;
    }else if(iseditor === true&&isemp===true){
        nav.innerHTML += `
            <a href="dprops.html">Draft Proprosals</a>
            <a href="sprops.html">Story Proprosals</a>
            <span>
                <a href="profile.html">${loggedUser.username}&nbsp;</a>
                <button type="button" id="loginBtn">Log Out</button>
                
            </span>
        `;
    } else if(iseditor===false &&isemp===true){
        nav.innerHTML += `
        <a href="draftslist.html">Draft Proprosals</a>
        
        <span>
            <a href="profile.html">${loggedUser.username}&nbsp;</a>
            <button type="button" id="loginBtn">Log Out</button>
            
        </span>
    `;
    }
    else {
        nav.innerHTML += `
            <a href="mydrafts.html">My Drafts</a>
            <a href="myprops.html">My Proposals</a>
            <a href="story.html">My Story</a>
            <span>
                <a href="profile.html">${loggedUser.username}&nbsp;</a>
                <button type="button" id="loginBtn">Log Out</button>
                
            </span>
        `;
    }

    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else loginBtn.onclick = login;
}

async function login() {
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/users?';
    url += 'user=' + document.getElementById('user').value + '&';
    url += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(url, {method: 'PUT'});
    
    switch (response.status) {
        case 200: // successful
            loggedUser = await response.json();
            console.log(loggedUser);

          //  alert (loggedUser);
            findrole();
            setNav();
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

    if (response.status != 200) alert('Something went wrong.');
    loggedUser = null;
    setNav();
}

async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();
    
   await findrole();
    setNav();
}
async function findrole(){
   // alert(loggedUser.is_employee);
    if(loggedUser.is_employee === true){
       // alert('Glorious Success.');
        isemp = true;
        let url = baseUrl +'/Employee?';
        url += 'id='+loggedUser.id;
        let response = await fetch(url, {method: 'PUT'});
         
    switch (response.status) {
        case 200: // successful
       // alert('Glorious Success.');
            loggedEmp = await response.json();
            let url2 = baseUrl+'/Editor?';
            url2+='id='+loggedEmp.id;
            response=await fetch(url2,{method:'PUT'});
            switch(response.status){
                case 200:
                   // alert('Yess');
                    loggedEdit = await response.json();
                   // alert (loggedEdit);
                   // getProps();
                   
                    if(loggedEdit.is_senior ===true){
                    
                    iseditor=true;
                    
            }else{
                iseditor=false;
            }  
            setNav();
                    break;
                default:
                    alert('Not an editor');
                    setNav();
                    break;
            }
            //setNav();
            break;
        case 400: // incorrect password
            alert('dont exist.');
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
    else{
        //alert('hi')
        let url2 = baseUrl +'/Author/user?';
        url2 += 'id='+loggedUser.id;
        let response2 = await fetch(url2, {method: 'GET'});
        loggedAuth = await response2.json();
        
    }
}



