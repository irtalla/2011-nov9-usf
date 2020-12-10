"use strict"

import {baseUrl, setLoggedUser} from "./global.js";
let form = document.getElementById("submissionForm");

setSubmission();

function setSubmission() {

    getGenres();
    // getAdditionalClasses();
}

async function getGenres() {
    let url = baseUrl + "/pitch/genre";
    let response = await fetch(url);
    let length = 0;
    let genres = [];
    if (response.status === 200) {
        for (let etnry of response.json()) {
            console.log(Object.keys(entry));
        }
    }
}

async function getAdditionalClasses() {

    url = baseUrl + "/pitch/story_type";
    response = await fetch(url);
    if (response.status === 200) {
        console.log(await response.json());
    }

    url = baseUrl + "/pitch/pitch_stage";
    response = await fetch(url);
    if (response.status === 200) {
        console.log(await response.json());
    }

    url = baseUrl + "/pitch/review_status";
    response = await fetch(url);
    if (response.status === 200) {
        console.log(await response.json());
    }

    url = baseUrl + "/pitch/priority";
    response = await fetch(url);
    if (response.status === 200) {
        console.log(await response.json());
    }
}