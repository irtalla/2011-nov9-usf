setup();

function setup() {
		getApprovals().then(() => {
			console.log(loggedUser);
			checkLogin().then(() => {
			if (loggedUser.role.id > 1) editorSetup();
		});
	});
}

async function getApprovals () {
	let url = baseUrl + '/pitches/approve/';
	let response = await fetch(url);
	if (response.status === 200) {
		let approvals = await response.json();
		populateApprovals(approvals);
		console.log(approvals);
	}
}

function populateApprovals(approvals) {
	let approvalSection = document.getElementById('approvalSection');
	approvalSection.innerHTML = '';
	
	if (approvals.length > 0) {
		let table = document.createElement('table');
		table.id = 'approvalTable';
		
		table.innerHTML = `
		<tr>
			<th> ID </th>
			<th> Author ID</th>
			<th> Genre of Interest </th>
			<th> Pitch ID </th>
			<th> Suggestion </th>
			`
			for (let approval of approvals) {
				let tr = document.createElement('tr');
				tr.innerHTML = `
				<td>${approval.pitch.p_id}</td>
				<td>${approval.pitch.author.usr_id}
				<td>${approval.genre.name}</td>
				<td>${approval.approval_id}</td>
				<td>${approval.suggestion}</td>`;
				window.approvalId = approval.approval_id;
				window.approvalgenre = approval.editor_genre;
				window.approvaltype = approval.pitch.st;
				let td = document.createElement('td');
				let ul = document.createElement('ul');
				td.appendChild(ul);
				tr.appendChild(td);
				
				let draftBtn = document.createElement('button');
				draftBtn.type='button';
				draftBtn.id = approval.title + '_' + approval.pitch.p_id;
				draftBtn.textContent = 'Move to draft';
				draftBtn.disabled = loggedUser.role.name === ('Author');
				
				let draftTd = document.createElement('td');
				draftTd.appendChild(draftBtn);
				tr.appendChild(draftTd);
				table.appendChild(tr);
				
				draftBtn.addEventListener('click', createDraft)
				
			}
			approvalSection.appendChild(table);
		} else {
			approvalSection.innerHTML = 'No approvals are available.';
		}
}
async function createDraft() {
	let btnId = event.target.id;
	console.log(btnId);
	let index=btnId.indexOf('_');
	let id = btnId.slice(index+1);
	let name = btnId.replace('_', '');
	let draft = {};
	draft.pitch = {};
	draft.pitch.p_id = id;
	draft.story_type = approvaltype;
	draft.contents = '';
	if (confirm('You want to accept' + name +'?')) {
		let url = baseUrl + '/pitches/drafts/';
		let response = await fetch(url, {method:'POST', body:JSON.stringify(draft)});
		switch (response.status) {
			case 201:
			alert('You approved' + name +'!');
			break;
			case 409:
			alert('That approval doesn\`t seem to exist...');
			break
			case 401:
			alert('Hold on, you\`re not logged in!');
			break;
			default:
			alert('Something went wrong.');
			break;
		}
	}
}
function editorSetup() {
	console.log("Setup reached")
	let editorSpan = document.getElementById('editor');
	editorSpan.removeAttribute('hidden');
/*		if (!(document.getElementById('addPitchBtn'))) {
		let addDraftBtn = document.createElement('button')
		addDraftBtn.type = 'button';
		addDraftBtn.textContent = 'Add pitch';
		addDraftBtn.id = 'addPitchBtn';
		addDraftBtn.onclick = addPitchMenu;
		draftSpan.appendChild(addPitchBtn);
	}*/
	let approvalTable = document.getElementById('approvalTable');
	for (let tr of approvalTable.childNodes) {
		if (tr.nodeName === 'TR') {
			let td = document.createElement('td');
			if (tr != approvalTable.childNodes.item(0)) {
				let editBtn = document.createElement('button');
				editBtn.id = 'edit_' + tr.childNodes.item(1).textContent;
				editBtn.type= 'button';
				editBtn.textContent = 'Edit';
				editBtn.onclick = editApproval;
				td.appendChild(editBtn);
			}
			tr.appendChild(td);
		}
	}
}
async function editApproval() {
	let editBtn = event.target;
	let editId = event.target.id;
	let editTd = editBtn.parentElement;
	let editTr = editTd.parentElement;
	
	let nodes = editTr.childNodes;
	
	editTr.innerHTML = `
		<td>${nodes.item(1).innerHTML}</td>
		<td><input id = "eAContent" type = "text" value = ${nodes.item(4).innerHTML}></td>
		<td><button disabled = 'true'>Accept</button>
		<button id = ${editId}>save</button></td>
		`;
		editBtn = document.getElementById(editId);
		editBtn = addEventListener('click', saveApproval);
}

async function saveApproval(){
	let btnId = event.target.id;
	console.log(btnId);
	let index = btnId.indexOf('_');
	let id = btnId.slice(index+1);
	console.log(id);
	let url = baseUrl + '/pitches/approve/' + id;
	let response = await fetch(url);
	
	let approval = await response.json();
	approval.suggestion = document.getElementById('eAContent').value;
	let newResponse = await fetch(url,{method:'PUT', body:JSON.stringify(approval)});
	if (newResponse.status === 200) {
		alert('Updated successfully.');
	} else {
		alert('Something went wrong.');
	}
	
	setup();
}