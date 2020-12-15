checkLogin().then(populateStoryForm);
var points = null;


function populateStoryForm() {
	console.log(loggedUser);
	points = loggedUser.points;

    let storySection = document.getElementById('storySection');
    let table = document.createElement('table');
    let str = "<br> You have: " + points + " submission points. Novels require 50, novellas 25, short stories 20 and articles 10";
    table.innerHTML= str;
    let tr2 = document.createElement('tr2');	
    tr2.innerHTML =`<br>  
           	<th>Story ID</th>
           	<td>
    			<input type="text" id="storyID" placeholder="Type the story ID here" />
    		</td>`
           
    table.appendChild(tr2);	
	
	let tr8 = document.createElement('tr8');	   	
	tr8.innerHTML =`  <br>  <br> <br>
			<td>
				<button type="button" id="resubmitBtn">Resubmit</button>
			</td>
	`
    
    table.appendChild(tr8);
	
    storySection.appendChild(table);
    resubmitBtn.onclick = resubmitStoryPitch;
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

async  function resubmitStoryPitch() {
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
	story = null;
	if(stories.length > 0){
		console.log(stories);
		for(let i = 0; i < stories.length; i++){
			if(stories[i].storyID == document.getElementById('storyID').value){
				console.log(stories[i]);
				if(stories[i].status.statusID == 0){
					validStoryID = true;
					story = stories[i];
					console.log(story);
				}
			}
		}
	}
	
	
	if(validStoryID){
	//check if the author has enough points to resubmit
	enoughPoints = false;
	switch(story.type.typeID){
	case 1: 
		if(loggedUser.points > 49){
			enoughPoints = true;
		}
		break;
	case 2:
		if(loggedUser.points > 24){
			enoughPoints = true;
		}
		break;
	case 3:
		if(loggedUser.points > 19){
			enoughPoints = true;
		}
		break;
	case 4:
		if(loggedUser.points > 9){
			enoughPoints = true;
		}
		break;
	default: 
		break;
	}
		if(enoughPoints){
		    let url = baseUrl + '/resubmitStory?';
		    url += 'storyID=' + document.getElementById('storyID').value;
		    let response = await fetch(url, {method: 'PUT'});
		    
		    switch (response.status) {
		        case 200: // successful
		            alert("Story Pitch Successfully Resubmitted!")
		            checkLogin().then(setNav());
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
		else{
			alert("You do not have enough points to resubmit this story")
		}
	}
	else{
		alert("You must submit a valid story ID");
	}
}