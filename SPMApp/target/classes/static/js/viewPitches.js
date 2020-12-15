

let addPitchMenuOpen = true;
setup();

function setup() {
		getPitches().then(() => {
			console.log(loggedUser);
			checkLogin().then(() => {
				if (loggedUser) Setup();
				console.log(loggedUser.role.id);
		});
	});
}

async function getPitches() {
	let url = baseUrl + '/pitches/';
	let response = await fetch(url);
	if (response.status === 200) {
		let pitches = await response.json();
		populatePitches(pitches);
		console.log(pitches);
	}
}

function populatePitches(pitches) {
	let pitchSection = document.getElementById('pitchSection');
	pitchSection.innerHTML = '';
	
	if (pitches.length > 0) {
		let table = document.createElement('table');
		table.id = 'pitchTable';
		
		table.innerHTML = `
			<tr>
				<th>ID</th>
				<th>Author Id</th>
				<th>Title</th>
				<th>Description</th>
				<th>Story type</th>
				<th>Priority</th>
				<th>Genre</th>
				<th>Status</th>
				<th>Author Info</th>
				<th>Genre Approval</th>
				<th>Editor Approval</th>
				<th>Assistant approval</th>
				<th>Suggestion</th>
				</tr>`;
				
				for (let pitch of pitches) {
					let tr = document.createElement('tr');
					tr.innerHTML = `
					<td>${pitch.p_id}</td>
					<td>${pitch.author.usr_id}</td>
					<td>${pitch.title}</td>
					<td>${pitch.description}</td>
					<td>${pitch.st.typename}</td>
					<td>${pitch.priority}</td>
					<td>${pitch.genre.name}</td>
					<td>${pitch.status.status_name}</td>
					<td>${pitch.ainfo}</td>
					<td>${pitch.suggestion}</td>
					`;
					window.pitchgenre = pitch.genre;
					window.pitchauthor = pitch.author;
					let td = document.createElement('td');
					let ul = document.createElement('ul');
					td.appendChild(ul);
					tr.appendChild(td);
					
					let approveBtn = document.createElement('button');
					approveBtn.type='button';
					approveBtn.id = pitch.title + '_' + pitch.p_id;
					approveBtn.textContent = 'Approve';
					approveBtn.disabled = loggedUser.role.id < 4;
					
					let btnTd = document.createElement('td');
					btnTd.appendChild(approveBtn);
					tr.appendChild(btnTd);
					table.appendChild(tr);
					
					approveBtn.addEventListener('click', approvePitch)
				}
				
				pitchSection.appendChild(table);
			} else {
				pitchSection.innerHTML = 'No pitches are available.';
			}
}

async function approvePitch() {
	let btnId = event.target.id;
	console.log(btnId);
	let index = btnId.indexOf('_');
	let id = btnId.slice(index+1);
	let name = btnId.replace('_', '');
	let approval = {};
	approval.pitch= {};
	approval.pitch.p_id = id;
	approval.genre = pitchgenre;
	approval.suggestion = '';
	if (confirm('You want to approve' + name + '?')) {
		let url = baseUrl + '/pitches/approve/';
		let response = await fetch(url, {method:'POST', body:JSON.stringify(approval)});
		switch (response.status)	{
			case 201:
			alert('You approved ' + name + '!');
			break;
			case 409:
			alert('That pitch doesn\`t seem to exist...');
			break;
			case 401:
			alert('Hold on, you\`re not logged in!');
			break;
			default:
			alert('Something went wrong.');
			break;
		}
	}
}

function Setup() {
	console.log("Setup reached")
	let authorSpan = document.getElementById('editor'); // Authors and Editors respond to each other via the suggestion field.
	authorSpan.removeAttribute('hidden');
	if (!(document.getElementById('addPitchBtn'))) {
		let addPitchBtn = document.createElement('button')
		addPitchBtn.type = 'button';
		addPitchBtn.textContent = 'Add pitch';
		addPitchBtn.id = 'addPitchBtn';
		addPitchBtn.onclick = addPitchMenu;
		authorSpan.appendChild(addPitchBtn);
	}
	
	let pitchTable = document.getElementById('pitchTable');
	for (let tr of pitchTable.childNodes) {
		if (tr.nodeName === 'TR') {
			let td = document.createElement('td');
			if (tr != pitchTable.childNodes.item(0)) {
				let editBtn = document.createElement('button');
				editBtn.id = 'edit_' + tr.childNodes.item(1).textContent;
				editBtn.type= 'button';
				editBtn.textContent = 'Edit';
				editBtn.onclick = editPitch;
				td.appendChild(editBtn);
			}
			tr.appendChild(td);
		}
	}
}

