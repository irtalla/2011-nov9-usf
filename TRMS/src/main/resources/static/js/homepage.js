"use strict";

checkLogin(populateData);
async function populateData()
{
    console.log("populating Data");
    document.getElementById("welcome").innerHTML = "Welcome " + user.name + "!"

    let forms =  await getReimbursementForms();
    let dataDiv = document.getElementById("data");
    for(let f of forms)
    {
        let permissionlevel = await canViewReimbursment(f);
        if (permissionlevel > 0)
        {
            displayFormToData(dataDiv, f, permissionlevel);
        }
    }

    let infoRequestsRecipient = await getInfoRequestByTargetId(user.id);

    let infoRequestSender = await getInfoRequestByRequestorId(user.id);

    let sidebarDiv = document.getElementById("rightSideBar");

    for(let i of infoRequestsRecipient)
    {
        if (i.status.id == 1)
        {
            displayNotification(sidebarDiv, i, true);
        }
    }

    for (let i of infoRequestSender)
    {
        if (i.status.id == 3)
        {
            displayNotification(sidebarDiv, i, false);
        }
    }


}

async function checkUrgency(event)
{
    let now = new Date();


    let eventDate = new Date(event.date);

    if((eventDate - now) < (86400000*14))
    {
        return true;
    }

    return false;
}

async function displayNotification(sidebarDiv, request, isRecipient)
{
    let notification = document.createElement("p");

    notification.innerHTML = "Message: " + request.message;
    notification.className = "info" + request.id;
    sidebarDiv.appendChild(notification);
    if(!isRecipient)
    {
        let resp = document.createElement("p");
        resp.innerHTML = "Response: " + request.response;
        resp.className = "info" + request.id;

        let closeBtn = document.createElement("button");
        closeBtn.innerHTML = "Close";
        closeBtn.className = "info" + request.id;
        closeBtn.addEventListener("click", function() {closeInfoRequest(sidebarDiv, request);});

        sidebarDiv.appendChild(resp);
        sidebarDiv.appendChild(closeBtn);
    }
    else
    {

        let fillInfoRequestBtn = document.createElement("button");
        fillInfoRequestBtn.innerHTML = "Reply";
        fillInfoRequestBtn.className = "info" + request.id;
        fillInfoRequestBtn.addEventListener("click", function() {respondToInfoRequest(sidebarDiv, request);});
        
        sidebarDiv.appendChild(fillInfoRequestBtn);
    }
}

async function closeInfoRequest(sidebarDiv, request)
{
    let children = sidebarDiv.childNodes;

    let toRemove =[];

    for(let c of children)
    {
        toRemove.push(c)
    }

    for(let c of toRemove)
    {
        //console.log(c);
        if (c.className == ("info" + request.id))
        {
            sidebarDiv.removeChild(c);
        }
    }
    request.status = await getStatusById(5);

    updateInfoRequest(request);
}

async function respondToInfoRequest(sidebarDiv, request)
{
    let prompt = document.createElement("label");
    prompt.innerHTML = "Enter a Response: ";
    prompt.className = "info" + request.id;

    let infoResponse = document.createElement("input");
    infoResponse.setAttribute("type", "text");
    infoResponse.className = "info" + request.id;

    let submitResponseBtn = document.createElement("button");
    submitResponseBtn.innerHTML = "Submit";
    submitResponseBtn.className = "info" + request.id;
    submitResponseBtn.addEventListener("click", function() {sumbitInfoRequestResponse(sidebarDiv, request, infoResponse.value);});

    sidebarDiv.appendChild(prompt);
    sidebarDiv.appendChild(infoResponse);
    sidebarDiv.appendChild(submitResponseBtn);

}

async function sumbitInfoRequestResponse(sidebarDiv, request, message)
{
    let children = sidebarDiv.childNodes;

    let toRemove =[];

    for(let c of children)
    {
        toRemove.push(c)
    }

    for(let c of toRemove)
    {
        //console.log(c);
        if (c.className == ("info" + request.id))
        {
            sidebarDiv.removeChild(c);
        }
    }

    request.response = message;
    request.status = await getStatusById(3);

    updateInfoRequest(request);

    alert("Response Sent!");
}

