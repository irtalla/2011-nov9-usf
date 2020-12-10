checkLogin().then(getPitches);

async function getPitches(){
    let url = baseUrl + '/users/pitches/' + loggedUser.id;
    let response = await fetch(url);
    if(response.status === 200) {
        let requests = await response.json();
        populatePitches(requests);
    }
}

function populatePitches(pitches) {

    let pitchSection = document.getElementById('pitchSection');

    if (pitches.length > 0) {
        let table = document.createElement('table');
        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Story Title</th>
                <th>Finish Date</th>
                <th>Story Type</th>
                <th>Genre</th>
                <th>Description</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Stage</th>
            </tr>
        `;

        for (let pitch of pitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
                <td>${pitch.story_title}</td>
                <td>${pitch.storytype.storytype_name}</td>
                <td>${pitch.genre.genre_name}</td>
                <td>${pitch.description}</td>
                <td>${pitch.status.status_name}</td>
                <td>${pitch.pitch_priority.priority_name}</td>
                <td>${pitch.pitch_stage.stage_name}</td>
                <td>${pitch.finish_date}</td>
            `;
          
            table.appendChild(tr);
        } //end for

        pitchSection.appendChild(table);
    } else {
        pitchSection.innerHTML = 'Currently zero pitches';
        
    }//else of if
} //end poppitch 
