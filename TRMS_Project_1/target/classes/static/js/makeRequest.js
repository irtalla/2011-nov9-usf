let addRequestMenuOpen = false;

employeeSetup();

function employeeSetup() {
    let employeeSpan = document.getElementById('emp');
    
    // add request
    if (!(document.getElementById('addRequestBtn'))) {
        let addRequestBtn = document.createElement('button');
        addRequestBtn.type = 'button';
        addRequestBtn.textContent = 'Add Request';
        addRequestBtn.id = 'addRequestBtn';
        addRequestBtn.onclick = addRequestMenu;
        employeeSpan.appendChild(addRequestBtn);
    }
}

function addRequestMenu() {
	let employeeSpan = document.getElementById('emp');
	//employeeSpan.removeAttribute('hidden');
	addRequestMenuOpen = !addRequestMenuOpen;   
    console.log('add request menu open? ' + addRequestMenuOpen);

    if (addRequestMenuOpen) {
        employeeSpan.innerHTML += `<form id="add-request-form">
        <br>
		<lablel>				TRMS    FORM			</label><br>
                
        <label for="eventType">Event Type:</label>		
        <input type="text" id="eventType" list="eventChoice" placeholder="Event Type" required />
			<datalist id="eventChoice">
				<option value="University Course">
				<option value="Seminar">
				<option value="Certification Preparation Classes">
				<option value="Certification">
				<option value="Technical Training">
				<option value="Other">
			</datalist> 	
			
        
        <label for="eventDescription">Event Description:</label>
        <input type="text" id="eventDescription" placeholder="Event Description" required /><br><br><br style="line-height:7;">
        
        <label for="amount">Amount:</label>
        <input type="number" id="amount" placeholder="Amount" required />

        
        <label for="gradeFormat">Grade Format:</label>
        <input type="text" id="gradeFormat" list="gradeChoice"  placeholder="gradeFormat" required />
			<datalist id="gradeChoice">
				<option value="A-F">
				<option value="P/F">
				<option value="NA">
				opttion value="Other">
			</datalist>

        <label for="gradePres">Ggrade/Presentation:</label>
        <input type="text" id="gradePres" placeholder="Ggrade/Presentation" required /><br>
        

        <button type="button" onclick="addRequest()" id="submit-add-request-form" >Submit</button>
        </form>
        `;
        
        let submitAddBtn = document.getElementById('submit-add-request-form');
        submitAddBtn.onclick = addRequest;
    } else {
        employeeSpan.removeChild(document.getElementById('add-request-form'));
    }

    let addRequestBtn = document.getElementById('addRequestBtn');
    addRequestBtn.onclick = addRequestMenu;	
}

async function addRequest() {
    let request = {};
	request.id = 0;
    request.username = loggedUser.username;
    request.eventType = document.getElementById('eventType').value;
	request.eventDescription = document.getElementById('eventDescription').value;
	request.amount = document.getElementById('amount').value;
	request.gradeFormat = document.getElementById('gradeFormat').value;    
	request.gradePres = document.getElementById('gradePres').value;
	
	request.status = {};
	request.status.id = 1;
	request.status.name = '';
	//	To be added latter if required by the supervisor
    request.reason = 'NA';
	request.addInfo = 'NA';

    let url = baseUrl + '/requests';
    let response = await fetch(url, {method:'POST', body:JSON.stringify(request)});
    if (response.status === 201) {
        alert('Added request successfully.');
    } else {
        alert('Something went wrong.');
    }
    addRequestMenu();
    employeeSetup();
}