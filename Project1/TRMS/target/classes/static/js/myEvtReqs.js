checkLogin().then(populateEvtReqs);

function populateEvtReqs() {
    let evtReqs = loggedUser.evtReqs;
    //alert(evtReqs);
    let evtReqSection = document.getElementById('evtReqSection');

    if (evtReqs.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        `;

        for (let evtReq of evtReqs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${evtReq.id}</td>
                <td>${evtReq.name}</td>
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');
       
            td.appendChild(ul);
            tr.appendChild(td);
            table.appendChild(tr);
        }

        evtReqSection.appendChild(table);
    } else {
        evtReqSection.innerHTML = 'You don\'t have any event requests. :(';
    }
}
