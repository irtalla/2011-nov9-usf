checkLogin().then(setup);
let events;
let statuses;
let activity;
let globalreq;
let statusNext = {};
let optionStatus1 = {};
let optionStatus2 = {};
let optionStatus3 = {};


function setup() {
    getRequest().then(() => {
        checkLogin().then(() => {
            if (loggedUser.id === globalreq.requestor.id) {
                requestorOptions();
            } else if (loggedUser.id === globalreq.manager) {
                managerOptions();
            } else if (loggedUser.id === globalreq.dHead) {
                dHeadOptions();
            }else if (loggedUser.department.name === 'HR') {
                benCoOptions();
            }
        });
    });
}

async function getRequest() {
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    let id = urlParams.get('id');
    let url = baseUrl + '/request/' + id;

    // console.log(queryString);
    let response = await fetch(url, {method:'GET'});
    if (response.status === 200) {
        let request = await response.json();
        // console.log(request);
        globalreq = request;
        populateRequest(request);
    }
}
    async function populateRequest(request) {
        let requestSection = document.getElementById('Request');
        requestSection.innerHTML = '';
        let testDate = request.testDate.replace("T", " ");
        testDate = testDate.replace("-" , "/");
        testDate = testDate.replace("-" , "/");
        request.testDate = testDate;
        if (!request.due) {
            request.due = "";
        } else {
            let due = request.due;
            let minute = due.minute;
            let hour = due.hour;
            let month = due.monthValue;
            let day = due.dayOfMonth;
            let year = due.year;
            request.due = `${year}/${month}/${day} ${hour}:${minute}`
        }
        if (!request.closed) {
            request.closed = "";
        } else {
            let closed = request.closed;
            let minute = closed.minute;
            let hour = closed.hour;
            let month = closed.monthValue;
            let day = closed.dayOfMonth;
            let year = closed.year;
            request.closed = `${year}/${month}/${day} ${hour}:${minute}`
        }
        let table = document.createElement('table');
        table.id = 'requestTable'

        table.innerHTML = `
        <tr>
            <th></th>
            <th></th>

        </tr>
        <tr>
        <td>Request ID</td>
        <td>${request.id}</td>
        </tr>
        <tr>
        <td>Requestor</td>
        <td>${request.requestor.first} ${request.requestor.last}</td>
        </tr>
        <tr>
        <td>Request Type</td>
        <td>${request.requestType.name}</td>
        </tr>
        <tr>
        <td>Description</td>
        <td>${request.description}</td>
        </tr>
        <tr>
        <td>Location</td>
        <td>${request.location}</td>
        </tr>
        <tr>
        <td>Test/Completion Date</td>
        <td>${request.testDate}</td>
        </tr>
        <tr>
        <td>Work Hours Missed</td>
        <td>${request.hoursMissed}</td>
        </tr>
        <tr>
        <td>Cost</td>
        <td>$${Math.round(request.cost * 100) / 100}</td>
        </tr>
        <tr>
        <td>Projected Reimbursement Amount</td>
        <td>$${Math.round(request.projectedAmount * 100) / 100}</td>
        </tr>
        <tr>
        <td>Final Amount</td>
        <td>$${Math.round(request.amount * 100) / 100}</td>
        </tr>
        <tr>
        <td>Next Due Date</td>
        <td>${request.due}</td>
        </tr>
        <tr>
        <td>Date Closed</td>
        <td>${request.closed}</td>
        </tr>
        <tr>
        <td>Status</td>
        <td>${request.status.name}</td>
        </tr>
        
        `;

        requestSection.appendChild(table);



        let attachment = document.createElement('input');
        attachment.id = 'myfile';
        attachment.name = "filename";
        attachment.action = addAttachment;
        attachment.type = 'file';
        // attachment.setAttribute('multiple');
        let attBtn = document.createElement('button');
        attBtn.type = 'button';
        attBtn.textContent = 'Upload Attachment';
        attBtn.addEventListener('click', addAttachment);
        // attachment.addEventListener("change", addAttachment, false);
        requestSection.appendChild(attachment);
        requestSection.appendChild(attBtn);

        let activityArr = request.activity


        let activityTable = document.createElement('table');
        activityTable.id = 'activityTable';

        activityTable.innerHTML = `
            <th></th>
            <th></th>
            <th></th>`;

        for (let a of activityArr) {
            let tr = document.createElement('tr');
            // let ts = a.timestamp;
            let minute = a.timestamp.minute;
            if (minute < 10) minute = `0${minute}`;
            let hour  = a.timestamp.hour;
            if (hour < 10) hour = `0${hour}`;

            
            if (a.event.id === 3) {

                tr.innerHTML = `
                <td>${a.timestamp.monthValue}/${a.timestamp.dayOfMonth}/${a.timestamp.year} ${hour}:${minute}</td>
                <td>${a.name}</td>
                <td>
                <a href="${a.filepath}" download><strong>${a.filepath.replace("upload/", "")}</strong></a>
                </td>`
            } else {
                tr.innerHTML = `
                <td>${a.timestamp.monthValue}/${a.timestamp.dayOfMonth}/${a.timestamp.year} ${hour}:${minute}</td>
                <td>${a.name}</td>
                <td>${a.comment}</td>`
            }

            activityTable.appendChild(tr);

        }

        let commentLabel = document.createElement('label');
        commentLabel.setAttribute('for', 'commentInput' );

        let commentInput = document.createElement('input');
        commentInput.type = 'text';
        commentInput.id = 'commentInput';
        // commentTd.appendChild(commentInput);


        
        let commentBtn = document.createElement('button');
        commentBtn.type = 'button';
        commentBtn.id = 'commentBtn';
        commentBtn.textContent = 'Add Comment';

        commentBtn.addEventListener('click', addComment);



        // activityTable.appendChild(commentTr);
        requestSection.appendChild(activityTable);
        requestSection.appendChild(commentInput);
        requestSection.appendChild(commentLabel);
        requestSection.appendChild(commentBtn);
    }
