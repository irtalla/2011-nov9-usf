

checkLogin().then(populatePitches).then(makePitch);
//makePitch();


// async function getPitches(){
//     let url = baseUrl + '/users/pitches/';
//     let response = await fetch(url);
//     if(response.status === 200) {
//         let requests = await response.json();
//         populatePitches(requests);
//     }
// }
let isAuthor = false;

function populatePitches() {
    let pitches = loggedUser.pitches;
    let pitchSection = document.getElementById('pitchSection');

    if (pitches.length > 0) {
        let table = document.createElement('table');
        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Author ID</th?
                <th>Story Title</th>
                <th>Story Type</th>
                <th>Genre</th>
                <th>Description</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Stage</th>
                <th>Finish Date</th>
                <th> Delete? </th>
                <th> ReSubmit? </th>
            </tr>
        `;

        for (let pitch of pitches) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td id ="${pitch.id}pitchId">${pitch.id}</td>
                <td id ="${pitch.id}pitchAuth">${pitch.author}</td>
                <td id ="${pitch.id}pitchTitle">${pitch.story_title}</td>
                <td id ="${pitch.id}pitchType">${pitch.story_type.name}</td>
                <td id ="${pitch.id}pitchGenre">${pitch.genre.name}</td>
                <td id ="${pitch.id}pitchDesc">${pitch.description}</td>
                <td id ="${pitch.id}pitchStatus">${pitch.status.name}</td>
                <td id ="${pitch.id}pitchPriority">${pitch.priority.name}</td>
                <td id ="${pitch.id}pitchStage">${pitch.stage.name}</td>
                <td id ="${pitch.id}pitchFinish">${pitch.finish_date}</td>

                <td><button id="deletePitch" type="button" value="${pitch.id}" 
                onclick="deletePitch(${pitch.id})">Delete This Pitch? 
                </button></td>

                <td><button id="reSubPitch" type="button" value="${pitch.id}" 
                onclick="reSubPitch(${pitch.id})">Resubmit Pitch? 
                </button></td>
            `;
          
            table.appendChild(tr);
        } //end for

        pitchSection.appendChild(table);
    } else {
        pitchSection.innerHTML = 'Authors may make pitches';
        
    }//else of if
    let titles = loggedUser.title;
    if(titles.length >0){
        for(let title of titles){
            if(title.name === 'author'){
                isAuthor = true;
            break;
            }
        }
    }
} //end poppitch 

function makePitch(){
    if(isAuthor){
    let makeSection = document.getElementById('makeSection');
    makeSection.innerHTML = "New Pitch Form";

    let pitchform = document.createElement('form');
    pitchform.id = 'newPitch'
    pitchform.innerHTML = `
        <input type='text' id='story_title' placeholder= 'Story Title'><br>
        <label for="story_type">Choose story length:</label>

        <select name="story_type" id="story_type">
        <option value="1">Novel</option>
        <option value="2">Novella</option>
        <option value="3">Short Story</option>
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

    }   
}
async function submitChanges() {

    let pitches = loggedUser.pitches;
    let total = 0;
    let statweight =0;
    let priorityChecker = 0;
    let pitchStoryType = document.getElementById('story_type');
    let pitchweight = 0;
    if(pitchStoryType.options[pitchStoryType.selectedIndex].text == 'Novel'){
        pitchweight = 50;
    }else if(pitchStoryType.options[pitchStoryType.selectedIndex].text == 'Novella'){
        pitchweight = 25;
    }else if(pitchStoryType.options[pitchStoryType.selectedIndex].text == 'Short Story'){
        pitchweight = 20;
    }else if(pitchStoryType.options[pitchStoryType.selectedIndex].text == 'Article'){
        pitchweight = 10;
    }else{
        alert("Code Monkey get a job!")
    }
    for (let pitch of pitches){
        total += pitch.story_type.points;
    }
    if((total + pitchweight) >= 100) {
        statweight = 4;
        priorityChecker = 3;
    }else{
        statweight =1;
        priorityChecker = 1;
    }
    let data = {
        author: loggedUser.id,
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
            id: priorityChecker
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
        document.location.reload();
    } else if(response.status >=400 && response.status < 500){
        alert('400 response oh no')
    }else if(response.status === null){
        alert('there was no response')
    }else{
        alert('Something went wrong.');
    }

}

async function deletePitch(number){
    let url = baseUrl + '/users/pitches/'+number;
    let data = {
        author: document.getElementById(`${number}pitchId`).value,       
    };

    
    let response = await fetch(url, {method: 'DELETE', body: JSON.stringify(data)});
    if(response.status >= 200 && response.status < 300){

        document.location.reload();
    }
}