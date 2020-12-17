checkLogin().then(getRequests);
async function getRequests() {
    let url = baseUrl + '/console';
    let response;
    if (loggedUser.department.id === 1) {
        response = await fetch(url, {method: 'POST'});
    } else if (loggedUser.role.id === 1 || loggedUser.role.id === 2) response = await fetch(url); 
    if (response.status === 200) {
        let requests = await response.json();

        populateRequests(requests);
    }
async function populateRequests(requests) {
    
    let requestSection = document.getElementById('dashboard');
    requestSection.innerHTML = '';

    if (requests.length > 0) {
        let table = document.createElement('table');
        table.id = 'requestTable'

        table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Requestor</th>
            <th>Test Date</th>
            <th>Next Approval Due By</th>
            <th>Status</th>
			<th></th>
        </tr>
        `;
        for (let request of requests) {
            let testDate = request.testDate.replace("T", " ");
            testDate = testDate.replace("-" , "/");
            testDate = testDate.replace("-" , "/");
            

            if (!request.due) {
                request.due = "Completed";
            } else {
                let due = request.due;
                let minute = due.minute;
                let hour = due.hour;
                let month = due.monthValue;
                let day = due.dayOfMonth;
                let year = due.year;
                request.due = `${year}/${month}/${day} ${hour}:${minute}`
            }
            
            let tr = document.createElement('tr');
            
            if (!request.due) request.due = "Completed";
            

            tr.innerHTML = `
            <td>${request.id}</td>
            <td>${request.requestor.first} ${request.requestor.last}</td>
            <td>${testDate}</td>
            <td>${request.due}</td>
            <td>${request.status.name}</td>
            <td><button onclick="location.href = 'request.html?id=${request.id}';" type="button" id="View:${request.id}">View</button></td>
            `;
            
            
        // let td = document.createElement('td');
        // tr.appendChild(td);
        
        table.appendChild(tr);
        }
        requestSection.appendChild(table);
    }
}


}