async function addComment() {
    var newComment = document.getElementById('commentInput');

    
    let newActivity = {};
    newActivity.creator = loggedUser;
    newActivity.comment = newComment.value;
    newActivity.requestId = globalreq.id;
    let activityTable = document.getElementById('activityTable');
    
    let url = baseUrl + "/activity";
    let response = await fetch(url, {method:'POST', body:JSON.stringify(newActivity)});
    if (response.status === 200) {
        let a = await response.json();
        let minute = a.timestamp.minute;
        if (minute < 10) minute = `0${minute}`;
        let hour  = a.timestamp.hour;
        if (hour < 10) hour = `0${hour}`;

        let tr = document.createElement('tr');
        tr.innerHTML = `
        <td>${a.timestamp.monthValue}/${a.timestamp.dayOfMonth}/${a.timestamp.year} ${hour}:${minute}</td>
        <td>${a.name}</td>
        <td>${a.comment}</td>`

        activityTable.appendChild(tr);
    } else {
        alert('Something went wrong');
    }
}
async function addAttachment() {
    let attachment = document.getElementById('myfile').files[0];
    let url1 = baseUrl + "/upload";
    let url2 = baseUrl + "/activity";
    let formData1 = new FormData();
    // console.log("attachment.name " +attachment.name);
    // console.log("attachment.filename " +attachment.filename);
    const timeElapsed = Date.now();
    let today = new Date(timeElapsed);
    today = today.toISOString().replace(":", ".");
    
    // attachment.name = "user-" + loggedUser.id + "-" + today + "-" + attachment.name;
    formData1.append('files', attachment);
    let newActivity = {};
    newActivity.creator = loggedUser;
    // newActivity.comment = newComment.value;
    newActivity.requestId = globalreq.id;
    newActivity.filepath = "upload/" + attachment.name;
    let activityTable = document.getElementById('activityTable');
    
    await fetch(url1, {method: "POST", body: formData1});
    let response = await fetch(url2, {method: "PUT", body:JSON.stringify(newActivity)})

    if (response.status === 200) {
        let a = await response.json();
        let minute = a.timestamp.minute;
        if (minute < 10) minute = `0${minute}`;
        let hour  = a.timestamp.hour;
        if (hour < 10) hour = `0${hour}`;

        let tr = document.createElement('tr');
        tr.innerHTML = `
        <td>${a.timestamp.monthValue}/${a.timestamp.dayOfMonth}/${a.timestamp.year} ${hour}:${minute}</td>
        <td>${a.name}</td>
        
        <td>
        <a href="${a.filepath}" download><strong>${a.filepath.replace("upload/", "")}</strong></a>
        </td>`

        activityTable.appendChild(tr);

    } else {
        alert('Something went wrong');
    }
   



}


