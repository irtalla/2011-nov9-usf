checkLogin().then(populatePitches);

function populatePitches() {
    let pitches = loggedUser.pitches;
    let pitchSection = document.getElementById('pitchSection');
    pitchSection.innerHTML = '';
    if (pitches.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Author</th>
                <th>Title</th>
                <th>Genre</th>
                <th>Storytype</th>
                <th>Tagline</th>
                <th>Description</th>
                <th>CompletionDate</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Extra Info</th>
            </tr>
        `;

        for (let pitch of pitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
                <td>${pitch.authorname}</td>
                <td>${pitch.title}</td>
                <td>${pitch.genre.name}</td>
                <td>${pitch.pitchtype.name}</td>
                <td>${pitch.tagline}</td>
                <td>${pitch.description}</td>
                <td>${pitch.compldate}</td>
                <td>${pitch.status.name}</td>
                <td>${pitch.priority}</td>
                <td>${pitch.extrainfo}</td>
            `;
            if (pitch.status.id % 2 != 0 && pitch.status.id != 1 && pitch.status.id != 11) {
                let editBtn = document.createElement('button');
                let editText = document.createTextNode("Update Info");
                editBtn.type = "button";
                editBtn.appendChild(editText);
                editBtn.onclick = () => {editPitch(pitch);};
                tr.appendChild(editBtn);
            }
            
            table.appendChild(tr);
        }

        pitchSection.appendChild(table);
    } else {
        pitchSection.innerHTML = 'You haven\'t submitted any pitches. What a loser.';
    }
}

function editPitch(pitch) {
    let editBtn = event.target;
    let editId = event.target.id;
    let editTd = editBtn.parentElement;
    let editTr = editTd.parentElement;

    editTr.innerHTML += `
        <td><input id = "eCName" type = "text" placeholder="Respond to the editor here."></td>
        `;
    let saveBtn = document.createElement('button');
    let saveText = document.createTextNode("Save Info Update");
    saveBtn.type = "button";
    saveBtn.appendChild(saveText);
    editTr.appendChild(saveBtn);
    saveBtn.onclick = () => {submitEdits(pitch);};
    alert('Remember to click Save Info Update when you are done to submit your info request.');
}

function submitEdits(pitch) {
    switch (pitch.status.id) {
        case 3:
            pitch.status.id = 2;
            pitch.status.name = "Pending Assistant Approval"; 
            break;
        case 5:
            pitch.status.id = 4;
            pitch.status.name = "Pending Editor Approval";
            break;
        case 7:
            pitch.status.id = 6;
            pitch.status.name = "Pending Senior Approval";
            break;
        case 9:
            pitch.status.id = 8;
            pitch.status.name = "Pending Draft Approval";
            break;
        default:
            break;
    }
    pitch.extrainfo = document.getElementById('eCName').value;
    savePitch(pitch);
}

async function savePitch(newpitch)
{
    let url = baseUrl + '/pitches/' + newpitch.id;

    let response = await fetch(url);

    let pitch = await response.json();
    pitch = newpitch;

    let newResponse = await fetch(url,{method:'PUT',body:JSON.stringify(pitch)});
    if (newResponse.status === 200) {
        alert('Updated successfully.');
    } else {
        alert('Something went wrong.');
    }
    alert('Pitch updated!');
    populatePitches();
}