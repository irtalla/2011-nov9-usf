

/**
 * This section gets the currentUser object from local broswer storage
 */

if (!sessionStorage.getItem("currentUser")) {
    populateStorage();
}
let currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
console.log(typeof (currentUser));
console.log(currentUser);

document.getElementById('main-header').innerText = `Welcome Back, ${currentUser.username}!`;

const logCurrentUser = () => {
    console.log(currentUser);
}




/**
 * Dictionary that stores all the current user's
 * pitches. The key is pitch id, the value is the pitch object 
 */
const requestMap = new Map();
const pitchMap = new Map();
const commentMap = new Map(); 
const personMap = new Map(); 
const decisionMap = new Map(); 

/**
 * Fetch pitches for current user. 
 */
const fetchPitches = async () => {

    let response = await fetchPitchesByAuthorId(currentUser.id); 
    let pitches = JSON.parse(await response.json());

    console.log(typeof (pitches));
    console.log(pitches);

    for (const pitch of pitches) {
        pitchMap.set(pitch.id, pitch) 
        document.getElementById('main-data-display-row').innerHTML += createPitchCard(pitch);
    }

    loadPitchData();
}

const loadPitchData = () => {


    let pitchStatistcs = `
    <li class="list-group-item">
      <strong>Ptich Statistics</strong>
    </li> 
      <li class="list-group-item list-group-item-info">
      Total Pitches: ${pitchMap.size}
    </li>
    <li class="list-group-item list-group-item-warning">
      Total Pitches under Genre Review: 0
    </li>
    <li class="list-group-item list-group-item-warning">
      Total Pitches under General Review: 0
    </li>
    <li class="list-group-item list-group-item-warning">
      Total Pitches under Senior Review: 0
    </li>
    <li class="list-group-item list-group-item-success">
      Total Pitches Accepted: 0
    </li>
    <li class="list-group-item list-group-item-danger">
      Total Pitches Rejected: 0
    </li>`;

    document.getElementById('pitch-data-loading-zone').innerHTML = pitchStatistcs;

};


fetchPitches();





const createPitchModalTemplate =
    `<form>
<div class="form-group">
  <label for="exampleFormControlInput1">Title</label>
  <input type="text" class="form-control" id="input-title-form" placeholder="Pick something catchy!">
</div>
<div class="form-group">
  <label for="exampleFormControlSelect1">Select Genre</label>
  <select class="form-control" id="input-genre-form">
    <option>poetry</option>
    <option>drama</option>
    <option>literature</option>
    <option>non-fiction</option>
    <option>crime</option>
  </select>
</div>
<div class="form-group">
  <label for="exampleFormControlSelect1">Select From</label>
  <select class="form-control" id="input-form-form">
    <option>Article</option>
    <option>Short Story</option>
    <option>Novella</option>
    <option>Novel</option>
  </select>
</div>

<div class="form-group">
  <label for="exampleFormControlTextarea1">Tag Line</label>
  <textarea class="form-control" id="input-tagline-form" rows="4"></textarea>
</div>
</form>
`;


/**
 * Callback method to populate modal with data. The id parameter specifies 
 * Is used to get get the corresponding pitch data from a hashmap. If id is default 
 * value of null, this indicates that a user wants to create a new pitch, and none
 * of the input values will be populated. 
 */
const populateModalWithData = (id = null) => {

    if (id) {
        const pitch = pitchMap.get(id);
        document.getElementById("pitch-modal-body").innerHTML = `
      <h3>Title: ${pitch.title} </h3>
      <h5>tagline: ${pitch.tagline} </h5>
      <h5>Genre: ${pitch.genre.name} </h5>
      <h5>Form: ${pitch.form.name} </h5>`;

        document.getElementById("modal-btn-section").innerHTML = `
      <button type="button" class="btn btn-success">Edit</button>
      <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      <button type="button" class="btn btn-primary">Save changes</button>
      `;
    } else {
        document.getElementById("pitch-modal-body").innerHTML = createPitchModalTemplate;
        document.getElementById("modal-btn-section").innerHTML = `
    <button type="button" class="btn btn-success" data-dismiss="modal" onClick="savePitch()">Save</button>
      <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
      `;

    }

};

const savePitch = async () => {

    let pitch = {
        id: 0,
        authorId: currentUser.id,
        tagline: document.getElementById('input-tagline-form').value,
        title: document.getElementById('input-title-form').value,
        genre: { id: 1, name: document.getElementById('input-genre-form').value },
        form: { id: 1, name: document.getElementById('input-form-form').value }
    }
    console.log(pitch);

    let response = await postPitch(pitch);


    if (response.status === 200) {
        let newPitch = JSON.parse(await response.json());
        pitchMap.set(newPitch.id, newPitch);
        createPitchCard(newPitch);
        document.getElementById('main-data-display-row').innerHTML += pitchCard;
        loadPitchData();
        alert("Save successful. Check out your new submission below. Good luck!")
    } else {
        alert("Something went wrong, unable to add pitch");
    }

}

const deletePitch = async (id) => {
    alert(`attempting to delete pitch: ${id}`);

    let response = await fetch(`http://localhost:4000/api/pitches/${id}`, {
        method: 'DELETE',
    });

    if (response.status === 200) {
        alert("Delete successful. Trash belongs in the trash!");
        document.getElementById('main-data-display-row').removeChild(
            document.getElementById(`pitch-card-${id}`)
        );
        pitchMap.delete(id);
        loadPitchData();
    } else {
        alert("Something went wrong, unable to delete pitch");
    }


}

