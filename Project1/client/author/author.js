

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
const pitchMap = new Map();

/**
 * Fetch pitches for current user. 
 */
const fetchPitches = async () => {


  let response = await fetch(`http://localhost:4000/api/pitches/authorid/${currentUser.id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    }
  });


  let pitches = JSON.parse(await response.json());

  console.log(typeof (pitches));
  console.log(pitches);

  for (const pitch of pitches) {
    pitchMap.set(pitch.id, pitch)
    loadArticleCard(pitch)
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
    <option>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>
    <option>5</option>
  </select>
</div>
<div class="form-group">
  <label for="exampleFormControlSelect1">Select From</label>
  <select class="form-control" id="input-form-form">
    <option>Article (10 points)</option>
    <option>Short Story (15 points)</option>
    <option>Novella (25 points)</option>
    <option>Novel (50 points)</option>
  </select>
</div>

<div class="form-group">
  <label for="exampleFormControlTextarea1">Tag Line</label>
  <textarea class="form-control" id="input-tagLine-form" rows="4"></textarea>
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
      <h5>Tagline: ${pitch.tagLine} </h5>
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

  const title = document.getElementById('input-title-form').value;
  const genre = document.getElementById('input-genre-form').value;
  const form = document.getElementById('input-form-form').value;
  const tagLine = document.getElementById('input-tagLine-form').value;

  let pitch = {
    id: 0,
    title: title,
    genre: {
      id: -1,
      name: genre
    },
    form: {
      id: -1,
      name: form
    },
    tagLine: tagLine,
    authorId: currentUser.id
  }




  console.log(pitch);

  let response = await fetch('http://localhost:4000/api/pitches', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    },
    body: JSON.stringify(pitch)
  });


  if (response.status === 200) {
    let newPitch = JSON.parse(await response.json());
    pitchMap.set(newPitch.id, newPitch);
    loadArticleCard(newPitch);
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



/**
 * 
 * quick and dirty way to generate html templates:
 * https://stackoverflow.com/questions/18673860/defining-a-html-template-to-append-using-jquery
 * 
 */

const loadArticleCard = (pitch) => {

  //     const Item = ({ url, img, title }) => `
  //     <a href="${url}" class="list-group-item">
  //       <div class="image">
  //         <img src="${img}" />
  //       </div>
  //       <p class="list-group-item-text">${title}</p>
  //     </a>
  //   `;

  let generic_image_src = `https://thumbs.dreamstime.com/t/white-feather-quill-pen-glass-inkwell-old-glass-ink-pen-feather-well-quill-image-106467206.jpg`;

  let progressBarColor;
  let progressBarWidth;


  const articleCard =
    `<div id="pitch-card-${pitch.id}" class="col-md-4">
  <div class="card">
    <img src="${generic_image_src} class="card-img-top" alt="..." />
    <div class="card-body">
      <h5 class="card-title"> ${pitch.title || 'no title found'} </h5>
      <p class="card-text">
        ${pitch.tagLine.length > 100 ? `${pitch.tagLine.slice(0, 100)}...` : pitch.tagLine}
      </p>
    </div>
    <div class="progress">
      <div class="progress-bar bg-warning" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Genre: ${pitch.genre.name || 'genre unspecified'} </li>
      <li class="list-group-item">Form: ${pitch.form.name || 'form unspecified'} </li>
      <li class="list-group-item">Completion Deadline: ${pitch.completionDate || 'completion date unspecified'} </li>
    </ul>
    <div class="card-body">
      <a href="#" class="card-link">Card link</a>
      <button type="button" 
        class="btn btn-primary" 
        data-toggle="modal" 
        data-target="#exampleModal"
        onClick="populateModalWithData(${pitch.id})">
        Expand/Edit
      </button>
      <button type="button" class="btn btn-danger" onClick="deletePitch(${pitch.id})">Delete</button>
    </div>
  </div>
</div>`;

  document.getElementById('main-data-display-row').innerHTML += articleCard;


}
