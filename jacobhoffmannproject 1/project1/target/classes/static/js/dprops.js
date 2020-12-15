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
    let props = loggedEdit.id;
    let url = baseUrl +'/Draft_Approval/edit?';
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
                <th>Draft ID</th>
                <th>Approved</th>
                <th>Reason</th>
            </tr>
        `;
        for (let Draft_Approval of a) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${Draft_Approval.id}</td>
                <td>${Draft_Approval.draft.id}</td>
                <td>${Draft_Approval.approved}</td>
                <td>${Draft_Approval.reject_reason}</td>
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');

            td.appendChild(ul);
            tr.appendChild(td);
            table.appendChild(tr);
        }
        propSection.appendChild(table);
        let Approvebtn = document.getElementById('approve');
        Approvebtn.onclick = ApproveSubmit;
}

}async function ApproveSubmit(){
    // alert('dont exist.');
     let url = baseUrl + '/Draft_Approval?';
     url += 'id=' + document.getElementById('approve_idin').value;
     let response = await fetch(url, {method: 'GET'});
     let newapproval = await response.json();
     newapproval.approved = true;
     let url2 = baseUrl+'/Draft_Approval/'+document.getElementById('approve_idin').value;
     let newResponse = await fetch(url2,{method:'PUT',body:JSON.stringify(newapproval)});
     //checkApproved();
 }