"use strict"

import {baseUrl, setLoggedUser} from "./global.js";
let form = document.getElementById("submissionForm");

setSubmission();

function setSubmission() {
    form.innerHTML = `
        <form>
            <label for="title">Title:</label>
            <input id="title" name="title" type="text" maxlength="50"/>
            <br>
            <label for="completionDate">Expected Completion Date:</label>
            <input id="completionDate" name="completionDate" type="date" value="2020-12-11" main="2020-12-11"/>
            <br>
            <label for="tagline">Tagline:</label>
            <input id="tagline" name="tagline" type="text" maxlength="150"/>
            <br>
            <label for="description">Description:</label>
            <input id="description" name="description" type="text"/>
            <br>
        `;
    getGenres();
    form.innerHTML += `

        `
}

async function getGenres() {
    let url = baseUrl + "/pitch/genre";
    let response = await fetch(url);
    let genres = [];
    if (response.status === 200) {
        genres = await response.json();
    }
    form.innerHTML += `
          <label for="genre">Genre:</label>
          <select id="genre" name="genre">
        `;
    for (let genre of genres) {
        form.innerHTML += `<option value="${genre.name}">${genre.name}</option>`;
    }
    form.innerHTML += `
            </select>
            <br>
        `;
}

async function getStoryTypes() {
    let url = baseUrl + "/pitch/story_type";
    let response = await fetch(url);
    if (response.status === 200) {
        return await response.json();
    }
}

async function  getPitchStage() {
    let url = baseUrl + "/pitch/pitch_stage";
    let response = await fetch(url);
    if (response.status === 200) {
        return await response.json();
    }
}

async function getReviewStatus() {
    let url = baseUrl + "/pitch/review_status";
    let response = await fetch(url);
    if (response.status === 200) {
        return await response.json();
    }
}

async function getPriority() {
    let url = baseUrl + "/pitch/priority";
    let response = await fetch(url);
    if (response.status === 200) {
        return await response.json();
    }
}