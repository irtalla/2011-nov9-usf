checkLogin();
// alert(loggedUser);


let evtReqSection = document.getElementById('evtReqSection');
let form = document.createElement('form'); 

form.innerHTML = `
	<div> <label>Name</label> 
	<input id="name"> </div>
	<div><label type='date'>Posting Date</label>
	<input id="posting_date"> </div>
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
	<input id="start_date"> </div>
	<div><label>Amount</label>
	<input id="amount"> </div>
	<label>Submit</label>
	<input type='submit' onClick="addEvtReq()" > 	
`
evtReqSection.appendChild(form); 

async function addEvtReq() {
	let evtReq = {};
	evtReq.name = document.getElementById("name").value;
	evtReq.posting_date = document.getElementById("posting_date").value;
	evtReq.type_id = document.getElementById("types").value;
	evtReq.start_date = document.getElementById("start_date").value;
	evtReq.amount = document.getElementById("amount").value;

	let url = baseUrl + '/events/';
	let response = await fetch(url, {method:'POST', body:JSON.stringify(evtReq)});
	 if (response.status === 201) {
        alert('Added event successfully.');
    } else {
        alert('Something went wrong.');
    }
	
}