function addPitchMenu() {
	let authorSpan = document.getElementById('editor');
	addPitchMenuOpen = !addPitchMenuOpen;
	console.log('add pitch menu open? ' + addPitchMenuOpen);
	
	if (addPitchMenuOpen) {
		authorSpan.innerHTML += `<form id="add-pitch-form">
		<label for="title">Title:</label>
		<input type="text" id="title" placeholder="Title" required />
		
		<label for="story_type">Story type id:</label>
		<input type="number" id="type" placeholder="Id" required />
		
		<label for="description">Description:</label>
		<input type="text" id="description" placeholder="description" required</label>
		
		<label for="authinfo">Author Information:</label>
		<input type="text" id="authinfo" placeholder="Author Info" required</label>
		<label for="genre">Genre: placeholder="Fantasy"</label>
		<select id="genre" required>
		
		</select>
		
		<button type="button" onclick="addPitch()" id="submit-add-pitch-form" >Submit</button>
		</form>`;
		populateGenres();
		populateTypes();
		let submitAddBtn = document.getElementById('submit-add-pitch-form');
		submitAddBtn.onclick=addPitch;
	} else {
		authorSpan.removeChild(document.getElementById('add-pitch-form'));
	}
	let addPitchBtn = document.getElementById('addPitchBtn');
	addPitchBtn.onclick = addPitchMenu;
}
function editPitch() {
	let editBtn = event.target;
	let editId = event.target.id;
	let editTd = editBtn.parentElement;
	let editTr = editTd.parentElement;
	
	let nodes = editTr.childNodes;
	
	editTr.innerHTML = `
		<td>${nodes.item(1).innerHTML}</td>
		<td><input id = "ePTitle" type = "text" value = ${nodes.item(3).innerHTML}></td>
		<td><input id = "ePDescription" type = "text" value = ${nodes.item(5).innerHTML}></td>
		<td>${nodes.item(7).innerHTML}</td>
		<td><input id = "ePSuggestion" type = "text" value = ${nodes.item(12).innerHTML}></td>
		<td><button disabled = 'true'>Accept</button>
		<button id = ${editId}>Save</button></td>
		`;
		editBtn = document.getElementById(editId);
		editBtn.addEventListener('click', savePitch);
		
}

async function savePitch()
{
	let btnId = event.target.id;
	let index = btnId.indexOf('_');
	let id = btnId.slice(index+1);
	
	let url = baseUrl + '/pitches/' + id;
	
	let response = await fetch(url);
	
	let pitch = await response.json();
	
	pitch.title = document.getElementById('ePTitle').value;
	pitch.description = document.getElementById('ePDescription').value;
	pitch.suggestion = document.getElementById('ePSuggestion').value;
	console.log(pitch);
	
	let newResponse = await fetch(url,{method:'PUT',body:JSON.stringify(pitch)});
	if (newResponse.status === 200) {
		alert('Updated successfully.');
	} else {
		alert('Something went wrong.');
	}
	
	setup();
}
async function populateTypes() {
	let typeDropDown = document.getElementById('type');
	let url = baseUrl +'/types';
	let response = await fetch(url);
	if (response.status === 200) {
		let types = await response.json();
		for (let type of types) {
			let typeOption = document.createElement('option');
			typeOption.value = type.type_id;
			typeOption.textContent = type.type_name;
			typeDropDown.appendChild(typeOption);
		}
	} else {
		alert('Something went wrong');
		addPitchMenuOption = true;
		addPitchMenu();
	}
}
// need to implement populate Genres
async function populateGenres() {
	let genreDropDown = document.getElementById('genre');
	let url = baseUrl + '/genres';
	let response = await fetch(url);
	if (response.status === 200) {
		let genres = await response.json();
		for (let genre of genres) {
			let genreOption = document.createElement('option');
			genreOption.value = genre.genres_id
			genreOption.textContent = genre.name;
			genreDropDown.appendChild(genreOption);
		}
	} else {
		alert('Something went wrong');
		addPitchMenuOption = true;
		addPitchMenu();
	}
	
}

async function addPitch() {
	let pitch = {};
	pitch.author={};
	pitch.author.usr_id = loggedUser.usr_id;
	pitch.title = document.getElementById('title').value;
	pitch.description = document.getElementById('description').value;
	pitch.priority = {};
	pitch.priority.level_id = "1";
	pitch.priority.p_level = 'standard';
	pitch.status = {};
	pitch.status.status_id = "1";
	pitch.st = {};
	pitch.st.type_id = document.getElementById('type').value;
	pitch.genre={};
	pitch.genre.genres_id = document.getElementById('genre').value;
	pitch.authinfo = document.getElementById('authinfo').value;
/*	pitch.editor_approval = "pending";
	pitch._assistant_approval = "pending";
	pitch.genre_approval = "pending";*/

	
	let url = baseUrl + '/pitches/';
	let response = await fetch(url, {method:'POST', body:JSON.stringify(pitch)});
	if (response.status === 201) {
		alert('Added Pitch successfully.');
	} else {
		alert('Something went wrong.');
	}
	addPitchMenu();
	setup();
}