function requestorOptions(){
    let requestSection = document.getElementById('Request');
    let denyBtn = document.createElement('button');
    denyBtn.id = "denyBtn";
    denyBtn.addEventListener('click', cancelRequest);
    denyBtn.type = 'button';
    denyBtn.textContent = "Cancel request";

    let optionForm = document.createElement('form');
    optionForm.id = "optionForm";
    let nextBtn = document.createElement('button');

    requestSection.appendChild(denyBtn);
    switch(globalreq.status.id) {
        case 4: 
            //back to 1
            statusNext.id = 1;
            statusNext.name = "Created, awaiting manager";
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Submit to Manager";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);
            break;
        case 6:
            statusNext.id = 5;
            statusNext.name = "Manager approved, awaiting dhead";
            // let nextBtn = document.createElement('button');
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Submit to Department Head";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);
            break;
        case 9:
            statusNext.id = 8;
            statusNext.name = "Dhead approval, awaiting bencor";
            // let nextBtn = document.createElement('button');
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Submit to Benco";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);
            break;
        case 12:
            //push to 14 or 15 
            if (globalreq.requestType.id === 1 || globalreq.requestType.id === 4) {
                statusNext.id = 14;
                statusNext.name = "Approved, pending grade";
            } else {
                statusNext.id = 15;
                statusNext.name = "Approved, pending presentation";
            }
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Approve amount under projected";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);
            break;
        case 14:
            //push to 18
            statusNext.id = 18;
            statusNext.name = "Pending Benco review";
            // let nextBtn = document.createElement('button');
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Submit to Benco";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);
            break;
        case 16:
            //push to 19
            statusNext.id = 19;
            statusNext.name = "Pending Benco review, over standard amount";
            // let nextBtn = document.createElement('button');
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Submit to Benco";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);
            break;
    }
}
function managerOptions(){
    let requestSection = document.getElementById('Request');
    let denyBtn = document.createElement('button');
    denyBtn.id = "denyBtn";
    denyBtn.addEventListener('click', denyRequest);
    denyBtn.type = 'button';
    denyBtn.textContent = "Deny request";

    let optionForm = document.createElement('form');
    optionForm.id = "optionForm";

    requestSection.appendChild(denyBtn);
    let nextBtn = document.createElement('button');
    
    let optionBtn1 = document.createElement('button');
    switch(globalreq.status.id) {
            case 1: 
            //forward to 5 or back to 4
                statusNext.id = 5;
                statusNext.name = "Manager approved, awaiting dhead";
                nextBtn.id = "nextBtn";
                nextBtn.type = 'button';
                nextBtn.textContent = "Submit to Department Head";
                nextBtn.addEventListener('click', nextApproval);
                requestSection.appendChild(nextBtn);
                

                
                optionStatus1.id = 4;
                optionStatus1.name = "Manager needs info from requestor"
                optionBtn1.id = "optionBtn1";
                optionBtn1.type = 'button';
                optionBtn1.textContent = "Ask requestor for more info";
                optionBtn1.addEventListener('click', option1);

                requestSection.appendChild(optionBtn1);

                break;
            case 7:
                //add push to 5
                statusNext.id = 5;
                statusNext.name = "Manager approved, awaiting dhead";
                // let nextBtn = document.createElement('button');
                nextBtn.id = "nextBtn";
                nextBtn.type = 'button';
                nextBtn.textContent = "Submit to Department Head";
                nextBtn.addEventListener('click', nextApproval);
                requestSection.appendChild(nextBtn);
                break;
            case 10:
                //add push back to 8
                statusNext.id = 8;
                statusNext.name = "Dhead approval, awaiting benco";
                // let nextBtn = document.createElement('button');
                nextBtn.id = "nextBtn";
                nextBtn.type = 'button';
                nextBtn.textContent = "Submit to Benco";
                nextBtn.addEventListener('click', nextApproval);
                requestSection.appendChild(nextBtn);
                break;
            case 15:
                //push to 20
                statusNext.id = 20;
                statusNext.name = "Completed";
                // let nextBtn = document.createElement('button');
                nextBtn.id = "nextBtn";
                nextBtn.type = 'button';
                nextBtn.textContent = "Approve request";
                nextBtn.addEventListener('click', nextApproval);
                requestSection.appendChild(nextBtn);
                break;
            case 17:
                statusNext.id = 21;
                statusNext.name = "Completed over standard amount";
                // let nextBtn = document.createElement('button');
                nextBtn.id = "nextBtn";
                nextBtn.type = 'button';
                nextBtn.textContent = "Approve request";
                nextBtn.addEventListener('click', nextApproval);
                requestSection.appendChild(nextBtn);
                break;
        }

}
function dHeadOptions(){
    let requestSection = document.getElementById('Request');
    let denyBtn = document.createElement('button');
    denyBtn.id = "denyBtn";
    denyBtn.addEventListener('click', denyRequest);
    denyBtn.type = 'button';
    denyBtn.textContent = "Deny request";

    let optionForm = document.createElement('form');
    optionForm.id = "optionForm";

    requestSection.appendChild(denyBtn);
    let nextBtn = document.createElement('button');
    let optionBtn1 = document.createElement('button');
    let optionBtn2 = document.createElement('button');
    switch(globalreq.status.id) {
        case 5: 
            //push to 8, fall back to 6,7
            statusNext.id = 8;
            statusNext.name = "Dhead approval, awaiting benco";
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Submit to Benco";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);

            optionStatus1.id = 6;
            optionStatus1.name = "Dhead needs info from requestor"
            optionBtn1.id = "optionBtn1";
            optionBtn1.type = 'button';
            optionBtn1.textContent = "Ask requestor for more info";
            optionBtn1.addEventListener('click', option1);

            requestSection.appendChild(optionBtn1);

            optionStatus2.id = 7;
            optionStatus2.name = "Dhead needs info from supervisor"
            optionBtn2.id = "optionBtn2";
            optionBtn2.type = 'button';
            optionBtn2.textContent = "Ask manager for more info";
            optionBtn2.addEventListener('click', option2);

            requestSection.appendChild(optionBtn2);

            break;
        case 11:
            //push to 8
            statusNext.id = 8;
            statusNext.name = "Dhead approval, awaiting benco";
            // let nextBtn = document.createElement('button');
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Submit to Benco";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);
            break;
    }
}
function benCoOptions(){
    let requestSection = document.getElementById('Request');
    let denyBtn = document.createElement('button');
    denyBtn.id = "denyBtn";
    denyBtn.addEventListener('click', denyRequest);
    denyBtn.type = 'button';
    denyBtn.textContent = "Deny request";

    let optionForm = document.createElement('form');
    optionForm.id = "optionForm";

    requestSection.appendChild(denyBtn);
    let nextBtn = document.createElement('button');
    let optionBtn1 = document.createElement('button');
    let optionBtn2 = document.createElement('button');
    let optionBtn3 = document.createElement('button');
    switch(globalreq.status.id) {
        case 8: 
            //back to 9, 10, 11, push to 12, 14, 15, 16, 17,
            if (globalreq.requestType.id === 1 || globalreq.requestType.id === 4) {
                statusNext.id = 14;
                statusNext.name = "Approved, pending grade";
            } else {
                statusNext.id = 15;
                statusNext.name = "Approved, pending presentation";
            }
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Approve request";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);

            optionStatus1.id = 9;
            optionStatus1.name = "Benco needs info from requestor"
            
            optionBtn1.id = "optionBtn1";
            optionBtn1.type = 'button';
            optionBtn1.textContent = "Ask requestor for more info";
            optionBtn1.addEventListener('click', option1);

            requestSection.appendChild(optionBtn1);

            optionStatus2.id = 10;
            optionStatus2.name = "Benco needs info from supervisor"
            
            optionBtn2.id = "optionBtn2";
            optionBtn2.type = 'button';
            optionBtn2.textContent = "Ask manager for more info";
            optionBtn2.addEventListener('click', option2);

            requestSection.appendChild(optionBtn2);

            optionStatus3.id = 11;
            optionStatus3.name = "Benco needs info from dhead"
            
            optionBtn3.id = "optionBtn3";
            optionBtn3.type = 'button';
            optionBtn3.textContent = "Ask Department Head for more info";
            optionBtn3.addEventListener('click', option3);

            requestSection.appendChild(optionBtn3);

            
            let amountLabel = document.createElement('label');
            amountLabel.setAttribute('for', 'amountInput' );
            amountLabel.innerHTML = "Want to alter the amount awarded? Enter it here."
    
            let amountInput = document.createElement('input');
            amountInput.type = 'text';
            amountInput.id = 'amountInput';
            requestSection.appendChild(amountLabel);
            requestSection.appendChild(amountInput);


            break;
        case 18:
            //push to 20
            statusNext.id = 20;
            statusNext.name = "Completed";
            // let nextBtn = document.createElement('button');
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Approve request";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);
            break;
        case 19:
            //push to 21
            statusNext.id = 21;
            statusNext.name = "Completed over standard amount";
            // let nextBtn = document.createElement('button');
            nextBtn.id = "nextBtn";
            nextBtn.type = 'button';
            nextBtn.textContent = "Approve request";
            nextBtn.addEventListener('click', nextApproval);
            requestSection.appendChild(nextBtn);
            break;
    }

}



