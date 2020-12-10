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