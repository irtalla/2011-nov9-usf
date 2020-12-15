checkLogin().then(populateApprovalForm);
var points = null;


function populateApprovalForm() {
	console.log(loggedUser);

    let storySection = document.getElementById('storySection');
    let table = document.createElement('table');
    let str = " ";
    table.innerHTML= str;
    let tr0 = document.createElement('tr0');
    tr0.innerHTML = ` <br> <br>
        <tr>
            <th>Story ID</th>
		    <td>
				<input type=text id="storyID" size = 40 placeholder = "Enter the ID of the story pitch you wish to approve" />
			</td>
        </tr>`;

    table.appendChild(tr0);	
    let tr1 = document.createElement('tr1');
    if(loggedUser.roleID == 1){
	    tr1.innerHTML =`<br>  
	    	<th>Status approved for:</th>
			    <td>
					<select name="Status" id="statusID" 
						<br>
						<option value=2>Approved by Assistant Genre Editor</option>
						<option value=3>Approved by General Editor</option>
						<option value=6>Approved by Senior Genre Editor</option>
						<option value=7>Approved by Genre Comittee Member</option>
					<select/>
				</td>`;
    }
    else{
    	tr1.innerHTML =`<br>  
	    	<th>Status approved for:</th>
			    <td>
					<select name="Status" id="statusID" 
						<br>
						<option value=2>Approved by Assistant Genre Editor</option>
						<option value=3>Approved by General Editor</option>
						<option value=7>Approved by Genre Comittee Member</option>
					<select/>
				</td>`;
    }
    table.appendChild(tr1);
    let tr8 = document.createElement('tr8');	   	
	tr8.innerHTML =`  <br>  <br> <br>
		<td>
			<button type="button" id="submitBtn">Submit</button>
		</td>`;

	table.appendChild(tr8);
	    storySection.appendChild(table);
	    submitBtn.onclick = submitApproval;
}


async  function submitApproval() {
	let baseUrl = 'http://localhost:8080';
	let storyurl = baseUrl + '/getStory?storyID=' + document.getElementById('storyID').value;
	let storyResponse = await fetch(storyurl, {method: 'GET'});
	if(storyResponse.status == 200){
		let story = await storyResponse.json();
		console.log(story);
		let gid = story.genre.genreID;
		let correctGenre = false;
			if(document.getElementById('statusID').value != 3){
				console.log(document.getElementById('statusID').value);
				 let url = baseUrl + '/getGenreSpecializations?';
				    url += 'genreSpecializationID=' + gid;
					let response = await fetch(url, {method: 'GET'});
					if(response.status == 200){
						console.log("200");
						let gspecs = await response.json();
						console.log(gspecs);
						for(let j = 0; j < gspecs.length; j++){
							if(gspecs[j].personID == loggedUser.personID){
								correctGenre = true;
							}
						}
						if(correctGenre == false){
							alert("You do not specialize in the this story's genre")
						}
					}
			}

			console.log(correctGenre);
			if(document.getElementById('statusID').value == 3){
				correctGenre = true;
				 let url = baseUrl + '/getGenreSpecializations?';
				    url += 'genreSpecializationID=' + gid;
					let response = await fetch(url, {method: 'GET'});
					if(response.status == 200){
						let gspecs = await response.json();
						console.log(gspecs);
						for(let j = 0; j < gspecs.length; j++){
							if(gspecs[j].personID == loggedUser.personID){
								correctGenre = false;
								alert("You specialize in this genre but the story must be approved by an editor who does not");
							}
						}
					}
			}
			if(correctGenre){
			    let url = baseUrl + '/submitApproval?';
			    url += 'storyID=' + document.getElementById('storyID').value + '&';
			    url += 'statusID=' + document.getElementById('statusID').value + '&';
			    url += 'approverID=' + loggedUser.personID;
			    
			    console.log(url);
			    let response = await fetch(url, {method: 'PUT'});
			    
			    switch (response.status) {
			        case 200: // successful
			            alert("Approval Successfully Submitted!")
			            setNav();
			            console.log(loggedUser);
			            break;
			        case 400: // person ID or story ID not in database
			            alert('Double check that you have entered a valid story ID and Status ID');
			            break;
			        case 404: 
			            alert('Page not found.');
			            break;
			        default: // other error
			            alert('Something went wrong.');
			            break;
			    }
			}
	}
	else{
		alert("Enter a valid story ID");
	}
}