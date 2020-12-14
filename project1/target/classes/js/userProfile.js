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
        <table id="rTable" style="width:100%">
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
                    <th>ID</th>
                    <th>Status</th>
                    <th>Employee</th>
                    <th>Amount</th>
                </tr>
            `;

                for (let i = 0; i < reimbursements.length; i++) {
                    let tr = document.createElement('tr');
                    tr.innerHTML = `
                    <td>${reimbursements[i].id}</td>
                    <td>${reimbursements[i].status.name}</td>
                    <td>${reimbursements[i].employee.name}</td>
                    <td>${reimbursements[i].amount}</td>
                `;
                    table.appendChild(tr);
                }
            });
            break;

        case "supervisor":


            getReimbursements((rs) => {

                let table = document.getElementById("rTable");

                table.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Employee</th>
                    <th>Amount</th>
                    <th>Attachments<th>
                </tr>
            `;
                for (let i = 0; i < reimbursements.length; i++) {
                    let tr = document.createElement('tr');
                    tr.innerHTML = `
                    <td>${reimbursements[i].id}</td>
                    <td>${reimbursements[i].status.name}</td>
                    <td>${reimbursements[i].employee.name}</td>
                    <td>${reimbursements[i].amount}</td>
                    <td><ul id = "aUl${i}"></ul></td>
                    `;
                    table.appendChild(tr);
                }
                for (let i = 0; i < reimbursements.length; i++) {
                    getAttachmentsById(reimbursements[i].id, () => {
                        atts = document.getElementById(`aUl${i}`);
                        for (let j = 0; j < attachments.length; j++) {
                            let aLi = document.createElement('li');
                            aLi.innerHTML = `${attachments[j]}`
                            atts.appendChild(aLi);
                    }});
                }
            });

            break;
        case "benco":

            getReimbursements((rs) => {

                let table = document.getElementById("rTable");

                table.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Employee</th>
                    <th>Amount</th>
                    <th>Attachments<th>
                </tr>
            `;
                for (let i = 0; i < reimbursements.length; i++) {
                    let tr = document.createElement('tr');
                    tr.innerHTML = `
                    <td>${reimbursements[i].id}</td>
                    <td>${reimbursements[i].status.name}</td>
                    <td>${reimbursements[i].employee.name}</td>
                    <td>${reimbursements[i].amount}</td>
                    <td><ul id = "aUl${i}"></ul></td>
                    `;
                    table.appendChild(tr);
                }
                for (let i = 0; i < reimbursements.length; i++) {
                    getAttachmentsById(reimbursements[i].id, () => {
                        atts = document.getElementById(`aUl${i}`);
                        for (let j = 0; j < attachments.length; j++) {
                            let aLi = document.createElement('li');
                            aLi.innerHTML = `<a href="${baseurl}/attachments/${reimbursements[i].id}/${attachments[j]}">${attachments[j]}</a>`
                            atts.appendChild(aLi);
                    }});
                }
            });
            break;
    }
}

