"use strict"

import {baseUrl, loggedUser, setLoggedUser} from "./global.js";

console.log(JSON.parse(localStorage.getItem("loggedUser")));

let main = document.getElementById("mainSection");

setUpMainSection();

function setUpMainSection() {
    let pitches = document.createElement("table");
    main.appendChild(pitches);
    let toSubmitPitchBtn = document.createElement("button");
    toSubmitPitchBtn.type = "button";
    toSubmitPitchBtn.id = "toSubmitPitchBtn";
    toSubmitPitchBtn.name = "toSubmitPitchBtn";
    let tspText = document.createTextNode("make a new pitch");
    toSubmitPitchBtn.appendChild(tspText);
    toSubmitPitchBtn.onclick = toSubmitPitch;
    main.appendChild(toSubmitPitchBtn);
}

function toSubmitPitch() {
    window.location.replace("./submitPitch.html");
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