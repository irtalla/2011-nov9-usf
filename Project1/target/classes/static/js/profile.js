/*jshint esversion: 8 */
checkLogin().then(showInfo);
let changingUser = false;
let changingPass = false;

function showInfo(){
   let heading = document.getElementById('heading');
   heading.innerhtml = loggedUser.username;

   let pitches = getPitchesByAuthor();
   let info = document.getElementById('info');
   info.innerHTML = loggedUser.authorInfo;
   document.getElementById('authorInfo').innerHTML = loggedUser.authorInfo;
   let changeInfo = document.getElementById('changeInfo');
   info.innerHTML = `
      <span id="usernameForm">
         <label for="newUser">Change Username:&nbsp;</label>
         <br>
         <input name="newUser" id="newUser" type="text" placeholder="Mr. Username" />
      </span>

      <br>
      <br>

      <span id="passwordForm">
         <label for="newPass">Change Password:&nbsp;</label>
         <br>
         <input name="newPass" id="newPass" type="password" placeholder="P455W0RD" />
         <br>
         <input name="newPassConf" id="newPassConf" type="password" />
      </span>

      <br>
      <br>

      <span id="infoForm">
         <label for="newInfo">New Info:&nbsp;</label>
         <br>
         <input name="newInfo" id="newInfo" type="authorInfo" />
      </span>

      <br>
      <br>

      <button id="submitChanges" onclick="submitChanges" type="button">
         Submit Changes
      </button>
   `;
}

function updateInfo(){

}

function changeUsername(){
   changingUser = !changingUser;
   document.getElementById('usernameForm').toggleAttribute('hidden');
}

function changePassword(){
   changingPass = !changingPass;
   document.getElementById('passwordForm').toggleAttribute('hidden');
}

async function submitChanges(){
   let confirmed = false;
   if(changingUser){
      loggedUser.username = document.getElementById('newUser').value;
      confirmed = true;
   }
   if(changingPass){
      let newPass = document.getElementById('newPass');
      let newPassConf = document.getElementById('newPassConf');
      if(newPass === newPassConf){
         loggedUser.passwd = newPass;
         confirmed = true;
      }
      else{
         alert('Those passwords don\'t match, bruh.');
         confirmed = false;
      }
   }
   if(confirmed){
      let url = baseUrl + '/users/' + loggedUser.id;
      let response = await fetch(url, {method:'PUT', body:JSON.stringify(loggedUser)});
      if(respons === 202){
         alert('Updated successfully.');
      }
      else{
         alert('Something went wrong, boss :(');
      }
      checkLogin().then(showInfo);
   }
}

async function getPitchesByAuthor(){
   let url = baseUrl + '/users/' + loggedUser.id + '/pitches';
   let response = await fetch(url);
   if(response.status === 200){
      let pitches = await response.json();
   }
}
