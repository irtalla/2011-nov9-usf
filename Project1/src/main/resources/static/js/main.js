/*jshint esversion: 8 */
let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
let loginMenu = document.getElementById('loginMenu');
let loggedUser = null;
checkLogin();
setNav();

function setNav() {
   nav.innerHTML = `
      <a class="navbar-brand">Pitcher</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
         <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
         <div class="navbar-nav">
         <a class="nav-link active" href="index.html">Home <span class="sr-only">(current)</span></a>

            <div class="navbar-nav" id="navBarRight">
            </div>
         </div>
      </div>
   `;
   loginMenu.innerHTML = ``;
   if (!loggedUser) {
      loginMenu.innerHTML = `
         <div class="form-group">
            <label for="user">Username</label>
            <input type="text" class="form-control" id="user">
         </div>
         <div class="form-group">
            <label for="pass">Password</label>
            <input type="password" class="form-control" id="pass">
         </div>
         <button type="submit" class="btn btn-primary" id="loginBtn">Submit</button>
      `;
   }
   else {
      let navRight = document.getElementById('navBarRight');
      navRight.innerHTML = `
      <a class="nav-link" href="viewSubmissions.html">View Submissions</a>
      <a class="nav-link" href="requestManager.html">Request Manager</a>
      <li><a class="nav-link" href="mySubmissions.html">My Submissions</a></li>
      <li><a class="nav-link" href="requestManager.html">Request Manager</a></li>
      <li><a class="nav-link" href="profile.html">${loggedUser.username}&nbsp;</a></li>
      <form class="form-inline my-2 my-lg-0">
         <button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="loginBtn">Log Out</button>
      </form>
      `;
   }

   let loginBtn = document.getElementById('loginBtn');
   if (loggedUser) loginBtn.onclick = logout;
   else loginBtn.onclick = login;
}

async function login(){
   // example -> http://localhost:8080/users?user=username&pass=password
   let url = baseUrl + '/users?';
   url += 'user=' + document.getElementById('user').value + '&';
   url += 'pass=' + document.getElementById('pass').value;
   let response = await fetch(url, {method: 'PUT'});

   switch(response.status){
      case 200:
         loggedUser = await response.json();
         setNav();
         break;
      case 400:
         alert('Wrong password, dog.');
         document.getElementById('pass').value = '';
         break;
      case 404:
         alert('Couldn\'t find that username, boss.');
         document.getElementById('user').value = '';
         document.getElementById('pass').value = '';
         break;
      default:
         alert('I\'m not sure what went wrong, b.');
         break;
   }
}

async function logout(){
   let url = baseUrl + '/users';
   let response = await fetch(url, {method:'DELETE'});

   if(response.status != 200){
      alert('Something\'s not right, my dude.');
   }
   loggedUser = null;

   setNav();
}

async function checkLogin(){
   let url = baseUrl + '/users';
   let response = await fetch(url);
   if(response.status === 200){
      loggedUser = await response.json();
   }
   setNav();
}
