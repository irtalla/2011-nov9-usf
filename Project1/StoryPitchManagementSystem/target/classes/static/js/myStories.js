checkLogin().then(populateStories);

function populateStories() {
	console.log(loggedUser);
    let stories = loggedUser.storyPitches;
    let storySection = document.getElementById('storySection');

    if (stories.length > 0) {
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

        for (let sp of stories) {
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
            //tr.appendChild(td);
            table.appendChild(tr);
        }

        storySection.appendChild(table);
    } else {
        storySection.innerHTML = 'You don\'t have any stories.';
    }
}

