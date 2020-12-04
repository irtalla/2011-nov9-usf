//checkLogin().then(update);
var section= document.getElementById('us');
update();
async function update(){

    section.innerHTML+= `
    <form>
        <label for="user">Username: </label>
        <input id="updateuser" name="user" type="text" />
        <label for="pass"> Password: </label>
        <input id="updatepass" name="pass" type="password" />
        <button type="button" id="update">Update</button>
        
    </form>`;

    let up=document.getElementById('update');
    up.onclick=updateUser;


}

// async function updateUser(){
    
//     console.log("hoaj");
//     let url = baseUrl + '/users/' + loggedUser.id;
//     url += 'user=' + document.getElementById('updateuser').value + '&';
//     url += 'pass=' + document.getElementById('updatepass').value;
//     let response = await fetch(url, {method: 'PUT'});
//     switch (response.status) {
//         case 200:
//             alert('You update the user ');
//             break;
//         case 409:
//             alert('That error');
//             break;
//         case 401:
//             alert('Hold on, you\'re not logged in!');
//             break;
//         default:
//             alert('Something went wrong.');
//             break;
//     }


async function update() {
    let url = baseUrl + '/users';
    // let response = await fetch(url);
    // if (response.status === 200) loggedUser = await response.json();
    // setNav();


    nav.innerHTML += `<form>
    <label for="updateUser">Username: </label>
    <input id="updateUser" name="user" type="text" />
    <label for="updatePass">Password: </label>
    <input id="updatePass" name="pass" type="password" />
    <button type="button" id="updateSubmitBtn">Update User</button>
    </form>`;

    let updateSubmitBtn = document.getElementById('updateSubmitBtn');
    updateSubmitBtn.onclick = updateSubmit;
    

    async function updateSubmit(){
        let usr = {};
        usr.username = document.getElementById('updateUser').value;
        usr.password = document.getElementById('updatePass').value;

        const options = {
            method: 'PUT',
            body: JSON.stringify(usr),
            headers: {
                'Content-Type': 'application/json'
            }
        };
            let url = baseUrl + '/users/' + loggedUser.id;
            let response = await fetch(url, options);

            switch (response.status) {
                case 202: // successful
                   //loggedUser = await response.json();
                    setNav();
                    break;

                default: // other error
                    alert('Something went wrong.');
                    break;
            }
    }
}






   

