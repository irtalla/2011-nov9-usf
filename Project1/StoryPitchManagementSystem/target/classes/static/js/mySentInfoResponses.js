checkLogin().then(populateInfoResponses);

async function populateInfoResponses() {
	console.log(loggedUser);
    let stories = loggedUser.storyPitches;
    let infoResponses = loggedUser.infoResponses;
    console.log(stories);
    console.log(infoResponses);
    let storySection = document.getElementById('storySection');
    if (infoResponses.length > 0) {
    	//console.log(stories.length)
    	//console.log(stories[0].genre)
        let table = document.createElement('table');
 	
        table.innerHTML = `
            <tr>
                <th>Story ID</th>
                <th>Info Response Message</th>
                <th>Requested By </th>
                <th>Date of Response </th>
            </tr>
        `;

        for (let i = 0; i < infoResponses.length; i++) {
    		
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${infoResponses[i].storyID}</td>
                <td>${infoResponses[i].message}</td>
            `;
            
            let x = await getRequestingName(infoResponses[i]);
            console.log(x);
            tr.innerHTML += `<td> ${x} </td>`;
            let ad = infoResponses[i].responseDate;
            let newadate = new Date(ad);
            //console.log(newcdate.toUTCString());
            let aUTCDate = newadate.toUTCString();
             tr.innerHTML += `<td> ${aUTCDate} </td>`;
        	table.appendChild(tr);
        }
	storySection.appendChild(table);
    }
    else {
        storySection.innerHTML = 'You don\'t have any info responses.';
    }
}

async function getRequestingName(infoResponse){
    let url = baseUrl + '/users/';
    url += infoResponse.personRespondedID;
  //  console.log(url);
    
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
