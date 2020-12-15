setup();

function setup() {
		getDrafts().then(() => {
			console.log(loggedUser);
			checkLogin().then(() => {
			if (loggedUser.role.id > 1) editorSetup();
		});
	});
}

async function getDrafts() {
	let url = baseUrl +'/pitches/drafts';
	let response = await fetch(url);
	if (response.status === 200) {
		let drafts = await response.json();
		populateDrafts(drafts);
		console.log(drafts);
	}
}

function populateDrafts(drafts) {
	let draftSection = document.getElementById('draftSection');
	draftSection.innerHTML = '';
	
	if (drafts.length > 0) {
		let table = document.createElement('table');
		table.id = 'draftTable';
		
		table.innerHTML = `
		<tr>
			<th>ID</th>
			<th>Author Id</th>
			<th>Title</th>
			<th>State</th>
			<th>Contents</th>
			<th>Pitch Id</th>
			<th></th>
			</tr>
		`;
		for (let draft of drafts) {
			let tr = document.createElement('tr');
			tr.innerHTML = `
			<td>${draft.draft_id}</td>
			<td>${draft.pitch.author.usr_id}</td>
			<td>${draft.pitch.title}</td>
			<td>${draft.state}</td>
			<td>${draft.contents}</td>
			<td>${draft.pitch.p_id}`;
			let td = document.createElement('td');
			let ul = document.createElement('ul');
			td.appendChild(ul);
			tr.appendChild(td);
			
			let acceptBtn = document.createElement('button');
			acceptBtn.type='button';
			acceptBtn.id = draft.pitch.title + '_' + draft.draft_id;
			acceptBtn.textContext = 'Approve';
			acceptBtn.disabled = loggedUser.role.name ===('Author');
			
			let btnTd = document.createElement('td');
			btnTd.appendChild(acceptBtn);
			tr.appendChild(btnTd);
			table.appendChild(tr);
			
			acceptBtn.addEventListener('click', acceptBtn)
		}
		draftSection.appendChild(table);
	} else {
		draftSection.innerHTML = 'No drafts are available.';
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
/*	let draftTable = document.getElementById('draftTable');
	for (let tr of draftTable.childNodes) {
		if (tr.nodeName === 'TR') {
			let td = document.createElement('td');*/
/*			if (tr != approvalTable.childNodes.item(0)) {
				let editBtn = document.createElement('button');
				editBtn.id = 'edit_' + tr.childNodes.item(1).textContent;
				editBtn.type= 'button';
				editBtn.textContent = 'Edit';
				editBtn.onclick = editApproval;
/*				td.appendChild(editBtn);*/
			}

		

