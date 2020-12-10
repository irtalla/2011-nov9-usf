checkLogin().then(populatePitches);

// async function getPitches(){
//     let url = baseUrl + '/users/pitches/';
//     let response = await fetch(url);
//     if(response.status === 200) {
//         let requests = await response.json();
//         populatePitches(requests);
//     }
// }

function populatePitches() {
    let pitches = loggedUser.pitches;
    let pitchSection = document.getElementById('pitchSection');

    if (pitches.length > 0) {
        let table = document.createElement('table');
        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Story Title</th>
                <th>Story Type</th>
                <th>Genre</th>
                <th>Description</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Stage</th>
                <th>Finish Date</th>
            </tr>
        `;

        for (let pitch of pitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
                <td>${pitch.story_title}</td>
                <td>${pitch.story_type.name}</td>
                <td>${pitch.genre.name}</td>
                <td>${pitch.description}</td>
                <td>${pitch.status.name}</td>
                <td>${pitch.priority.name}</td>
                <td>${pitch.stage.name}</td>
                <td>${pitch.finish_date.month}/${pitch.finish_date.dayOfMonth}/${pitch.finish_date.year}</td>
            `;
          
            table.appendChild(tr);
        } //end for

        pitchSection.appendChild(table);
    } else {
        pitchSection.innerHTML = 'Currently zero pitches';
        
    }//else of if
} //end poppitch 