async function nextApproval() {
    let amountInput = document.getElementById('amountInput');
    let url = baseUrl + "/request";
    let newActivity = {};
    let updateReq = {}
    if (loggedUser.department.id == 1 && statusNext.id == 8) {

        if (amountInput.value > 0) {
            updateReq.amount = amountInput.value;
            if (amountInput.value < globalreq.projectedAmount) {
                statusNext.id = 12;
                statusNext.name = "Partial approval, awaiting requestor acceptance";
            } else if (amountInput.value > globalreq.projectedAmount) {
                if (globalreq.requestType.id === 1 || globalreq.requestType.id === 4) {
                    statusNext.id = 16;
                    statusNext.name = "Approved over standard amount, pending grade";
                } else {
                    statusNext.id = 17;
                    statusNext.name = "Approved over standard amount, pending presentation";
                    
                }
            }
        }
    }
        
    newActivity.creator = loggedUser;
    newActivity.requestId = globalreq.id;
    updateReq.status = {};
    updateReq.activity = [];
    updateReq.status = statusNext;
    updateReq.activity[0] = newActivity;




    let response = await fetch(url, {method: "POST", body: JSON.stringify(updateReq)});
    if (response.status === 200) {
        let a = await response.json();
        let minute = a.timestamp.minute;
        if (minute < 10) minute = `0${minute}`;
        let hour  = a.timestamp.hour;
        if (hour < 10) hour = `0${hour}`;

        let tr = document.createElement('tr');
        tr.innerHTML = `
        <td>${a.timestamp.monthValue}/${a.timestamp.dayOfMonth}/${a.timestamp.year} ${hour}:${minute}</td>
        <td>${a.name}</td>
        <td>${a.comment}</td>`

        activityTable.appendChild(tr);
    } else {
        alert('Something went wrong');
    }

}
async function option1() {
    let url = baseUrl + "/request";
    let newActivity = {};
    newActivity.creator = loggedUser;
    newActivity.requestId = globalreq.id;
    let updateReq = {}
    updateReq.status = {};
    updateReq.activity = [];
    updateReq.status = optionStatus1;
    updateReq.activity[0] = newActivity;
    let response = await fetch(url, {method: "POST", body: JSON.stringify(updateReq)});
    if (response.status === 200) {
        let a = await response.json();
        let minute = a.timestamp.minute;
        if (minute < 10) minute = `0${minute}`;
        let hour  = a.timestamp.hour;
        if (hour < 10) hour = `0${hour}`;

        let tr = document.createElement('tr');
        tr.innerHTML = `
        <td>${a.timestamp.monthValue}/${a.timestamp.dayOfMonth}/${a.timestamp.year} ${hour}:${minute}</td>
        <td>${a.name}</td>
        <td>${a.comment}</td>`

        activityTable.appendChild(tr);
    } else {
        alert('Something went wrong');
    }
}
async function option2() {
    let url = baseUrl + "/request";
    let newActivity = {};
    newActivity.creator = loggedUser;
    newActivity.requestId = globalreq.id;
    let updateReq = {}
    updateReq.status = {};
    updateReq.activity = [];
    updateReq.status = optionStatus2;
    updateReq.activity[0] = newActivity;
    let response = await fetch(url, {method: "POST", body: JSON.stringify(updateReq)});
    if (response.status === 200) {
        let a = await response.json();
        let minute = a.timestamp.minute;
        if (minute < 10) minute = `0${minute}`;
        let hour  = a.timestamp.hour;
        if (hour < 10) hour = `0${hour}`;

        let tr = document.createElement('tr');
        tr.innerHTML = `
        <td>${a.timestamp.monthValue}/${a.timestamp.dayOfMonth}/${a.timestamp.year} ${hour}:${minute}</td>
        <td>${a.name}</td>
        <td>${a.comment}</td>`

        activityTable.appendChild(tr);
    } else {
        alert('Something went wrong');
    }
}

