checkLogin();

getEvtReqs();

async function getEvtReqs() {
	// let baseUrl = 'http://localhost:8080';
    let url = baseUrl + '/events';
    let response = await fetch(url);
    if (response.status === 200) {
        let evtReqs = await response.json();
        //alert(evtReqs);
        populateEvtReqs(evtReqs);
    }
}


function populateEvtReqs(evtReqs) {
    let evtReqSection = document.getElementById('evtReqSection'); //evtReqSection

    if (evtReqs.length > 0) {
        let table = document.createElement('table');
        table.classList.add("table");
        table.classList.add("table-bordered");
        table.classList.add("border-primary");
        table.classList.add("table-hover");

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
		
        // alert(evtReqs.length);
        
        let tbody = document.createElement('tbody');
		
        for (let evtReq of evtReqs) {
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
            `;
            
             tbody.appendChild(tr);                    
        }
             table.appendChild(tbody);
             evtReqSection.appendChild(table);
    } else {
        evtReqSection.innerHTML = 'No events are available.';
    }
}


