let selectedDraft = null;
let pitch = selectedPitch;
let status = pitch.status;
let role = loggedUser.role;
populatePitchSections();

function populatePitchSections(){
    document.getElementById("pitchSection").innerHTML = (role == "AUTHOR") ? getUpdatePitchForm() : getPitchDetails();
    document.getElementById("pitchInfoRequestSection").innerHTML = getListOfPitchInfoRequests();
    document.getElementById("pitchFeedbackSection").innerHTML =getListOfPitchFeedback();
    
    if(status == "APPROVED"){
        if(pitch.draft){
            selectedDraft = pitch.draft;
            str += `<a href="draft.html">Draft</a>`;
        }else if(role == "AUTHOR") {
            str += getAddDraftForm();
        }
    }else if(role != "AUTHOR" && status == "PENDING"){
        document.getElementById("addPitchInfoRequestSection").innerHTML = getAddPitchInfoRequestForm();

        const mayReactAsAsst = !hasBeenApprovedByEditorWithRole("ASSISTANT_EDITOR") && (loggedUser.role == "ASSISTANT_EDITOR");
        const mayReactAsGeneral = !hasBeenApprovedByEditorWithRole("GENERAL_EDITOR") && loggedUser.role == "GENERAL_EDITOR";
        const mayReactAsSenior = !hasBeenApprovedByEditorWithRole("SENIOR_EDITOR") && loggedUser.role == "SENIOR_EDITOR";
        
        if(mayReactAsAsst || mayReactAsGeneral || mayReactAsSenior){
            document.getElementById("addPitchFeedbackSection").innerHTML = getAddPitchFeedbackForm();
        }
    }
}

function getUpdatePitchForm(){
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

    keysInOrder.forEach(key => {
        if(Object.keys(stringsForKeys).includes(key)){
            str += stringsForKeys[key]
        }else{
            str += `
                <label for=${key}>${toPascalCase(str)}: </label>
                <input id=${key} name=${key} type="text" value=${selectedPitch[key]}/>   
            `;
        }
    });

    str += `    <button onclick="updatePitch" id="submit-add-pitch-form" >Add Pitch</button>
        </form>
    `;
    return str;
}

function updatePitch(){
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
        window.open("pitch.html","_self");
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

function getListOfPitchFeedback(){
    let str = `
        <ul>
    `;

    pitch.feedback.forEach(reaction => {
        let editor = reaction.editor;
        str += `<li>${editor.role.toLowerCase()} Editor ${editor.firstName} + ${editor.lastName} (${reaction.status}): ${reation.explanation}</li>`    
    });

    str += `</ul>`
    return str;
}

function getListOfPitchInfoRequests(){
    let str = `
        <ul>
    `;

    pitch.infoRequests.forEach(request=> {
        let editor = request.requestingEditor;
        str += `<li>${editor.role.toLowerCase()} Editor ${editor.firstName} + ${editor.lastName}: ${reation.explanation}</li>`    
    });

    str += `</ul>`
    return str;
}

function getAddDraftForm(){
   return `
        <form>
            <input id="narrative" name="narrative" type="text">
            <button type="button" id="addPitchButton" onclik="addDraft">App Draft<button>
        </form>
    `;
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
        window.open("draft.html","_self");
    }else{
        alert("Could not add draft");
    }
}

function getAddPitchInfoRequestForm(){
    if(!(status == "PENDING" && role != "AUTHOR")) {
        return "";
    }

    let str = `
        <form>
            <input id="explanation" name="explanation" type="text" />
            <select>
    `;

    getTargetableEditors().forEach(editor => 
        str += `<option value="${editor}">${editor.firstName} + ${editor.lastName}</option>`
    );

    str += `</select>
            <button id="addPitchFeedbackBtn" onclick="addPitchFeedback"></button>
        </form>
    `;

    return str;
    // document.getElementById("addPitchFeedbackSection").innerHTML = str;
}

async function getTargetableEditors(){
    if(selectedPitch.status == "PENDING"){
        let editors = new Set();
        selectedPitch.feedback.forEach(reaction => editors.add(reaction.editor));
        selectedPitch.infoRequests.forEach(request => editors.add(reaction.requestingEditor));
        
        return editors;
    }else{
        return [];
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

function addPitchRequest(){
    let response = await fetch(baseUrl + `pitches/${selectedPitch.id}/info_requests`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newPitchRequest)
    });
    if(response.status == 200){
        //re-populate pitch feedback list, adding new pitch request

    }else{
        alert("Could not add Pitch Info Request");
    }
}

function getAddPitchFeedbackForm(){
    return `
        <form>
            <select id="status" name="status">
                <option value="APPROVED">Approved</option>
                <option value="SELECTED" selected>Pending</option>
                <option value="DENIED">Denied</option>
            </select>
            <input id="explanation" name="explanation" type="text" />
            <button id="addPitchFeedbackBtn" onclikc="addPitchFeedback" />
        </form>
    `;
}

async function addPitchFeedback(){
    let status = document.getElementById("status").value.toUpperCase();
    let explanation = document.getElementById("explanation").value;
    
    let newFeedback = {
        status: status,
        explanation: explanation
    }

    if(status == "DENIED" && !!explanation){
        alert("Denying a Draft Requires an Explanation!");
        return;
    }
    let response = await fetch(baseUrl + `/pitches/${selectedPitch.id}/pitch_feedback`, {
        method: "POST",
        headers: {
            "Content-Type": 'application/json'
        },
        body: JSON.stringify(newFeedback)
    });

    if(response.status == 200){
        populatePitchSections();
    }else{
        alert("Could not add Feedback to Pitch.");
    }
}

function getPitchDetails(){
    let pitch = selectedPitch;
    
    return `
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