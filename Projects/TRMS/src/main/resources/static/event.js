var eventsdiv=document.getElementById('events');
let eventsurl = 'http://localhost:8080/events';
let eventtable=null;
setevents();

async function setevents(){

    let response = await fetch(eventsurl,{method: 'GET'});
    switch (response.status) {
        case 200: // successful
            eventtable = await response.json();
           // console.log("logged in "+);
            settable(eventtable);
           // form.innerHTML='';
            
         //   document.getElementById('loo').style.display="none";
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
         //   document.getElementById('pass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }

}


function settable(eventtable) {
   // let tablesection = document.getElementById('catSection');
    //catSection.innerHTML = '';
    eventsdiv.innerHTML='';
    if (eventtable.length > 0) {
        let table = document.createElement('table');
        table.id = 'eventtable';
        table.innerHTML='Events <br>';
        table.innerHTML += `
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Coverage</th>
               
               
            </tr>
        `;

        for (let events of eventtable) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${events.event_id}</td>
                <td>${events.event_name}</td>
                <td>${events.event_coverage}</td>
            `;
         
           // td.appendChild(ul);
           // tr.appendChild(td);

             // <button type="button" id="Howard_6"
            //  disabled="false">Adopt</button>
            table.appendChild(tr); 
          
        }

        eventsdiv.appendChild(table);
    } else {
        eventsdiv.innerHTML = 'No cats are available.';
    }
}