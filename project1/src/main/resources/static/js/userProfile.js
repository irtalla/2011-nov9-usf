function showProfile(parentDivId) {
    const formDiv = document.getElementById(parentDivId);
    formDiv.innerHTML = `
    <div class="container-fluid">
        <table>
            <tr>
                <td>Username: </td><td>${this.loggedUser.name}</td><td></td>
            </tr>

            <tr>
                <td>UserId: </td><td>${this.loggedUser.id}</td><td></td>
            </tr>

            <tr>            
                <td>Name:  </td><td>${this.loggedUser.fname} ${this.loggedUser.lname}</td><td></td>
            </tr>

            <tr>            
                <td>Role:  </td><td>${this.loggedUser.role.name}</td><td></td>
            </tr>

            <tr>            
                <td>Department:  </td><td>${this.loggedUser.department.name}</td><td></td>
            </tr>

            <tr>            
                <td>Reimbursement requests:  </td>
                <td></td>
                <td></td>
            </tr>
        <table id="rTable" style="width:80%">
        </table>
        </table>
    </div>
`;
    switch (loggedUser.role.name) {
        case "employee":

            getReimbursementsById(this.loggedUser.id, (rs) => {

                let table = document.getElementById("rTable");

                table.innerHTML = `
                <tr>
                    <th> </th>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Employee</th>
                    <th>Amount</th>
                    <th> </th>
                </tr>
            `;

                for (let i = 0; i < reimbursements.length; i++) {
                    let tr = document.createElement('tr');
                    tr.innerHTML = `
                    <td align="right"><i class="fa fa-eye fa-2x" aria-hidden="true" onclick="openReimbursement(${reimbursements[i].id})"></i></td>
                    <td>${reimbursements[i].id}</td>
                    <td>${reimbursements[i].status.name}</td>
                    <td>${reimbursements[i].employee.name}</td>
                    <td>${reimbursements[i].amount}</td>
                    <td align="left"><div id = "alert${i}"></div> </td>
                `;

                    table.appendChild(tr);
                    if (reimbursements[i].message) {
                        let elementsId = "alert" + reimbursements[i].id;
                        document.getElementById(`alert${i}`).innerHTML = `<i class="fa fa-exclamation" aria-hidden="true"></i>`;
                    }
                }
            });

            break;

        case "supervisor":


            getReimbursements((rs) => {

                let table = document.getElementById("rTable");

                table.innerHTML = `
                <tr>
                    <th> </th>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Employee</th>
                    <th>Amount</th>
                    <th>Attachments<th>
                    <th> </th>
                </tr>
            `;
                for (let i = 0; i < reimbursements.length; i++) {
                    if (!reimbursements[i].supervisor_approved)//if not supervisorapproved
                    {
                        let tr = document.createElement('tr');
                        tr.innerHTML = `
                        <td align="right"><i class="fa fa-eye fa-2x" aria-hidden="true" onclick="openReimbursementSup(${reimbursements[i].id})"></i></td>
                        <td>${reimbursements[i].id}</td>
                        <td>${reimbursements[i].status.name}</td>
                        <td>${reimbursements[i].employee.name}</td>
                        <td>${reimbursements[i].amount}</td>
                        <td><ul id = "aUl${i}"></ul></td>
                        <td align="left"><div id = "alert${i}"></div> </td>
                        `;

                        table.appendChild(tr);

                        if (reimbursements[i].message) {
                            let elementsId = "alert" + reimbursements[i].id;
                            document.getElementById(`alert${i}`).innerHTML = `<i class="fa fa-exclamation" aria-hidden="true"></i>`;
                        }
                    }
                }

                for (let i = 0; i < reimbursements.length; i++) {
                    if (!reimbursements[i].supervisor_approved)//if not supervisorapproved
                    {
                    getAttachmentsById(reimbursements[i].id, () => {
                        atts = document.getElementById(`aUl${i}`);
                        for (let j = 0; j < attachments.length; j++) {
                            let aLi = document.createElement('li');
                            aLi.innerHTML = `<a href="${baseUrl}/attachments/${reimbursements[i].id}/${attachments[j]}" target=”_blank”>${attachments[j]}</a>`
                            atts.appendChild(aLi);
                        }
                    });
                }
                }

            });
            break;
            
        case "benco":

            getReimbursements((rs) => {

                let table = document.getElementById("rTable");

                table.innerHTML = `
                <tr>
                    <th> </th>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Employee</th>
                    <th>Amount</th>
                    <th>Attachments<th>
                    <th> </th>
                </tr>
            `;

                for (let i = 0; i < reimbursements.length; i++) {
                    if (!reimbursements[i].benco_approved)//if not supervisorapproved
                    {
                        let tr = document.createElement('tr');
                        tr.innerHTML = `
                <td align="right"><i class="fa fa-eye fa-2x" aria-hidden="true" onclick="openReimbursementSup(${reimbursements[i].id})"></i></td>
                <td>${reimbursements[i].id}</td>
                <td>${reimbursements[i].status.name}</td>
                <td>${reimbursements[i].employee.name}</td>
                <td>${reimbursements[i].amount}</td>
                <td><ul id = "aUl${i}"></ul></td>
                <td align="left"><div id = "alert${i}"></div> </td>
                `;

                        table.appendChild(tr);

                        if (reimbursements[i].message) {
                            let elementsId = "alert" + reimbursements[i].id;
                            document.getElementById(`alert${i}`).innerHTML = `<i class="fa fa-exclamation" aria-hidden="true"></i>`;
                        }

                    }
                }
                for (let i = 0; i < reimbursements.length; i++) {
                    getAttachmentsById(reimbursements[i].id, () => {
                        atts = document.getElementById(`aUl${i}`);
                        for (let j = 0; j < attachments.length; j++) {
                            let aLi = document.createElement('li');
                            aLi.innerHTML = `<a href="${baseUrl}/attachments/${reimbursements[i].id}/${attachments[j]}">${attachments[j]}</a>`
                            atts.appendChild(aLi);
                        }
                    });
                }
            });
            break;
            case "head":


            getReimbursements((rs) => {

                let table = document.getElementById("rTable");

                table.innerHTML = `
                <tr>
                    <th> </th>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Employee</th>
                    <th>Amount</th>
                    <th>Attachments<th>
                    <th> </th>
                </tr>
            `;
                for (let i = 0; i < reimbursements.length; i++) {
                    if (!reimbursements[i].department_head_approved)//if not supervisorapproved
                    {
                        let tr = document.createElement('tr');
                        tr.innerHTML = `
                        <td align="right"><i class="fa fa-eye fa-2x" aria-hidden="true" onclick="openReimbursementSup(${reimbursements[i].id})"></i></td>
                        <td>${reimbursements[i].id}</td>
                        <td>${reimbursements[i].status.name}</td>
                        <td>${reimbursements[i].employee.name}</td>
                        <td>${reimbursements[i].amount}</td>
                        <td><ul id = "aUl${i}"></ul></td>
                        <td align="left"><div id = "alert${i}"></div> </td>
                        `;

                        table.appendChild(tr);

                        if (reimbursements[i].message) {
                            let elementsId = "alert" + reimbursements[i].id;
                            document.getElementById(`alert${i}`).innerHTML = `<i class="fa fa-exclamation" aria-hidden="true"></i>`;
                        }
                    }
                }

                for (let i = 0; i < reimbursements.length; i++) {
                    getAttachmentsById(reimbursements[i].id, () => {
                        atts = document.getElementById(`aUl${i}`);
                        for (let j = 0; j < attachments.length; j++) {
                            let aLi = document.createElement('li');
                            aLi.innerHTML = `<a href="${baseUrl}/attachments/${reimbursements[i].id}/${attachments[j]}" target=”_blank”>${attachments[j]}</a>`
                            atts.appendChild(aLi);
                        }
                    });
                }

            });
            break;
    }
}

