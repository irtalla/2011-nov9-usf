checkLogin();
// alert(loggedUser);


let evtReqSection = document.getElementById('evtReqSection');
let form = document.createElement('form'); 

form.innerHTML = `
	<label>Name</label>
	<input> 
	<label type='date'>Posting Date</label>
	<input> 
	<label>Event Type ID</label>
	<input> 
	<label for="types">Event Type (Reimbursement Rate)</label>
		<select name="types" id="types">
		 	 <option value="1. University Courses (80%)">1. University Courses (80%)</option>
		  	 <option value="2. Seminars (60%)">2. Seminars (60%)</option>
		  	 <option value="3. Certification Preparation Class (75%)">3. Certification Preparation Class (75%)</option>
		     <option value="4. Certification (100%)">4. Certification (100%)</option>
		     <option value="5. Technical Training (90%)">5. Technical Training (90%)</option>
		     <option value="6. Other (30%)">6. Other (30%)</option>
		</select>
	<label type='date'>Start Date</label>
	<input> 
	<label>Amount</label>
	<input> 
	<label>Submit</label>
	<input type='submit' onClick="addEvtReq()" > 	
`

evtReqSection.appendChild(form); 

async function addEvtReq() {
	let evtReq = {};
	evtReq.id = 0;

	let url = baseUrl + '/events/' + id;
	let response = await fetch(url, {method:'POST', body:JSON.stringify(evtReq)});
	 if (response.status === 201) {
        alert('Added event successfully.');
    } else {
        alert('Something went wrong.');
    }
	
}