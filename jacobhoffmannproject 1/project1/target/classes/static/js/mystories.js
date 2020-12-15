setup();
function setup() {
    ///console.log("loggedUser");
    checkLogin().then(() => {
        getStories().then(() => {
            //if (iseditor=== true);
        });
    });
}
async function getStories() {
    let props = loggedAuth.users.id;
    let url = baseUrl +'/Story/user?';
     url += 'id='+props;
     response = await fetch(url, {method: 'GET'});
     let a = await response.json();
 
     let propSection = document.getElementById('story');
     //alert('yo')
     if (a.length > 0) {
       //  alert('hi there');
        // alert('hi');
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
                 <th>Story Type</th>
                 <th>Non Fiction</th>
                 <th>Completion Date</th>
                 <th>Genre Type</th>
                 <th>Story Description</th>
                 <th>Story Tag</th>
             </tr>
         `; //alert('aaaaa');
         for (let Story of a) {
            
             let tr = document.createElement('tr');
             tr.innerHTML = `
                 <td>${Story.id}</td>
                 <td>${Story.stype.type_name}</td>
                 <td>${Story.genre_type.nonfiction}</td>
                 <td>${Story.completion_date}</td>
                 <td>${Story.genre_type.genre}</td>
                 <td>${Story.description}</td>
                 <td>${Story.tag_line}</td>

             `;
             let td = document.createElement('td');
             let ul = document.createElement('ul');

             td.appendChild(ul);
             tr.appendChild(td);
             table.appendChild(tr);
         }
         propSection.appendChild(table);
        //let Approvebtn = document.getElementById('approve');
        //Approvebtn.onclick = ApproveSubmit;
}

}