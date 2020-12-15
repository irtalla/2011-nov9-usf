checkLogin().then(populateInfoResponses);

async function populateInfoResponses() {
	console.log(loggedUser);
    let infoResponses = loggedUser.infoResponses;
    console.log(infoResponses);
    let storySection = document.getElementById('storySection');

    if (infoResponses.length > 0) {
    	//console.log(stories.length)
    	//console.log(stories[0].genre)
        let table = document.createElement('table');
 	
        table.innerHTML = `
            <tr>
                <th>InfoResponse ID</th>
                <th>Message</th>
                <th>Story ID</th>
                <th>Type</th>
                <th>Title</th>
                <th>Genre</th>
                <th>Tagline</th>
                <th>Submission Date</th>
                <th>Tentative Completion Date</th>
                <th>Status</th>
                <th>Submitted by</th>
                <th>Author ID</th>
            </tr>
        `;

        for (let i = 0; i < infoResponses.length; i++) {
            let tr = document.createElement('tr');
            storyPitch = await getStoryByID(infoResponses[i].storyID);
        	let cd = storyPitch.completionDate;
    		let newcdate = new Date(cd);
    		//console.log(newcdate.toUTCString());
    		let cUTCDate = newcdate.toUTCString();
        	let sd = storyPitch.submissionDate;
    		let newsdate = new Date(sd);
    		//console.log(newsdate.toUTCString());
    		let sUTCDate = newsdate.toUTCString();
            tr.innerHTML = `
                <td>${infoResponses[i].responseID}</td>
                <td>${infoResponses[i].message}</td>
                <td>${storyPitch.storyID}</td>
                <td>${storyPitch.type.name}</td>
                <td>${storyPitch.title}</td>
                <td>${storyPitch.genre.genreName}</td>
                <td>${storyPitch.tagline}</td>
                <td>${sUTCDate}</td>
                <td>${cUTCDate}</td>
                <td>${storyPitch.status.name}</td>
            `;
            
            let x = await getRespondorName(infoResponses[i].personRespondingID);
            console.log(x);
            tr.innerHTML += `
            	<td> ${x} </td>
            	<td> ${infoResponses[i].personRespondingID} </td>`;
            table.appendChild(tr);
            }
        storySection.appendChild(table);
    } else {
        storySection.innerHTML = 'You don\'t have any info responses.';
    }
}

async function getRespondorName(id){
    let url = baseUrl + '/users/';
    url += id;
    console.log(url);
    
    let response = await fetch(url, {method: 'GET'});
    if(response.status == 200){

        let x = await response.json();
//        console.log(response)
//        console.log(x);
//        console.log(x.name);
//        console.log(response);
     return await x.name;
    }
	
}

async function getStoryByID(id){
    let url = baseUrl + '/getStory?';
    url += 'storyID=' + id;
    console.log(url);
    
    let response = await fetch(url, {method: 'GET'});
    if(response.status == 200){

        let x = response.json();
        console.log(response)
        console.log(x);
     return x;
    }
    else{
    	console.log(response.status)
    	alert("Something went wrong")
    }
	
}
