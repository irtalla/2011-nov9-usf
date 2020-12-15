/*jshint esversion: 8 */
checkLogin().then(setup);
let inbox = document.getElementById('inbox');

function setup(){
   let sendRequestBtn = document.getElementById('sendRequestBtn');
   sendRequestBtn.addEventListener("click", getUser);
   getRequests();
}

function getRequests(){
   let requests = loggedUser.requests;
   populateInbox(requests);
}

function populateInbox(requests){
   if(requests.length > 0){
      let table = document.createElement('table');
      table.id = 'requestTable';

      table.innerHTML = `
         <tr>
            <th>ID</th>
            <th>From</th>
            <th>To</th>
            <th>Description</th>
         </tr>
      `;

      for(let request of requests){
         let tr = document.createElement('tr');
         tr.innerHTML = `
            <td>${request.id}</td>
            <td>${request.fromPerson.name}</td>
            <td>${request.toPerson.name}</td>
            <td>${request.description}</td>
         `;
         let td = document.createElement('td');
         tr.appendChild(td);
         tr.appendChild(td);
         table.appendChild(tr)
      }
      inbox.appendChild(table);
   }
   else{
      inbox.innerHTML = 'No requests';
   }
}

async function getUser(){
   let toId = document.getElementById('toId');
   let request = {};
   let url = baseUrl + '/:' + toId;
   let response = await fetch(url);
   if(response.status === 200){
      let user = response;
      sendRequest(user);
   }
}

async function sendRequest(user){
   let requestText = document.getElementById('requestText');
   getUser(toId);
   let request = {};
   let url = baseUrl + '/:' + toId;
   let response = await fetch(url, {method: 'PUT'});
   if(response.status === 200){
      alert('Success');

   }
   else{
      alert('no bueno');
   }
}
