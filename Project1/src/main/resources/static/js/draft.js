function generateDraftProfile(){
    generateDraftDetails();
    generateListOfDraftFeedback();
    generateAddDraftFeedbackForm();
}

function hasBeenApprovedByEditorWithRole(role){
    selectedPitch.feedback.forEach(reaction => {
        if(reaction.status == "APPROVED" && feedback.editor.role == role){
            return true;
        }
    });
    return false;
}

function generateDraftDetails(){
    let str = `<h4><button type="button" id="linkToPitch">Pitch Info:</button></h4>`;

    if((role == "AUTHOR" || role == "SENIOR_EDITOR") && draftStatus != "PENDING"){
        str += getPitchUpdateForm();
        if(role=="AUTHOR"){
            str += `
            <form>
                <h2>Narrative:</h2>
                <textarea>${draft.narrative}</textarea>

                <button type="button" id="updateNarrative"></button>
            <form>
            `;
        }
    }else{
        str += getDraftDetails();
    }
    subject.innerHTML += str;
    document.getElementById("linkToPitch").onclick = () => {
        clearSubject();
        generatePitchProfile();
    };
}

function generateListOfDraftFeedback(){
    let str = `
        <ul>
    `;

    draft.feedback.forEach(reaction => {
        let editor = reaction.editor;
        str += `<li>${editor.role.toLowerCase()} Editor ${editor.firstName} + ${editor.lastName} (${reaction.draftStatus}): ${reation.explanation}</li>`    
    });

    str += `</ul>`
    subject += str;
}

function getAddDraftFeedbackForm(){
    if(draftStatus == "PENDING" && loggedUser.role != "GENERAL_EDITOR"){
        //should have already checked that logged editor is in genre committee with this genre
        subject.innerHTML += `
            <form>
                <select id="status">
                    <option value="PENDING" selected>Pending</option>
                    <option value="APPROVED">Approved</option>
                    <option value="DENIED">Denied</option>
                </select>
                
                <label for="explanation">Explanation:</label>
                <input id="explanation" name="explanation" type="text" />

                <button type="button" id="addDraftFeedbackBtn">Add Feedback</button>
            </form>
        `;
        document.getElementById("addDraftFeedbackBtn").onclick = addDraftFeedback;
    }
}

async function addDraftFeedback(){
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
    let response = await fetch(baseUrl + `/drafts/${selectedDraft.id}/feedback`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newFeedback)
    });
    if(response.draftStatus == 200){
        //re-populate pitch feedback list, adding new pitch request
        draft.feedback.push(await response.json());
        clearSubject();
        generateDraftProfile();
    }else{
        alert("Could not add Pitch Info Request");
    }
    //controller should check if conditions met for full-approval via DAO
}