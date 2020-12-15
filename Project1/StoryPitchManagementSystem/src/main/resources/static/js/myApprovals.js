checkLogin().then(populateApprovals);

async function populateApprovals() {
	console.log(loggedUser);
    let stories = loggedUser.storyPitches;
    let approvals = loggedUser.approvals;
    console.log(stories);
    console.log(approvals);
    let storySection = document.getElementById('storySection');
    console.log(approvals);
    if (stories.length > 0) {
    	//console.log(stories.length)
    	//console.log(stories[0].genre)
        let table = document.createElement('table');
 	
        table.innerHTML = `
            <tr>
                <th>Story ID</th>
                <th>Approved for by</th>
                <th>Date Approved </th>
                <th>Status Approved </th>
            </tr>
        `;

        let minStatus = 0;
        for (let i = 0; i < stories.length; i++) {
    		
            let bool = true;
    		for(let k = minStatus; bool; k++){
    			if(k > 1000){
    				break;
    			}
                   for(let j = 0; j < approvals.length; j++){
                    if(approvals[j].storyRejectedID = stories[i].storyID){
            			if(approvals[j].statusApproved == k){
            	            let tr = document.createElement('tr');
            	            tr.innerHTML = `
            	                <td>${approvals[i].storyApprovedID}</td>
            	            `;
                			let x = await getApproverName(approvals[j]);
                			console.log(x);
                			tr.innerHTML += `<td> ${x} </td>`;
                    		let ad = approvals[j].approvalDate;
                    		let newadate = new Date(ad);
                    		//console.log(newcdate.toUTCString());
                    		let aUTCDate = newadate.toUTCString();
                    		tr.innerHTML += `<td> ${aUTCDate} </td>`;
                			tr.innerHTML += `<td> ${approvals[j].statusApproved} </td>`;
                            table.appendChild(tr);
            				bool = false;
            				minStatus = k+1;
            			}
            		}
            	}
            }
        }

        storySection.appendChild(table);
    } else {
        storySection.innerHTML = 'You don\'t have any approvals.';
    }
}

async function getApproverName(approval){
    let url = baseUrl + '/users/';
    url += approval.approverID;
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
