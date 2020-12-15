checkLogin().then(populateStoryForm);
var points = null;


function populateStoryForm() {
	console.log(loggedUser);
	points = loggedUser.points;

    let storySection = document.getElementById('storySection');
    let table = document.createElement('table');
    let str = "<br> You have: " + points + " submission points. Novels require 50, novellas 25, short stories 20 and articles 10";
    table.innerHTML= str;
    let tr0 = document.createElement('tr0');
    tr0.innerHTML = ` <br> <br>
        <tr>
            <th>Type</th>
		    <td>
				<select name="Type" id="typeID">
				  <option value=1>Novel</option>
				  <option value=2>Novella</option>
				  <option value=3>Short Story</option>
				  <option value=4>Article</option>
				</select>
			</td>
        </tr>
    `;

    table.appendChild(tr0);	
    let tr1 = document.createElement('tr1');
    tr1.innerHTML =`<br>  
    	<th>Genre</th>
	    <td>
			<select name="Genre" id="genreID">
			  <option value=1>History</option>
			  <option value=2>Biography</option>
			  <option value=3>Science and Technology</option>
			  <option value=5>Poetry</option>
			  <option value=6>Non-Fiction</option>
			  <option value=7>Contemporary Fiction</option>
			  <option value=8>Historical Fiction</option>
			  <option value=9>Juvenile Fiction</option>
			  <option value=10>Science Fiction/Fantasy</option>
			  <option value=11>Mystery</option>
			  <option value=12>Thriller</option>
			  <option value=13>Horror</option>
			</select>
		</td>`
    	
    table.appendChild(tr1);	
    let tr2 = document.createElement('tr2');	
    tr2.innerHTML =`<br>  
           	<th>Title</th>
           	<td>
    			<input type="text" id="titleInput" placeholder="Type your title here" />
    		</td>`
           
    table.appendChild(tr2);	
    let tr3 = document.createElement('tr3');	
    tr3.innerHTML =`    <br>     
            <th>Tagline</th>
            	<td>
    				<input type="text" id="taglineInput" placeholder="Type your tagline here" />
    			</td>`
    	
	table.appendChild(tr3);	
	let tr5 = document.createElement('tr5');	   	
	tr5.innerHTML =`<br>  
            <th>Description</th>
			<td>
    			<input type="text" id="detailedDescriptionInput"  size = 33 placeholder="Enter additional story details here" />
			</td>`
            
	table.appendChild(tr5);	
	let tr6 = document.createElement('tr6');	   	
	tr6.innerHTML =` <br>      
            <th>Tentative Completion Date (YYYY-MM-DD)</th>
			<td>
    			<input type="text" id="dateInput"  size = 33 placeholder="Enter anticipated completion date here" />
			</td>`
        
	table.appendChild(tr6);	
	let tr7 = document.createElement('tr7');	   	
	tr7.innerHTML =`  <br>  
			<th>Draft</th>
			<td>
		    	<input type="text" id="draftInput" placeholder="Paste your draft here" />
			</td>
	`
    
    table.appendChild(tr7);
	
	let tr8 = document.createElement('tr8');	   	
	tr8.innerHTML =`  <br>  <br> <br>
			<td>
				<button type="button" id="submitBtn">Submit</button>
			</td>
	`
    
    table.appendChild(tr8);
	
    storySection.appendChild(table);
    submitBtn.onclick = submitStoryPitch;
}

function reformatDate(){
	let d = document.getElementById('dateInput').value;
	console.log("Inside the test function");
    console.log(d);
    console.log(Date.parse(d));
    let cdate = Date.parse(d);
    console.log(typeof cdate);
    let stories = loggedUser.storyPitches;
    console.log(stories[0].completionDate);
    return cdate;
}

async  function submitStoryPitch() {
	if(document.getElementById('dateInput').value){
	    // http://localhost:8080/users?user=sierra&pass=pass
		//reformatDate();
	    let url = baseUrl + '/submitStory?';
	    url += 'typeID=' + document.getElementById('typeID').value + '&';
	    url += 'genreID=' + document.getElementById('genreID').value + '&';
	    url += 'title=' + document.getElementById('titleInput').value + '&';
	    url += 'tagline=' + document.getElementById('taglineInput').value + '&';
	    url += 'detailedDescription=' + document.getElementById('detailedDescriptionInput').value + '&';
	    url += 'completionDate=' + Date.parse(document.getElementById('dateInput').value) + '&';
	    url += 'draft=' + document.getElementById('draftInput').value + '&';
	    url += 'author=' + loggedUser.personID;
	    console.log(Date.parse(document.getElementById('dateInput').value));
	    console.log(url);
	    let response = await fetch(url, {method: 'PUT'});
	    
	    switch (response.status) {
	        case 200: // successful
	        	let enoughPoints = false;
	        	console.log(document.getElementById('typeID').value);
				switch(document.getElementById('typeID').value){
				case "1": 
					if(loggedUser.points > 49){
						enoughPoints = true;
					}
					break;
				case "2":
					//console.log("Case 2");
					if(loggedUser.points > 24){
						//console.log(loggedUser.points);
						enoughPoints = true;
					}
					break;
				case "3":
					if(loggedUser.points > 19){
						enoughPoints = true;
					}
					break;
				case "4":
					if(loggedUser.points > 9){
						enoughPoints = true;
					}
					break;
				default: 
					break;
				}
				if(enoughPoints){
		            alert("Story Pitch Successfully Submitted!");
				}
				else{
					alert("Story Pitch Placed On Hold");
				}
	            setNav();
	            console.log(loggedUser);
	            break;
	        case 404:
	            alert('That page does not exist.');
	            break;
	        default: // other error
	            alert('Something went wrong.');
	            break;
	    }
	}
	else{
		alert("You must enter a completion date")
	}
}