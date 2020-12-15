checkLogin().then(getCourses);
id=0;
firstTime=true;
approveId=loggedUser.id;
async function getCourses() {
    let url = baseUrl + '/courses';
    let response = await fetch(url);
    if (response.status === 200) {
        let courses = await response.json();
        populateCourses(courses);
       
    }
}

function populateCourses(courses) {
    let courseSection = document.getElementById('courseSection');
    
    if (true){
        console.log(courses.length);
      if (courses.length > 0)  {
          console.log(firstTime);
    
            let table = document.createElement('table');
        table.id = 'courseTable';
        
        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Employee Id</th>
                <th>Start Date</th>
                <th>Start Time</th>
                <th>Description</th>
                <th>Cost</th>
                <th>Grading Format</th>
                <th>Event Type</th>
                <th>Dir Sup</th>
                <th>Dept Head</th>
                <th>Ben Cor.</th>
                <th>Award Granted</th>
                <th>Approver Id</th>
                <th></th>
            </tr>
        `;
var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();

today = mm + '/' + dd + '/' + yyyy;

       for (let course of courses) {
        var courseDate = course.startDate;

        var DELIMITER = "-";
        var parts = courseDate.split(DELIMITER);
        courseDate = parts.join("/");

        const date1 = new Date(today);
        const date2 = new Date(courseDate);
        const diffTime = Math.abs(date1 - date2);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 


            let tr = document.createElement('tr');
            if ((diffDays < 14) & (diffDays>=8)) tr.setAttribute('style','color: maroon');
            else if (diffDays<8)  { 
            tr.setAttribute('style','color: maroon; text-decoration: line-through');
       }
            tr.innerHTML = `
                <td>${course.id}</td>
                <td>${course.employeeId}</td>
                <td>${course.startDate}</td>
                <td>${course.startTime}</td>
                <td>${course.description}</td>
                <td>${course.courseCost}</td>
                <td>${course.gradingFormat}</td>
                <td>${course.eventType.name}</td>
                <td>${course.dirSup}</td>
                <td>${course.deptHead}</td>
                <td>${course.benCor}</td>
                <td>${course.awardGranted}</td>
                <td>${course.approverId}</td>
            `;
            
            if (id ==0){ 
                id = course.id;
                approveId = course.approverId;
            }
         

        let editFeedbackBtn = document.createElement('button');
        editFeedbackBtn.type = 'button';
        editFeedbackBtn.id = course.id;
        console.log("editfeedbackbutton id "+ course.id);
        editFeedbackBtn.textContent = 'Edit Feedback';
        if (diffDays<8) editFeedbackBtn.disabled = true; // it's too late to provide feedback


     // how do I associate each feedback button to their respective course id?
        
        let editBtnTd = document.createElement('td');
        editBtnTd.appendChild(editFeedbackBtn);
        tr.appendChild(editBtnTd);
        table.appendChild(tr);


        // let btnTd = document.createElement('td');
        // btnTd.appendChild(feedbackBtn);
        // tr.appendChild(btnTd);
        // table.appendChild(tr);
        
        editFeedbackBtn.addEventListener('click', editCourse);

        // feedbackBtn.addEventListener('click', submitFeedback);    
            table.appendChild(tr);
       }

        courseSection.appendChild(table);
 
    } else {
        courseSection.innerHTML = 'No courses are available.';
    }
}
}
// here I update set of courses 
async function submitFeedback()
{
    let editId = event.target.id;
    // console.log("id is "+editId);
    let url = baseUrl + '/courses/' + editId;
    let response = await fetch(url);

    let course = await response.json();
    
    course.dirSup = document.getElementById('dirSupSelect').value;
    course.deptHead = document.getElementById('deptHeadSelect').value;
    course.benCor = document.getElementById('benCorSelect').value;
    course.approverId = loggedUser.id;
  
    // course.awardGranted = document.getElementById('awardGrantSelect').value;
    // why does the above line produce a getelementbyid is null when there is a awardGrantSelect?
   
    url = baseUrl + '/courses/' + editId;


     response = await fetch(url, {method:'PUT', body:JSON.stringify(course)});
    ;
    if (response.status === 201) {
        alert('Updated course successfully.');
    } else {
        alert('Something went wrong.');
        console.log(response.status);
    }
 console.log(approveId);

}
async function editCourse()
{
    let editBtn = event.target;
    let editId = event.target.id;
    // console.log('editId '+editId);
    let editTd = editBtn.parentElement;
    let editTr = editTd.parentElement;

    let nodes = editTr.childNodes;
    if (loggedUser.role.id == 4) // benefits coordinator
    editTr.innerHTML = `
        <td>${nodes.item(1).innerHTML}</td>
        <td>${nodes.item(3).innerHTML}</td>
        <td>${nodes.item(5).innerHTML}</td>
        <td>${nodes.item(7).innerHTML}</td>
        <td>${nodes.item(9).innerHTML}</td>
        <td>${nodes.item(11).innerHTML}</td>
        <td>${nodes.item(13).innerHTML}</td>
        <td>${nodes.item(15).innerHTML}</td>
        <td>${nodes[17].innerHTML}</td>
        <td>${nodes[19].innerHTML}</td>
     
        <td><input id = "benCorSelect" type = "text" value = ${nodes[21].innerHTML}></td>
        <td>${nodes[23].innerHTML}</td>
        <td> ${nodes[25].innerHTML}</td>
        <button id = ${editId}>Send Feedback</button></td>
        
        
        `;
    else if (loggedUser.role.id ==3) // Department Head
    editTr.innerHTML = `
    <td>${nodes.item(1).innerHTML}</td>
    <td>${nodes.item(3).innerHTML}</td>
    <td>${nodes.item(5).innerHTML}</td>
    <td>${nodes.item(7).innerHTML}</td>
    <td>${nodes.item(9).innerHTML}</td>
    <td>${nodes.item(11).innerHTML}</td>
    <td>${nodes.item(13).innerHTML}</td>
    <td>${nodes.item(15).innerHTML}</td>
    <td>${nodes[17].innerHTML}</td>
    <td><input id = "deptHeadSelect" type = "text" value = ${nodes[19].innerHTML}></td>
    <td>${nodes[21].innerHTML}</td>
    <td>${nodes[23].innerHTML}</td>
    <td> ${nodes[25].innerHTML}</td>
    <button id = ${editId}>Send Feedback</button></td>
    
    
    `;
    else if (loggedUser.role.id == 2) // direct supervisor
    editTr.innerHTML = `
    <td>${nodes.item(1).innerHTML}</td>
    <td>${nodes.item(3).innerHTML}</td>
    <td>${nodes.item(5).innerHTML}</td>
    <td>${nodes.item(7).innerHTML}</td>
    <td>${nodes.item(9).innerHTML}</td>
    <td>${nodes.item(11).innerHTML}</td>
    <td>${nodes.item(13).innerHTML}</td>
    <td>${nodes.item(15).innerHTML}</td>
    <td><input id = "dirSupSelect" type = "text" value = ${nodes[17].innerHTML}></td>
    <td>${nodes[19].innerHTML}</td>
    <td>${nodes[21].innerHTML}</td>
    <td>${nodes[23].innerHTML}</td>
    <td> ${nodes[25].innerHTML}</td>
    <button id = ${editId}>Send Feedback</button></td>
    
    
    `;


{/* <button id = "back">Back</button></td> */}
    editBtn = document.getElementById(editId);
    editBtn.addEventListener('click', submitFeedback);
    // backBtn = document.getElementById("back");
    // backBtn.addEventListener('click', getCourses);

    // let btnId = event.target.id;
    // let index = btnId.indexOf('_');
    // let id = btnId.slice(index+1); // get text after underscore
    /*
    let url = baseUrl + '/courses/' + id;

    let response = await fetch(url);

    let course = await response.json();

    course.dirSup = document.getElementById('dirSupSelect').value;
    course.deptHead = document.getElementById('deptHeadSelect').value;
    course.benCor = document.getElementById('benCorSelect').value;
    course.awardGranted = document.getElementById('awardGrantedSelect').value;
    // console.log (document.getElementById('benCorSelect').value);
    // let newResponse = await fetch(url,{method:'PUT',body:JSON.stringify(course)});
    // if (newResponse.status === 200) {
    //     alert('Updated successfully.');
    // } else {
    //     alert('Something went wrong.');
    // }
    */
    
}






// function editCat() {
//     let btnId = event.target.id;
// }

