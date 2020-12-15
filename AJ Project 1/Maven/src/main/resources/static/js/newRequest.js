checkLogin().then(populateRequestForm);

// populateRequestForm();
function populateRequestForm()  {
    let reqType = ['University Course', 'Seminar', 'Certification Preparation Class', 'Certification', 'Technical Training', 'Other'];

    
    let newRequestSection = document.getElementById('newRequest');
    let form = document.createElement("form");
    form.id = 'req-form';
    // form.setAttribute("method", "post")
    // form.setAttribute('action', url);

    //this sets up the type dropdown and label
    let rLabel = document.createElement('label');
    rLabel.setAttribute('for', 'ReqDropDown');
    rLabel.innerHTML = 'Please select a request type   ';
    
    let rType = document.createElement('select');
    rType.setAttribute('id', 'ReqDropDown');
    
    for (var i = 0; i < reqType.length; i++) {
        let req = reqType[i];

        var el = document.createElement('option');
        el.textContent = req;
        el.value = Number(i)+1;
        rType.appendChild(el);
    }
    let br1 = document.createElement('br');
    form.appendChild(rLabel);
    form.appendChild(rType);
    form.appendChild(br1);
    
    //end dropdown section
    
    
    //date/time label and picker
    // let dLabel = document.createElement('label');
    // dLabel.setAttribute('for', 'datePicker');
    // dLabel.innerHTML('');
    let dLabel = document.createElement('label');
    dLabel.setAttribute('for', 'datePicker');
    dLabel.innerHTML = 'Please enter the date and time of completion (YYYY-MM-DD HH:mm:ss)   ';
    
    let datePicker = document.createElement('input');
    datePicker.setAttribute('id', 'datePicker');
    datePicker.setAttribute('type', 'datetime-local');
    // datePicker.innerHTML = 'type="text" id="datePicker"';
	console.log(datePicker);
    form.appendChild(dLabel);
    form.appendChild(datePicker);
    let br2 = document.createElement('br');
    form.appendChild(br2);
    //end date/time

    //cost section
    let cLabel = document.createElement('label');
    cLabel.setAttribute('for', 'cost');
    cLabel.innerHTML = 'What is the cost of the request?  '; 

    let costField = document.createElement('input');
    costField.type = 'text';
    costField.setAttribute('id', 'cost');

    form.appendChild(cLabel);
    form.appendChild(costField);

    let br3 = document.createElement('br');
    form.appendChild(br3);
    //end cost section

    //location section
    let lLabel = document.createElement('label');
    lLabel.setAttribute('for', 'location');
    lLabel.innerHTML = 'What is the location?  '; 

    let locationField = document.createElement('input');
    locationField.type = 'text';
    locationField.setAttribute('id', 'location');

    form.appendChild(lLabel);
    form.appendChild(locationField);

    let br4 = document.createElement('br');
    form.appendChild(br4);

    //end location section

    //hours missed
    let hLabel = document.createElement('label');
    hLabel.setAttribute('for', 'hours');
    hLabel.innerHTML = 'How many hours, if any, of work will be missed?  '; 

    
    let hoursField = document.createElement('input');
    hoursField.type = 'text';
    hoursField.setAttribute('id', 'hours');

    form.appendChild(hLabel);
    form.appendChild(hoursField);

    let br5 = document.createElement('br');
    form.appendChild(br5);

    //end hours missed

    //description
    let desLabel = document.createElement('label');
    desLabel.setAttribute('for', 'description');
    desLabel.innerHTML = 'Please enter a short description with a business justification.'; 

    let desField = document.createElement('input');
    desField.type = 'text';
    desField.setAttribute('id', 'description');

    form.appendChild(desLabel);
    form.appendChild(desField);

    let br6 = document.createElement('br');
    form.appendChild(br6);
    //end description

    let submitBtn = document.createElement('button');
    submitBtn.type = 'button';
    // let submitBtn = document.getElementById('submit-req-form');
    // submitBtn.id = 'submit-new-request';
    submitBtn.textContent = 'Submit Request';
    submitBtn.onclick = submitRequest;

    form.appendChild(submitBtn);

    
    
    newRequestSection.appendChild(form);
}

async function submitRequest() {
    let completed = {};
    completed.requestType = {};
    let e = document.getElementById('ReqDropDown');

    completed.requestType.name = e.options[e.selectedIndex].text;
    completed.requestType.id = e.value;
    // for (var i = 0; i < reqType.length; i++) {
    //     let req = reqType[i];
    //     if (completed.requestType.name == req) {
    //         completed.requestType.id = (Number(i)+1);
    //     }
    // }
    let d = document.getElementById('datePicker');
    console.log(d);
    completed.testDate = d.value;
    completed.cost = document.getElementById('cost').value;
    completed.location = document.getElementById('location').value;
    completed.hoursMissed = document.getElementById('hours').value;
    completed.description = document.getElementById('description').value;
    completed.id = 0;
    completed.requestor = loggedUser;
    completed.manager = loggedUser.managerID;
    completed.dHead = loggedUser.dHeadID;
    completed.department = loggedUser.department.id;
    completed.amount = 0;
    completed.projectedAmount = 0;
    completed.due = null;
    completed.status = {};
    completed.status.id = 1;
    completed.status.name = "Created, awaiting manager";

    if (loggedUser.role.id === 2) {
        completed.status.id = 5;
        completed.status.name = "Manager approved, awaiting dhead";

    } else if (loggedUser.role.id === 1) {
        completed.status.id = 8;
        completed.status.name = "Dhead approval, awaiting benco";

    }

    let url = baseUrl + '/request';
    let response = await fetch(url, {method:'PUT', body:JSON.stringify(completed)});
    if (response.status === 200) {
        alert('Request Added successfully.');
    } else {
        alert('Something went wrong.');
    }

}