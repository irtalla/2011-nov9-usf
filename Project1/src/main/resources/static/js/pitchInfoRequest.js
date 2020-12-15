function getAddPitchInfoRequestForm(){
    if(!(status == "PENDING" && role != "AUTHOR")) {
        return "";
    }

    let str = `
        <form>
            <input id="explanation" name="explanation" type="text" />
            <select id="targetEditor" name="targetEditor">
    `;

    getTargetableEditors().forEach(editor => 
        str += `<option value="${editor}">${editor.firstName} + ${editor.lastName}</option>`
    );

    str += `</select>
            <button type="button" id="addPitchInfoRequestBtn"></button>
        </form>
    `;

    subject.innerHTML += str;
    document.getElementById("addPitchInfoRequestBtn").onclick = addPitchInfoRequest;
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

async function addPitchInfoRequest(){
    let status = document.getElementById("status").value;
    let explanation = document.getElementById("explanation").value;
    
    let newPitchInfoRequest = {
        targetedPerson: document.getElementById("targetEditor").value,
        status: status,
        explanation: explanation
    };
    
    let response = await fetch(baseUrl + `/pitches/${selectedPitch.id}/info_requests`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newPitchInfoRequest)
    });
    if(response.status == 200){
        //re-populate pitch feedback list, adding new pitch request
        selectedPitch.infoRequests.push(await response.json());
        clearSubject();
        generatePitchProfile();
    }else{
        alert("Could not add Pitch Info Request");
    }
}