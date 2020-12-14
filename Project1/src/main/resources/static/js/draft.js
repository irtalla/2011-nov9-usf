populateDraftSections();
let draftStatus = selectedDraft.status;

function populateDraftSections(){
    document.getElementById("draftSection").innerHTML = getDraftSection();
    document.getElementById("draftFeedbackSection").innerHTML = getListOfDraftFeedback();
    document.getElementById("addDraftFeedbackSection").innerHTML = getAddDraftFeedbackForm();
}

function hasBeenApprovedByEditorWithRole(role){
    selectedPitch.feedback.forEach(reaction => {
        if(reaction.draftStatus == "APPROVED" && feedback.editor.role == role){
            return true;
        }
    });
    return false;
}

function getListOfDraftFeedback(){
    let str = `
        <ul>
    `;

    draft.feedback.forEach(reaction => {
        let editor = reaction.editor;
        str += `<li>${editor.role.toLowerCase()} Editor ${editor.firstName} + ${editor.lastName} (${reaction.draftStatus}): ${reation.explanation}</li>`    
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

function getDraftSection(){
    let str = `<h4><a href="pitch.html">Pitch Info:</a></h4>`;

    if((role == "AUTHOR" || role == "SENIOR_EDITOR") && draftStatus != "PENDING"){
        str += getPitchUpdateForm();
        if(role=="AUTHOR"){
            str += `
            <form>
                <h2>Narrative:</h2>
                <textarea>${draft.narrative}</textarea>

                <button id="updateNarrative"></button>
            <form>
            `;
        }
    }else{
        str += getDraftDetails();
    }
    return str;
}

function getAddDraftFeedbackForm(){
    if(draftStatus == "PENDING" && loggedUser.role != "GENERAL_EDITOR"){
        //should have already checked that logged editor is in genre committee with this genre
        return `
            <form>
                <select id="status">
                    <option value="PENDING" selected>Pending</option>
                    <option value="APPROVED">Approved</option>
                    <option value="DENIED">Denied</option>
                </select>
                
                <label for="explanation">Explanation:</label>
                <input id="explanation" name="explanation" type="text" />

                <button id="addDraftFeedbackBtn" onclick="addDraftFeedback">Add Feedback</button>
            </form>
        `;
    }
}

function addDraftFeedback(){
    let status = document.getElementById("status").value.toUpperCase();
    let explanation = document.getElementById("explanation").value;
    let newFeedback = {
        draft: selectedDraft,
        status: status,
        explanation: explanation
    };
    if(status == "DENIED" && !!explanation){
        alert("Denying a Draft Requires an Explanation!");
        return;
    }
    let response = await fetch(baseUrl + `drafts/${selectedDraft.id}/feedback`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newFeedback)
    });
    if(response.draftStatus == 200){
        //re-populate pitch feedback list, adding new pitch request
        populateDraftSections();
    }else{
        alert("Could not add Pitch Info Request");
    }
    //controller should check if conditions met for full-approval via DAO
}