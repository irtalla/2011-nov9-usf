"use strict"

import {baseUrl, loggedUser, setLoggedUser} from "./global.js";
let form = document.getElementById("submissionForm");

setSubmission();

function setSubmission() {
    let today = getToday();
    form.innerHTML = `
        <form id="form">
            <label for="title">Title:</label>
            <input id="title" name="title" type="text" maxlength="50"/>
            <br>
            <label for="completionDate">Expected Completion Date:</label>
            <input id="completionDate" name="completionDate" type="date" value="${today}" min="${today}"/>
            <br>
            <label for="tagline">Tagline:</label>
            <input id="tagline" name="tagline" type="text" maxlength="150"/>
            <br>
            <label for="description">Description:</label>
            <input id="description" name="description" type="text"/>
            <br>
        </form>
        `;
    insertGenres();
    document.getElementById("form").appendChild(document.createElement("br"));
    insertStoryTypes();
    document.getElementById("form").appendChild(document.createElement("br"));
    insertSubmitButton();
}

async function submitPitch() {
    let url = baseUrl + "/pitch";

    let currentUser = JSON.parse(localStorage.getItem("loggedUser"));

    let types = await getStoryTypes();
    let storyType = null;
    for (let type of types) {
        if (type.name == document.getElementById("storyType").value) {
            storyType = type;
        }
    }
    let genres = await getGenres();
    let genre = null;
    for (let g of genres) {
        if (g.name == document.getElementById("genre").value) {
            genre = g;
        }
    }

    let priorities = await getPriority();
    let priority = priorities[0];

    let pitchStages = await getPitchStage();
    let stage = pitchStages[0];

    let reviewStatus = await getReviewStatus();
    let status = reviewStatus[0];

    let data = {
        id: 0,
        author: currentUser,
        title: document.getElementById("title").value,
        tagline: document.getElementById("tagline").value,
        storyType: storyType,
        genre: genre,
        description: document.getElementById("description").value,
        completionDate: document.getElementById("completionDate").value,
        pitchMadeAt: getNow(),
        priority: priority,
        pitchStage: stage,
        reviewStatus: status,
        additionalFiles: null
    };

    console.log(data);

    let response = await fetch(url, {
        method: 'POST',
        body: JSON.stringify(data)
    });

    console.log(response);
    if (response.status === 200) {
        alert("Successfully submited the pitch! Heading back to your portal...");
        window.location.replace(baseUrl);
    } else {
        alert("There was an error. Try again.");
    }
}

async function insertGenres() {
    let label = document.createElement("label");
    label.setAttribute("for", "genre");
    let text = document.createTextNode("Genre:");
    label.appendChild(text);
    document.getElementById("form").appendChild(label);
    let selection = document.createElement("select");
    selection.id = "genre";
    selection.name = "genre";
    document.getElementById("form").appendChild(selection);
    let genres = await getGenres();
    for (let genre of genres) {
        let option = document.createElement("option");
        option.value = genre.name;
        option.text = genre.name;
        selection.appendChild(option);
    }
    return genres;
}

async function insertStoryTypes() {
    let label = document.createElement("label");
    label.setAttribute("for", "storyType");
    let text = document.createTextNode("Story Type:");
    label.appendChild(text);
    document.getElementById("form").appendChild(label);
    let selection = document.createElement("select");
    selection.id = "storyType";
    selection.name = "storyType";
    document.getElementById("form").appendChild(selection);
    let types = await getStoryTypes();
    for (let type of types) {
        let option = document.createElement("option");
        option.value = type.name;
        option.text = type.name;
        selection.appendChild(option);
    }
    return types;
}

function insertSubmitButton() {
    let button = document.createElement("button");
    button.type="button";
    button.id = "submitPitch";
    button.className = "submitBtn";
    let text = document.createTextNode("submit");
    button.appendChild(text);
    button.onclick = submitPitch;
    document.getElementById("form").appendChild(button);
}

function getToday() {
    return getNow().split("T")[0];
}

function getNow() {
    let now = new Date();
    return now.toISOString().slice(0,-1);
}

async function getGenres() {
    let url = baseUrl + "/pitch/genre";
    let response = await fetch(url);
    if (response.status === 200) {
        return await response.json();
    }
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