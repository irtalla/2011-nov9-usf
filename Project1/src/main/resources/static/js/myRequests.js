checkLogin().then(getRequests);

async function getRequests(){
    let getUserUrl = baseUrl + "/users/requests/"+ loggedUser.id;
    let response = await fetch(getUserUrl, {method: 'GET'});
    if(response.status === 200){
        let userReqs = response.json();
        populateRequests(userReqs);
    }
}

async function populateRequests(userReqs){
    let reqSection = document.getElementById('reqSection');
    alert(userReqs.length)
    if(userReqs.length > 0){
        let table = document.createElement('table');
        table.innerHTML = `
        <tr>
            <th>Your Requests<th>
        </tr>
        `;
    
        for(let req in userReqs) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td id= "recipient${req.id}" >Recipient: ${req.recipient.username}</td>
                <td id= "sender${req.id}" >Sender: ${req.sender.username}</td>
                <td id= "question${req.id}" > ${req.question} </td>
                `;
                // if user is sender or no answer has been sent
                if(req.answer != null || (loggedUser.id === req.sender.id)){
                    let td1 = document.createElement('td');
                    td1.innerHTML = `<td id= "answer${req.id}" > ${req.answer} </td>`;
                    tr.appendChild(tr);
                //else if the user is the recipient and can respond
                }else if(loggedUser.id === req.recipient.id){
                    let td2 = document.createElement('td');
                    td2.innerHTML = ` <input type='text' id='answer' placeholder= 'How do you respond?'>`;
                    tr.appendChild(td2);
                    let td3 = document.createElement('td');
                    td3.innerHTML = `
                    <button id="ansBtn${req.id}" onclick = "sendAns(${req.id})" type="button">
                        Send answer
                    </button>
                    `;
                    tr.appendChild(td3);
                }//end answer section and append the row to table
        table.appendChild(tr);    
        }//end for
        reqSection.appendChild(table);
    }//end if
    else{
        reqSection.innerHTML = "No Current Requests";
    }//end else

}//end function

function sendAns(num){
    alert(num + "should be the id of the request we are replying to");
}