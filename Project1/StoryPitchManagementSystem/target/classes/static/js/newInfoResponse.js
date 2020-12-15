checkLogin().then(populateinfoResponseForm);
var points = null;


function populateinfoResponseForm() {
	console.log(loggedUser);

    let storySection = document.getElementById('storySection');
    let table = document.createElement('table');
    let str = " ";
    table.innerHTML= str;
    let tr0 = document.createElement('tr0');
    tr0.innerHTML = ` <br> <br>
        <tr>
            <th>Info Request ID</th>
		    <td>
				<input type=text id="infoRequestID" size = 50 placeholder = "Enter the ID of the info request to which you are responding" />
			</td>
        </tr>
    `;

    table.appendChild(tr0);	
    let tr1 = document.createElement('tr1');
    tr1.innerHTML =`<br>  
    	<th>Message</th>
		    <td>
				<input type=text id="message" size = 50 placeholder = "Type your response here" />
			</td>`
    	
    table.appendChild(tr1);
    let tr8 = document.createElement('tr8');	   	
	tr8.innerHTML =`  <br>  <br> <br>
		<td>
			<button type="button" id="submitBtn">Submit</button>
		</td>`

	table.appendChild(tr8);
    storySection.appendChild(table);
    submitBtn.onclick = submitInfoResponse;
}


async  function submitInfoResponse() {
		//reformatDate();
	    let url = baseUrl + '/submitInfoResponse?';
	    url += 'infoRequestID=' + document.getElementById('infoRequestID').value + '&';
	    url += 'message=' + document.getElementById('message').value;
	    
	    console.log(url);
	    let response = await fetch(url, {method: 'PUT'});
	    
	    switch (response.status) {
	        case 200: // successful
	            alert("Info Response Successfully Submitted!")
	            setNav();
	            console.log(loggedUser);
	            break;
	        case 404: // user not found
	            alert('That user does not exist.');
	            break;
	        default: // other error
	            alert('Something went wrong.');
	            break;
	    }
}