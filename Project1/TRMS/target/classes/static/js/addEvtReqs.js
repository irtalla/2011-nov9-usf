checkLogin();
// alert(loggedUser);


let evtReqSection = document.getElementById('evtReqSection');
let form = document.createElement('form'); 

form.innerHTML = `
	<div> <label>Name</label> 
	<input id="name"> </div>
	<div><label type='date'>Posting Date</label>
	<input type="date" id="posting_date"> </div>
	<div><label for="types">Event Type (Reimbursement Rate)</label>
		<select name="types" id="types">
		 	 <option value="1">1. University Courses (80%)</option>
		  	 <option value="2">2. Seminars (60%)</option>
		  	 <option value="3">3. Certification Preparation Class (75%)</option>
		     <option value="4">4. Certification (100%)</option>
		     <option value="5">5. Technical Training (90%)</option>
		     <option value="6">6. Other (30%)</option>
		</select></div>
	<div><label type='date'>Start Date</label>
	<input type="date" id="start_date"> </div>
	<div>
		<label>Amount</label>
		<input id="amount"> 
	</div>
	<div>
		<label>Event Time</label>
		<input type="time" id="event_time"> 
	</div>
	<div><label for="locations">Location</label>
		<select name="locations" id="locations">
		 	<option value="1">1. Reston, VA</option>
			<option value="2">2. Tampa, FL</option>
			<option value="3">3. New York, NY</option>
			<option value="4">4. Dallas, TX</option>
			<option value="5">5. Orlando, FL</option>
			<option value="6">6. Morgantown, WV</option>
		</select>
	</div>

	<div><label for="grading_format">Grading Format</label>
		<select name="grading_format" id="grading_format">
		 	<option value="1">1. A : F</option>
			<option value="2">2. 0 - 100 </option>
		</select>
	</div>
	<div>
		<label>Work Related Justifications</label>
		<input id="justifications">
	</div>
	<div><label for="passing_cutoff_grade">Passing Cutoff Grade</label>
		<select name="passing_cutoff_grade" id="passing_cutoff_grade">
		 	<option value="1">1. 90 - 100 : A </option>
			<option value="2">2. 80 - 89 : B </option>
			<option selected value="3">3. 70 - 79 : C </option>
			<option value="4">4. 60 - 69 : D </option>
			<option value="5">5. 50 - 59 : F </option>
		</select>
	</div>

	<input class="btn btn-primary" type='submit' onClick="addEvtReq()" > 	
`
evtReqSection.appendChild(form); 

async function addEvtReq() {
	let evtReq = {};
	evtReq.name = document.getElementById("name").value;
	evtReq.posting_date = document.getElementById("posting_date").value;
	evtReq.type_id = document.getElementById("types").value;
	evtReq.start_date = document.getElementById("start_date").value;
	evtReq.amount = document.getElementById("amount").value;
	evtReq.event_time = document.getElementById("event_time").value + ":00";
	
	// need to complete the following codes
	
	evtReq.location_id = document.getElementById("locations").value;
	evtReq.grading_format_id = document.getElementById("grading_format").value;
	evtReq.work_related_justification = document.getElementById("justifications").value;
	evtReq.passing_cutoff_grade_id = document.getElementById("passing_cutoff_grade").value;
	

	let url = baseUrl + '/events/';
	let response = await fetch(url, {method:'POST', body:JSON.stringify(evtReq)});
	 if (response.status === 201) {
        alert('Added event successfully.');
    } else {
        alert('Something went wrong.');
    }
	
}