async function getReimbursement(id) {

    let url = baseUrl + '/reimbursements/' + id;
    let response = await fetch(url, { method: 'PUT' });

    switch (response.status) {
        case 200: // successful
            reimbursement = await response.json();
            return reimbursement;
            break;
        default: // other error
            alert('Something went wrong retrieving reimbursement.');
            break;
    }
}
function openReimbursementSup(id) {
    if (loggedUser.isdepartmenthead) {
        loggedUser.role.id = 2;
    }
    getReimbursement(id)
        .then(
            res => {
                $('#modalbod').html(`
                Reimbursement ${id}:<br>
                <br>

                <br>
                <div id="message"></div><br>
                <form>
                <input type="text" id="msg"><i class="fa fa-plus-square-o" aria-hidden="true" onClick="addMessage(${id})">Add a note to request.</i></input>
                </form>
                <br>
                <div id="approve"><i class="fa fa-check" aria-hidden="true" onClick="approve(${id}, ${loggedUser.role.id})">Approve Request</i></div>
                `);
                $("#myModal").modal('show');

                if (res.message) {
                    document.getElementById("message").innerHTML = `<i class="fa fa-exclamation" aria-hidden="true">   ${res.message}</i>`
                }
            }).catch(
                err => console.log(err)
            );
}

