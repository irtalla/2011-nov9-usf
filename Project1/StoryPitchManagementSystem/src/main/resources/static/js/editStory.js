checkLogin().then(populateStoryForm);
var points = null;


async function populateStoryForm() {
	let storiesurl = baseUrl + '/stories';
	let stories = [];
    let response = await fetch(storiesurl);
    if(response.status == 200){
    	stories = await response.json();
    }
	console.log(loggedUser);
	points = loggedUser.points;

    let storySection = document.getElementById('storySection');
    let table = document.createElement('table');
    table.innerHTML= "";
    let tr00 = document.createElement('tr00');
    tr00.innerHTML = ` <br> <br>
        	<th>StoryID</th>
           	<td>
    			<input type="text" id="storyID" placeholder="enter the storyID of the pitch you wish to modify" />
    		</td>
    `;

    table.appendChild(tr00);	
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
	let tr6 = document.createElement('tr6');	   	
	tr6.innerHTML =` <br>      
            <th>Tentative Completion Date (YYYY-MM-DD)</th>
			<td>
    			<input type="text" id="dateInput" placeholder="Enter anticipated completion date here" />
			</td>`
        
	table.appendChild(tr6);	
	
	let tr8 = document.createElement('tr8');	   	
	tr8.innerHTML =`  <br>  <br> <br>
			<td>
				<button type="button" id="submitBtn">Submit</button>
			</td>
	`
    
    table.appendChild(tr8);
	
    storySection.appendChild(table);
    submitBtn.onclick = changeStoryPitch;
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

async  function changeStoryPitch() {
	stories = [];
	let url = baseUrl + '/stories';
    //console.log(url);
    let response = await fetch(url);
    console.log(response);
    if (response.status === 200) {
        stories = await response.json();
        //console.log(stories);
    }
    
	let validStoryID = false;
	if(stories.length > 0){
		console.log(stories);
		for(let i = 0; i < stories.length; i++){
			console.log("i= " + i);
			if(document.getElementById('storyID').value == stories[i].storyID){
				console.log("Story match");
				if(stories[i].status.statusID == 3){ // 3 is the statusID for awaiting senior editor approval
					console.log("Status = 3");
					console.log(stories[i].status.statusID);
				    let url = baseUrl + '/getGenreSpecializations?';
				    url += 'genreSpecializationID=' + stories[i].genre.genreID;
					let response = await fetch(url, {method: 'GET'});
					if(response.status == 200){
						console.log("200");
						let gspecs = await response.json();
						console.log(gspecs);
							for(let j = 0; j < gspecs.length; j++){
								if(gspecs[j].personID == loggedUser.personID){
								validStoryID = true;
							}
						}
					}
				}
			}
		}
	}
	if(document.getElementById('dateInput').value){
			if(validStoryID){
			
				//reformatDate();
			    let url = baseUrl + '/editStory?';
			    url += 'storyID=' + document.getElementById('storyID').value + '&';
			    url += 'title=' + document.getElementById('titleInput').value + '&';
			    url += 'tagline=' + document.getElementById('taglineInput').value + '&';
			    url += 'completionDate=' + Date.parse(document.getElementById('dateInput').value);
			    console.log(Date.parse(document.getElementById('dateInput').value));
			    console.log(url);
			    let response = await fetch(url, {method: 'PUT'});
			    
			    switch (response.status) {
			        case 200: // successful
			            alert("Story Pitch Successfully Changed!")
			            setNav();
			            console.log(loggedUser);
			            break;
			        case 404: // user not found
			            alert('That story pitch does not exist.');
			            break;
			        default: // other error
			            alert('Something went wrong.');
			            break;
			    }
			}
			else{
				alert("You must enter a valid storyID: the story must have been approved by a general editor "
						+ "and be awaiting approval of a senior genre editor, and it must be in a genre in which you specialize")
			}
	}
	else{
		alert("You must enter a completion date")
	}
}