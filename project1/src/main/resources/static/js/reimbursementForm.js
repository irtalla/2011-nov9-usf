var aWeekFromToday = null;
var formDiv;
function closeRequest(){
        formDiv.innerHTML = ``;
}
function showForm(parentDivId){
        this.style="display: none;"
        formDiv = document.getElementById(parentDivId);
        formDiv.innerHTML = 
`
<form>     
        <table class="table table-striped">
                <tr> 
                        <td>
                                <label for="empId">Your employee Id:</label><br>
                                <input type="text" id="empId" name="empId" readonly><br>
                        </td>        
                        <td>
                                <label for="fname">First name:</label><br>
                                <input type="text" id="fname" name="fname" readonly><br>
                        </td>   
                        <td>
                                <label for="lname">Last name:</label><br>
                                <input type="text" id="lname" name="lname" readonly><br>
                        </td>     
                </tr>

                <tr>        
                        <td>
                                <label for="date">Event Begin Date:</label><br>
                                <input class="form-control" type="date" value="2011-08-19" id="date" required>
                        </td>   
                        <td>
                                <label for="location">Location:</label><br>
                                <input type="text" id="location" name="location" required><br>
                        </td>   
                        <td>
                                <label for="time">Time:</label><br>
                                <input type="text" id="time" name="time" required><br>
                        </td>   
                </tr>

                <tr>        
                        <td>
                                <label for="cost">Cost:</label><br>
                                <input type="number" id="cost" name="cost" onchange="estimate()" required><br>
                        </td>   
                        <td>
                                <label for="gformat">Grading Format:</label><br>
                                <input type="text" id="gformat" name="gformat" required><br>
                        </td>   
                        <td>
                        <div class="form-group">
                        <label for="eventType">Event Type:</label>
                                <select class="form-control" id="eventType" name="eventType" onchange="estimate()">
                                        <option value=1>University Course</option>
                                        <option value=2>Seminar</option>
                                        <option value=3>Certification Preparation Class</option>
                                        <option value=4>Certification</option>
                                        <option value=5>Technical Training</option>
                                        <option value=6>Other</option>
                                </select>
                        </div>        
                        </td>   
                </tr>
                <tr>        
                        <td>
                                <div id = "estimate"><br>
                        </td>   
                        <td colspan="2">
                        <div class="input-group">
                        <div class="input-group-prepend">
                          <span class="input-group-text">Description</span>
                        </div>
                        <textarea class="form-control" id="description" name="description" aria-label="Description"></textarea>
                      </div>
                        </td>
                </tr>
                <tr>
                        <td colspan="3">
                                <button type="button" onclick="submitForm('${parentDivId}')" class="btn btn-primary">Submit Reimbursement Form</button>
                        </td>
                        <td><button type="button" onclick = "closeRequest()">Close</button></td>
                </tr>

        </table>
</form>
`;


document.getElementById("empId").value = this.loggedUser.id;
document.getElementById("fname").value = this.loggedUser.fname;
document.getElementById("lname").value = this.loggedUser.lname;

//set default in begin date to a week from today
var date = new Date();
aWeekFromToday = new Date(date.getTime() + (7 * 24 * 60 * 60 * 1000));
var day =aWeekFromToday.getDate();
var month=aWeekFromToday.getMonth()+1;
var year=aWeekFromToday.getFullYear();
var d = year+"-"+month+"-"+day;
document.getElementById("date").value = d;

}
function estimate(){
        let c = document.getElementById('cost').value;
        let t = document.getElementById("eventType").value;
        let est = coverageBasedOnType(t, c);
        if(est <= 1000){
                document.getElementById("estimate").innerHTML = `Up to $${est} could be reimbursed for this event.`;
                return est;
        }
        else {
                document.getElementById("estimate").innerHTML = `Up to $1000 could be reimbursed for this event.`;
                return 1000;
        }

}
async function submitForm(parentDivId){
        const formDiv = document.getElementById(parentDivId);

        var dat = new Date(document.getElementById("date").value);
        var date = new Date();
        aWeekFromToday = new Date(date.getTime() + (6 * 24 * 60 * 60 * 1000));
        //ensure a date earlier than a week from today cannot be submitted
        if(dat < aWeekFromToday ){
        alert("Please enter a valid begin date for event");
        }
        if(!document.getElementById('location').validity.valid)
        {
                alert("Please enter a location for event");
        }
        if(!document.getElementById('time').validity.valid)
        {
                alert("Please enter a time for event");
        }
        if(!document.getElementById('cost').validity.valid)
        {
                alert("Please enter a cost for event");
        }
        if(!document.getElementById('gformat').validity.valid)
        {
                alert("Please enter a grading format for event");
        }
        if(!document.getElementById('description').validity.valid)
        {
                alert("Please enter a description for event");
        }
        let formIsFilledOut = (dat > aWeekFromToday)&&
        (document.getElementById('location').validity.valid)&&
        (document.getElementById('time').validity.valid)&&
        (document.getElementById('cost').validity.valid)&&
        (document.getElementById('gformat').validity.valid)&&
        (document.getElementById('description').validity.valid);

        if(formIsFilledOut){ 
                
                let formUrl = baseUrl + '/upload';
                //post(ReimbursementController::addReimbursement);
                //^ it is expecting a reimbursement object (including event child object)
                let reimbursementToAdd = {};
                let status = {};
                status.id = 1;
                status.name = 'pending';
                reimbursementToAdd.status = status;
                let event = {};
                let eventType = {};
                eventType.id =          document.getElementById("eventType").value;
                event.type = eventType;
                event.begin_date =      document.getElementById("date").value;
                event.location =        document.getElementById("location").value;
                event.time =            document.getElementById("time").value;
                event.cost =            document.getElementById("cost").value;
                event.grading_format =  document.getElementById("gformat").value;
                event.description =     document.getElementById("description").value;
                reimbursementToAdd.event = event;
                employee = {};
                employee.id = this.loggedUser.id;
                reimbursementToAdd.employee = employee;
                reimbursementToAdd.amount =     reimbursementToAdd.event.cost;
                //todos: 
                // 1. determine estimated reimbursement
                //      a)first calculate based on event type and value of cost field what would be paid
                //      b)subtract that from whatever is left in the employee's 1000 dollars per year
                 
                // $('.modal-body').innerHTML = "Estimated reimbursement for proposed event: "
                
                formDiv.innerHTML = ``;

                let url = baseUrl + '/reimbursements';
                const settings = {
                        method: 'POST',
                        headers: {
                            Accept: 'application/json',
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(reimbursementToAdd)
                    };
                    try {
                        const fetchResponse = await fetch(url, settings);
                        const data = await fetchResponse.json();
                    $('#modalbod').html(`
                    Reimbursement request added.<br>
                    <br>
                    Please upload any event related files below.
                    <br><br>
                    <h1>Upload</h1><br><br>
                    
                    <form target="_blank" method="post" action=${formUrl}?rid=${data} enctype="multipart/form-data">
                    <input type="file" name="files" multiple>
                    <button>Submit</button>
                    </form>
                    
                    `);   
                    $("#myModal").modal('show');
                    } catch (e) {
                        return e;
                    }

        }
        else{
                alert("form is not filled out");
        }
}