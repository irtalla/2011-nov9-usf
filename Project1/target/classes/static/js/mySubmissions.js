/*jshint esversion: 8 */
checkLogin().then(getPitchesByAuthor);

function populatePitches(pitches){
   let pitchSection = document.getElementById('pitchSection');

   if(pitches.length > 0){
      let table = document.createElement('table');

      table.innerHTML = `
         <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Title</th>
            <th>Tag Line</th>
            <th>Description</th>
            <th>Status</th>
            <th>Priority</th>
            <th>Editor Notes</th>
            <th>Approvals</th>
         </tr>
      `;

      for(let pitch of pitches){
         let tr = document.createElement('tr');
         tr.innerHTML = `
            <td>${pitch.id}</td>
            <td>${pitch.type.name}</td>
            <td>${pitch.author.username}</td>
            <td>${pitch.genre.name}</td>
            <td>${pitch.title}</td>
            <td>${pitch.tagLine}</td>
            <td>${pitch.description}</td>
            <td>${pitch.status.name}</td>
            <td>${pitch.priority}</td>
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
         tr.appendChild(td);
         if(pitch.status.name === 'Approved'){
            let addDraftBtn = document.createElement('button');
            button.onclick = addDraft(pitch);
            tr.appendChild(addDraftBtn);
         }
         table.appendChild(tr);
      }

      pitchSection.appendChild(table);
   }
   else{
      pitchSection.innerHTML = 'You\'re all outta pitches, boss.';
   }
}

async function getPitchesByAuthor(){
   console.log(loggedUser);

   let url = baseUrl + '/users/' + loggedUser.id + '/pitches';
   let response = await fetch(url);
   if(response.status === 200){
      let pitches = await response.json();
      populatePitches(pitches);
   }
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
}
