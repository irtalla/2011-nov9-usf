checkLogin().then(getRequests);

function populateRequests() {
    let requests = loggedUser.requests;
    let requestSection = document.getElementById('requestSection');

    if (requests.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>User Name</th>
                <th>Event Type/th>
                <th>Event Description</th>
                <th>Amount</th>
				<th>Grade Format</th>
				<th>Request Status<th>
            </tr>
        `;

        for (let request of requests) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${request.id}</td>
                <td>${request.username}</td>
                <td>${request.eventType}</td>
                <td>${request.eventDescription}</td>
            `;
            let td = document.createElement('td');
            td.appendChild(ul);
            tr.appendChild(td);
            table.appendChild(tr);
        }

        catSection.appendChild(table);
    } else {
        requestSection.innerHTML = 'You don\'t have any requests. :(';
    }
}

async function getRequests() {
    let url = baseUrl + '/requests';
    let response = await fetch(url, {method: 'GET'});
    if (response.status === 200) {
		alert('Added request successfully.');
        let requests = await response.json();
        populateRequests(requests);
    }
}

