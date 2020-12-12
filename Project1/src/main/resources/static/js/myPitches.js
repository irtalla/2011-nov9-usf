

checkLogin().then(populatePitches);
makePitch();
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
                <td>${pitch.finish_date}</td>
            `;
          
            table.appendChild(tr);
        } //end for

        pitchSection.appendChild(table);
    } else {
        pitchSection.innerHTML = 'Authors may make pitches';
        
    }//else of if
} //end poppitch 

function makePitch(){
    let makeSection = document.getElementById('makeSection');
    makeSection.innerHTML = "New Pitch Form";

    let pitchform = document.createElement('form');
    pitchform.id = 'newPitch'
    pitchform.innerHTML = `
        <input type='text' id='story_title' placeholder= 'Story Title'><br>
        <label for="story_type">Choose story length:</label>

        <select name="story_type" id="story_type">
        <option value="1">Novel</option>
        <option value="2">Novelle</option>
        <option value="3">Short Stories</option>
        <option value="4">Article</option>
        </select><br>

        <label for="genre">Choose Genre:</label>

        <select name="genre" id="genre">
        <option value="1">Fantasy</option>
        <option value="2">Sci-fi</option>
        <option value="3">Fiction</option>
        <option value="4">History</option>
        <option value="5">Cooking</option>
        <option value="6">Biography</option>
        <option value="7">Sports</option>
        <option value="8">Kids</option>
        </select><br>

        <textarea rows="4" cols="50" id="description" form="newPitch">
        Description of Story...</textarea>

        <label for="fdate">Finish date:</label>

        <input type="date" id="fdate" name="finish_date"
               value="2020-12-01"
               min="2020-12-01" max="2021-12-31">

        <button id="submitChanges" onclick="submitChanges" type="button">
               Submit Changes
        </button>
    `;

    makeSection.appendChild(pitchform);
    let pitchButton = document.getElementById('submitChanges');
    pitchButton.addEventListener("click", submitChanges);
    pitchButton.disabled = !loggedUser;
    
}
async function submitChanges() {

    let pitches = loggedUser.pitches;
    let total = 0;
    let statweight =0;
    for (let pitch of pitches){
        total += pitch.story_type.points;
    }
    if(total >= 100) {
        statweight = 4;
    }else{
        statweight =1;
    }
    let data = {
        //person_id: loggedUser.id,
        story_title: document.getElementById('story_title').value,
        story_type:{
           id: document.getElementById('story_type').value,
        },
        genre:{
            id: document.getElementById('genre').value
        },
        description: document.getElementById('description').value,
        status:{
            id: statweight
        },
        priority:{
            id: 1
        },
        stage:{
            id:1
        },
        finish_date: document.getElementById('fdate').value
           
    };
    let url = baseUrl + '/pitch';
   
    let response = await fetch(url, {method: 'POST', body:JSON.stringify(data)});
    if (response.status >=200 && response.status <300) {
        alert('Pitch sent successfully.');
    } else if(response.status >=400 && response.status < 500){
        alert('400 response oh no')
    }else if(response.status === null){
        alert('there was no response')
    }else{
        alert('Something went wrong.');
    }

}