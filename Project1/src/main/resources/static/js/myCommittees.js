checkLogin().then(populateCommittees);

// async function getPitches(){
//     let url = baseUrl + '/users/pitches/';
//     let response = await fetch(url);
//     if(response.status === 200) {
//         let requests = await response.json();
//         populatePitches(requests);
//     }
// }
let poppitch = document.getElementById('poppitch');
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
                <button id="${com.committee_name}Pitches" onclick = "${com.committee_name}Pitches" type="button">
                    ${com.committee_name}
                </button>
            `
            //I was using this earlier but I am trying to use less pages
            //<td><a href="${com.committee_name}Pitches">${com.committee_name}</a><td>
            ;
          
            table.appendChild(tr);
        } //end for

        committeeSection.appendChild(table);
        let fantasyPitch = document.getElementById('fantasyPitches');
        let sci_fiPitch = document.getElementById('sci_fiPitches');
        let fictionPitch = document.getElementById('fictionPitches');
        let historyPitch = document.getElementById('historyPitches');
        let cookingPitch = document.getElementById('cookingPitches');
        let biographyPitch = document.getElementById('biographyPitches');
        let sportsPitch = document.getElementById('sportsPitches');
        let kidsPitch = document.getElementById('kidsPitches');

        fantasyPitch.addEventListener("click", fantasyPitches);
        sci_fiPitch.addEventListener("click", sci_fiPitches);
        fictionPitch.addEventListener("click", fictionPitches);
        historyPitch.addEventListener("click", historyPitches);
        cookingPitch.addEventListener("click", cookingPitches);
        biographyPitch.addEventListener("click", biographyPitches);
        sportsPitch.addEventListener("click", sportsPitches);
        kidsPitch.addEventListener("click", kidsPitches);



    } else {
        committeeSection.innerHTML = 'Editors\' committees';
    }//else of if
 }//end populate committees 
    function fantasyPitches(){
        let url = baseUrl + '/pitch/committees/1'
        populatePitchSection(url);    
    }
    
    function sci_fiPitches(){
        let url = baseUrl + '/pitch/committees/2'
        populatePitchSection(url);
    }
    function fictionPitches(){
        let url = baseUrl + '/pitch/committees/3'
        populatePitchSection(url);
    }
    function historyPitches(){
        let url = baseUrl + '/pitch/committees/4'
        populatePitchSection(url);
    }
    function cookingPitches(){
        let url = baseUrl + '/pitch/committees/5'
        populatePitchSection(url);
    }
    function biographyPitches(){
        let url = baseUrl + '/pitch/committees/6'
        populatePitchSection(url);
    }
    function sportsPitches(){
        let url = baseUrl + '/pitch/committees/7'
        populatePitchSection(url);
    }
    function kidsPitches(){
        let url = baseUrl + '/pitch/committees/8'
        populatePitchSection(url);
    }
//end url switcher function set idk whate else to call this area
    async function populatePitchSection(url){
        poppitch.innerHTML = "";
        let response = await fetch(url);
        if(response.status === 200){
            let genPitches = await response.json();

            if (genPitches.length > 0) {
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
        
                for (let pitch of genPitches) {
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
                        <td><button id="accept" onclick="acceptPitch" type="button" value="${pitch.id}">accept 
                        </button></td>
                        <td><button id="reject" onclick="rejectPitch" type="button" value="${pitch.id}>reject 
                        </button></td>
                        <td><button id="request" onclick="requestPitch" type="button">request 
                        </button></td>
                    `;
                  
                    table.appendChild(tr);
                } //end for
                
                poppitch.appendChild(table);
            } else {
                poppitch.innerHTML = 'No Pitches found for this Committee';
                
            }//else of if
            
        }else{
            poppitch.innerHTML = 'No Pitches found for this Committee';
                
        }//end if staus 200
        let acceptBtn = document.getElementById('accept');
        let rejectBtn = document.getElementById('reject');
        let requestBtn = document.getElementById('request');

        acceptBtn.addEventListener("click", acceptPitch(acceptBtn.value));
        rejectBtn.addEventListener("click", rejectPitch(rejectBtn.value));
        requestBtn.addEventListener("click", requestPitch);

    }//end pop pitch section asnyc funtion stuffs

async function acceptPitch(number){
    let url = baseUrl + '/pitch/' + number;
    let response = await fetch(url, {method: 'PUT', body:JSON.stringify(data)});
    if(response.status >= 200 && response.status < 300){
        console.log("yup");
    }else{
        alert("Something may have happened and the accept was not processed");
    }
}
async function rejectPitch(number){
    let url = baseUrl + '/pitch/'+number;
    let response = await fetch(url, {method: 'DELETE'});
    if(response.status >= 200 && response.status < 300){
        console.log("yup");
    }else{
        alert("Something may have happened and the accept was not processed");
    }
}
async function requestPitch(){
    let url = baseUrl + '/request';
    let response = await fetch(url, {method: 'POST', body:JSON.stringify(data)});
    if(response.status >= 200 && response.status < 300){
        console.log("yup");
    }else{
        alert("Something may have happened and the accept was not processed");
    }
}


//steps for tomorrow, rejectPitch deletes a pitch should be easy enough
//accept a pitch doesn't actually change the status but instead moves
//up the chain of stages depending on if editors exist i guess? 
//request will require a request controller to be built for infoRequest
//this is the mvp for saturday/