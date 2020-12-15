checkLogin().then(employeeSetup);
let updateRequestMenuOpen = false;


function employeeSetup() {
	getRequests();
    let employeeSpan = document.getElementById('emp');
    
    // update request
    if (!(document.getElementById('updateRequestBtn'))) {
        let updateRequestBtn = document.createElement('button');
        updateRequestBtn.type = 'button';
        updateRequestBtn.textContent = 'Update Request';
        updateRequestBtn.id = 'updateRequestBtn';
        updateRequestBtn.onclick = updateRequestMenu;
        employeeSpan.appendChild(updateRequestBtn);
    }
}

async function getRequests() {
    let url = baseUrl + '/requests';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) {
		alert('Added request successfully.');
        let requests = await response.json();
        populateRequests(requests);
    }
}

function populateRequests(requests) {
    
    let requestSection = document.getElementById('requestSection');

    if (requests.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>User Name</th>
                <th>Event Type</th>
                <th>Event Description</th>
                <th>Amount</th>
				<th>Grade Format</th>
				<th>Grade/Presentation</th>
				<th>Request Status<th>
				<th>Reason</th>
				<th>Additional Information</th>
            </tr>
        `;

        for (let request of requests) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${request.id}</td>
                <td>${request.username}</td>
                <td>${request.eventType}</td>
               	<td>${request.eventDescription}</td>
				<td>${request.amount}</td>
				<td>${request.gradeFormat}</td>
				<td>${request.gradePres}</td>
				<td>${request.status.name}</td>
				<td>${request.reason}</td>
				<td>${request.addIfo}</td>
            `;
            let td = document.createElement('td');            
            tr.appendChild(td);
            table.appendChild(tr);			
        }

        requestSection.appendChild(table);
		
    } else {
        requestSection.innerHTML = 'You don\'t have any requests. :(';
    }
	
}

function updateRequestMenu() {
	let employeeSpan = document.getElementById('emp');
	//employeeSpan.removeAttribute('hidden');
	updateRequestMenuOpen = !updateRequestMenuOpen;   
    console.log('update request menu open? ' + updateRequestMenuOpen);

    if (updateRequestMenuOpen) {
        employeeSpan.innerHTML += `<form id="update-request-form">
		
        <br>
		<lablel>				TRMS    FORM			</label><br>
                
        
        <br style="line-height:14;">      
		<label for="myId">Id # : <label>
		<input type="number" id="myId" placeholder="10" required />
		
		<label for="accepted" >accepted </label>
		<input type="radio" id="accepted" /><br>
		
		<label for="rejected" >rejected</label>
		<input type="radio" id="rejected" /><br>
		
		<label for="reason" >reason</label>
		<input type="text" id="reason" /><br>
		

        <button type="button" onclick="updateRequest()" id="submit-update-request-form" >Submit</button>
        </form>
        `;
        
        let submitUpdateBtn = document.getElementById('submit-update-request-form');
        submitUpdateBtn.onclick = updateRequest;
    } else {
        employeeSpan.removeChild(document.getElementById('update-request-form'));
    }

    let updateRequestBtn = document.getElementById('updateRequestBtn');
    updateRequestBtn.onclick = updateRequestMenu;	
}



async function updateRequest() {
    let request = {};
	request.status = {};
	let acceptBntChecked = document.getElementById('accepted').checked;
	let rejectBntCheked = document.getElementById('rejected').checked;
	let myInput = document.getElementById('reason');
	let myId = document.getElementById('myId');	
	
	if (myId == '') {
		alert('You must enter a valid ID.');
		updateRequestMenu();   
	} else if (acceptBntChecked) {
		request.status.id = 2;		 
		window.location.replace("requestReview.html");
	} 	
	
	if (rejectBntCheked && myInput.value == '') {
		alert('You must giva a reason for a rejection');
		updateRequestMenu();    	
	} else {
		request.status.id = 3;
		request.reason = document.getElementById('reason').value; 
	}
	
	request.id = document.getElementById('myId').value;  	
	
	
	request.status.name = '';
	//	To be added latter if required by the supervisor
   // request.reason = 'NA';
	// request.addInfo = 'NA'; 

    let url = baseUrl + '/requests/' + request.id;
    let response = await fetch(url, {method:'POST', body:JSON.stringify(request)});
    if (response.status === 200) {
        alert('Update request successfully.');
    } else {
        alert('Something went wrong.');
    }
	window.location.replace("requestReview.html");
    //	updateRequestMenu(); 
   //	 employeeSetup();
}