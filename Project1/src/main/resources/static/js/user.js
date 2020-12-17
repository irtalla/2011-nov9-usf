function generateUserProfile(){
    console.log("Generating user profile...")
    generateUpdateUserForm(); //way to both show user details and allow user to update details
    generateViewablePitchesList();
    generateViewableDraftsList();
    generateAddPitchForm();
}

function generateUpdateUserForm(){
    console.log("Generating update form...");
    let user = loggedUser;
    let str = `
        <h2>Your Details:</h2>
        <form id="register-form">
            <label for="email">Email: </label>
            <input id="email" name="email" type="text" value=${user.email} />
            
            <label for="username">Username: </label>
            <input id="username" name="username" type="text" value=${user.username}/>
            
            <label for="password"> Password: </label>
            <input id="password" name="password" type="password" value=${user.password}/>

            <br>
            <label for="firstName">First Name: </label>
            <input id="firstName" name="firstName" type="text" value=${user.firstName}/>
            
            <label for="lastName">Last Name: </label>
            <input id="lastName" name="lastName" type="text" value=${user.lastName}/>
            
            <label for="bio">Bio: </label>
            <textarea id="bio" name="bio" type="text" bio=${user.bio}/></textarea>
        
            <label for="role"> Role: </label>
            <select id="role" name="role">
                <option value="AUTHOR" ${tagRoleOptionAsSelected("AUTHOR", user.role)}>Author</option>
                <option value="ASSISTANT_EDITOR" ${tagRoleOptionAsSelected("ASSISTANT_EDITOR", user.role)}>Assistant Editor</option>
                <option value="GENERAL_EDITOR" ${tagRoleOptionAsSelected("GENERAL_EDITOR", user.role)}>General Editor</option>
                <option value="SENIOR_EDITOR" ${tagRoleOptionAsSelected("SENIOR_EDITOR", user.role)}>Senior Editor</option>
            </select>
        `;

        str += getGenreSelection(); //in main.js

        str += `
            <button type="button" id="updateUserBtn">Update</button>
        </form>`;
    subject.innerHTML += str;
    let updateUserBtn = document.getElementById("updateUserBtn");
    updateUserBtn.onclick = updateUser;
}

function tagRoleOptionAsSelected(roleName, userRole){
    if(roleName == userRole){
        return "selected";
    }
    return "";
}

async function updateUser(){
    console.log("Updating user...");

    let updatedUser = {};
    userLazyKeys.forEach(key => updatedUser[key] = document.getElementById(key).value);
    
    let response = await fetch(baseUrl + `/users/${loggedUser.id}`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedUser)
    });
    if(response.status == 200){
        loggedUser = await response.json();
    }else if(response.status = 409){
        alert("Username already taken!");
    }else{
        alert("Could not update user.");
    }
}

async function generateViewablePitchesList(){
    console.log("Generating viewable pitches list...");

    subject.innerHTML += "<h3>Pitches:</h3>";
    let list = document.createElement("ul");
    let viewablePitches = await getViewablePitches();
    console.log(viewablePitches);
    viewablePitches.forEach(pitch => {
        let item = document.createElement("li");
        let link = document.createElement("button");
        // link.href="pitch.html";
        link.innerHTML= `${pitch.tentativeTitle} by ${pitch.psuedoLastName}; Priority: ${pitch.priority}`;
        link.addEventListener("click", () => {
            selectedPitch = pitch;
            clearSubject();
            generatePitchProfile();
        })
        item.appendChild(link);
        list.appendChild(item);
    });
    subject.appendChild(list);
}

async function getViewablePitches(){
    console.log("Getting viewable pitches...");

    console.log(loggedUser.id);
    let response = await fetch(baseUrl + `/users/${loggedUser.id}/viewablePitches`);
    if(response.status == 200){
        return await response.json();
    }else{
        alert("Could not retrieve viewable pitches.")
    }
}

async function generateViewableDraftsList(){
    console.log("Generating viewable drafts...");
    
    subject.innerHTML += "<h3>Drafts:</h3>";

    let list = document.createElement("ul");
    let drafts = await getViewableDrafts();
    console.log(drafts);
    drafts.forEach(draft => {
        let item = document.createElement("li");
        let link = document.createElement("a");
        link.innerHTML= `${draft.tentativeTitle} by ${draft.psuedoLastName}; Priority: ${draft.priority}`;
        link.addEventListener("click", () => {
            selectedDraft = draft;
            clearSubject();
            generateDraftProfile();
        })
        item.appendChild(link);
        list.appendChild(item);
    });
    subject.appendChild(list);
}

async function getViewableDrafts(){
    console.log("Getting viewable drafts...");

    let response = await fetch(baseUrl + `/users/${loggedUser.id}/viewableDrafts`);
    if(response.status == 200){
        return await response.json();
    }else{
        alert("Could not retrieve viewable pitches.")
    }
}

function authorRemainingPoints(author){
    if(author.pitches.size == 0){
        return 100;
    }
    return 100 - author.pitches.reduce((sum, pitch) => {
        return sum + pitch.storyType.points
    }, 0);
}

function generateAddPitchForm(){
    console.log("Generating add pitch form...");
    //createdAt should be handled in db? status and priority handled in controller?
    if(loggedUser.role != "AUTHOR"){
        return;
    }

    const stringsForKeys = {
        storyType: getSelectForEnum("storyType", storyTypes),
        genre: getSelectForEnum("genre", genres),
        tentativeCompletionDate: `
            <label>TenativeCompletionDate:</label>
            <input type="date" id="tentativeCompletionDate" name="tentativeCompletionDate"
                value=${Date.now}
                min=${Date.now}
            ><br>`
    };

    let str = `
        <h3>Add Pitch:</h3>
        <form id="add-pitch-form">
    `;

    pitchKeysInOrder.forEach(key => {
        if(Object.keys(stringsForKeys).includes(key)){
            str += stringsForKeys[key]
        }else{
            str += `
                <label for=${key}>${toSentenceCase(key)}: </label>
                <input id=${key} name=${key} type="text" /> 
                <br>  
            `;
        }
    });

    str += `    <button type="button" id="addPitchBtn" >Add Pitch</button>
        </form>
    `;
    subject.innerHTML += str;
    document.getElementById("addPitchBtn").onclick = addPitch;
}

async function addPitch(){
    //createdAt should be handled in db? status and priority handled in controller?
    console.log("Adding pitch...");

    let newPitch = {};
    pitchKeysInOrder.forEach(key => {
        console.log(key);
        newPitch[key] = document.getElementById(key).value;
    });
    newPitch["status"] = authorRemainingPoints(loggedUser) > 0 ? "PENDING" : "SAVED";
    newPitch["createdAt"] = Date.now();
    newPitch["priority"] = "NORMAL";
    newPitch["author"] = loggedUser;

    console.log(newPitch);
    let response = await fetch(baseUrl + "/pitches", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newPitch)
    });

    if(response.status == 200){
        // successful
        selectedPitch = await response.json();
        clearSubject();
        generatePitchProfile();
    }else{
        alert('Pitch could not be added.');
    }
}