async function displayFormToData(dataDiv, form, permissionlevel)
{
    let formCollapsed = document.createElement("p");
    formCollapsed.className = "form";

    let expandButton = document.createElement("button");

    expandButton.addEventListener("click", function() {expandForm(formCollapsed, form, permissionlevel);});
    expandButton.innerHTML = "Expand";
    let event = await getEventById(form.eventId);

    let owner = await getUserById(form.employeeId);
    
    formCollapsed.innerHTML = " Owner: " + owner.name + " Event Name: " + event.name +  " Type: " + event.type.name + " File Date: " + millisToStringDate(form.fileDate) + " Current Stage: " + form.stage.name + " Status: " + form.status.name + " Expected Reimbursement: " + form.reimbursementAmount;
    if (permissionlevel > 1 && checkUrgency(event))
    {
        let urgent = document.createElement("p");
        urgent.className = "urgent";
        urgent.innerHTML = "Urgent!"

        formCollapsed.appendChild(urgent);
    }
    formCollapsed.appendChild(expandButton);

    dataDiv.appendChild(formCollapsed);

}

async function expandForm(parent, form, permissionlevel)
{

    while(parent.firstChild)
    {
        parent.removeChild(parent.firstChild);
    }

    let event = await getEventById(form.eventId);
    let owner = await getUserById(form.employeeId);
    parent.innerHTML = "";

    if (permissionlevel > 1 && checkUrgency(event))
    {
        let urgent = document.createElement("p");
        urgent.className = "urgent";
        urgent.innerHTML = "Urgent!"

        parent.appendChild(urgent);
    }

    addDataPiece(parent, owner.name, "Owner Name: ")
    addDataPiece(parent, event.name, "Event Name: ");
    addDataPiece(parent, event.type.name, "Event Type:");
    addDataPiece(parent, millisToStringDate(event.date), "Event Date: ");
    addDataPiece(parent, event.time, "Event Time: ");
    addDataPiece(parent, event.location, "Event Location: ");
    addDataPiece(parent, event.description, "Event: Description");
    addDataPiece(parent, event.cost, "Event Cost");
    addDataPiece(parent, millisToStringDate(form.fileDate), "File Date: ");
    addDataPiece(parent, form.gradingFormat.name, "Grading Format: ");
    addDataPiece(parent, form.passingGrade, "Passing Grade: ");
    addDataPiece(parent, form.justification, "Justification");
    addDataPiece(parent, form.reimbursementAmount, "Expected Reimbursement");
    addDataPiece(parent, form.hoursMissed, "Hours Missed:");
    addDataPiece(parent, form.stage.name, "Current Stage: ");
    addDataPiece(parent, millisToStringDate(form.stageEntryDate), "Date Current Stage Entered: ");
    addDataPiece(parent, form.status.name, "Current Status: ");

    let collapseButton = document.createElement("button");
    collapseButton.addEventListener("click", function() {collapseForm(parent, form, permissionlevel);});
    collapseButton.innerHTML = "Collapse";
    parent.appendChild(collapseButton);

    if (permissionlevel > 1)
    {
        let acceptButton = document.createElement("button");
        let rejectButton = document.createElement("button");
        let requestEmployeeInfoButton = document.createElement("button");

        acceptButton.addEventListener("click", function() {acceptForm(parent, form);});
        rejectButton.addEventListener("click", function() {rejectForm(parent, form);});

        requestEmployeeInfoButton.addEventListener("click", function() {requestInfo(parent, form, form.employeeId, permissionlevel)});

        acceptButton.innerHTML = "Accept Request";
        rejectButton.innerHTML = "Reject Request";
        requestEmployeeInfoButton.innerHTML = "Request Additional Employee Info";

        parent.appendChild(acceptButton);
        parent.appendChild(rejectButton);
        parent.appendChild(requestEmployeeInfoButton);
    }
    if (permissionlevel > 2)
    {
        let requestSupervisorInfoButton = document.createElement("button");
        let supervisor = await getSupervisorByReimbursementFormId(form.id);
        requestSupervisorInfoButton.addEventListener("click", function() {requestInfo(parent, form, supervisor.id, permissionlevel)});

        requestSupervisorInfoButton.innerHTML = "Request Additional Supervisor Info";
        parent.appendChild(requestSupervisorInfoButton);
    }
    if (permissionlevel > 3)
    {
        let requestDepartmentHeadInfoButton = document.createElement("button");
        let DepartmentHead = await getDepartmentHeadByReimbursementFormId(form.id);
        requestDepartmentHeadInfoButton.addEventListener("click", function() {requestInfo(parent, form, DepartmentHead.id, permissionlevel)});

        requestDepartmentHeadInfoButton.innerHTML = "Request Additional Department Head Info";
        parent.appendChild(requestDepartmentHeadInfoButton);
    }
    if (permissionlevel == 1 && form.stage.id == 4)
    {
        let uploadPresentationButton = document.createElement("input");
        uploadPresentationButton.setAttribute("type", "file");
        uploadPresentationButton.addEventListener("change", function() {uploadPresentationFile(parent, form, uploadPresentationButton.files)});
        uploadPresentationButton.innerHTML = "Upload Grade/Presentation";
        parent.appendChild(uploadPresentationButton);
    }

}

