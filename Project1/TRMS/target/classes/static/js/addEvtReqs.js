checkLogin();
// alert(JSON.parse(loggedUser));


let evtReqSection = document.getElementById('evtReqSection');
let form = document.createElement('form'); 

form.innerHTML = `
	<div class="mb-3"> 
		<label class="form-label">Name</label> 
		<input class="form-control" id="name"> 
		<div class="form-text">Enter Event Name</div>
	</div>
	<div class="mb-3">
		<label class="form-label" type='date'>Posting Date</label>
		<input class="form-control" type="date" id="posting_date"> 
	</div>
	<div class="mb-3"> 
		<label class="form-label" for="types">Event Type (Reimbursement Rate)</label>
		<select class="form-select" name="types" id="types">
		 	 <option value="1">1. University Courses (80%)</option>
		  	 <option value="2">2. Seminars (60%)</option>
		  	 <option value="3">3. Certification Preparation Class (75%)</option>
		     <option value="4">4. Certification (100%)</option>
		     <option value="5">5. Technical Training (90%)</option>
		     <option value="6">6. Other (30%)</option>
		</select>
	</div>
	<div class="mb-3">
		<label class="form-label" type='date'>Start Date</label>
		<input class="form-control" type="date" id="start_date"> </div>
	<div class="mb-3">
		<label class="form-label">Amount</label>
		<input class="form-control" id="amount"> 
	</div>
	<div class="mb-3">
		<label class="form-label">Event Time</label>
		<input class="form-control" type="time" id="event_time"> 
	</div>
	<div class="mb-3">
		<label class="form-label" for="locations">Location</label>
		<select class="form-select" name="locations" id="locations">
		 	<option value="1">1. Reston, VA</option>
			<option value="2">2. Tampa, FL</option>
			<option value="3">3. New York, NY</option>
			<option value="4">4. Dallas, TX</option>
			<option value="5">5. Orlando, FL</option>
			<option value="6">6. Morgantown, WV</option>
		</select>
	</div>
	<div class="mb-3">
		<label class="form-label" for="grading_format">Grading Format</label>
		<select class="form-select" name="grading_format" id="grading_format">
		 	<option value="1">1. A : F</option>
			<option value="2">2. 0 - 100 </option>
		</select>
	</div>
	<div class="mb-3">
		<label class="form-label">Work Related Justifications</label>
		<input class="form-control" id="justifications">
	</div>
	<div class="mb-3">
		<label class="form-label" for="passing_cutoff_grade">Passing Cutoff Grade</label>
		<select class="form-select" name="passing_cutoff_grade" id="passing_cutoff_grade">
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

	// added additional data retrieval information
	evtReq.status = 0; 
	

	let url = baseUrl + '/events/';
	let response = await fetch(url, {method:'POST', body:JSON.stringify(evtReq)});
	 if (response.status === 201) {
        alert('Added event successfully.');
    } else {
        alert('Something went wrong.');
    }
	
}