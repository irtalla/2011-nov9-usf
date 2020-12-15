checkLogin().then(populateChangeRequests);

async function populateChangeRequests() {
	console.log(loggedUser);
    let stories = loggedUser.storyPitches;
    let changeRequests = loggedUser.changeRequests;
    console.log(stories);
    console.log(changeRequests);
    let storySection = document.getElementById('storySection');
    console.log(changeRequests);
    if (changeRequests.length > 0) {
    	//console.log(stories.length)
    	//console.log(stories[0].genre)
        let table = document.createElement('table');
 	
        table.innerHTML = `
            <tr>
                <th>Story ID</th>
                <th>Changes Request Message</th>
                <th>Requested By </th>
                <th>Date of Request </th>
            </tr>
        `;

        for (let i = 0; i < changeRequests.length; i++) {
    		
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${changeRequests[i].storyID}</td>
                <td>${changeRequests[i].message}</td>
            `;
            
            let x = await getRequestorName(changeRequests[i]);
            console.log(x);
            tr.innerHTML += `<td> ${x} </td>`;
            let ad = changeRequests[i].requestDate;
            let newadate = new Date(ad);
            //console.log(newcdate.toUTCString());
            let aUTCDate = newadate.toUTCString();
             tr.innerHTML += `<td> ${aUTCDate} </td>`;
        	table.appendChild(tr);
        }
	storySection.appendChild(table);
    }
    else {
        storySection.innerHTML = 'You don\'t have any change requests.';
    }
}

async function getRequestorName(changeRequest){
    let url = baseUrl + '/users/';
    url += changeRequest.personRequestingID;
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
