setup();
function setup() {
    ///console.log("loggedUser");
    checkLogin().then(() => {
        getprops().then(() => {
            //if (iseditor=== true);
        });
    });
}
async function getprops() {
    let props = loggedAuth.id;
    let url = baseUrl +'/Pitch_Status/author?';
     url += 'id='+props;
     response = await fetch(url, {method: 'GET'});
     let a = await response.json();
 
     let propSection = document.getElementById('sprops');
     if (a.length > 0) {
        //alert('hi');
         let table = document.createElement('table');
 
         table.innerHTML = `
         <form>
         <label for="status_id">Status Id: </label>
         <input id="status_idin" name="status_id" type="text" />
         <button type="button" id="approve">Approve</button>

         <label for="rinfo"> Request Info: </label>
         <input id="rinfoin" name="rinfo" type="text" />
         <button type="button" id="rinfo">Request Info</button>
     </form>    
         <tr>
                 <th>ID</th>
                 <th>Story Title</th>
                 <th>genre status</th>
                 <th>outside status</th>
                 <th>assistant status</th>
                 <th>Story Description</th>
                 <th>Story Type</th>
             </tr>
         `;
         for (let Pitch_Status of a) {
             let tr = document.createElement('tr');
             tr.innerHTML = `
                 <td>${Pitch_Status.id}</td>
                 <td>${Pitch_Status.story.id}</td>
                 <td>${Pitch_Status.genre_status.id}</td>
                 <td>${Pitch_Status.outside_status.id}</td>
                 <td>${Pitch_Status.assitant_status.id}</td>
                 <td>${Pitch_Status.story.description}</td>
                 <td>${Pitch_Status.story.stype.type_name}</td>

             `;
             let td = document.createElement('td');
             let ul = document.createElement('ul');

             td.appendChild(ul);
             tr.appendChild(td);
             table.appendChild(tr);
         }
         propSection.appendChild(table);
        //let Approvebtn = document.getElementById('approve');
       // Approvebtn.onclick = ApproveSubmit;
}

}