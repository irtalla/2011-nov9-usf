checkLogin().then(setForm);

  

  function setForm() {
     
    if (loggedUser) {
        mainNav.innerHTML += `
            <form>
                <label for="firstName">Request form for: </label>
                ${loggedUser.firstName}&nbsp;            
                ${loggedUser.lastName}&nbsp;
                <br><br><label>Cost: </label>
                <input type="number" min="0.01" step="0.01" id="cost"></input><br><br>
                <label>Date:</label>
                <input type="date" id="start" name="trip-start"
                value="2020-12-23"
                min="2020-12-14" max="2021-12-31"><br><br>
                <label for="appt">Time (9 AM to 9 PM):</label>
                <input type="time" id="appt" name="appt"
                min="09:00" max="21:00" required><br><br>
                <label>Description:</label>
                <input type="text" id="description"></input><br><br>
                <label>Grading Format:</label>
                <select id = "myGradeList">
                 <option value = "Letter Grade">Letter Grade</option>
                 <option value = "Presentation">Presentation</option>
                </select>
                <label>Grade Minimum</label>
                <select id = "gradeMinList">
                <option value = "B">B</option>
                <option value = "C">C</option>
                <option value = "D">D</option>
                <option value = "Unknown">Unknown</option>
            
              </select>

             <label>Event Type</label>
             
                 <select id = "myEventList">
                   <option value = "1">Certification</option>
                   <option value = "2">Technical Training</option>
                   <option value = "3">University Courses</option>
                   <option value = "4">Cert. Prep. Classes</option>
                   <option value = "5">Seminar</option>
                   <option value = "6">Other</option>
                 </select>
               
             </div> 
             <button onclick="addCourse()" id="submit-add-course-form" >Add Course</button>
            </form>
        `;

        let formBtn = document.getElementById('formBtn');
       
      employeeId=loggedUser.id;
    } 

}
async function addCourse() {
    let course = {};
    course.id = 0;
    course.employeeId = loggedUser.id;
    console.log(loggedUser.id);
    course.courseCost = document.getElementById('cost').value;
    course.startDate = document.getElementById('start').value;
    course.startTime = document.getElementById('appt').value;
    course.description = document.getElementById('description').value;
    course.gradingFormat = document.getElementById('myGradeList').value;
    course.gradingMin = document.getElementById("gradeMinList").value;
    course.eventType = {};
    course.eventType.id = document.getElementById('myEventList').value
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();

    today = mm + '/' + dd + '/' + yyyy;
    course.latestSubmitDate = today;
    console.log(JSON.stringify(course))
    let url = baseUrl + '/courses';
    let response = await fetch(url, {method:'POST', body:JSON.stringify(course)});
    ;
    if (response.status === 201) {
        alert('Added course successfully.');
    } else {
        alert('Something went wrong.');
    }
   
}



    
