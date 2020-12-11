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


/*
 onload, we want to fetch ALL open requests associated with the user. 
*/

/**
 * Dictionary that stores all the current user's
 * request. The key is request id, the value is the request object 
 */
const requestMap = new Map();
const pitchMap = new Map();
const commentMap = new Map();
const personMap = new Map();
const decisionMap = new Map();


const handleCloseRequest = async (id) => {

    let response = await closeRequest(id);

    if (response.status === 200) {

        // Remove from outgoing requests if currentUser is sender, 
        // otherwise remove from incoming requests
        if (requestMap.get(id).senderId === currentUser.id) {
            document.getElementById('outgoing-requests-display-selection').
                removeChild(document.getElementById(`request-card-${id}`));
        } else {
            document.getElementById('incoming-requests-display-selection').
                removeChild(document.getElementById(`request-card-${id}`));
        }
        requestMap.delete(id);

    } else {
        alert('Technical difficulties: unable to close request');
    }
};


const updateRequest = async (id) => {

    let response = await putUpdatedRequest(requestMap.get(id));

    if (response.status === 200) {
        alert("Udpated Request recieved by system. We'll let you know if there are any updates!");
        document.getElementById('incoming-requests-display-selection').
            removeChild(document.getElementById(`request-card-${id}`));
        requestMap.delete(id);
    } else {
        alert("Could not update request");
    }
}


// const handleReponseContentChange = (id) => {

//     let currentResponseContent = document.getElementById(`input-response-section-${id}`).value;

//     requestMap.set(id, {
//         ...requestMap.get(id),
//         responseContent: currentResponseContent
//     });

//     console.log(requestMap.get(id).responseContent);

// }

const saveComment = async (id) => {

    const newCommentContent = document.getElementById(`section-${id}-draft-comment`).value; 
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

    if (response.status === 200 ) {
        const comment = JSON.parse( await response.json() );
        document.getElementById(`request-card-${comment.requestId}-comment-section`)
        .innerHTML += createCommentCard(comment);
        document.getElementById(`request-card-${comment.requestId}-comment-section`).
        removeChild(document.getElementById(`draft-comment-response-section-${comment.requestId}`));
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
        onclick='saveComment(${id})'
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
        console.log(response);
        let requests = JSON.parse(await response.json());
        // This removes the spinners. Maybe we should check and remove in loadRequests? 
        document.getElementById("outgoing-requests-display-selection").innerHTML = "";
        document.getElementById("incoming-requests-display-selection").innerHTML = "";
        for (const request of requests) {
            requestMap.set(request.id, request);
            await loadRequestCard(request);
            let response = await fetchCommentsByRequestId(request.id);
            if (response.status == 200) {
                let comments = JSON.parse ( await response.json() ); 
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

const getPitchesByGenreAndGeneralEditor = async () => {

    let response = await fetchPitchesByGeneralEditorId(currentUser.id);
    if (response.status === 200) {
        let pitches = JSON.parse(await response.json());
        for (const pitch of pitches) {
            pitchMap.set(pitch.id, pitch); 
            document.getElementById("general-pitch-data-display-row").innerHTML += createPitchCard(pitch);
        }
    } else {
        alert("Internal system error: could not load pitches by general id");
    }

    for (const genre of currentUser.genres) {
        let response = await fetchPitchesByGenre(genre.name);
        if (response.status === 200) {
            let pitches = JSON.parse(await response.json());
            for (const pitch of pitches) {
                pitchMap.set(pitch.id, pitch); 
                document.getElementById("genre-pitch-data-display-row").innerHTML += createPitchCard(pitch);
            }
        } else {
            alert("Internal system error: could not load pitches by genre");
        }
    }
}
getPitchesByGenreAndGeneralEditor(); 