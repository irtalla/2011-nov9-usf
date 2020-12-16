getPitches();

async function getPitches() {
    let url = baseUrl + '/pitches';
    let response = await fetch(url);
    if (response.status === 200) {
        let pitches = await response.json();
        populatePitches(pitches);
    }
}

function populatePitches(pitches) {
    let pitchSection = document.getElementById('pitchSection');
    pitchSection.innerHTML = '';
    if (pitches.length > 0) {
        let table = document.createElement('table');
        table.innerHTML = '';
        table.innerHTML += `
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
            tr.innerHTML += `
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
            if (loggedUser.job.id == 2 && ((pitch.status.id == 2) || (pitch.status.id == 3))/* && loggedUser.genres.includes(pitch.genre)*/) {
                let approveBtn = document.createElement('button');
                let approveText = document.createTextNode("Approve");
                approveBtn.type = "button";
                approveBtn.appendChild(approveText);
                approveBtn.onclick = () => {approvePitch(pitch);};
                let rejectBtn = document.createElement('button');
                let rejectText = document.createTextNode("Reject");
                rejectBtn.type = "button";
                rejectBtn.appendChild(rejectText);
                rejectBtn.onclick = () => {rejectPitch(pitch);};
                let editBtn = document.createElement('button');
                let editText = document.createTextNode("Request Info");
                editBtn.type = "button";
                editBtn.appendChild(editText);
                editBtn.onclick = () => {editPitch(pitch);};
                tr.appendChild(approveBtn);
                tr.appendChild(rejectBtn);
                tr.appendChild(editBtn);
            } else if (loggedUser.job.id == 3 && ((pitch.status.id == 4) || (pitch.status.id == 5))) {
                let approveBtn = document.createElement('button');
                let approveText = document.createTextNode("Approve");
                approveBtn.type = "button";
                approveBtn.appendChild(approveText);
                approveBtn.onclick = () => {approvePitch(pitch);};
                let rejectBtn = document.createElement('button');
                let rejectText = document.createTextNode("Reject");
                rejectBtn.type = "button";
                rejectBtn.appendChild(rejectText);
                rejectBtn.onclick = () => {rejectPitch(pitch);};
                let editBtn = document.createElement('button');
                let editText = document.createTextNode("Request Info");
                editBtn.type = "button";
                editBtn.appendChild(editText);
                editBtn.onclick = () => {editPitch(pitch);};
                tr.appendChild(approveBtn);
                tr.appendChild(rejectBtn);
                tr.appendChild(editBtn);
            } else if (loggedUser.job.id == 4 && ((pitch.status.id == 6) || (pitch.status.id == 7))) {
                let approveBtn = document.createElement('button');
                let approveText = document.createTextNode("Approve");
                approveBtn.type = "button";
                approveBtn.appendChild(approveText);
                approveBtn.onclick = () => {approvePitch(pitch);};
                let rejectBtn = document.createElement('button');
                let rejectText = document.createTextNode("Reject");
                rejectBtn.type = "button";
                rejectBtn.appendChild(rejectText);
                rejectBtn.onclick = () => {rejectPitch(pitch);};
                let editBtn = document.createElement('button');
                let editText = document.createTextNode("Request Info");
                editBtn.type = "button";
                editBtn.appendChild(editText);
                editBtn.onclick = () => {editPitch(pitch);};
                tr.appendChild(approveBtn);
                tr.appendChild(rejectBtn);
                tr.appendChild(editBtn);
            } else if ((pitch.status.id == 8) || (pitch.status.id == 9)) {
                let voteBtn = document.createElement('button');
                let voteText = document.createTextNode("Vote to Approve");
                voteBtn.type = "button";
                voteBtn.appendChild(voteText);
                voteBtn.onclick = () => {votePitch(pitch);};
                let rejectBtn = document.createElement('button');
                let rejectText = document.createTextNode("Reject");
                rejectBtn.type = "button";
                rejectBtn.appendChild(rejectText);
                rejectBtn.onclick = () => {rejectPitch(pitch);};
                let editBtn = document.createElement('button');
                let editText = document.createTextNode("Request Info");
                editBtn.type = "button";
                editBtn.appendChild(editText);
                editBtn.onclick = () => {editPitch(pitch);};
                tr.appendChild(approveBtn);
                tr.appendChild(rejectBtn);
                tr.appendChild(editBtn);
            }
            table.appendChild(tr);
        }
        pitchSection.appendChild(table);

    } else {
        pitchSection.innerHTML = 'No one has submitted any pitches.';
    }
}

function editPitch(pitch) {
    let editBtn = event.target;
    let editId = event.target.id;
    let editTd = editBtn.parentElement;
    let editTr = editTd.parentElement;

    editTr.innerHTML += `
        <td><input id = "eCName" type = "text" placeholder="Type your request here."></td>
        `;
    let saveBtn = document.createElement('button');
    let saveText = document.createTextNode("Save Info Request");
    saveBtn.type = "button";
    saveBtn.appendChild(saveText);
    editTr.appendChild(saveBtn);
    saveBtn.onclick = () => {submitEdits(pitch);};
    alert('Remember to click Save Info Request when you are done to submit your info request.');
}

function submitEdits(pitch) {
    switch (pitch.status.id) {
        case 2:
            pitch.status.id = 3;
            pitch.status.name = "Assistant Requested Info"; 
            break;
        case 4:
            pitch.status.id = 5;
            pitch.status.name = "Editor Requested Info";
            break;
        case 6:
            pitch.status.id = 7;
            pitch.status.name = "Senior Requested Info";
            break;
        case 8:
            pitch.status.id = 9;
            pitch.status.name = "Draft Edits Requested";
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
    getPitches();
}

async function approvePitch(pitch) {
    let editBtn = event.target;
    if ((pitch.status.id == 2) || (pitch.status.id == 3)) {
        pitch.status.id = 4;
        pitch.status.name = "Pending Editor Approval";
    } else if ((pitch.status.id == 4) || (pitch.status.id == 5)) {
        pitch.status.id = 6;
        pitch.status.name = "Pending Senior Approval";
    } else if ((pitch.status.id == 6) || (pitch.status.id == 7)) {
        pitch.status.id = 8;
        pitch.status.name = "Pending Draft Approval";
    } 
    savePitch(pitch);
}
async function rejectPitch(pitch) {
    let editBtn = event.target;
    pitch.status.id = 11;
    pitch.status.name = "Rejected";
    savePitch(pitch);
}
async function votePitch(pitch) {
    let editBtn = event.target;
    pitch.votes += 1;
    if (pitch.votes >= 3) {
        pitch.status.id = 10;
        pitch.status.name = "Approved! Congratulations!";
        alert("This pitch has reached required votes and has been approved!");
    } else {
        alert("Vote for approval submitted! Almost there!");
    }
    savePitch(pitch);
}