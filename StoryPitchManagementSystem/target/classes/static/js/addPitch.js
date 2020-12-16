let pitchSection = document.getElementById('pitchSection');

pitchSection.innerHTML +=`
        <form id="add-pitch-form">
        <label for="authorname">Author Name:</label>
        <input type="text" id="authorname" placeholder="Name" required />
        <label for="title">Working Title:</label>
        <input type="text" id="title" placeholder="Title" required />
        
        <label for="genre">Genre:</label>
        <select id="genre" required>

        </select>
        
        <label for="storytype">Format:</label>
        <select id="storytype" required>

        </select>

        <label for="tagline">Tagline:</label>
        <input type="text" id="tagline" placeholder="Tagline" required />
        <label for="description">Description:</label>
        <input type="text" id="description" placeholder="What is your work about?" required />
        <label for="compldate">Target Completion Date (mmddyy):</label>
        <input type="number" id="compldate" placeholder="010121" required />
        <label for="extrainfo">Extra Info:</label>
        <input type="text" id="extrainfo" placeholder="Anything else you want us to know?" required />

        <button type="button" onclick="addPitch()" id="submit-add-pitch-form" >Submit Pitch</button>
        </form>
        `;
    let submitAddBtn = document.getElementById('submit-add-pitch-form');
    submitAddBtn.onclick = addPitch;
    populateGenres();
    populateStorytypes();

async function addPitch() {
    let pitch = {};
    pitch.id = 0;
    pitch.authorId = loggedUser.id;
    pitch.authorname = document.getElementById('authorname').value;
    pitch.tagline = document.getElementById('tagline').value;
    pitch.description = document.getElementById('description').value;
    pitch.extrainfo = document.getElementById('extrainfo').value;
    pitch.compldate = document.getElementById('compldate').value;
    pitch.genre = {};
    pitch.genre.id = document.getElementById('genre').value;
    pitch.pitchtype = {};
    pitch.pitchtype.id = document.getElementById('storytype').value;
    pitch.votes = 0;
    pitch.priority = 'Low';
    pitch.status = {};
    //calculate story weight of user to determine starting status of pitch
    let refpitches = loggedUser.pitches;
    let weight = 0;
    for (let refpitch of refpitches) {
        weight += refpitch.pitchtype.weight;
    }
    if (weight >= 100) {
        pitch.status.id = 1;
    } else {
        pitch.status.id = 2;
    }

    let url = baseUrl + '/pitches';
    let response = await fetch(url, {method:'POST', body:JSON.stringify(pitch)});
    if (response.status === 201) {
        alert('Pitch submitted successfully.');
    } else {
        alert('Something went wrong.');
    }
}

async function populateGenres() {
    let genreDropDown = document.getElementById('genre');
    let url = baseUrl + '/genres';
    let response = await fetch(url);
    if (response.status === 200) {
        let genres = await response.json();
        for (let genre of genres) {
            let genreOption = document.createElement('option');
            genreOption.value = genre.id;
            genreOption.textContent = genre.name;
            genreDropDown.appendChild(genreOption);
        }
    } else {
        alert('Something went wrong.');
    }
}

async function populateStorytypes() {
    let typeDropDown = document.getElementById('storytype');
    let url = baseUrl + '/storytypes';
    let response = await fetch(url);
    if (response.status === 200) {
        let types = await response.json();
        for (let type of types) {
            let typeOption = document.createElement('option');
            typeOption.value = type.id;
            typeOption.textContent = type.name;
            typeDropDown.appendChild(typeOption);
        }
    } else {
        alert('Something went wrong.');
    }
}