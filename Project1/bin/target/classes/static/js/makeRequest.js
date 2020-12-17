"use strict"

import {baseUrl, loggedUser, setLoggedUser} from "./global.js";
let form = document.getElementById("submissionForm");
const currentUser = localStorage.getItem("loggedUser");
const currentPitch = localStorage.getItem("pitchToView");
if (!currentUser) logout();
setLoggedUser(loggedUser);

setSubmission();

function setSubmission() {
    getPitch();
}

function setForm(pitch) {
    form.innerHTML = `
        <form id="form">
            <h1>Request to ${pitch.author.firstName} ${pitch.author.lastName}</h1>
            <h2>Regarding ${pitch.title}</h2>
            <label for="question" name="question">Question:</label>
            <input id="question" name="question" type="text"/>
            <button type="button" name="sendRequest" id="sendRequestBtn" class="submitBtn">send request</button>
            <button type="button" name="returnToPitch", id="returnToPitchBtn" class="submitBtn">return to pitch</button>
        </form>
        `;
        document.getElementById("sendRequestBtn").onclick = () => { submitRequest(pitch); };
        document.getElementById("returnToPitchBtn").onclick = returnToPitch;
}

async function getPitch() {
    let pitchId = JSON.parse(currentPitch);
    let url = baseUrl;
    url += "/pitch/" + pitchId; 
    let pitch = null;
    let response = await fetch(url);
    if (response.status === 200) {
        pitch = await response.json();
        setForm(pitch);
    }
}

async function submitRequest(pitch) {
    let url = baseUrl + "/request";
    let requester = JSON.parse(currentUser);
    let requestee = pitch.author;
    let reviewStatus = await getReviewStatus();
    let status = reviewStatus[2];
    console.log(status);

    let data = {
        id: 0,
        question: document.getElementById("question").value,
        answer: ``,
        requestMadeAt: getNow(),
        requester: requester,
        requestee: requestee,
        requestStatus: status,
        pitchId: JSON.parse(currentPitch)
    }

    console.log(data);

    let response = await fetch(url, {
        method: 'POST',
        body: JSON.stringify(data)
    });

    if (response.status === 200) {
        alert("Successfully made request! Heading back to the hub...");
        returnToHub();
    } else {
        alert("There was an error. Try again.");
    }
}

function getNow() {
    let now = new Date();
    return now.toISOString().slice(0,-1);
}

async function getReviewStatus() {
    let url = baseUrl + "/pitch/review_status";
    let response = await fetch(url);
    if (response.status === 200) {
        return await response.json();
    }
}

function returnToHub() {
    localStorage.setItem("pitchToView", null);
    localStorage.setItem("loggedUser", currentUser);
    window.location.replace("./pitchHub.html");
}

function returnToPitch() {
    localStorage.setItem("pitchToView", currentPitch);
    localStorage.setItem("loggedUser", currentUser);
    window.location.replace("./viewPitch.html");
}