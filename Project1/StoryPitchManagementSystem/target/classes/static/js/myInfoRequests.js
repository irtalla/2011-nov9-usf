checkLogin().then(populateInfoRequests);

async function populateInfoRequests() {
	console.log(loggedUser);
    let stories = loggedUser.storyPitches;
    let infoRequests = loggedUser.infoRequests;
    console.log(stories);
    console.log(infoRequests);
    let storySection = document.getElementById('storySection');
    if (infoRequests.length > 0) {
    	//console.log(stories.length)
    	//console.log(stories[0].genre)
        let table = document.createElement('table');
 	
        table.innerHTML = `
            <tr>
            	<th>Info Request ID</th>
                <th>Story ID</th>
                <th>Info Request Message</th>
                <th>Requested By </th>
                <th>Date of Request </th>
            </tr>
        `;

        for (let i = 0; i < infoRequests.length; i++) {
    		
            let tr = document.createElement('tr');
            tr.innerHTML = `
            	<td>${infoRequests[i].requestID}</td>
                <td>${infoRequests[i].storyID}</td>
                <td>${infoRequests[i].message}</td>
            `;
            
            let x = await getRequestorName(infoRequests[i]);
            console.log(x);
            tr.innerHTML += `<td> ${x} </td>`;
            let ad = infoRequests[i].requestDate;
            let newadate = new Date(ad);
            //console.log(newcdate.toUTCString());
            let aUTCDate = newadate.toUTCString();
             tr.innerHTML += `<td> ${aUTCDate} </td>`;
        	table.appendChild(tr);
        }
	storySection.appendChild(table);
    }
    else {
        storySection.innerHTML = 'You don\'t have any info requests.';
    }
}

async function getRequestorName(infoRequest){
    let url = baseUrl + '/users/';
    url += infoRequest.personRequestingID;
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
