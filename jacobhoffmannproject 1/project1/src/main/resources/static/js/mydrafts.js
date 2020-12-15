setup();
function setup() {
    ///console.log("loggedUser");
    checkLogin().then(() => {
        getDrafts().then(() => {
            //if (iseditor=== true);
        });
    });
}
async function getDrafts() {
    let props = loggedAuth.id;
    let url = baseUrl +'/Draft/edit?';
    url += 'id='+props;
    response = await fetch(url, {method: 'GET'});
    let a = await response.json();

    let propSection = document.getElementById('dprops');
    if (a.length > 0) {
        

       // alert('hi');
        let table = document.createElement('table');

        table.innerHTML = `
        <form>
        <label for="approve_id">Approval Id: </label>
        <input id="approve_idin" name="approve_id" type="text" />
        <button type="button" id="approve">Approve</button>
    </form>    
        <tr>
                <th>ID</th>
                <th>Draft</th>
                <th>Approved</th>
                <th>Reason</th>
            </tr>
        `;
        for (let Draft of a) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${Draft.id}</td>
                <td>${Draft.draft}</td>
                <td>${Draft.approved}</td>
                <td>${Draft.story_id.id}</td>
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');

            td.appendChild(ul);
            tr.appendChild(td);
            table.appendChild(tr);
        }
        propSection.appendChild(table);
        //let Approvebtn = document.getElementById('approve');
//        Approvebtn.onclick = ApproveSubmit;
}

}