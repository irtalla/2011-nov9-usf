checkLogin().then(populateRejections);

async function populateRejections() {
	console.log(loggedUser);
    let stories = loggedUser.storyPitches;
    let rejections = loggedUser.rejections;
    let storySection = document.getElementById('storySection');

    if (rejections.length > 0) {
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
                <th>Reason for rejection</th>
                <th>Rejected by</th>
            </tr>
        `;

        for (let i = 0; i < rejections.length; i++) {
        	let cd = stories[i].completionDate;
    		let newcdate = new Date(cd);
    		//console.log(newcdate.toUTCString());
    		let cUTCDate = newcdate.toUTCString();
        	let sd = stories[i].submissionDate;
    		let newsdate = new Date(sd);
    		//console.log(newsdate.toUTCString());
    		let sUTCDate = newsdate.toUTCString();
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${stories[i].storyID}</td>
                <td>${stories[i].type.name}</td>
                <td>${stories[i].title}</td>
                <td>${stories[i].genre.genreName}</td>
                <td>${stories[i].tagline}</td>
                <td>${stories[i].detailedDescription}</td>
                <td>${sUTCDate}</td>
                <td>${cUTCDate}</td>
                <td>${stories[i].status.name}</td>
            `;
            for(let j = 0; j < rejections.length; j++){
            	if(rejections[j].storyRejectedID = stories[i].storyID){
            		tr.innerHTML +=`
            		<td>${rejections[j].reason}</td>
            		`;
            		let x = await getRejectorName(rejections[j]);
            		console.log(x);
            		tr.innerHTML += `<td> ${x} </td>`;
            	}
            }
            table.appendChild(tr);
        }

        storySection.appendChild(table);
    } else {
        storySection.innerHTML = 'You don\'t have any rejections.';
    }
}

async function getRejectorName(rejection){
    let url = baseUrl + '/users/';
    url += rejection.rejectorID;
    console.log(url);
    
    let response = await fetch(url, {method: 'GET'});
    if(response.status == 200){

        let x = await response.json();
//        console.log(response)
//        console.log(x);
//        console.log(x.name);
       // console.log(response);
     return await x.name;
    }
	
}
