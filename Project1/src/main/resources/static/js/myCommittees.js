checkLogin().then(populateCommittees);

// async function getPitches(){
//     let url = baseUrl + '/users/pitches/';
//     let response = await fetch(url);
//     if(response.status === 200) {
//         let requests = await response.json();
//         populatePitches(requests);
//     }
// }

function populateCommittees() {
    let comms = loggedUser.committees;
    let committeeSection = document.getElementById('commSection');

    if (comms.length > 0) {
        let table = document.createElement('table');
        table.innerHTML = `
            <tr>
                <th>Committees</th>

            </tr>
        `;

        for (let com of comms) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${com.committee_name}</td>
                <td>${com.genre.name}</td>
                
            `;
          
            table.appendChild(tr);
        } //end for

        pitchSection.appendChild(table);
    } else {
        pitchSection.innerHTML = 'Currently zero committees';
        
    }//else of if
} //end poppitch 
