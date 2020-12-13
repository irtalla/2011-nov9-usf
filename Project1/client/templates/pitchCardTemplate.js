



const loadModalWithDecisionExplanationPrompt = (pitchId, type) => {

    const remainingChar = 2000; 

    const updateRemainingChar = (event) => remainingChar - event.target.value.length; 

    document.getElementById('modal-dynamic-content-section').innerHTML =
        `<p> 
            You are making a decision of type ${type} for pitch ${pitchId}. 
            Please provide an explanation (${remainingChar} characters remaining).
         <p>
        <div>
        <div>
        <textarea id="explanation-draft-area" class="form-control" rows="8" />
        </textarea>
        </div>
        <div class="btn-group" role="group" aria-label="Basic mixed styles example">
            <button type="button" class="btn btn-primary" onClick="saveDecision(${pitchId}, \'${type}\')" 
            data-dismiss="modal" aria-label="Close" >
            Save
            </button>
            <button type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close" >
            Cancel
            </button>
        </div>
    `;
}

const loadModalWithPitchRequestPrompt = (targetId, targetType) => {

    const remainingChar = 2000; 

    const updateRemainingChar = (event) => remainingChar - event.target.value.length; 
    
  document.getElementById('modal-dynamic-content-section').innerHTML =
  `<p> 
      You are making an informational request for a <strong>${targetType}</strong> with ID: <strong>${targetId}</strong>. 
      Please provide an initial comment for this request (${remainingChar} characters remaining).
   <p>
  <div>
  <div>
  <textarea id="request-draft-area" class="form-control" rows="8" />
  </textarea>
  </div>
  <div class="btn-group" role="group" aria-label="Basic mixed styles example">
      <button type="button" class="btn btn-primary" onClick="postRequestWithInitialComment(${targetId}, \'${targetType}\')" 
      data-dismiss="modal" aria-label="Close" >
      Save
      </button>
      <button type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close" >
      Cancel
      </button>
  </div>
`;


}

const createPitchCard = (pitch) => {


    let controlButtons;
    if (currentUser.role.name.toUpperCase() === 'AUTHOR') {
        controlButtons =
            `<div class="btn-group" role="group" aria-label="Basic mixed styles example">
            <button type="button" class="btn btn-danger" onClick="deletePitch(${pitch.id})">Delete</button>
        </div>`
    } else {
        controlButtons =
            `<div class="btn-group" role="group" aria-label="Basic mixed styles example">
            <button type="button" class="btn btn-success" 
                onClick="loadModalWithDecisionExplanationPrompt(${pitch.id}, \'approval\')"
                data-toggle="modal"
                data-target="#exampleModal"
                >Approve
            </button>
            <button type="button" class="btn btn-warning" 
                onClick="loadModalWithPitchRequestPrompt(${pitch.id}, \'pitch\')"
                data-toggle="modal"
                data-target="#exampleModal"
                >Request Info
            </button>
            <button type="button" class="btn btn-danger" 
                onClick="loadModalWithDecisionExplanationPrompt(${pitch.id}, \'rejection\')"
                data-toggle="modal"
                data-target="#exampleModal"
                >Reject
            </button>
        </div>`
    }



    let generic_image_src = `https://thumbs.dreamstime.com/t/white-feather-quill-pen-glass-inkwell-old-glass-ink-pen-feather-well-quill-image-106467206.jpg`;

    let progressBarColor;
    let progressBarWidth;


    const pitchCard =
        `<div id="pitch-card-${pitch.id}" class="col-md-4">
  <div class="card">
    <img src="${generic_image_src} class="card-img-top" alt="..." />
    <div class="card-body">
      <h5 class="card-title"> ${pitch.title || 'no title found'} </h5>
      <p class="card-text">
        ${pitch.tagline.length > 100 ? `${pitch.tagline.slice(0, 100)}...` : pitch.tagline}
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
      ${controlButtons}
    </div>
  </div>
</div>`;

    return pitchCard;
}