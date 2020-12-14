function populateSections(){
    let draft = selectedDraft;
    let pitch = draft.pitch;
    let status = draft.status;
    let role = loggedUser.role;

    let str = `
        <h4><a href="pitch.html">Pitch Info:</a></h4>`;
    str += getPitchUpdateForm();
    str += `
        <form>
            <h2>Narrative:</h2>
            <textarea>${draft.narrative}</textarea>

            <button id="updateNarrative"></button>
        <form>
    `;
    document.getElementById("draftSection").innerHTML = str;

    str = ``;
    str += getListOfDraftFeedback();

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