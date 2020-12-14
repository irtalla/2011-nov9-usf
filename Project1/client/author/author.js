

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


/*

create table pitch (
	id serial primary key,
	author_id integer references person,
	title varchar(40),
	tagline varchar(1000),
	status_id integer references status,
	genre_id integer references genre,
	form_id integer references form, 
	general_editor_id integer references person,
	priority_lvl_id integer references priority,
	stage_id integer references stage,
	deadline TIMESTAMP, 
	createdTime TIMESTAMP, 
	lastModifiedTime TIMESTAMP
);


*/






/**
 * Fetch pitches for current user. 
 */
const fetchPitches = async () => {

    let response = await fetchPitchesByAuthorId(currentUser.id); 
    let pitches = JSON.parse(await response.json());
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





const createSubmitPitchModalTemplate =
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


const handlePitchContentChange = (id) => {
  pitchMap.set(id, {
      ...pitchMap.get(id),
      [event.target.name]: event.target.value
  });

  console.log( pitchMap.get(id)[event.target.name] );
}

const updatePitch = async (id) => {

  let updatedPitch = pitchMap.get(id); 
  let response = await putPitch(updatedPitch);
  if (response.status === 200) {
    alert("update successful"); 
    document.getElementById('main-data-display-row').
      removeChild( document.getElementById(`pitch-card-${id}`) ); 
    document.getElementById('main-data-display-row').innerHTML += createPitchCard(updatedPitch);
  } else {
    alert("Internal system error: could not update pitch");
  }
}


/**
 * Callback method to populate modal with data. The id parameter specifies 
 * Is used to get get the corresponding pitch data from a hashmap. If id is default 
 * value of null, this indicates that a user wants to create a new pitch, and none
 * of the input values will be populated. 
 */
const populateModalWithData = async (id = null) => {

    if (id !== null) {
        document.getElementById("pitch-modal-body").innerHTML = await createPitchModalCard( pitchMap.get(id) );
        document.getElementById("modal-btn-section").innerHTML = `
          <button type="button" class="btn btn-warning" onClick="updatePitch(${id})">Update</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          `;
    } else {
        document.getElementById("pitch-modal-body").innerHTML = createSubmitPitchModalTemplate;
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

const saveComment = async (id, selectorString) => {

  const newCommentContent = document.getElementById(selectorString).value;
  if (newCommentContent.length === 0) {
      alert('Cannot save empty comment');
      return;
  }
  const newComment = {
      requestId: id,
      commentorId: currentUser.id,
      content: newCommentContent
  };

  let response = await postComment(newComment);

  if (response.status === 200) {
      const comment = JSON.parse(await response.json());
      document.getElementById(`request-card-${comment.requestId}-comment-section`)
          .innerHTML += createCommentCard(comment);

      /**
       * This block sometimes fails if a new request card is being added. However, it's
       * a benign failure, so we will wrap it in a try-catch block and print the error
       */
      try {
          document.getElementById(`request-card-${comment.requestId}-comment-section`).
              removeChild(document.getElementById(`draft-comment-response-section-${comment.requestId}`));
      } catch (e) {
          console.warn(e);
      }

  } else {
      alert("Internal System error: Unable to save comment");
      console.log(resposne);
  }

}

const handleRespond = (id) => {
    document.getElementById(`request-card-${id}-comment-section`).innerHTML +=
        `<div id="draft-comment-response-section-${id}">
        <textarea 
        class="form-control" 
        id="section-${id}-draft-comment"
        rows="4"
        ></textarea>
        <button 
        type="button" class="btn btn-primary"
        onclick='saveComment(${id}, \`section-${id}-draft-comment\`)'
        class 
        >
        Save
        </button>
        `;
}
/**
 * 
 * @param {*} id The id of the current user
 */
const getRequests = async (id) => {

  let response = await fetchRequests(id);
  if (response.status === 200) {
      let requests = JSON.parse(await response.json());
      console.log(requests);
      // This removes the spinners. Maybe we should check and remove in loadRequests? 
      document.getElementById("outgoing-requests-display-selection").innerHTML = "";
      document.getElementById("incoming-requests-display-selection").innerHTML = "";
      for (const request of requests) {
          requestMap.set(request.id, request);
          await createRequestCard(request);
          let response = await fetchCommentsByRequestId(request.id);
          if (response.status == 200) {
              let comments = JSON.parse(await response.json());
              for (let comment of comments) {
                  commentMap.set(comment.id, comment);
                  document.getElementById(`request-card-${comment.requestId}-comment-section`)
                      .innerHTML += createCommentCard(comment);
              }
          } else {
              alert(`unable to load comments for request ${request.id}`);
          }

      }

  } else {
      alert("could not fetch requests");
  }
}

getRequests(currentUser.id); 