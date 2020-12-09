var aWeekFromToday = null;

function showForm(parentDivId){
        this.style="display: none;"
        const formDiv = document.getElementById(parentDivId);
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
                                <input class="form-control" type="date" value="2011-08-19" id="date">
                        </td>   
                        <td>
                                <label for="location">Location:</label><br>
                                <input type="text" id="location" name="location"><br>
                        </td>   
                        <td>
                                <label for="time">Time:</label><br>
                                <input type="text" id="time" name="time"><br>
                        </td>   
                </tr>

                <tr>        
                        <td>
                                <label for="cost">Cost:</label><br>
                                <input type="text" id="cost" name="cost"><br>
                        </td>   
                        <td>
                                <label for="gformat">Grading Format:</label><br>
                                <input type="text" id="gformat" name="gformat"><br>
                        </td>   
                        <td>
                        <div class="form-group">
                        <label for="eventType">Event Type:</label>
                                <select class="form-control" id="eventType" name="eventType">
                                        <option>University Course</option>
                                        <option>Seminar</option>
                                        <option>Certification Preparation Class</option>
                                        <option>Certification</option>
                                        <option>Technical Training</option>
                                        <option>Other</option>
                                </select>
                        </div>        
                        </td>   
                </tr>


                <tr>        
                        <td>
                                <div class="input-group mb-3">
                                        <div class="custom-file">
                                                <input type="file" class="custom-file-input" id="inputGroupFile02">
                                                <label class="custom-file-label" for="inputGroupFile02">Choose file</label>
                                        </div>
                                        <div class="input-group-append">
                                                <span class="input-group-text" id="">Upload</span>
                                        </div>
                                </div>
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
                </tr>

        </table>
</form>
`;
// document.getElementById("empId").value = "value1";
// document.getElementById("fname").value = "value2";
// document.getElementById("lname").value = "value3";
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

function submitForm(parentDivId){
        const formDiv = document.getElementById(parentDivId);
        $('#modalbod').html("Estimated reimbursement for proposed event: ");
        // $('.modal-body').innerHTML = "Estimated reimbursement for proposed event: "
        $("#myModal").modal('show');

        var dat = new Date(document.getElementById("date").value);
        var date = new Date();
        aWeekFromToday = new Date(date.getTime() + (6 * 24 * 60 * 60 * 1000));
        //ensure a date earlier than a week from today cannot be submitted
        if(dat < aWeekFromToday ){
        alert("Please enter a valid begin date for event");
        }
        else{
                formDiv.innerHTML = ``;
        }
}
 
