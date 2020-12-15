let baseUrl = 'http://localhost:8080';
let nav = document.getElementById('navBar');
let loggedUser = null;
checkLogin();
setNav();
function setNav() {
    nav.innerHTML = `
            <a href="index.html"><strong>Story Pitch Management System</strong></a>`;
    if (!loggedUser) {
        nav.innerHTML += `
            <form>
                <label for="user">Username: </label>
                <input id="user" name="user" type="text" />
                <label for="pass"> Password: </label>
                <input id="pass" name="pass" type="password" />
                <button type="button" id="loginBtn">Log In</button>
            </form>
        `;
    } else {
    	if(loggedUser.roleID < 4){
            nav.innerHTML += `
	            <div class="dropdown">
            		<button class="dropbtn">Editor Functions</button>
            		<div class="dropdown-content">
            			<a href="viewStories.html">View All Stories</a> <br>
            			<a href="newInfoRequest.html">New Info Request</a> </br>
            			<a href="myInfoResponses.html">My Info Responses</a> </br>
            			<a href="newApproval.html">Approve a Story Pitch</a> </br>
            			<a href="newRejection.html">Reject a Story Pitch</a> </br>
            		</div>
            	</div>
    		`;
            if(loggedUser.roleID == 1){
                nav.innerHTML += `
	            <div class="dropdown">
            		<button class="dropbtn">Senior Editor Functions</button>
            		<div class="dropdown-content">
                		<a href="editStory.html">Edit Story Pitch</a></br>
            		</div>
            	</div>`;
            }
    	}
    	
        nav.innerHTML += `
        	<div class="dropdown">
            		<button class="dropbtn">Author Functions</button>
            		<div class="dropdown-content">
        				<a href="myStories.html">View My Stories</a> </br>
        				<a href="newStory.html">New Story Pitch</a> </br>
        				<a href="resubmitStory.html">Resubmit Story Pitch</a> </br>
        				<a href="changeStory.html">Change Story Pitch</a> </br>
        				<a href="acceptChanges.html">Accept/Reject Editorial Changes</a> </br>
        				<a href="myApprovals.html">My Approvals</a> </br>
        				<a href="myRejections.html">My Rejections</a> </br>
        				<a href="myChangeRequests.html">My Change Requests</a> </br>
        				<a href="myInfoRequests.html">My Info Requests</a> </br>
        				<a href="newInfoResponse.html">Send Info Responses</a> </br>
        				<a href="mySentInfoResponses.html">My Sent Info Responses</a> </br>
            		</div>
			</div>
            <span>
                ${loggedUser.name}&nbsp;
                <button type="button" id="loginBtn">Log Out</button>
            </span>
        `;
    }

    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else loginBtn.onclick = login;
}

async function login() {
    // http://localhost:8080/users?user=sierra&pass=pass
	
    var masterurl = baseUrl + '/users?';
    masterurl += 'user=' + document.getElementById('user').value + '&';
    masterurl += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(masterurl, {method: 'PUT'});
    
    switch (response.status) {
        case 200: // successful
            console.log(response);
            loggedUser = await response.json();
            setNav();
            console.log(loggedUser);
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('pass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            document.getElementById('user').value = '';
            document.getElementById('pass').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function logout() {
    let url = baseUrl + '/users';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedUser = null;
    setNav();
    let tbl = document.getElementById('storySection');
    if(tbl){
    	//console.log("got here");
    	tbl.parentNode.removeChild(tbl);
    }
}

async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();
//	console.log("Checking login - logged user = ")
//	console.log(loggedUser);
    setNav();

 
}