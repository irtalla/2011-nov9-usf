checkLogin().then(populatePitches);

function populatePitches() {
    let pitches = loggedUser.pitches;
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
                <td>${pitch.finish_date}</td>
                <td>${pitch.storytype.storytype_name}</td>
                <td>${pitch.genre.genre_name}</td>
                <td>${pitch.description}</td>
                <td>${pitch.status.status_name}</td>
                <td>${pitch.pitch_priority.priority_name}</td>
                <td>${pitch.pitch_stage.stage_name}</td>
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');
            for (let sn of cat.specialNeeds) {
                let li = document.createElement('li');
                li.innerHTML = sn.name;
                ul.appendChild(li);
            }
            td.appendChild(ul);
            tr.appendChild(td);
            table.appendChild(tr);
        }

        catSection.appendChild(table);
    } else {
        catSection.innerHTML = 'You don\'t have any cats. :(';
    }
}