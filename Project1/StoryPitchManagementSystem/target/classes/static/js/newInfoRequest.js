checkLogin().then(populateinfoRequestForm);
var points = null;


function populateinfoRequestForm() {
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
				<input type=text id="storyID" size = 60 placeholder = "Enter the ID of the story pitch for which you are requesting more info" />
			</td>
        </tr>`;

    table.appendChild(tr0);	
    let tr1 = document.createElement('tr1');
    tr1.innerHTML =`<br>  
    	<th>Message</th>
		    <td>
				<input type=text id="message" size = 60 placeholder = "Type your request here" />
			</td>`;
    	
    table.appendChild(tr1);
    let tr2 = document.createElement('tr2');
    tr2.innerHTML = ` <br> <br>
        <tr>
            <th>Author ID</th>
		    <td>
				<input type=text id="personRequestedID" size = 60 placeholder = "Enter the ID of the person from whom you are requesting more info" />
			</td>
        </tr>`;

    table.appendChild(tr2);	
    let tr8 = document.createElement('tr8');	   	
	tr8.innerHTML =`  <br>  <br> <br>
		<td>
			<button type="button" id="submitBtn">Submit</button>
		</td>`;

	table.appendChild(tr8);
	    storySection.appendChild(table);
	    submitBtn.onclick = submitInfoRequest;
}


async  function submitInfoRequest() {
	    // http://localhost:8080/users?user=sierra&pass=pass
		//reformatDate();
	    let url = baseUrl + '/submitInfoRequest?';
	    url += 'storyID=' + document.getElementById('storyID').value + '&';
	    url += 'personRequestingID=' + loggedUser.personID + '&';
	    url += 'personRequestedID=' + document.getElementById('personRequestedID').value + '&';
	    url += 'message=' + document.getElementById('message').value;
	    
	    console.log(url);
	    let response = await fetch(url, {method: 'PUT'});
	    
	    switch (response.status) {
	        case 200: // successful
	            alert("Info Request Successfully Submitted!")
	            setNav();
	            console.log(loggedUser);
	            break;
	        case 400: // person ID or story ID not in database
	            alert('Double check that you have entered a valid story ID and person ID');
	            break;
	        case 404: 
	            alert('Page not found.');
	            break;
	        default: // other error
	            alert('Something went wrong.');
	            break;
	    }
}