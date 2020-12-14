"use strict"

import {baseUrl, loggedUser, setLoggedUser} from "./global.js";

let main = document.getElementById("mainSection");
const currentUser = localStorage.getItem("loggedUser");
setLoggedUser(currentUser);

setUpMainSection();

function setUpMainSection() {
    if (JSON.parse(currentUser).role.id == 1) {
        setupAuthor();
    } else {

    }

}

function setupAuthor() {
    let pitches = document.createElement("table");
    pitches.id = "pitchTable";
    pitches.className = "pitchTable";
    let header = document.createElement("thead");
    header.id = "pitchTableHeader";
    header.className = "pitchTableHeader";
    header.innerHTML = `
        <tr>
            <th> title
            <th> submitted
            <th> current stage
            <th> current status
            <th> 
        </tr>
        `;
    pitches.appendChild(header);
    main.appendChild(pitches);

    let toSubmitPitchBtn = document.createElement("button");
    toSubmitPitchBtn.type = "button";
    toSubmitPitchBtn.id = "toSubmitPitchBtn";
    toSubmitPitchBtn.name = "toSubmitPitchBtn";
    let tspText = document.createTextNode("make a new pitch");
    toSubmitPitchBtn.appendChild(tspText);
    toSubmitPitchBtn.onclick = toSubmitPitch;
    main.appendChild(toSubmitPitchBtn);

    getAuthorPitches();
}

function toSubmitPitch() {
    window.location.replace("./submitPitch.html");
}

async function getAuthorPitches() {
    let userId = null;
    if (currentUser) {
        let parsedUser = JSON.parse(currentUser);
        userId = parsedUser.id;
    }

    let url = baseUrl + '/pitch/author/' + userId;
    let response = await fetch(url);
    if (response.status === 200) {
        populatePitchTable(await response.json());
    }
}

async function populatePitchTable(pitches) {
    console.log(pitches);
    let table = document.getElementById("pitchTable");
    for (let pitch of pitches) {
        let p = pitch;
        let row = document.createElement("tr");
        row.id = `pitch_${p.id}`;

        let title = p.title;
        let titleEntry = document.createElement("td");
        titleEntry.innerHTML = title;
        row.appendChild(titleEntry);

        let submission = reformatDate(p.pitchMadeAt);
        let subEntry = document.createElement("td");
        subEntry.innerHTML = submission;
        row.appendChild(subEntry);

        let pitchStage = p.pitchStage.name;
        let stageEntry = document.createElement("td");
        stageEntry.innerHTML = pitchStage;
        row.appendChild(stageEntry);

        let reviewStatus = p.reviewStatus.name;
        let statusEntry = document.createElement("td");
        statusEntry.innerHTML = reviewStatus;
        row.appendChild(statusEntry);

        let viewPitchBtn = document.createElement("button");
        viewPitchBtn.type = "button";
        viewPitchBtn.id = `viewPitchBtn_${p.id}`;
        viewPitchBtn.className = "submitBnt";
        let vpText = document.createTextNode("view this pitch");
        viewPitchBtn.appendChild(vpText);
        viewPitchBtn.onclick = () => { toViewPitch(`${p.id}`); };
        row.appendChild(viewPitchBtn);

        if (JSON.parse(currentUser).role.id == 1) {
            let toDraftSubmissionBtn = document.createElement("button");
            toDraftSubmissionBtn.type = "button";
            toDraftSubmissionBtn.id = `toDraftSubmissionBtn_${p.id}`;
            toDraftSubmissionBtn.className = "submitBtn";
            if (pitchStage != "Draft") toDraftSubmissionBtn.disabled = true;
            let dsText = document.createTextNode("submit draft");
            toDraftSubmissionBtn.appendChild(dsText);
            toDraftSubmissionBtn.onclick = toDraftSubmission;
            row.appendChild(toDraftSubmissionBtn);
        }

        table.appendChild(row);
    }
}

function reformatDate(rawDate) {

    let year = rawDate.year;
    let month = rawDate.month;
    let date = rawDate.dayOfMonth;
    return new Date(`${month} ${date}, ${year}`).toDateString();
}

function toViewPitch(id) {
    localStorage.setItem("loggedUser", JSON.stringify(loggedUser));
    localStorage.setItem("pitchToView", JSON.stringify(id));
    window.location.replace("./viewPitch.html");
}

function toDraftSubmission() {
    
}

