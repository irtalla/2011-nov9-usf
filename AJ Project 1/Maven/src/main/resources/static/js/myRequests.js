checkLogin().then(getRequests);
async function getRequests() {
    let url = baseUrl + '/request';
    let response = await fetch(url);
    if (response.status === 200) {
        let requests = await response.json();
        populateRequests(requests);
    }
async function populateRequests(requests) {
    
    let requestSection = document.getElementById('myRequests');
    requestSection.innerHTML = '';

    if (requests.length > 0) {
        let table = document.createElement('table');
        table.id = 'requestTable'

        table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Status</th>
			<th></th>
        </tr>
        `;
        for (let request of requests) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
            <td>${request.id}</td>
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