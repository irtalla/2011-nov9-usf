"use strict"

import {baseUrl, loggedUser, setLoggedUser} from "./global.js";

let viewer = document.getElementById("pitchViewer");
const currentUser = localStorage.getItem("loggedUser");
const currentPitch = localStorage.getItem("pitchToView");
setLoggedUser(currentUser);

setViewer();

function setViewer() {
    getPitch();
}

function setupDefault(pitch) {
    console.log(pitch);
    const parsedPitch = pitch;
    let pitchDay = parsedPitch.pitchMadeAt.dayOfMonth;
    let pitchMonth = parsedPitch.pitchMadeAt.monthValue - 1;
    let pitchYear = parsedPitch.pitchMadeAt.year;
    let pitchMade = new Date(pitchYear, pitchMonth, pitchDay).toDateString();
    let complDay = parsedPitch.completionDate.dayOfMonth;
    let complMonth = parsedPitch.completionDate.monthValue - 1;
    let complYear = parsedPitch.completionDate.year;
    let completionDate = new Date(complYear, complMonth, complDay).toDateString();

    viewer.innerHTML += `
        <h1> Title:  ${parsedPitch.title} </h1>
        <h2> Author:  ${parsedPitch.author.firstName} ${parsedPitch.author.lastName} </h2>
        <h3> Author Email: ${parsedPitch.author.email} </h3>
        <h2> Genre:  ${parsedPitch.genre.name} </h2>
        <h2> Story Type:  ${parsedPitch.storyType.name} </h2>
        <h2> Tagline:  </h2>
        <p> ${parsedPitch.tagline} </p>
        <h2> Description:  </h2>
        <p> ${parsedPitch.description} </p>
        <h3> Pitch Made:  ${pitchMade} </h3>
        <h3> Projected Completion:  ${completionDate} </h3>
        <h3> Additional Files: </h3>
        <section id="additionalFiles"> </section>

        `;
    let returnBtn = document.createElement("button");
    returnBtn.type = "button";
    returnBtn.id = "returnToHubBtn";
    returnBtn.className = "submitBtn";
    returnBtn.onclick = returnToHub;
    let rText = document.createTextNode("return to hub");
    returnBtn.appendChild(rText);
    viewer.appendChild(returnBtn);

    for (let file of parsedPitch.additionalFiles) {
        let path = file.path;
        console.log(path);
        path = path.replace("./src/main/resources/static/", "/");
        console.log(path);
        let fileName = path.slice(path.lastIndexOf("/") + 1);
        console.log(fileName);
        console.log(path);
        // path = "/files/pitch_1/index.html";
        let downloads = document.createElement("a");
        downloads.href = path;
        downloads.download = true;
        downloads.text = fileName;
        document.getElementById("additionalFiles").appendChild(downloads);
    }
}

async function getPitch () {
    let pitchId = JSON.parse(currentPitch);
    let url = baseUrl + "/pitch/" + pitchId;
    // console.log(url);

    let pitch = null;
    let response = await fetch(url);
    if (response.status === 200) {
        pitch = await response.json();
        setupDefault(pitch);
    }
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

function returnToHub() {
    localStorage.setItem("pitchToView", null);
    window.location.replace("./pitchHub.html");
}