"use strict"

import {baseUrl, loggedUser, setLoggedUser} from "./global.js";

let main = document.getElementById("mainSection");
const currentUser = localStorage.getItem("loggedUser");
if (!currentUser) logout();
setLoggedUser(currentUser);


setUpMainSection();

function setUpMainSection() {
    if (JSON.parse(currentUser).role.id == 1) {
        setupAuthor();
    } else if (JSON.parse(currentUser).role.id == 2) {
        setupAssistEditor();
    } else if (JSON.parse(currentUser).role.id == 3) {
        setupGeneralEditor();
    } else {
        setupSeniorEditor();
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

function setupAssistEditor() {
    let pitches = document.createElement("table");
    pitches.id = "pitchTable";
    pitches.className = "pitchTable";
    let header = document.createElement("thead");
    header.id = "pitchTableHeader";
    header.className = "pitchTableHeader";
    header.innerHTML = `
        <tr>
            <th> priority
            <th> committee
            <th> author
            <th> title
            <th> submitted
            <th> current stage
            <th> current status
            <th> 
        </tr>
        `;
    pitches.appendChild(header);
    main.appendChild(pitches);

    getAssistantPitches();
}

function setupGeneralEditor() {
    let pitches = document.createElement("table");
    pitches.id = "pitchTable";
    pitches.className = "pitchTable";
    let header = document.createElement("thead");
    header.id = "pitchTableHeader";
    header.className = "pitchTableHeader";
    header.innerHTML = `
        <tr>
            <th> priority
            <th> committee
            <th> author
            <th> title
            <th> submitted
            <th> current stage
            <th> current status
            <th> 
        </tr>
        `;
    pitches.appendChild(header);
    main.appendChild(pitches);

    getGeneralEditorPitches();
}

function setupSeniorEditor() {
    let pitches = document.createElement("table");
    pitches.id = "pitchTable";
    pitches.className = "pitchTable";
    let header = document.createElement("thead");
    header.id = "pitchTableHeader";
    header.className = "pitchTableHeader";
    header.innerHTML = `
        <tr>
            <th> priority
            <th> committee
            <th> author
            <th> title
            <th> submitted
            <th> current stage
            <th> current status
            <th> 
        </tr>
        `;
    pitches.appendChild(header);
    main.appendChild(pitches);

    getSenioEditorPitches();
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

async function getAssistantPitches() {
    let userId = null;
    if (currentUser) {
        let parsedUser = JSON.parse(currentUser);
        userId = parsedUser.id;
    }

    let url = baseUrl + '/users/editor/committees/' + userId;
    let response = await fetch(url);
    if (response.status === 200) {
        let committees = await response.json();
        let genres = [];
        for (let committee of committees) {
            genres.push(committee.genre.id);
        }
        console.log(genres);
        url = baseUrl + `/pitch/genre/${genres}/${true}`;
        response = await fetch(url);
        if (response.status === 200) {
            let allPitches = await response.json();
            console.log(allPitches);
            let assistantPitches = [];
            for (let pitch of allPitches) {
                if (pitch.pitchStage.id == 2) {
                    assistantPitches.push(pitch);
                }
            }
            populatePitchTable(assistantPitches);
        }
    }
}

async function getGeneralEditorPitches() {
    let userId = null;
    if (currentUser) {
        let parsedUser = JSON.parse(currentUser);
        userId = parsedUser.id;
    }

    let url = baseUrl + '/users/editor/committees/' + userId;
    let response = await fetch(url);
    if (response.status === 200) {
        let committees = await response.json();
        let genres = [];
        for (let committee of committees) {
            genres.push(committee.genre.id);
        }
        console.log(genres);
        url = baseUrl + `/pitch/genre/${genres}/${false}`;
        response = await fetch(url);
        if (response.status === 200) {
            let allPitches = await response.json();
            console.log(allPitches);
            let generalPitches = [];
            for (let pitch of allPitches) {
                if (pitch.pitchStage.id == 3) {
                    generalPitches.push(pitch);
                }
            }
            populatePitchTable(generalPitches);
        }
    }
}

async function getSenioEditorPitches() {
    let userId = null;
    if (currentUser) {
        let parsedUser = JSON.parse(currentUser);
        userId = parsedUser.id;
    }

    let url = baseUrl + '/users/editor/committees/' + userId;
    let response = await fetch(url);
    if (response.status === 200) {
        let committees = await response.json();
        let genres = [];
        for (let committee of committees) {
            genres.push(committee.genre.id);
        }
        console.log(genres);
        url = baseUrl + `/pitch/genre/${genres}/${true}`;
        response = await fetch(url);
        if (response.status === 200) {
            let allPitches = await response.json();
            console.log(allPitches);
            let seniorPitches = [];
            for (let pitch of allPitches) {
                if (pitch.pitchStage.id == 4) {
                    seniorPitches.push(pitch);
                }
            }
            populatePitchTable(seniorPitches);
        }
    }
}

async function populatePitchTable(pitches) {
    console.log(pitches);
    let table = document.getElementById("pitchTable");
    let highPriorities = [];
    let allPitches = [];
    for (let pitch of pitches) {
        let p = pitch;
        let row = document.createElement("tr");
        row.id = `pitch_${p.id}`;

        if (JSON.parse(currentUser).role.id != 1) {
            let priority = p.priority;
            let priorityEntry = document.createElement("td");
            if (priority == "NORMAL") priorityEntry.className = "NormalPriority";
            else {
                priorityEntry.className = "HighPriority";
                highPriorities.push(p.id);
            }
            allPitches.push(p.id);
            priorityEntry.innerHTML = priority;
            row.appendChild(priorityEntry);
    
            let committee = p.genre.name;
            let committeeEntry = document.createElement("td");
            committeeEntry.innerHTML = committee;
            row.appendChild(committeeEntry);

            let author = p.author.firstName + " " + p.author.lastName;
            let authorEntry = document.createElement("td");
            authorEntry.innerHTML = author;
            row.appendChild(authorEntry);
        }

        let title = p.title;
        let titleEntry = document.createElement("td");
        titleEntry.innerHTML = title;
        row.appendChild(titleEntry);

        let submission = null;
        if (JSON.parse(currentUser).role.id == 1) {
            submission = reformatDate(p.pitchMadeAt);
        } else {
            submission = reformatDate(p.pitchArrivedAt);
        }

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
        viewPitchBtn.className = "submitBtn";
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

    if (highPriorities.length > 0) {
        forbidView(highPriorities, allPitches);
    }
}

function forbidView(highPriorities, allPitches) {
    for (let id of allPitches) {
        if (!highPriorities.includes(id)) {
            let btn = document.getElementById(`viewPitchBtn_${id}`);
            btn.disabled = true;
        }
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