function openReimbursement(id) {
    getReimbursement(id)
        .then(
            res => {
                console.log(res)

                $('#modalbod').html(`
        Reimbursement ${id}:<br>
        <br>

        <div id="checkboxSup"></div><br>
        <div id="checkboxBenco"></div><br>
        <div id="checkboxHead"></div><br>
        <br>
        <div id="message"></div><br>
        <div id="addAttach" onclick = "addMoreAttachments(${id})"><i class="fa fa-plus-square-o" aria-hidden="true">Add Attachments</i> </div>
        <br>
        `);
                $("#myModal").modal('show');
                if (res.supervisor_approved) {
                    document.getElementById("checkboxSup").innerHTML = `<i class="fa fa-check-circle" aria-hidden="true">   Supervisor Approved</i>`
                }
                else {
                    document.getElementById("checkboxSup").innerHTML = `<i class="fa fa-times-circle" aria-hidden="true">   Supervisor Approved</i>`
                }
                if (res.benco_approved) {
                    document.getElementById("checkboxBenco").innerHTML = `<i class="fa fa-check-circle" aria-hidden="true">   Benefits Coordinator Approved</i>`
                } else {
                    document.getElementById("checkboxBenco").innerHTML = `<i class="fa fa-times-circle" aria-hidden="true">   Benefits Coordinator Approved</i>`;
                }
                if (res.department_head_approved) {
                    document.getElementById("checkboxHead").innerHTML = `<i class="fa fa-check-circle" aria-hidden="true">   Department Head Approved</i>`
                }
                else {
                    document.getElementById("checkboxHead").innerHTML = `<i class="fa fa-times-circle" aria-hidden="true">   Department Head Approved</i>`;
                }
                if (res.message) {
                    document.getElementById("message").innerHTML = `<i class="fa fa-exclamation" aria-hidden="true">   ${res.message}</i>`
                }
            }).catch(
                err => console.log(err)
            );
}
async function addMessage(id) {
    alert("add message");

    let url = baseUrl + '/reimbursements/addmessage/' + id + '?message=' + document.getElementById("msg").value;
    let response = await fetch(url, { method: 'PUT' });
    document.getElementById("msg").value = ``;
    switch (response.status) {
        case 200: // successful
            reimbursement = await response.json();
            return reimbursement;
            break;
        default: // other error
            alert('Something went wrong retrieving reimbursement.');
            break;
    }
}
function addMoreAttachments(id) {
    let formUrl = baseUrl + '/upload';
    $('#modalbod').html(`
    Reimbursement request added.<br>
    <br>
    Please upload any event related files below.
    <br><br>
    <h1>Upload</h1><br><br>
    
    <form target="_blank" method="post" action=${formUrl}?rid=${id} enctype="multipart/form-data">
    <input type="file" name="files" multiple>
    <button>Submit</button>
    </form>
    
    `);
    $("#myModal").modal('show');
}

async function approve(id, approver) {
    document.getElementById("approve").innerHTML = "Approved";
    switch (approver) {
        case 1://supervisor
            url = baseUrl + '/reimbursements/supapprove/' + id;
            break;

        case 2://department head
            url = baseUrl + '/reimbursements/headapprove/' + id;
            let response = await fetch(url, { method: 'PUT' });

            switch (response.status) {
                case 200: // successful
                    await response.json();

                    break;
                default: // other error
                    alert('Something went wrong approving.');
                    break;
            }
            url = baseUrl + '/reimbursements/supapprove/' + id;
            break;

        case 3://benco
            url = baseUrl + '/reimbursements/benapprove/' + id;
            break;
    }
    let response = await fetch(url, { method: 'PUT' });

    switch (response.status) {
        case 200: // successful
            await response.json();

            break;
        default: // other error
            alert('Something went wrong approving.');
            break;
    }
    //checkIfAllApprovalsAreDoneNow(id);
}
// async function checkIfAllApprovalsAreDoneNow(id){
//     url = baseUrl + '/reimbursements/' + id;
//     let response = await fetch(url, { method: 'PUT' });

//     switch (response.status) {
//         case 200: // successful
//             let reimburs = await response.json();

//             break;
//         default: // other error
//             alert('Something went wrong approving.');
//             break;
//     }
//     if((reimburs.supervisor_approved)&&(reimburs.benco_approved)&&(reimburs.department_head_approved)){
//         alert("all three approvals done");

//     }
// }