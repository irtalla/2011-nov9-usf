"use strict"

import {baseUrl, loggedUser, setLoggedUser} from "./global.js";

let viewer = document.getElementById("pitchViewer");
const currentUser = localStorage.getItem("loggedUser");
const currentPitch = localStorage.getItem("pitchToView");
if (!currentUser) logout();
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
        <br><br>
        `;
    let returnBtn = document.createElement("button");
    returnBtn.type = "button";
    returnBtn.id = "returnToHubBtn";
    returnBtn.className = "submitBtn";
    returnBtn.onclick = returnToHub;
    let rText = document.createTextNode("return to hub");
    returnBtn.appendChild(rText);
    viewer.appendChild(returnBtn);

    if (JSON.parse(currentUser).role.id >= 2) {
        let requestBtn = document.createElement("button");
        requestBtn.type = "button";
        requestBtn.id = "makeRequestBtn";
        requestBtn.className = "submitBtn";
        requestBtn.onclick = () => { makeRequest(`${parsedPitch.id}`); };
        let reqText = document.createTextNode("make a request");
        requestBtn.appendChild(reqText);
        viewer.appendChild(requestBtn);

        let acceptBtn = document.createElement("button");
        acceptBtn.type = "button";
        acceptBtn.id = "acceptPitchBtn";
        acceptBtn.className = "submitBtn";
        acceptBtn.onclick = acceptPitch;
        let accText = document.createTextNode("approve pitch");
        acceptBtn.appendChild(accText);
        viewer.appendChild(acceptBtn);

        let rejectBtn = document.createElement("button");
        rejectBtn.type = "button";
        rejectBtn.id = "rejectPitchBtn";
        rejectBtn.className = "submitBtn";
        rejectBtn.onclick = rejectPitch;
        let rejText = document.createTextNode("reject pitch");
        rejectBtn.appendChild(rejText);
        viewer.appendChild(rejectBtn);
    }

    for (let file of parsedPitch.additionalFiles) {
        let path = file.path;
        let pitchId = path.slice(path.indexOf("_") + 1);
        pitchId = pitchId.slice(0, pitchId.indexOf("/"));
        let fileName = path.slice(path.lastIndexOf("/") + 1);
        console.log(fileName);
        let url = baseUrl + `/pitch/file/${pitchId}/${fileName}`;
        console.log(url);
        let downloads = document.createElement("a");
        downloads.href = url;
        downloads.download = fileName;
        downloads.text = fileName;
        downloads.onclick = () => { getFile(url); };
        document.getElementById("additionalFiles").appendChild(downloads);
    }

}

async function getPitch () {
    let pitchId = JSON.parse(currentPitch);
    let url = baseUrl;
    url += "/pitch/" + pitchId; 
    let pitch = null;
    let response = await fetch(url);
    if (response.status === 200) {
        pitch = await response.json();
        setupDefault(pitch);
    }
}

async function getFile(url) {
    let fileUrl = url;
    let response = await fetch(fileUrl);
    if (response.status === 200) {
        console.log("Filed successfully downloaded!");
    } else {
        alert("Failed to download the file. Sorry!");
    }
}

function returnToHub() {
    localStorage.setItem("pitchToView", null);
    window.location.replace("./pitchHub.html");
}

function makeRequest(pitch) {
    localStorage.setItem("loggedUser", loggedUser);
    localStorage.setItem("pitchToView", JSON.stringify(pitch));
    window.location.replace("./submitRequest.html");
}

function acceptPitch() {

}

function rejectPitch() {

}