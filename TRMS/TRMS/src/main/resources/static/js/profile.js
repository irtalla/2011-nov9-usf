"use strict"
let addFormMenuOpen = false;
let titleTag = document.getElementById("viewFormTitle");
checkLogin().then(setup);

function setup() {
    getForms().then(() => {
        checkLogin().then(() => {
            if (loggedUser.role.name === 'ben co') benCoSetup();
        });
    });
}

async function getForms() {
    let url = baseUrl + '/submitForms/all';
    let response = await fetch(url);
    if (response.status === 200) {
        let forms = await response.json();
        populateForms(forms);
    }
}

function populateForms(forms) {
    let formSection = document.getElementById('formSection');
    formSection.innerHTML = '';

    if (forms.length > 0) {
        let table = document.createElement('table');
        table.id = 'formTable';

        table.innerHTML = `
            <tr>
				<th>Emp id</th>
                <th>Name</th>
				<th>Event id</th>
                <th>Event</th>
                <th>% of Reim</th>
                <th>Cost</th>
                <th>Appr type</th>
				<th>Status id</th>
                <th>Status</th>
                <th>Grade</th>
                <th>Descript</th>
                <th>Date</th
            </tr>
        `;

        for (let form of forms) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
				<td>${form.emp.id}</td>
                <td>${form.emp.fullName}</td>
				<td>${form.et.id}</td>
                <td>${form.et.name}</td>
                <td>${form.et.por}</td>
                <td>$${form.et.cost}</td>
                <td>${form.et.appr.name}</td>
				<td>${form.stat.id}</td>
                <td>${form.stat.name}</td>
                <td>${form.grade}</td>
                <td>${form.description}</td>
                <td>${form.date}</td>
            `;
			table.appendChild(tr);

        }
        formSection.appendChild(table);
    } else {
        formSection.innerHTML = 'There are no forms.';
    }
}

    let employeeSpan = document.getElementById('emp');
    employeeSpan.removeAttribute('hidden');
    // add form
    if (!(document.getElementById('addFormBtn'))) {
        let addFormBtn = document.createElement('button');
        addFormBtn.type = 'button';
        addFormBtn.textContent = 'Add Form';
        addFormBtn.id = 'addFormBtn';
        addFormBtn.onclick = addFormMenu;
        employeeSpan.appendChild(addFormBtn);
    }
function benCoSetup() {
    // edit form
    let formsTable = document.getElementById('formTable');
    for (let tr of formsTable.childNodes) {
        if (tr.nodeName === 'TR') {
            let td = document.createElement('td');
            if (tr != formsTable.childNodes.item(0)) {
                let editBtn = document.createElement('button');
                editBtn.id = 'edit_' + tr.childNodes.item(1).textContent;
                editBtn.type = 'button';
                editBtn.textContent = 'Edit';
                editBtn.onclick = editForm;
                td.appendChild(editBtn);
            }
            tr.appendChild(td);
        }
    }
}

function addFormMenu() {
    let employeeSpan = document.getElementById('emp');
    addFormMenuOpen = !addFormMenuOpen;
    console.log('add form menu open? ' + addFormMenuOpen);

    if (addFormMenuOpen) {
        employeeSpan.innerHTML += `<form id="add-form-form">
        <label for="empId">Emp id:</label>
        <input type="text" id="empId" placeholder="enter your employee id" required />
        
        <label for="eventId">Event id:</label>
        <input type="number" id="eventId" placeholder="enter the event id" required />
        
        <label for="descript">Description:</label>
        <input type="text" id="descript" placeholder="enter a short description" required />

        

        <button type="button" onclick="addForm()" id="submit-add-form-form" >Submit</button>
        </form>
        `;
       // populateBreeds();
        let submitAddBtn = document.getElementById('submit-add-form-form');
        submitAddBtn.onclick = addForm;
    } else {
        employeeSpan.removeChild(document.getElementById('add-form-form'));
    }

    let addFormBtn = document.getElementById('addFormBtn');
    addFormBtn.onclick = addFormMenu;
}

function editForm() {
    let editBtn = event.target;
    let editId = event.target.id;
    let editTd = editBtn.parentElement;
    let editTr = editTd.parentElement;

    let nodes = editTr.childNodes;

    editTr.innerHTML = `
        <td id="emp_idtd">${nodes.item(1).innerHTML}</td>
        <td id="event_idtd">${nodes.item(3).innerHTML}</td>
        <td>${nodes.item(5).innerHTML}</td>
		<td>${nodes.item(7).innerHTML}</td>
        <td>${nodes.item(9).innerHTML}</td>
		<td>${nodes.item(11).innerHTML}</td>
        <td>${nodes.item(13).innerHTML}</td>
		<td><input id = "eCSid" type = "text" value = ${nodes.item(15).innerHTML}></td>
		<td>${nodes.item(17).innerHTML}</td>
        <td><input id = "eCGrade" type = "text" value = ${nodes.item(19).innerHTML}></td>
        <td><input id = "eCDescript" type = "text" value = ${nodes.item(21).innerHTML}></td>
		<td>${nodes.item(23).innerHTML}</td>
        <button id = ${editId}>Save</button></td>
        `;
    //<input id = "eCBreed" type = "text" value = ${nodes[3].innerHTML}>
    editBtn = document.getElementById(editId);
    editBtn.addEventListener('click', saveForm);
	console.log(document.getElementById('emp_idtd').value);
}

async function saveForm()
{
    let btnId = event.target.id;
    let index = btnId.indexOf('_');
    let id = btnId.slice(index+1); // get text after underscore
	

    let url = baseUrl + '/submitForms/' + id;

    let response = await fetch(url);

    let form = await response.json();

	console.log(form);
    form.stat.id = document.getElementById('eCSid').value;
	form.grade = document.getElementById('eCGrade').value;
	form.description = document.getElementById('eCDescript').value;
    

    let newResponse = await fetch(url,{method:'PUT',body:JSON.stringify(form)});
    if (newResponse.status === 200) {
        alert('Updated successfully.');
    } else {
        alert('Something went wrong.');
    }
    
    setup();
}



async function addForm() {
    let form = {};
    form.emp = {};
	form.et = {};
	form.emp.id = document.getElementById('empId').value;
    form.et.id = document.getElementById('eventId').value;
    form.description = document.getElementById('descript').value;

    let url = baseUrl + '/submitForms';
    let response = await fetch(url, {method:'POST', body:JSON.stringify(form)});
    if (response.status === 201) {
        alert('Added form successfully.');
    } else {
        alert('Something went wrong.');
    }
    addFormMenu();
    setup();
}