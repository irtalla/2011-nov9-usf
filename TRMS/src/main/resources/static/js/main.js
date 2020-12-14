"use strict";

var baseUrl = 'http://localhost:8080';
var user = null;
//checkLogin();

async function checkLogin(callback = null)
{
    console.log("Checking Login");
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200)
    {
        user = await response.json();
        console.log("logged in as " + user.name);
    } 
    else
        console.log("Not Logged In");
    
    if (callback) callback();
}

async function login()
{
    console.log("login attempt");
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    console.log('password is' + password);
    let url = baseUrl + '/users?';
    url += 'user=' + username + '&';
    url += 'pass=' + password;
    let response = await fetch(url, {method: 'PUT'});
    switch (response.status) {
        case 200: // successful
            await updateFormTimes();
            user = await response.json();
            window.location.replace('homepage.html');
            break;
        case 400: // incorrect login
            alert('Invalid username or password, please try again.');
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function addApprovalFile(formData, formId, name)
{
    let presentationFileResponse = await fetch(baseUrl + '/uploads/approvalfiles?formid=' + formId + '&name=' + name,{method: 'POST', body: formData});

    if (presentationFileResponse.status === 200)
    {
        return true;
    }
    return false;
}

async function addEventAttatchmentFile(formData, eventId, name)
{
    let presentationFileResponse = await fetch(baseUrl + '/uploads/eventattatchments?eventid=' + eventId + '&name=' + name,{method: 'POST', body: formData});

    if (presentationFileResponse.status === 200)
    {
        return true;
    }
    return false;
}

async function getApprovalFile(formId)
{
    let presentationFileResponse = await fetch(baseUrl + '/downloads/approvalfiles?formid=' + formId);

    if (presentationFileResponse.status === 200)
    {
        return await presentationFileResponse.json();
    }
    return null;
}

async function getEventAttatchment(eventId)
{
    let presentationFileResponse = await fetch(baseUrl + '/downloads/eventattatchments?eventid=' + eventId);

    if (presentationFileResponse.status === 200)
    {
        return await presentationFileResponse.json();
    }
    return null;
}

async function addReimbursementChangeNotification(notif)
{
    let ReimbursementChangeNotificationResponse = await fetch(baseUrl + '/notifications', {method: 'POST', body: JSON.stringify(notif)});

    if (ReimbursementChangeNotificationResponse.status === 200)
    {
        return await ReimbursementChangeNotificationResponse.json();
    }
    return null;
}

async function updateReimbursementChangeNotification(notif)
{
    let ReimbursementChangeNotificationResponse = await fetch(baseUrl + '/notifications', {method: 'PUT', body: JSON.stringify(notif)});

    if (ReimbursementChangeNotificationResponse.status === 200)
    {
        return true;
    }
    return false;
}

async function getReimbursementChangeNotificationByFormId(id)
{
    let ReimbursementChangeNotificationResponse = await fetch(baseUrl + '/notifications/' + id);

    let notifs = null;
    if (ReimbursementChangeNotificationResponse.status === 200)
    {
        notifs = await ReimbursementChangeNotificationResponse.json();
    }
    return notifs;
}

async function addPresentationFile(formData, formId, name)
{

    let presentationFileResponse = await fetch(baseUrl + '/uploads/presentations?formid=' + formId + '&name=' + name,{method: 'POST', body: formData});

    if (presentationFileResponse.status === 200)
    {
        return true;
    }
    return false;
}

async function getPresentationFile(formId)
{
    
    let presentationFileResponse = await fetch(baseUrl + '/downloads/presentations?formid=' + formId);

    if (presentationFileResponse.status === 200)
    {
        return await presentationFileResponse.json();
    }
    return null;
}

//check if someone has not responded in a "timely manner" and progress stages accordingly
async function updateFormTimes()
{
    let forms = await getReimbursementForms();
    let now = new Date();
    for (let f of forms)
    {
        let stageDay = new Date(f.stageEntryDate);
        if ((now - stageDay) > (86400000*14))
        {
            if (f.stage.id < 3)
            {
                f.stage = await getStageById(f.stage.id + 1);
                f.stageEntryDate = now.getFullYear() + "-" + (now.getMonth()+1) + "-" + (now.getDate()+1);

                updateReimbursementForm(f);
            }
        }

    }
}

async function getReimbursementForms()
{
    let ReimbursementResponse = await fetch(baseUrl + "/forms/reimbursement");
    let allReimbursement = null;
    if(ReimbursementResponse.status === 200)
    {
        allReimbursement = await ReimbursementResponse.json();
    }


    return allReimbursement;
}

async function getReimbursementFormById(id)
{
    let ReimbursementResponse = await fetch(baseUrl + "/forms/reimbursement/" + id);
    let reimbursement = null;
    if (ReimbursementResponse.status === 200)
    {
        reimbursement = await ReimbursementResponse.json();
    }
    return reimbursement;
}

async function getEventById(id)
{
    let eventByIdResponse = await fetch(baseUrl + "/events/" + id);

    let eventById = null;
    if (eventByIdResponse.status === 200)
    {
        eventById = await eventByIdResponse.json();
    }

    return eventById;
}

function millisToStringDate(date)
{
    let realDate = new Date(parseInt(date));

    return realDate.getFullYear() + "-" + (realDate.getMonth()+1) + "-" + realDate.getDate();
}

async function getUserById(id)
{
    let userResponse = await fetch(baseUrl + "/users/" + id);

    let foundUser = null;
    if(userResponse.status === 200)
    {
        foundUser = await userResponse.json();
    }
    return foundUser;
}

async function getDepartmentById(id)
{
    let departmentResponse = await fetch(baseUrl + "/departments/" + id);
    
    let department = null;
    if (departmentResponse.status === 200)
    {
        department = await departmentResponse.json();
    }
    return department;
}

async function getStageById(id)
{
    let stageResponse = await fetch(baseUrl + "/forms/stages/" + id);

    let stage = null;
    if (stageResponse.status === 200)
    {
        stage = await stageResponse.json();
    }
    return stage;
}

async function getStatusById(id)
{
    let statusResponse = await fetch(baseUrl + "/forms/statuses/" + id);

    let status = null;
    if (statusResponse.status === 200)
    {
        status = await statusResponse.json();
    }
    return status;
}

async function updateReimbursementForm(reimbursementForm)
{
    let ReimbursementResponse = await fetch(baseUrl + "/forms/reimbursement", {method: "PUT", body: JSON.stringify(reimbursementForm)});

    if(ReimbursementResponse.status === 200)
    {
        return true;
    }
    return false;
}

async function deleteReimbursementForm(reimbursementForm)
{
    let ReimbursementResponse = await fetch(baseUrl + "/forms/reimbursement", {method: "DELETE", body: JSON.stringify(reimbursementForm)});

    if(ReimbursementResponse.status === 200)
    {
        return true;
    }
    return false;
}

async function addApproval(approval)
{
    let approvalResponse = await fetch(baseUrl + "/forms/approval", {method: "POST", body: JSON.stringify(approval)});
    let approvalId = null;
    if (approvalResponse.status === 200)
    {
        approvalId = await approvalResponse.json();
    }
    return approvalId;
}

async function getAllApprovals()
{
    let approvalResponse = await fetch(baseUrl + "/forms/approval");
    let approvals = null;
    if (approvalResponse.status === 200)
    {
        approvals = await approvalResponse.json();
    }
    return approvals;
}

async function addInfoRequest(infoRequest)
{
    let infoRequestResponse = await fetch(baseUrl + "/forms/inforequest", {method: "POST", body: JSON.stringify(infoRequest)});

    let requestId = null;
    if (infoRequestResponse.status === 200)
    {
        requestId = await infoRequestResponse.json();
    }
    return requestId;

}

async function updateInfoRequest(infoRequest)
{
    let infoRequestResponse = await fetch(baseUrl + "/forms/inforequest", {method: "PUT", body: JSON.stringify(infoRequest)});

    
    if (infoRequestResponse.status === 200)
    {
        return true;
    }
    return false;
}

async function getSupervisorByReimbursementFormId(id)
{
    let form = await getReimbursementFormById(id);

    let owner = await getUserById(form.employeeId);
    let supervisor = await getUserById(owner.supervisorId);
    return supervisor;
}

async function getDepartmentHeadByReimbursementFormId(id)
{
    let form = await getReimbursementFormById(id);

    let owner = await getUserById(form.employeeId);
    let department = await getDepartmentById(owner.department.id);
    let departmentHead = await getUserById(department.departmentHeadId);
    return departmentHead;
}

async function getBenCoByReimbursementFormId(id)
{
    let form = await getReimbursementFormById(id);

    let owner = await getUserById(form.employeeId);
    let department = await getDepartmentById(owner.department.id);
    let BenCo = await getUserById(department.bencoId);
    return BenCo;
}

async function getInfoRequestByTargetId(id)
{
    let formsResponse = await fetch(baseUrl + "/forms/inforequest");
    let forms = null;
    if (formsResponse.status === 200)
    {
        forms = await formsResponse.json();
    }
    let targets = [];    
    for (let f of forms)
    {
        if(f.targetId == id)
        {
            targets.push(f);
        }
    }

    return targets;

}

async function getInfoRequestByRequestorId(id)
{
    let formsResponse = await fetch(baseUrl + "/forms/inforequest");
    let forms = null;
    if (formsResponse.status === 200)
    {
        forms = await formsResponse.json();
    }
    let targets = [];    
    for (let f of forms)
    {
        if(f.requestorId == id)
        {
            targets.push(f);
        }
    }

    return targets;
}

//returns 0 if current user does not have permission to view form, 1 if they are the owner, 2 if they are a supervisor, 3 if they are the department head, 4 if they are the benco
//only owner and appropriate stage manager can view a form
async function canViewReimbursment(reimbursementForm)
{
    if(user.id == reimbursementForm.employeeId)
    {
        return 1;
    }

    //only owner can see accepted/rejected forms
    if(reimbursementForm.status.id == 2 || reimbursementForm.status.id == 3)
    {
        return 0;
    }


    

    let owner = await getUserById(reimbursementForm.employeeId);
    let supervisor = await getUserById(owner.supervisorId);
    let department = await getDepartmentById(owner.department.id);
    let departmentHead = await getUserById(department.departmentHeadId);
    let BenCo = await getUserById(department.bencoId);

    //console.log(user.id + "vs" + supervisor.id);
    //console.log(reimbursementForm.stage.id);

    if(reimbursementForm.stage.id == 1)
    {
        //supervisor view
        if (user.id == supervisor.id)
        {
            return 2;
        }
    }
    else if (reimbursementForm.stage.id == 2)
    {
        //department head view
        if (user.id == departmentHead.id)
        {
            return 3;
        }
    }
    else if (reimbursementForm.stage.id == 3 || reimbursementForm.stage.id == 4)
    {
        //benco view
        if (user.id == BenCo.id)
        {
            return 4;
        }
    }
    else
    {
        return 0;
    }
}

