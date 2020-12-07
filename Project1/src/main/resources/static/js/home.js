var baseUrl = 'http://localhost:8080';
var user = null;
var claimsDiv;

checkLogin().then(updateClaims);

async function checkLogin(){
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if(response.status === 200){
        user = await response.json();
        //console.log(user);
    } else if (response.status === 400){
        console.log('no logged in user');
    }
}

async function updateClaims(){
    claimsDiv = document.getElementById('claimsDiv');
    claimsDiv.innerHTML = '';

    //fetch all claims by this user
    let url = baseUrl + '/claims/person/' + user.id;
    let response = await fetch(url);


    if(response.status === 200){
        claims = await response.json();

        for(let i in claims){
            console.log(claims[i]);

            
            let claim = claims[i];
            let date = claim.eventDate;
            let claimHTML = `<div class="container justify-content-center claim">
            <div class="row claimTitleDate">
                <div class="col">
                    <h2>${claim.title}</h2>
                </div>
                <div class="col">
                    <h4 class="claimDate">${date.dayOfWeek} ${date.monthValue}/${date.dayOfMonth}/${date.year}</h4>
                </div>
            </div>

            <div class="row">
                <div class="col-1 statusCol">
                    <h4>Status:</h4>
                </div>
                <div class="col-6 statusCol">
                    <h4>${claim.approvalStage.name}</h4>
                </div>
            </div>`;

            claimsDiv.innerHTML += claimHTML;
        }

    } else if(response.status === 404){
        console.log('fail');
    }
}