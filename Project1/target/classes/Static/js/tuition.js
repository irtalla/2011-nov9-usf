let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
let loggedUser = null;

checkLogin();

setNav();
function setNav() {
    nav.innerHTML = `
            <a href="index.html"><strong>TRMS</strong></a>`;
            
    if (!loggedUser) {
        nav.innerHTML += `
            <form>
                <label for="userid">Employee ID: </label>
                <input id="empid" name="empid" type="text" />
                <label for="pass"> Password: </label>
                <input id="emppw" name="emppw" type="password" />
                <button type="button" id="loginBtn">Log In</button>
            </form>
        `;
        
    } else {
        if (loggedUser.role.id>1){
            nav.innerHTML += `
            <a href="viewCourses.html">View Courses</a>
            
            `;
        }
        nav.innerHTML += `
            <a href="courseForm.html">Course Form</a>
            <span>
                Hello, ${loggedUser.firstName}&nbsp;
                <button type="button" id="loginBtn">Log Out</button><br>
                Department: ${loggedUser.department.name}<br>
                Role: ${loggedUser.role.name}<br>
                Role2: ${loggedUser.role2.name}<br>
                Funds: ${loggedUser.funds}
            </span>
        `;
     
      
    }
 
    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else loginBtn.onclick = login;
}

async function login() {
   
    
    let url = baseUrl + '/users?';
    url += 'empid=' + document.getElementById('empid').value + '&';
    url += 'emppw=' + document.getElementById('emppw').value;
    let response = await fetch(url, {method: 'PUT'});
   
    switch (response.status) {
        case 200: // successful
            loggedUser = await response.json();
      
            setNav();
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('emppw').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            document.getElementById('empid').value = '';
            document.getElementById('emppw').value = '';
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
    let tr = document.getElementsByTagName('section');
    tr.innerHTML = `
 
`;



}
async function checkLogin() {
  
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();

    setNav();
 
    
}



    
