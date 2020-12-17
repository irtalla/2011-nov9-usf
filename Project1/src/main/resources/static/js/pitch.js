function generatePitchProfile(){
    let status = selectedPitch.status;
    let role = loggedUser.role;

    if(role == "AUTHOR") {
       generateUpdatePitchForm();
    }else{
       generatePitchDetails();
    }
         
    generateListOfPitchInfoRequests();
    generateListOfPitchFeedback();
    
    if(status == "APPROVED"){
        if(pitch.draft){
            selectedDraft = pitch.draft;
            generateLinkToDraft();
        }else if(role == "AUTHOR") {
            generateAddDraftForm();
        }
    }else if(role != "AUTHOR" && status == "PENDING"){
        generateAddPitchInfoRequestForm();
        generateAddPitchFeedbackForm();
    }
}

function generateLinkToDraft(){
    subject.innerHTML += `<button type="button" id="linkToDraftBtn">Draft</button>`;
    document.getElementById("linkToDraftBtn").onclick = () => {
        clearSubject();
        generateDraftProfile();
    };
}

function generateUpdatePitchForm(){
    //createdAt handled in db; status and priority handled in controller
    const stringsForKeys = {
        storyType: getSelectForEnum("Story Type", storyTypes),
        genre: getSelectForEnum("Genres", genres),
        tentativeCompletionDate: `
            <input type="date" id="tentativeCompletionDate" name="tentativeCompletionDate"
                value=${Date.now}
                min=${Date.now}
            >`
    };

    let str = `
        <h3>Add Pitch</h3>
        <form id="add-pitch-form">
    `;

    userLazyKeys.forEach(key => {
        if(Object.keys(stringsForKeys).includes(key)){
            str += stringsForKeys[key]
        }else{
            str += `
                <label for=${key}>${toPascalCase(str)}: </label>
                <input id=${key} name=${key} type="text" value=${selectedPitch[key]}/>   
            `;
        }
    });

    str += `    <button type="button" id="updatePitchButton" >Update Pitch</button>
        </form>
    `;
    subject.innerHTML += str;
    let updatePitchButton = document.getElementById("updatePitchButton");
    updatePitchButton.onclick = updatePitch;
}

async function updatePitch(){
    let pitchUpdate = {};
    keysInOrder.forEach(key => newPitch[key] = document.getElementById(key));
    newPitch["status"] = authorRemainingPoints(pitch.author) > 0 ? "PENDING" : "SAVED";

    let response = await fetch(baseUrl + `/pitches/${pitch.id}`, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(pitchUpdate)
    });

    if(response.status == 200){
        // successful
        selectedPitch = await response.json();
        clearSubject();
        generatePitchProfile();
    }else{
        alert('Pitch could not be updated.');
    }
}

function getSelectForEnum(enumName, values){
    let str = getLabelForKey(enumName);
    str += `
        <select id=${values} name=${enumName}>
    `; 
    
    values.forEach(value => {
        str += `<option value=${value.toUpperCase()}`;
        if(value == selectedPitch[getCamelCase(enumName)]){
            str += "selected";
        }
        str += `>${value}</option>`
    }); //tried reduce, but wouldn't work...

    str += `</select>`;
    return str;
}

function hasBeenApprovedByEditorWithRole(role){
    selectedPitch.feedback.forEach(reaction => {
        if(reaction.status == "APPROVED" && feedback.editor.role == role){
            return true;
        }
    });
    return false;
}

function generateListOfPitchFeedback(){
    let str = `
        <ul>
    `;

    pitch.feedback.forEach(reaction => {
        let editor = reaction.editor;
        str += `<li>${editor.role.toLowerCase()} Editor ${editor.firstName} + ${editor.lastName} (${reaction.status}): ${reation.explanation}</li>`    
    });

    str += `</ul>`
    subject.innerHTML += str;
}

function generateListOfPitchInfoRequests(){
    let str = `
        <ul>
    `;

    pitch.infoRequests.forEach(request=> {
        let editor = request.requestingEditor;
        str += `<li>${editor.role.toLowerCase()} Editor ${editor.firstName} + ${editor.lastName}: ${reation.explanation}</li>`    
    });

    str += `</ul>`
    subject.innerHTML += str;
}

function generateAddDraftForm(){
   let str = `
        <form>
            <input id="narrative" name="narrative" type="text">
            <button type="button" id="addDraftButton" onclik="addDraft">App Draft<button>
        </form>
    `;
    subject.innerHTML += str;
    let addDraftButton = document.getElementById("addDraftButton");
    addDraftButton.onclick = addDraft;
}


async function addDraft(){
    let newDraft = {
        pitch: selectedPitch,
        narrative: document.getElementById("narrative").nodeValue,
    }
    let response = await fetch(baseUrl + "/drafts", {
        method:"POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newDraft)
    });

    if(response.status == 200){
        selectedDraft = await response.json();
        clearSubject();
        generateDraftProfile();
    }else{
        alert("Could not add draft");
    }
}

async function getEditorsThatMayView(){
    if(selectedPitch.status != "PENDING"){
        return [];
    }else if(!hasBeenApprovedByEditorWithRole("ASSISTANT_EDITOR")){
        let response = await fetch(baseUrl + `/users/editors/ASSISTANT_EDITOR/${selectedPitch.genre}`);
        if(response.status == 200){
            return await response.json();
        }else{
            alert(`Could not retrieve editors with role ASSISTANT_EDITOR and genre ${genre}`);
        }
    }else if(!hasBeenApprovedByEditorWithRole("GENERAL_EDITOR")){
        return getEditorsByRoleAndGenre("ASSISTANT_EDITOR", selectedPitch.genre);
    }else if(!hasBeenApprovedByEditorWithRole("SENIOR_EDITOR")){
        let response = await fetch(baseUrl + `/users/editors/GENERAL_EDITOR`);
        if(response.status == 200){
            return await response.json();
        }else{
            alert(`Could not retrieve editors with role ${role} and genre ${genre}`);
        }
    }
}

async function getEditorsByRoleAndGenre(role, genre){
    let response = await fetch(baseUrl + `/users/editors/${role}/${genre}`);
    if(response.status == 200){
        return await response.json();
    }else{
        alert(`Could not retrieve editors with role ${role} and genre ${genre}`);
    }
}

function generatePitchDetails(){
    let pitch = selectedPitch;
    
    subject.innerHTML += `
        <h4>${pitch.tentativeTitle} (<i>${status}</i>)</h4>
        <p>By ${pitch.pseudoFirstName} ${pitch.pseudoLastName}</p>

        <b>Story Type: ${pitch.storyType.toLowerCase()}</b>
        <b>Genre: ${pitch.storyType.toLowerCase()}</b>
        
        <h5>Description:</h5>
        <p>${pitch.description}</p>

        <h5>Tag line: ${pitch.tagLine}</h5>

        <h5>Tentative Completion Date: ${pitch.tentativeCompletionDate}</h5>

        <h5>Author bio:</h5>
        <p>${pitch.bio}</p>

        <h5>Created at:${pitch.createdAt}</h5>
    `;
}