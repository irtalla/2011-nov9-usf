"use strict"

import {baseUrl, loggedUser, setLoggedUser} from "./global.js";
let main = document.getElementById("mainSection");
const currentUser = localStorage.getItem("loggedUser");
if (!currentUser) logout();
setLoggedUser(loggedUser);

setUpMainSection();

function setUpMainSection() {
    let requestsReceived = document.createElement("table");
    requestsReceived.id = "requestsReceived";
    requestsReceived.id = "requestsReceived";
    let rrHeader = document.createElement("thead");
    rrHeader.id = "requestsReceivedHeader";
    rrHeader.className = "requestsHeader";
    rrHeader.innerHTML = `
        <tr>
            <th> sent by
            <th> received at
            <th> their message
            <th> your reply
            <th> status
            <th> 
        </tr>
    `;
    requestsReceived.appendChild(rrHeader);
    main.appendChild(requestsReceived);
    main.appendChild(document.createElement("br"));
    main.appendChild(document.createElement("br"));
    getReceived();

    let requestsSent = document.createElement("table");
    requestsSent.id = "requestsSent";
    requestsSent.className = "requestsSent";
    let rsHeader = document.createElement("thead");
    rsHeader.id = "requestsSentHeader";
    rsHeader.className = "requestsHeader";
    rsHeader.innerHTML = `
        <tr>
            <th> sent to
            <th> sent at
            <th> your message
            <th> their reply
            <th> status
            <th> 
        </tr>
        `;
    requestsSent.appendChild(rsHeader);
    main.appendChild(requestsSent);
    getSent();

}

async function getReceived() {
    let parsedUser = JSON.parse(currentUser);
    let url = baseUrl + "/request/requestee/" + parsedUser.id;
    let response = await fetch(url);
    if (response.status === 200) {
        populateReceived(await response.json());
    }
}

function populateReceived(requests) {
    let table = document.getElementById("requestsReceived");    

    for (let request of requests) {
        let row = document.createElement("tr");
        row.id = `row_${request.id}`;

        let requester = `${request.requester.firstName} ${request.requester.lastName}`;
        let requesterEntry = document.createElement("td");
        requesterEntry.innerHTML = requester;
        row.appendChild(requesterEntry);

        let sentAt = reformatDate(request.requestMadeAt);
        let sentAtEntry = document.createElement("td");
        sentAtEntry.innerHTML = sentAt;
        row.appendChild(sentAtEntry);

        let messageReceived = `${request.question}`;
        let messageReceivedEntry = document.createElement("td");
        messageReceivedEntry.innerHTML = messageReceived;
        row.appendChild(messageReceivedEntry);

        let messageSent = `${request.answer}`;
        console.log(messageSent);
        let messageSentEntry = document.createElement("td");
        messageSentEntry.innerHTML = messageSent;
        row.appendChild(messageSentEntry);

        let status = `${request.requestStatus.name}`;
        let statusEntry = document.createElement("td");
        statusEntry.innerHTML = status;
        row.appendChild(statusEntry);

        let replyBtn = document.createElement("button");
        replyBtn.type = "button";
        replyBtn.id = `reply_${request.id}`;
        replyBtn.className = "submitBtn";
        let replyText = document.createTextNode("reply");
        replyBtn.appendChild(replyText);
        if (messageSent.length > 0 || messageSent) replyBtn.disabled = true;
        replyBtn.onclick = () => { replyToRequest(request) };
        row.appendChild(replyBtn);

        table.appendChild(row);
    }
}

async function getSent() {
    let parsedUser = JSON.parse(currentUser);
    let url = baseUrl + "/request/requester/" + parsedUser.id;
    let response = await fetch(url);
    if (response.status === 200) {
        populateSent(await response.json());
    }
}

function populateSent(requests) {
    let table = document.getElementById("requestsSent");

    for (let request of requests) {
        let row = document.createElement("tr");
        row.id = `row_${request.id}`;
    
        let requestee = `${request.requestee.firstName} ${request.requestee.lastName}`;
        let requesteeEntry = document.createElement("td");
        requesteeEntry.innerHTML = requestee;
        row.appendChild(requesteeEntry);
    
        let sentAt = reformatDate(request.requestMadeAt);
        let sentAtEntry = document.createElement("td");
        sentAtEntry.innerHTML = sentAt;
        row.appendChild(sentAtEntry);
    
        let messageSent = `${request.question}`;
        let messageSentEntry = document.createElement("td");
        messageSentEntry.innerHTML = messageSent;
        row.appendChild(messageSentEntry);
    
        let messageReceived = `${request.answer}`;
        let messageReceivedEntry = document.createElement("td");
        messageReceivedEntry.innerHTML = messageReceived;
        row.appendChild(messageReceivedEntry);
    
        let status = `${request.requestStatus.name}`;
        let statusEntry = document.createElement("td");
        statusEntry.innerHTML = status;
        row.appendChild(statusEntry);

        table.appendChild(row);
    }

}

async function replyToRequest(request) {
    let url = baseUrl + "/request/" + request.id;

    let message = null;
    let input = prompt("enter your reply:");
    if (input == null || input == "") {
        alert("No input given.");
        return;
    } else {
        message = input;
    }

    let response = await fetch(url);
    if (response.status === 200) {
        let retrieved = await response.json();
        console.log(retrieved);

        retrieved.answer = message;
        let reviewStatus = getReviewStatus();
        retrieved.reviewStatus = reviewStatus[3];

        let now = new Date();
        retrieved.requestMadeAt = now.toISOString().slice(0, -1);

        console.log(retrieved.requester);
        console.log(retrieved.requestee);
        let requester = await getUserById(retrieved.requester.id);
        let requestee = await getUserById(retrieved.requestee.id);


        let data = {
            id: retrieved.id,
            question: retrieved.question,
            answer: retrieved.answer,
            requestMadeAt: retrieved.requestMadeAt,
            rquester: requester,
            requestee: requestee,
            requestStatus: retrieved.requestStatus
        };

        response = await fetch(url, {
            method: 'PUT',
            body: JSON.stringify(data)
        });
        if (response.status === 200) {
            alert("Successfully replied!");
        } else {
            alert("There was an error. Try again.");
        }
    }

}

function reformatDate(rawDate) {
    let year = rawDate.year;
    let month = rawDate.month;
    let date = rawDate.dayOfMonth;
    return new Date(`${month} ${date}, ${year}`).toDateString();
}

async function getReviewStatus() {
    let url = baseUrl + "/pitch/review_status";
    let response = await fetch(url);
    if (response.status === 200) {
        return await response.json();
    }
}

async function getUserById(id){
    let url = baseUrl + "/users/" + id;
    let response = await fetch(url);
    if (response.satus === 200) {
        return await response.json();
    }
}