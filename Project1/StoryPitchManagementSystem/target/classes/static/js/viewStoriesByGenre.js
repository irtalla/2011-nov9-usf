checkLogin().then(populateStories());

async function getStoriesByGenre() {
    let url = baseUrl + '/stories';
    //console.log(url);
    let response = await fetch(url);
    console.log(response);
    if (response.status === 200) {
        let stories = await response.json();
        //console.log(stories);
        populateStories(stories);
    }
}

function populateStories(stories) {
    let storySection = document.getElementById('storySection');

    if (stories.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>Story ID</th>
                <th>Author ID</th>
                <th>Type</th>
                <th>Title</th>
                <th>Genre</th>
                <th>Tagline</th>
                <th>Description</th>
                <th>Submission Date</th>
                <th>Tentative Completion Date</th>
                <th>Status</th>
                <th>Priority</th>
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
            let z = "High";
            if(sp.priorityID == 1){
            	z = "Low";
            };
            tr.innerHTML = `
                <td>${sp.storyID}</td>
                <td>${sp.personID}</td>
                <td>${sp.type.name}</td>
                <td>${sp.title}</td>
                <td>${sp.genre.genreName}</td>
                <td>${sp.tagline}</td>
                <td>${sp.detailedDescription}</td>
                <td>${sUTCDate}</td>
                <td>${cUTCDate}</td>
                <td>${sp.status.name}</td>
                <td>${z}</td>
                <td>
            `;
            table.appendChild(tr);
        }

        storySection.appendChild(table);
    } else {
        storySection.innerHTML = 'No stories have been submitted.';
    }
    
}


