function generateAddPitchFeedbackForm(){
    const mayReactAsAsst = !hasBeenApprovedByEditorWithRole("ASSISTANT_EDITOR") && (loggedUser.role == "ASSISTANT_EDITOR");
    const mayReactAsGeneral = !hasBeenApprovedByEditorWithRole("GENERAL_EDITOR") && loggedUser.role == "GENERAL_EDITOR";
    const mayReactAsSenior = !hasBeenApprovedByEditorWithRole("SENIOR_EDITOR") && loggedUser.role == "SENIOR_EDITOR";
    
    if(mayReactAsAsst || mayReactAsGeneral || mayReactAsSenior){
        document.getElementById("addPitchFeedbackSection").innerHTML = getAddPitchFeedbackForm();
    }
}

function getAddPitchFeedbackForm(){
    subject.innerHTML += `
        <form>
            <select id="status" name="status">
                <option value="APPROVED">Approved</option>
                <option value="SELECTED" selected>Pending</option>
                <option value="DENIED">Denied</option>
            </select>
            <input id="explanation" name="explanation" type="text" />
            <button type="button" id="addPitchFeedbackBtn" />Add Feedback<button>
        </form>
    `;
    let addPitchFeedbackBtn = document.getElementById("addPitchFeedbackBtn");
    addPitchFeedbackBtn.onclick = addPitchFeedback;
}

async function addPitchFeedback(){
    let status = document.getElementById("status").value;
    let explanation = document.getElementById("explanation").value;
    let newPitchFeedback = {
        status: status,
        explanation: explanation
    };
    if(status == "DENIED" && !explanation){
        alert("Denials must be explained!");
    }
    
    let response = await fetch(baseUrl + `/pitches/${selectedPitch.id}/feedback`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newPitchFeedback)
    });
    if(response.status == 200){
        //re-populate pitch feedback list, adding new pitch request
        selectedPitch.feedback.push(await response.json());
        clearSubject();
        generatePitchProfile();
    }else{
        alert("Could not add Pitch Info Request");
    }
}