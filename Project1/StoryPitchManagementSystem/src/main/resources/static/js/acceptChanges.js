checkLogin().then(populateStories);

function populateStories() {
	console.log(loggedUser);
    let stories = loggedUser.storyPitches;
    let storySection = document.getElementById('storySection');

	console.log(stories);
    changedStories = [];
    if (stories.length > 0) {
    	for(let i = 0; i < stories.length; i++){
    		if(stories[i].status.statusID == 4){
    			console.log("Story has been changed");
    			changedStories.push(stories[i]);
    		}
    	}
    }

    if (changedStories.length > 0) {
    	//console.log(stories.length)
    	//console.log(stories[0].genre)
        let table = document.createElement('table');
 	
        table.innerHTML = `
            <tr>
                <th>Story ID</th>
                <th>Type</th>
                <th>Title</th>
                <th>Genre</th>
                <th>Tagline</th>
                <th>Description</th>
                <th>Submission Date</th>
                <th>Tentative Completion Date</th>
                <th>Status</th>
            </tr>
        `;

        for (let sp of changedStories) {
        	let cd = sp.completionDate;
    		let newcdate = new Date(cd);
    		//console.log(newcdate.toUTCString());
    		let cUTCDate = newcdate.toUTCString();
        	let sd = sp.submissionDate;
    		let newsdate = new Date(sd);
    		//console.log(newsdate.toUTCString());
    		let sUTCDate = newsdate.toUTCString();
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${sp.storyID}</td>
                <td>${sp.type.name}</td>
                <td>${sp.title}</td>
                <td>${sp.genre.genreName}</td>
                <td>${sp.tagline}</td>
                <td>${sp.detailedDescription}</td>
                <td>${sUTCDate}</td>
                <td>${cUTCDate}</td>
                <td>${sp.status.name}</td>
            `;
            table.appendChild(tr);
        }
        
        let tr0 = document.createElement('tr0');	   	
    	tr0.innerHTML =`  <br>  <br> <br>
    		
            <th>Info Request ID</th>
		    <td>
				<input type=text id="storyID" size = 22 placeholder = "Enter the ID of the story pitch" />
			</td>`
    	table.appendChild(tr0);
        
        let tr1 = document.createElement('tr1');	   	
    	tr1.innerHTML =`  <br>  <br> 
    		<td>
    			<button type="button" id="acceptBtn">Accept Changes</button>
    		</td>`
    	table.appendChild(tr1);
        
        let tr2 = document.createElement('tr2');	   	
    	tr2.innerHTML =`  <br>  <br> 
    		<td>
    			<button type="button" id="rejectBtn">Reject</button>
    		</td>`
    	table.appendChild(tr2);
    	
        storySection.appendChild(table);
        acceptBtn.onclick = acceptChanges;
        rejectBtn.onclick = rejectChanges;
        
    } else {
        storySection.innerHTML = 'You don\'t have any changed stories.';
    }
}

async function acceptChanges(){
    let url = baseUrl + '/acceptChanges?';
    url += 'storyID=' + document.getElementById('storyID').value;
    
    console.log(url);
    let stories = loggedUser.storyPitches;
	for(let j = 0; j < stories.length;j++){
		if(stories[j].storyID == document.getElementById('storyID').value){
			if(stories[j].status.statusID !=4){
				alert("That story is not eligible for the status change you are attempting to authorize");
			}
			else{

			    let response = await fetch(url, {method: 'PUT'});
			    switch (response.status) {
		        case 200: // successful
		            alert("Changes accepted!")
		            for(sp in changedStories){
		            	if(sp.storyID == document.getElementById('storyID').value){
		        			loggedUser.storyPitches[i].status.statusID = 6; // changing status to reflect the acceptance of the changes
		        			loggedUser.storyPitches[i].status.name = "Draft Under Review - Comittee Approval Needed";
		            	}
		            }
		            setNav();
		            console.log(loggedUser);
		            break;
		        case 404: // user not found
		            alert('That page does not exist.');
		            break;
		        default: // other error
		            alert('Something went wrong.');
		            break;
			    }
			    console.log(loggedUser);
			    checkLogin().then(setNav());
			}
		}
	}
    
}

async function rejectChanges(){
    let url = baseUrl + '/rejectChanges?';
    url += 'storyID=' + document.getElementById('storyID').value;
    
    console.log(url);


	let stories = loggedUser.storyPitches;
	for(let j = 0; j < stories.length;j++){
		if(stories[j].storyID == document.getElementById('storyID').value){
			if(stories[j].status.statusID !=4){
				alert("That story is not eligible for the status change you are attempting to authorize");
			}
			else{
			    let response = await fetch(url, {method: 'PUT'});
			    switch (response.status) {
		        case 200: // successful
		            alert("Changes rejected")
		            console.log(changedStories);
		            for(let i = 0; i < stories.length; i++){
		            	let sp = stories[i];
		            	if(sp.storyID == document.getElementById('storyID').value){
		            		if(sp.status.statusID = 4){
			            		sp.status.statusID = 9; // changing status to "Withdrawn"
			            		let points = 0; // checking how many points to add back to author since they are withdrawing this story pitch
			        			switch(sp.type.typeID) {
			        			case 1:
			        				points = 50;
			        				break;
			        			case 2:
			        				points = 25;
			        				break;
			        			case 3:
			        				points = 20;
			        				break;
			        			case 4:
			        				points = 10;
			        				break;
			        			default:{
			        					break;
			        				}
			        			}
			        			loggedUser.storyPitches[i].status.statusID = 9;
			        			loggedUser.storyPitches[i].status.name = "Withdrawn";
			        			loggedUser.points = loggedUser.points + points;
			        			console.log(loggedUser);
		            		}
		            		else{
		            			alert("That story is not eligible for the status change you wish to authorize")
		            		}
		            	}
		            }
		            break;
		        case 404: // user not found
		            alert('That page does not exist.');
		            break;
		        default: // other error
		            alert('Something went wrong.');
		            break;
		    }
		    checkLogin().then(setNav());
		    console.log(loggedUser);
			}
		}
	}
	
	
    
}