async function option3() {
    let url = baseUrl + "/request";
    let newActivity = {};
    newActivity.creator = loggedUser;
    newActivity.requestId = globalreq.id;
    let updateReq = {}
    updateReq.status = {};
    updateReq.activity = [];
    updateReq.status = optionStatus3;
    updateReq.activity[0] = newActivity;
    let response = await fetch(url, {method: "POST", body: JSON.stringify(updateReq)});
    if (response.status === 200) {
        let a = await response.json();
        let minute = a.timestamp.minute;
        if (minute < 10) minute = `0${minute}`;
        let hour  = a.timestamp.hour;
        if (hour < 10) hour = `0${hour}`;

        let tr = document.createElement('tr');
        tr.innerHTML = `
        <td>${a.timestamp.monthValue}/${a.timestamp.dayOfMonth}/${a.timestamp.year} ${hour}:${minute}</td>
        <td>${a.name}</td>
        <td>${a.comment}</td>`

        activityTable.appendChild(tr);
    } else {
        alert('Something went wrong');
    }
}

async function denyRequest() {
    let denyStatus = {};
    denyStatus.id = 2;
    denyStatus.name = "Denied";
    let url = baseUrl + "/request";
    let newActivity = {};
    newActivity.creator = loggedUser;
    newActivity.requestId = globalreq.id;
    let updateReq = {}
    updateReq.status = {};
    updateReq.activity = [];
    updateReq.status = denyStatus;
    updateReq.activity[0] = newActivity;

    let response = await fetch(url, {method: "POST", body: JSON.stringify(updateReq)});
    if (response.status === 200) {
        let a = await response.json();
        let minute = a.timestamp.minute;
        if (minute < 10) minute = `0${minute}`;
        let hour  = a.timestamp.hour;
        if (hour < 10) hour = `0${hour}`;

        let tr = document.createElement('tr');
        tr.innerHTML = `
        <td>${a.timestamp.monthValue}/${a.timestamp.dayOfMonth}/${a.timestamp.year} ${hour}:${minute}</td>
        <td>${a.name}</td>
        <td>${a.comment}</td>`

        activityTable.appendChild(tr);
    } else {
        alert('Something went wrong');
    }

}

async function cancelRequest() {
    let denyStatus = {};
    denyStatus.id = 3;
    denyStatus.name = "Requestor cancelled";
    let url = baseUrl + "/request";
    let newActivity = {};
    newActivity.creator = loggedUser;
    newActivity.requestId = globalreq.id;
    let updateReq = {}
    updateReq.status = {};
    updateReq.activity = [];
    updateReq.status = denyStatus;
    updateReq.activity[0] = newActivity;

    let response = await fetch(url, {method: "POST", body: JSON.stringify(updateReq)});
    if (response.status === 200) {
        let a = await response.json();
        let minute = a.timestamp.minute;
        if (minute < 10) minute = `0${minute}`;
        let hour  = a.timestamp.hour;
        if (hour < 10) hour = `0${hour}`;

        let tr = document.createElement('tr');
        tr.innerHTML = `
        <td>${a.timestamp.monthValue}/${a.timestamp.dayOfMonth}/${a.timestamp.year} ${hour}:${minute}</td>
        <td>${a.name}</td>
        <td>${a.comment}</td>`

        activityTable.appendChild(tr);
    } else {
        alert('Something went wrong');
    }

}