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

const handleReponseContentChange = (id) => {

    let currentResponseContent = document.getElementById(`input-response-section-${id}`).value; 

    requestMap.set(id, {
        ...requestMap.get(id),
        responseContent: currentResponseContent
    });

    console.log(requestMap.get(id).responseContent); 

}



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


const handleRespond = (id) => {

    let currentResponse = document.getElementById(`request-card-${id}-response`).innerText;
    document.getElementById(`request-card-${id}-response-section`).innerHTML =
        `<textarea 
        class="form-control" 
        id="input-response-section-${id}"
        rows="6"
        value=${requestMap.get(id).responseContent}
        onkeyup=handleReponseContentChange(${id})
        ></textarea>
        <button 
        type="button" class="btn btn-primary"
        onclick='updateRequest(${id})'
        class 
        >
        Save
        </button>
        `;
}


const loadRequestCard = (request) => {

    // Only senders should be able to close requets. So we expose the close function
    // on if the current user is the sender. Dynamic rendering is possible using 
    // template literals and the ternary statement. 
    const requestCardTemplate =
        `          <div id="request-card-${request.id}" class="col-md-3">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">${request.id}</h5>
                <p>From: ${request.senderId === currentUser.id ? 'Me' : request.senderId}</p>
                <p> To: ${request.recieverId === currentUser.id ? 'Me' : request.recieverId} </p>
                <p>Status: ${request.status.name}</p>
                <hr />
                <div>
                  <p class="card-text">Request Content</p>
                  <p>${request.requestContent}</p>
                </div>
                <div id="request-card-${request.id}-response-section">
                  <p class="card-text">Response Content</p>
                  <p id="request-card-${request.id}-response">
                  ${request.responseContent.length > 0 ? request.responseContent : 'No Resposne'}
                  </p>
                </div>
                ${request.senderId === currentUser.id ?
            ` </div>
              <button type="button" class="btn btn-danger" onClick="handleCloseRequest(${request.id})">Close</button>
                 </div>`
            :
            ` </div>
                 <button type="button" class="btn btn-primary" onClick="handleRespond(${request.id})">Respond</button>
                    </div>`
        }
          </div>`;


    if (request.senderId === currentUser.id) {
        document.getElementById("outgoing-requests-display-selection").innerHTML += requestCardTemplate;
    } else if (request.recieverId === currentUser.id) {
        document.getElementById("incoming-requests-display-selection").innerHTML += requestCardTemplate;
    } else {
        alert('currentUser is neither sender or reciever of request');
    }
}


/**
 * Fetch requestes for current user. 
 */
const getRequests = async (id) => {

    let response = await fetchRequests(id);
    if (response.status === 200) {
        console.log(response);
        let requests = JSON.parse(await response.json());
        for (const request of requests) {
            requestMap.set(request.id, request);
            console.log(request);
            loadRequestCard(request);
        }
        //   loadrequestData(); 

    } else {
        alert("could not fetch requests");
    }

}

getRequests(currentUser.id);


/*

    private Integer id;
    private Integer senderId;
    private Integer recieverId;
    private String requestContent;
    private String responseContent;
    private Status status;

*/