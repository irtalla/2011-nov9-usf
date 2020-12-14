let selectedDraft = null;

function populateSections(){
    let pitch = selectedPitch;
    let status = pitch.status;
    let role = loggedUser.role;

    document.getElementById("pitchSection").innerHTML = getUpdatePitchForm();
    document.getElementById("pitchInfoRequestSection").innerHTML = getListOfPitchInfoRequests();
    document.getElementById("addPitchFeedbackSection").innerHTML = getAddPitchFeedbackForm();
    document.getElementById("pitchFeedbackSection").innerHTML =getListOfPitchFeedback();
    document.getElementById("addPitchFeedbackSection").innerHTML = getAddPitchFeedbackForm();

    
    
    
    if(pitch.draft){
        selectedDraft = pitch.draft;
        str += `<a href="draft.html">Draft</a>`;
    }

    if(status == "APPROVED"){
        if(role == "AUTHOR") {
            str += getAddDraftForm();
        }
    }else{
        str += getPitchInfoRequestForm();

        const mayReactAsAsst = !hasBeenApprovedByEditorWithRole("ASSISTANT_EDITOR") && (loggedUser.role == "ASSISTANT_EDITOR");
        const mayReactAsGeneral = !hasBeenApprovedByEditorWithRole("GENERAL_EDITOR") && loggedUser.role == "GENERAL_EDITOR";
        const mayReactAsSenior = !hasBeenApprovedByEditorWithRole("SENIOR_EDITOR") && loggedUser.role == "SENIOR_EDITOR";
        
        if(mayReactAsAsst || mayReactAsGeneral || mayReactAsSenior){
            str += addPitchFeedbackForm
        }
    }
    
    document.getElementById("pitchSection").innerHTML = str;
}

function getUpdatePitchForm(){
    //createdAt handled in db; status and priority handled in controller
    let pitch = selectedPitch;

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

    str += `    <button onclick="updatePitch()" id="submit-add-pitch-form" >"Add Pitch"</button>
        </form>
    `;
    return str;
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
        str += `>${value}</option>
    `); //tried reduce, but wouldn't work...

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

function getTargetableEditors(){

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

}

function addPitchFeedback(){

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