checkLogin().then(getCourses);

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
        
      if (courses.length > 0)  {
        
    
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
                <th>Min. Grade</th>
                <th>Event Type</th>
                <th>Dir Sup</th>
                <th>Dept Head</th>
                <th>Ben Cor.</th>
                <th>Award Status</th>
                <th>Approver Id</th>
                <th>Latest Submit Date</th>
                <th>Reimbursement Amount</th>
            </tr>
        `;
        
let today = new Date();
let dd = String(today.getDate()).padStart(2, '0');
let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
let yyyy = today.getFullYear();

today = mm + '/' + dd + '/' + yyyy;

       for (let course of courses) {
        let courseDate = course.startDate;

        let DELIMITER = "-";
        let parts = courseDate.split(DELIMITER);
        courseDate = parts.join("/");

        let date1 = new Date(today);
        let date2 = new Date(courseDate);
        let diffTime = Math.abs(date1 - date2);
        let diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 


            let tr = document.createElement('tr');
            if ((diffDays < 14) & (diffDays>=7)) tr.setAttribute('style','color: maroon');
            else if (diffDays<7)  { 
            tr.setAttribute('style','color: maroon; font-style: italic');
       }
            tr.innerHTML = `
                <td>${course.id}</td>
                <td>${course.employeeId}</td>
                <td>${course.startDate}</td>
                <td>${course.startTime}</td>
                <td>${course.description}</td>
                <td>${course.courseCost}</td>
                <td>${course.gradingFormat}</td>
                <td>${course.gradingMin}</td>
                <td>${course.eventType.name}</td>
                <td>${course.dirSup}</td>
                <td>${course.deptHead}</td>
                <td>${course.benCor}</td>
                <td>${course.awardGranted}</td>
                <td>${course.approverId}</td>
                <td>${course.latestSubmitDate}</td>
                <td>${course.reimburseAmt}</td>
            `;
   
  
        let editFeedbackBtn = document.createElement('button');
        editFeedbackBtn.type = 'button';
        editFeedbackBtn.id = course.id;
        
        editFeedbackBtn.textContent = 'Edit Feedback';
    
        
         if ((diffDays<7) && !(course.dirSup))editFeedbackBtn.disabled = true; // it's too late to start providing feedback

        
        let editBtnTd = document.createElement('td');
        editBtnTd.appendChild(editFeedbackBtn);
        tr.appendChild(editBtnTd);
        table.appendChild(tr);
        
        editFeedbackBtn.addEventListener('click', editCourse);
   
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
    let url = baseUrl + '/courses/' + editId;
    let response = await fetch(url);

    let course = await response.json();

    url = baseUrl + '/users/' + course.employeeId;
    response = await fetch(url);
    if (response.status === 200) {
        student = await response.json();
    }    
    let lastRole =0;
    let lastRole2 =0;
    if (course.approverId>0) 
    {url = baseUrl + '/users/' + course.approverId;
    response = await fetch(url);
    if (response.status === 200) {
        lastApprover = await response.json();
        lastRole = lastApprover.role.id;
        lastRole2 = lastApprover.role2.id;
    }
}
    let submitDate = new Date(course.latestSubmitDate);
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();

    today = mm + '/' + dd + '/' + yyyy;
    let date1 = new Date(today);
      
    diffTime = Math.abs(date1 - submitDate);
    diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 

    if (loggedUser.role.id == 4) {
   
        if (lastRole ==2 && lastRole2 != 3) alert ("Department Head hasn't left feedback yet");
        else if (lastRole ==0) alert ("Direct Supervisor hasn't left feedback yet");
        else {
            course.benCor = document.getElementById('benCorSelect').value;
            course.reimburseAmt = document.getElementById('reimburseAmt').value;
            course.awardGranted = document.getElementById('awardGrant').value;
            // console.log(course.awardGranted);
            course.approverId = loggedUser.id;
            course.latestSubmitDate = today;
            url = baseUrl + '/courses/' + editId;
    
    
            response = await fetch(url, {method:'PUT', body:JSON.stringify(course)});
           ;
           if (response.status === 201) {
               alert('Updated course successfully.');
           } else {
               alert('Something went wrong.');
               console.log(response.status);
           }
    }
    }



    else if (loggedUser.department.id == student.department.id){
    if (loggedUser.role.id == 2 && loggedUser.role2.id == 3){
        course.dirSup = document.getElementById('dirSupSelect').value;
        if ((diffDays)>7) {
        course.dirSup = "Approved";
        course.deptHead = "Approved"
        alert("Took took long to respond.  Auto Approve")
        
        }
        course.latestSubmitDate = today;
        course.deptHead = course.dirSup;
        course.approverId = loggedUser.id;
        url = baseUrl + '/courses/' + editId;


            response = await fetch(url, {method:'PUT', body:JSON.stringify(course)});
           ;
           if (response.status === 201) {
               alert('Updated course successfully.');
           } else {
               alert('Something went wrong.');
               console.log(response.status);
           }
    }

    else if (loggedUser.role.id == 2){
       
        course.dirSup = document.getElementById('dirSupSelect').value;
        if ((diffDays)>7) {
        course.dirSup = "Approved";
        alert("Took took long to respond.  Auto Approve")
        
        }
        course.latestSubmitDate = today;
        course.approverId = loggedUser.id;
        url = baseUrl + '/courses/' + editId;


            response = await fetch(url, {method:'PUT', body:JSON.stringify(course)});
           ;
           if (response.status === 201) {
               alert('Updated course successfully.');
           } else {
               alert('Something went wrong.');
               console.log(response.status);
           }
    }
    else if (loggedUser.role.id == 3){
        
        course.deptHead = "Approved";
        if (lastRole ==0) alert ("Direct Supervisor hasn't left feedback yet");
        else {course.deptHead = document.getElementById('deptHeadSelect').value;
        if (diffDays>7) {
        alert("Took took long to respond.  Auto Approve");
        
        }
        course.latestSubmitDate = today;
        course.approverId = loggedUser.id;
        url = baseUrl + '/courses/' + editId;


            response = await fetch(url, {method:'PUT', body:JSON.stringify(course)});
           ;
           if (response.status === 201) {
               alert('Updated course successfully.');
           } else {
               alert('Something went wrong.');
               console.log(response.status);
           }
    }
    }
} else alert ("Departments don't match");

 

}
async function editCourse()
{
    let editBtn = event.target;
    let editId = event.target.id;

    let editTd = editBtn.parentElement;
    let editTr = editTd.parentElement;

    let nodes = editTr.childNodes;
    let url = baseUrl + '/courses/' + editId;
    let response = await fetch(url);

    let course = await response.json();
    // url = baseUrl + '/users/' + course.approverId;
    // response = await fetch(url);
    // if (response.status === 200) {
    //     lastApprover = await response.json();
    // } 
       
    if (loggedUser.role.id == 4) // benefits coordinator
    {
    editTr.innerHTML = `
        <td>${nodes.item(1).innerHTML}</td>
        <td>${nodes.item(3).innerHTML}</td>
        <td>${nodes.item(5).innerHTML}</td>
        <td>${nodes.item(7).innerHTML}</td>
        <td>${nodes.item(9).innerHTML}</td>
        <td>${nodes.item(11).innerHTML}</td>
        <td>${nodes.item(13).innerHTML}</td>
        <td>${nodes.item(15).innerHTML}</td>
        <td>${nodes.item(17).innerHTML}</td>
        <td>${nodes[19].innerHTML}</td>
        <td>${nodes[21].innerHTML}</td>
        <td><input id = "benCorSelect" type = "text" value = ${nodes[23].innerHTML}></td>
        <td><input id = "awardGrant" type = "text" value = ${nodes[25].innerHTML}></td>
        <td>${nodes[27].innerHTML}</td>
       
        <td>${nodes[29].innerHTML}</td>
        <td><input id = "reimburseAmt" type = "text" value = ${nodes[31].innerHTML}></td>
        <button id = ${editId}>Send Feedback</button></td>
       
        
        `;

}
    else if (loggedUser.role.id ==3 && !(course.benCor)){ // Department Head
    editTr.innerHTML = `
    <td>${nodes.item(1).innerHTML}</td>
    <td>${nodes.item(3).innerHTML}</td>
    <td>${nodes.item(5).innerHTML}</td>
    <td>${nodes.item(7).innerHTML}</td>
    <td>${nodes.item(9).innerHTML}</td>
    <td>${nodes.item(11).innerHTML}</td>
    <td>${nodes.item(13).innerHTML}</td>
    <td>${nodes.item(15).innerHTML}</td>
    <td>${nodes.item(17).innerHTML}</td>
    <td>${nodes[19].innerHTML}</td>
    <td><input id = "deptHeadSelect" type = "text" value = ${nodes[21].innerHTML}></td>
    <td>${nodes[23].innerHTML}</td>
    <td>${nodes[25].innerHTML}</td>
    <td>${nodes[27].innerHTML}</td>
    <td>${nodes[29].innerHTML}</td>
    <td>${nodes[31].innerHTML}</td>
    <button id = ${editId}>Send Feedback</button></td>
    
    
    `;

    // 
}
    else if (loggedUser.role.id == 2 && !(course.deptHead)) // direct supervisor
    editTr.innerHTML = `
    <td>${nodes.item(1).innerHTML}</td>
    <td>${nodes.item(3).innerHTML}</td>
    <td>${nodes.item(5).innerHTML}</td>
    <td>${nodes.item(7).innerHTML}</td>
    <td>${nodes.item(9).innerHTML}</td>
    <td>${nodes.item(11).innerHTML}</td>
    <td>${nodes.item(13).innerHTML}</td>
    <td>${nodes.item(15).innerHTML}</td>
    <td>${nodes.item(17).innerHTML}</td>
    <td><input id = "dirSupSelect" type = "text" value = ${nodes[19].innerHTML}></td>
    <td>${nodes[21].innerHTML}</td>
    <td>${nodes[23].innerHTML}</td>
    <td>${nodes[25].innerHTML}</td>
    <td>${nodes[27].innerHTML}</td>
    <td>${nodes[29].innerHTML}</td>
    <td>${nodes[31].innerHTML}</td>
    <button id = ${editId}>Send Feedback</button></td>
    
    
    `;

/*  */
    editBtn = document.getElementById(editId);
    // console.log("edit id "+editId);
    editBtn.addEventListener('click', submitFeedback);

    
}



   
  




