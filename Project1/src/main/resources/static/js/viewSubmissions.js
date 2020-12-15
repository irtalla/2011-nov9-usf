/*jshint esversion: 8 */
setup();

function setup(){
   let editorMenu = document.getElementById('editorMenu');
   //hide(editorMenu);
   getPitches().then(() => {
      checkLogin().then(checkEditor());
   });
   getDrafts();
   let submitPitchBtn = document.getElementById('submitPitchBtn');
   submitPitchBtn.onclick = submitPitch;
}

async function getPitches(){
   let url = baseUrl + '/pitches';
   let response = await fetch(url);
   if(response.status === 200){
      let pitches = await response.json();
      populatePitches(pitches);
   }
}

async function getDrafts(){
   let url = baseUrl + '/drafts';
   let response = await fetch(url);
   if(response.status === 200){
      let pitches = await response.json();
      populatePitches(pitches);
   }
}

function populatePitches(pitches){
   let pitchSection = document.getElementById('pitchSection');
   pitchSection.innerHTML = '';

   if(pitches.length > 0){
      let table = document.createElement('table');
      table.id = 'pitchTable';

      table.innerHTML = `
         <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Title</th>
            <th>Tag Line</th>
            <th>Description</th>
            <th>Priority</th>
            <th>Status</th>
            <th>Editor Notes</th>
            <th>Approvals</th>
         </tr>
      `;

      for(let pitch of pitches){
         var pitchBadge = document.createElement('span');
         var draftBadge = document.createElement('span');
         pitchBadge.innerHTML = `<span class="badge badge-pill badge-primary">Pitch</span>`;
         draftBadge.innerHTML = `<span class="badge badge-pill badge-primary">Draft</span>`;

         let tr = document.createElement('tr');
         tr.innerHTML = `
            <td>${pitch.id}</td>
            <td>${pitch.type.name}</td>
            <td>${pitch.author.username}</td>
            <td>${pitch.genre.name}</td>
            <td>${pitch.title}</td>
            <td>${pitch.tagLine}</td>
            <td>${pitch.description}</td>
            <td>${pitch.priority}</td>
            <td>${pitch.status.name}</td>
            <td>${pitch.editorNotes}</td>
         `;
         let td = document.createElement('td');
         let ul = document.createElement('ul');
         for(let person of pitch.approvals){
            let li = document.createElement('li');
            li.innerHTML = person.username;
            ul.appendChild(li);
         }
         td.appendChild(ul);
         //td.addEventListener('click', setPitchSelect(pitch.id));
         if(pitch.status.name === 'Approved'){
            let addDraftBtn = document.createElement('button');
            button.onclick = addDraft(pitch);
         }
         tr.appendChild(td);
         tr.appendChild(pitchBadge);

         table.appendChild(tr);
      }

      pitchSection.appendChild(table);

   }
   else{
      pitchSection.innerHTML = 'We\'re fresh outta pitches, fam.';
   }
}

