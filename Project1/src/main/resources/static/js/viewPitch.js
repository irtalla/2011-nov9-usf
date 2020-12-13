"use strict"

import {baseUrl, loggedUser, setLoggedUser} from "./global.js";

let main = document.getElementById("mainSection");


setUpMainSection();

function setUpMainSection() {
    let pitches = document.createElement("table");
    pitches.id = "pitchTable";
    pitches.className = "pitchTable";
    let header = document.createElement("thead");
    header.id = "pitchTableHeader";
    header.className = "pitchTableHeader";
    header.innerHTML = `
        <tr>
            <th> Title
            <th> Submitted
            <th> Current Stage
            <th> Current Status
            <th> Resubmit
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
    let currentUser = localStorage.getItem("loggedUser");
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

        table.appendChild(row);
    }
}

function reformatDate(rawDate) {

    let year = rawDate.year;
    let month = rawDate.month;
    let date = rawDate.dayOfMonth;
    return new Date(`${month} ${date}, ${year}`).toDateString();
}

async function getFiles(file) {
    let name = file.name;
    let blob = new Blob([file]);
    let url = URL.createObjectURL(blob);
    console.log(url);
    let downloader = document.createElement("a");
    downloader.download = name;
    downloader.href = url;
    console.log(downloader);
    downloader.click();
}