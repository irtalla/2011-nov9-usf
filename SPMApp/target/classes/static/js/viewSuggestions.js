let addSuggestionMenuOpen = true;
setup();

function setup() {
		getSuggestions().then(() => {
			console.log(loggedUser);
			checkLogin().then(() => {
			editorSetup();
		});
	});
}
async function getSuggestions() {
	let url = baseUrl +'/pitches/suggestions/';
	let response = await fetch(url);
	if (response.status === 200) {
		let suggestions = await response.json();
		populateSuggestions(suggestions);
		console.log(suggestions);
	}
}

function populateSuggestions(suggestions) {
	let suggestSection = document.getElementById('suggestSection');
	suggestSection.innerHTML = '';
	
	if (suggestions.length > 0) {
		let table = document.createElement('table');
		table.id = 'suggestionTable';
		
		table.innerHTML = `
		<tr>
			<th> ID </th>
			<th> Pitch Id </th>
			<th> Content </th>
			<th></th>
			</tr>;
		`
		
		for (let suggestion of suggestions) {
			let tr = document.createElement('tr');
			tr.innerHTML = `
			<td>${suggestion.suggestion_id}</td>
			<td>${suggestion.pitch.p_id}</td>
			<td>${suggestion.contents}</td>
			<th></th>
			</tr>`;
			window.pitchID = suggestion.pitch.p_id;
			let td = document.createElement('td');
			let ul = document.createElement('ul');
			td.appendChild(ul);
			tr.appendChild(td);
			
			table.appendChild(tr);
		}
		
		suggestSection.appendChild(table);
	} else {
		suggestSection.innerHTML = 'No suggestions are available.';
	}
}
function addSuggestionMenu() {
	let editorSpan = document.getElementById('editor');
	addSugestionMenuOpen = !AddSuggestionMenuOpen;
	
	if (addSuggestionMenuOpen) {
		editorSpan.innerHTML += `<form id = "add-suggestion-form">
		<label for="Pitch Id">Pitch Id: </label>
		<input type="text" id="pitchid" placeholder="1" required />
		
		<label for="contents">Contents of suggestion:</label>
		<input type="text" id="contents" placeholder="contents" required</label>
		<button type="button" onclick="addSuggestion()" id="submit-add-suggestion-form">Submit</button>
		</form>
		`;
		populateSuggestions();
		let submitAddBtn = document.getElemetById('submit-add-suggestion-form');
		submitAddBtn.onclick=addSuggestion;
	} else {
		editorSpan.removeChild(document.getElementById('add-suggestion-form'));
	}
	let addSuggestionBtn = document.getElementById('add-suggestion-form');
	addSuggestionBtn.onclick = addSuggestionMenu
}
function editorSetup() {
	console.log("Setup reached");
	let editorSpan = document.getElementById('editor');
	editorSpan.removeAttribute('hidden');
	if (!(document.getElementById('addSuggestionBtn'))) {
		let addSuggestionBtn = document.createElement('button')
		addSuggestionBtn.type = 'button';
		addSuggestionBtn.textContext = 'Add suggestion';
		addSuggestionBtn.id = 'addSuggestionBtn';
		addSuggestionBtn.onclick = addSuggestionMenu;
		suggestionSpan.appendChild(addSuggestionBtn);
	}
}
async function addSuggestion() {
	let suggestion = {};
	suggestion.pitch = {};
	suggestion.pitch.pitch_id = document.getElementById('pitchid').value;
	suggestion.contents = document.getElementById('contents').value;
	
	let url = baseUrl + `/pitches/suggestions/`;
	let response = await fetch(url, {method:'POST', body:JSON.stringify(suggestion)});
	if (response.status === 201) {
	alert('added Suggestion successfully.');
	} else {
		alert('Something went wrong.');
	}
	addSuggestionMenu();
	setup();
}