async function populateDrafts(drafts){

      let draftSection = document.getElementById('draftSection');
      pitchSection.innerHTML = '';

      if(drafts.length > 0){
         let table = document.createElement('table');
         table.id = 'draftTable';

         table.innerHTML = `
            <tr>
               <th>ID</th>
               <th>Type</th>
               <th>Author</th>
               <th>Genre</th>
               <th>Title</th>
               <th>Tag Line</th>
               <th>Body</th>
               <th>Priority</th>
               <th>Status</th>
               <th>Editor Notes</th>
               <th>Approvals</th>
            </tr>
         `;

         for(let draft of drafts){
            var draftBadge = document.createElement('span');
            draftBadge.innerHTML = `<span class="badge badge-pill badge-primary">Draft</span>`;

            let tr = document.createElement('tr');
            tr.innerHTML = `
               <td>${draft.id}</td>
               <td>${draft.type.name}</td>
               <td>${draft.author.username}</td>
               <td>${draft.genre.name}</td>
               <td>${draft.title}</td>
               <td>${draft.tagLine}</td>
               <td>${draft.description}</td>
               <td>${draft.priority}</td>
               <td>${draft.status.name}</td>
               <td>${draft.editorNotes}</td>
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');
            for(let person of draft.approvals){
               let li = document.createElement('li');
               li.innerHTML = person.username;
               ul.appendChild(li);
            }
            td.appendChild(ul);
            //td.addEventListener('click', setPitchSelect(pitch.id));
            tr.appendChild(td);
            tr.appendChild(draftBadge);

            table.appendChild(tr);
         }

         draftSection.appendChild(table);

      }
      else{
         draftSection.innerHTML = 'We\'re fresh outta drafts, fam.';
      }
}

function setPitchSelect(pitchId){
   let pitchSelect = document.getElementById('pitchSelect');
   pitchSelect.value = pitchId;
   console.log('Selected pitch id: ' + pitchId);
}

function checkEditor(){
   let role = loggedUser.role;
   let editorMenu = document.getElementById('editorMenu');

   if(role.name === 'Assistant Editor' || role.name === 'General Editor' || role.name === 'Senior Editor'){
      console.log("That's an editor if I've ever seen one");
      //show(editorMenu);
      editorMenu.removeAttribute('hidden');
      document.getElementById('approveBtn').addEventListener("click", approvePitch);
      document.getElementById('rejectBtn').addEventListener("click", rejectPitch);
   }
   else{
      //hide(editorMenu);
   }
}

async function approvePitch(){
   let id = document.getElementById('pitchSelect').value;
   if(confirm('You tryna approve this bad boy? Pitch ID: ' + id)){
      let url = baseUrl + '/pitches/approve/' + id;
      let response = await fetch(url, {method:'PUT'});
      switch(response.status){
         case 200:
            alert('Approved');
            break;
         case 409:
            alert('That pitch doesn\'t exist');
            break;
         case 401:
            alert('You\'re not logged in yet []');
            break;
         default:
            alert('Something went wrong');
            break;
      }
   }
}

async function rejectPitch(){
   let id = document.getElementById('pitchSelect').value;
   if(confirm('Yeah I didn\'t like this one either. Pitch ID: ' + id)){
      var editorNotes = prompt("It would be rude to reject someone without providing a reason.", "Editor Notes");
      if(editorNotes != null && editorNotes != ''){
         let url = baseUrl + '/pitches/reject/' + id;
         let response = await fetch(url, {method:'PUT'});
         switch(response.status){
            case 200:
               alert('Rejected');
               break;
            case 409:
               alert('That pitch doesn\'t exist');
               break;
            case 401:
               alert('You\'re not logged in []');
               break;
            default:
               alert('Something went wrong');
               break;
         }
      }
   }
}
async function submitPitch(){
   let pitch = {};
   pitch.id = 0;
   pitch.author = {};
   pitch.author.id = loggedUser.id;
   pitch.type = {};
   pitch.type.id = document.getElementById('inputTypeID').value;
   pitch.genre = {};
   pitch.genre.id = document.getElementById('inputGenre').value;
   pitch.title = document.getElementById('inputTitle').value;
   pitch.tagLine = document.getElementById('inputTagLine').value;
   pitch.description = document.getElementById('inputDescription').value;
   pitch.priority = 0;
   pitch.status = {};
   pitch.status.id = 1;

   let url = baseUrl + '/pitches/';
   let response = await fetch(url, {method: 'POST', body:JSON.stringify(pitch)});
   if(response.status === 201){
      alert('Pitch submitted');
   }
   else{
      alert('Error while submitting');
   }
   setup();
}

async function addDraft(pitch){
   let draft ={};
   draft.id = 0;
   draft.title = pitch.title;
   draft.type = pitch.type;
   draft.tagLine = pitch.tagLine;
   draft.description = pitch.description;
   draft.priority = 0;
   draft.status.id=1;
   draft.editorNotes='';
   draft.genre = pitch.genre;
   draft.pitch = pitch;

   let url = baseUrl + '/drafts/';
   let response = await fetch(url, {method: 'POST', body:JSON.stringify(pitch)});
   if(response.status === 201){
      alert('draft submitted');
   }
   else{
      alert('Error while submitting');
   }
   setup();
}
