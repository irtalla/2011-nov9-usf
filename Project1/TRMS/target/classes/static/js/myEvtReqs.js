checkLogin().then(populateEvtReqs);


function populateEvtReqs() {
	
	// alert(loggedUser.evtReqs);
	alert(JSON.stringify(loggedUser));
	
    let evtReqs = loggedUser.evtReqs;
	let reimbusementSection = document.getElementById('reimbusementSection');
	let p = document.createElement('p');
	let ura = 1000.00; 
	

	p.innerHTML = `
		<h3 class="reimbursement-header">2020 Unused Reimbursement Amount: </h3><span class="amount">$${ura.toFixed(2)}</span>
	`
	reimbusementSection.appendChild(p);
	
    let evtReqSection = document.getElementById('evtReqSection');

    if (evtReqs.length > 0) {
        let table = document.createElement('table');
        table.classList.add("table");
        table.classList.add("table-bordered");
        table.classList.add("border-primary");
        table.classList.add("table-hover");

        table.innerHTML = `
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Posting Date</th>
                    <th scope="col">DS Approval Status</th>
                    <th scope="col">DH Approval Status</th>
                    <th scope="col">BC Approval Status</th>
                    <th scope="col">Person ID</th>
                    <th scope="col">Request Type ID</th>
                    <th scope="col">Request for Comment ID</th>
                    <th scope="col">Priority ID</th>
                    <th scope="col">Start Date</th>
                    <th scope="col">Amount</th>
					<th scope="col">Status</th>
                </tr>
            </thead>
        `;

        let tbody = document.createElement('tbody');

        for (let evtReq of evtReqs) {
            //alert(evtReq.amount);
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${evtReq.id}</td>
                <td>${evtReq.name}</td>
                <td>${new Date(evtReq.posting_date).toLocaleDateString()}</td>
                <td>${evtReq.direct_supervisor_approval_status_id}</td>
				<td>${evtReq.department_head_approval_status_id}</td>
                <td>${evtReq.benefits_coordinator_approval_status_id}</td>
                <td>${evtReq.person_id}</td>
                <td>${evtReq.type_id}</td>
                <td>${evtReq.req_fr_cmnt_id}</td>
                <td>${evtReq.priority_id}</td>
                <td>${new Date(evtReq.start_date).toLocaleDateString()}</td>  
                <td>${evtReq.amount}</td>
				<td>${evtReq.status == 1 ? "Approved" : (evtReq.status == -1 ? "Rejected" : (evtReq.status == 0 ? "Pending" : "Unknown"))}</td>
            `;
             tbody.appendChild(tr);
        }
       
        table.appendChild(tbody);
        evtReqSection.appendChild(table);

    } else {
        evtReqSection.innerHTML = 'You don\'t have any evtReqs. :(';
    }
}