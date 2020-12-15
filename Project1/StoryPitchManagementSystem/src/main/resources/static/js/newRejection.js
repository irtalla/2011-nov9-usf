checkLogin().then(populateRejectionForm);
var points = null;


function populateRejectionForm() {
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
				<input type=text id="storyID" size = 50 placeholder = "Enter the ID of the story pitch which you are rejecting" />
			</td>
        </tr>`;

    table.appendChild(tr0);	
    let tr1 = document.createElement('tr1');
    tr1.innerHTML =`<br>  
    	<th>Message</th>
		    <td>
				<input type=text id="message" size = 50 placeholder = "Type the reason for the rejection here" />
			</td>`;
    	
    table.appendChild(tr1);
//    let tr2 = document.createElement('tr2');
//    tr2.innerHTML = ` <br> <br>
//        <tr>
//            <th>Author ID</th>
//		    <td>
//				<input type=text id="personRequestedID" size = 60 placeholder = "Enter the ID of the person from whom you are requesting more info" />
//			</td>
//        </tr>`;
//
//    table.appendChild(tr2);	
    let tr8 = document.createElement('tr8');	   	
	tr8.innerHTML =`  <br>  <br> <br>
		<td>
			<button type="button" id="submitBtn">Submit</button>
		</td>`;

	table.appendChild(tr8);
	    storySection.appendChild(table);
	    submitBtn.onclick = submitRejection;
}


async  function submitRejection() {
		if(document.getElementById('message').value){
		    let url = baseUrl + '/submitRejection?';
		    url += 'storyID=' + document.getElementById('storyID').value + '&';
		    url += 'rejector=' + loggedUser.personID + '&';
		    url += 'message=' + document.getElementById('message').value;
		    
		    console.log(url);
		    let response = await fetch(url, {method: 'PUT'});
		    
		    switch (response.status) {
		        case 200: // successful
		            alert("Rejection processed")
		            setNav();
		            console.log(loggedUser);
		            break;
		        case 400: // person ID or story ID not in database
		            alert('Double check that you have entered a valid story ID');
		            break;
		        case 404: 
		            alert('Page not found.');
		            break;
		        default: // other error
		            alert('Something went wrong.');
		            break;
		    }
		}
		else{
			alert("You must provide a reason for the rejection");
		}
}