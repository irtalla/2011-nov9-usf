//checkLogin().then(getProps);
//getProps();
//let baseUrl = 'http://localhost:8080';
let logEdit;
setup();
function setup() {
    ///console.log("loggedUser");
    checkLogin().then(() => {
        getProps().then(() => {
            //if (iseditor=== true);
        });
    });
}
async function getProps() {
    // alert('in sprops');

    console.log("logEdit");
     let props = loggedEdit.id;
     let url = baseUrl +'/Pitch_Status?';
     url += 'id='+props;
     response = await fetch(url, {method: 'GET'});
     let a = await response.json();
 
     let propSection = document.getElementById('sprops');
     if (a.length > 0) {
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
         let Approvebtn = document.getElementById('approve');
         Approvebtn.onclick = ApproveSubmit;
         let rinfobtn = document.getElementById('rinfo');
         rinfobtn.onclick = Rinfo;
     } 
     //alert('bye');
 }
 async function ApproveSubmit(){
   // alert('dont exist.');
    let url = baseUrl + '/Pitch_Approval?';
    url += 'id=' + document.getElementById('status_idin').value;
    let response = await fetch(url, {method: 'GET'});
    let newapproval = await response.json();
    newapproval.approved = true;
    let url2 = baseUrl+'/Pitch_Approval/'+document.getElementById('status_idin').value;
    let newResponse = await fetch(url2,{method:'PUT',body:JSON.stringify(newapproval)});
    checkApproved();
}
 async function Rinfo(){
    let url = baseUrl +'/PitchStatus?';
    url += 'id=' + document.getElementById('status_idin').value;
    let response = await fetch(url,{method:'GET'});
    let answer = await response.json();
    let Request_Info = {};
    Request_Info.id = 1;
    Request_Info.editor = loggedEdit;
    Request_Info.users.id = answer.story.author.users.id;
    Request_Info.description ='';
    let url2 = baseUrl +'/info';
   // let newResponse = await fetch(url2,{method:'POST',body:JSON.stringify(Request_Info)});
   let newResponse2 = await fetch(url2,{method:'POST',body:JSON.stringify(Request_Info)});

}
async function checkApproved(){
 let url = baseUrl+'/Pitch_Status/all';
 let response = await fetch(url,{method:'GET'});
 let listofthing = await response.json();
 if (listofthing.length > 0) {
 for (let Pitch_Status of listofthing) {
    if(Pitch_Status.genre_status.approved ===true)
    {
        if(Pitch_Status.outside_status.approved ===true)
        {
            if(Pitch_Status.assitant_status.approved ===true)
            {
                Pitch_Status.approved = true;
                let url2 = baseUrl+'/Pitch_Status/'+Pitch_Status.id;
                let newResponse = await fetch(url2,{method:'PUT',body:JSON.stringify(Pitch_Status)});
                let url3 = baseUrl+'/Draft';
                let Draft = {}; 
                Draft.id = 0;
                Draft.story_id = Pitch_Status.story;
                Draft.draft = 'hiiii';
                Draft.approved = false;
                let newResponse2 = await fetch(url3,{method:'POST',body:JSON.stringify(Draft)});

                let url4 = baseUrl +'/commitee';
                let newResponse3 = await fetch(url4,{method:'GET'});
                let comitlist = await newResponse3.json();
                if(Pitch_Status.story.stype.id === 1)
                {
                    let Editor ={};
                    let found = false
                for(let Commitee of comitlist || found === false){
                    if(Commitee.genre.genre ===Pitch_Status.story.genre_type.genre )
                    {
                        if(Commitee.editor.is_senior===true)
                        {
                        Editor.id = Commitee.editor.id;
                        Editor.is_assitant = false;
                        Editor.employee = Commitee.editor.employee;
                        //alert('aaaaa');
                        found = true;
                        }                                     
                    }
                }
                let url5 = baseUrl+'/DSA';
                let Draft_Approval = {};
                Draft_Approval.id = 1;
                Draft_Approval.editor = Editor;
                Draft_Approval.draft = Draft;
                Draft_Approval.approved = false;
                Draft_Approval.reject_reason = 'aaaaaaaaaa';

                let newResponse4 = await fetch(url5,{method:'POST',body:JSON.stringify(Draft_Approval)});
               // alert('let loops');
            }
            else{
                let Editor ={};
                    let found = false
                for(let Commitee of comitlist || found === false){
                    if(Commitee.genre.genre ===Pitch_Status.story.genre_type.genre )
                    {
                        if(Commitee.editor.is_senior===true)
                        {
                        Editor.id = Commitee.editor.id;
                        Editor.is_assitant = false;
                        Editor.employee = Commitee.editor.employee;
                        alert('aaaaa');
                        found = true;
                        }                                     
                    }
                }
                let url5 = baseUrl+'/DSA';
                let Draft_Approval = {};
                Draft_Approval.id = 1;
                Draft_Approval.editor = Editor;
                Draft_Approval.draft = Draft;
                Draft_Approval.approved = false;
                Draft_Approval.reject_reason = 'aaaaaaaaaa';

                let newResponse4 = await fetch(url5,{method:'POST',body:JSON.stringify(Draft_Approval)});
                //alert('let loops');
            }
            }
            }
        }
    }
}
 }
