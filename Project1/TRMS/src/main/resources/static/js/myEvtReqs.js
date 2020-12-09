checkLogin().then(populateEvtReqs);


function populateEvtReqs() {
	
	// alert(loggedUser.evtReqs);
	
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

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Name</th>
				<th>Posting Date</th>
                <th>DS Approval Status</th>
                <th>DH Approval Status</th>
                <th>BC Approval Status</th>
                <th>Person ID</th>
                <th>Request Type ID</th>
                <th>Request for Comment ID</th>
                <th>Priority ID</th>
                <th>Start Date</th>
   				<th>Amount</th>
            </tr>
        `;

        for (let evtReq of evtReqs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${evtReq.id}</td>
                <td>${evtReq.name}</td>
 				<td>${evtReq.posting_date}</td>
                <td>${evtReq.direct_supervisor_approval_status_id}</td>
                <td>${evtReq.benefits_coordinator_approval_status_id}</td>
                <td>${evtReq.person_id}</td>
                <td>${evtReq.type_id}</td>
                <td>${evtReq.req_fr_cmnt_id}</td>
                <td>${evtReq.priority_id}</td>
			    <td>${evtReq.start_date}</td>  
				<td>${evtReq.amount}</td>     
            `;
            
            table.appendChild(tr);
        }

        evtReqSection.appendChild(table);

    } else {
        evtReqSection.innerHTML = 'You don\'t have any evtReqs. :(';
    }
}