async function uploadPresentationFile(parent, form, files)
{
    if(!files)
    {
        alert("Nothing Uploaded!");
        return;
    }
    console.log(files);
    formData = new FormData();
    formData.append(filses[0].name,files[0]);

}

async function addDataPiece(parent, data, message)
{
    let text = document.createElement("label");
    text.innerHTML = message;
    let dataEl = document.createElement("p");
    dataEl.innerHTML = data;

    parent.appendChild(text);
    parent.appendChild(dataEl);
    parent.appendChild(document.createElement("br"));
    parent.appendChild(document.createElement("br"));
}

async function collapseForm(parent, form, permissionlevel)
{
    while(parent.firstChild)
    {
        parent.removeChild(parent.firstChild);
    }
    let event = await getEventById(form.eventId);
    let owner = await getUserById(form.employeeId);
    parent.innerHTML = "Owner Name: " + owner.name + " Event Name: " + event.name +  " Type: " + event.type.name + " File Date: " + millisToStringDate(form.fileDate) + " Current Stage: " + form.stage.name + " Status: " + form.status.name + " Expected Reimbursement: " + form.reimbursementAmount;

    if (permissionlevel > 1 && checkUrgency(event))
    {
        let urgent = document.createElement("p");
        urgent.className = "urgent";
        urgent.innerHTML = "Urgent!"

        parent.appendChild(urgent);
    }


    let expandButton = document.createElement("button");

    expandButton.addEventListener("click", function() {expandForm(parent, form, permissionlevel);});
    expandButton.innerHTML = "Expand";
    parent.appendChild(expandButton);
}

async function acceptForm(parent, form)
{
    form.stage.id += 1;

    form.stage = await getStageById(form.stage.id);

    if(form.stage.id == 4)
    {
        form.status.id = 3;
        form.status = await getStatusById(form.status.id);
    }
    else
    {
        form.status.id = 1;
        form.status = await getStatusById(form.status.id);
    }
    updateReimbursementForm(form);

    while(parent.firstChild)
    {
        parent.removeChild(parent.firstChild);
    }

    let accepted = document.createElement("p");
    accepted.className = "accepted";
    accepted.innerHTML = "Request Accepted!";

    let acceptApproval = {id: -1, formId: form.id, approverId: user.id, statusId: 3, message: ""};

    acceptApproval.id = await addApproval(acceptApproval);

    parent.appendChild(accepted);

}

async function rejectForm(parent, form)
{
    form.status.id = 2;
    form.status = await getStatusById(form.status.id);

    

    while(parent.firstChild)
    {
        parent.removeChild(parent.firstChild);
    }

    let text = document.createElement("label");
    text.innerHTML = "Please Enter The Reason For Rejection: ";

    parent.appendChild(text);

    let reason = document.createElement("input");
    reason.setAttribute("type", "text");

    parent.appendChild(reason);

    let submitButn = document.createElement("button");
    submitButn.innerHTML = "Submit";
    submitButn.addEventListener('click', function() {setRejectView(parent, form, reason.value);});
    parent.appendChild(submitButn);
}

 async function setRejectView(parent, form, message)
{
    while(parent.firstChild)
    {
        parent.removeChild(parent.firstChild);
    }
    let accepted = document.createElement("p");
    accepted.className = "rejected";
    accepted.innerHTML = "Request Rejected!";
    parent.appendChild(accepted);

    let rejectApproval = {id: -1, formId: form.id, approverId: user.id, statusId: 2, message: message};

    rejectApproval.id = await addApproval(rejectApproval);

    updateReimbursementForm(form);
}

async function requestInfo(parent, form, target, permissionlevel)
{
    while(parent.firstChild)
    {
        parent.removeChild(parent.firstChild);
    }

    let text = document.createElement("label");
    text.innerHTML = "Please enter a message: ";
    parent.appendChild(text);

    let message = document.createElement("input");
    message.setAttribute("type", "text");
    parent.appendChild(message);

    let submitButn = document.createElement("button");
    submitButn.innerHTML = "Submit";

    submitButn.addEventListener("click", function() {submitInfoRequest(parent, form, permissionlevel, target, message.value)});
    parent.appendChild(submitButn);

    
}

async function submitInfoRequest(parent, form, permissionlevel, target, message)
{
    let status = await getStatusById(1);

    let infoRequestForm = {id: -1, formId: form.id, requestorId: user.id, targetId: target, message: message, response: "", status: status};

    if (addInfoRequest(infoRequestForm))
    {
        alert("Request submitted successfully!");
    }

    collapseForm(parent, form, permissionlevel);
}