var baseUrl = 'http://localhost:8080';
var user = null;
var claimsDiv;
var claims;

checkLogin().then(getClaimsList);

async function checkLogin() {
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200) {
        user = await response.json();
        //console.log(user);
    } else if (response.status === 400) {
        console.log('no logged in user');
    }
}

async function getClaimsList() {
    if (user.role.id == 4) {
        //Employee
        //show all claims made

        //fetch all claims by this user
        let url = baseUrl + '/claims/person/' + user.id;
        let response = await fetch(url);

        //update the html
        if (response.status === 200) {
            claims = await response.json();
            updateClaims();
        } else if (response.status === 400){
            console.log("failed");
        }
    } else if (user.role.id <= 3) {
        //Direct Supervisor
        //show all claims made that have been approved by no one
        
        //fetch all claims that have not been approved by the direct supervisor
        let url = baseUrl + '/claims/';

        if(user.role.id == 3){
            url += 'ds';
        } else if(user.role.id == 2){
            url += 'dh';
        } else if(user.role.id == 1){
            url += 'bc';
        }

        let response = await fetch(url);

        //update the html
        if (response.status === 200) {
            claims = await response.json();
            updateClaims();
        } else if (response.status === 400){
            console.log("failed");
        }
    }
}

async function updateClaims() {
    currentClaimID = null;
    claimsDiv = document.getElementById('claimsDiv');
    claimsDiv.innerHTML = '';

    

    

    for (let i in claims) {
        //console.log(claims[i]);


        let claim = claims[i];
        let date = claim.eventDate;
        let claimHTML = `<div id="claim${claim.id}" class="container justify-content-center claim" onclick="viewClaimDetails(${i})">
            <div class="row claimTitleDate">
                <div class="col" >
                    <h2>${claim.title}</h2>
                </div>
                <div class="col" >
                    <h4 class="claimDate">${date.dayOfWeek} ${date.monthValue}/${date.dayOfMonth}/${date.year}</h4>
                </div>
            </div>

            <div class="row">
                <div class="col-1 statusCol" >
                    <h4>Status:</h4>
                </div>
                <div class="col-6 statusCol" >
                    <h4>${claim.approvalStage.name}</h4>
                </div>
            </div>`;

        claimsDiv.innerHTML += claimHTML;
    }


}

async function viewClaimDetails(index) {
    //console.log(id);
    let claim = claims[index];
    let date = claim.eventDate;

    let claimHTML = `<div id="claim${claim.id}" class="container justify-content-center claim">
                        <div class="row claimTitleDate">
                            <div class="col" >
                                <h2>${claim.title}</h2>
                            </div>
                            <div class="col" >
                                <h4 class="claimDate">${date.dayOfWeek} ${date.monthValue}/${date.dayOfMonth}/${date.year}</h4>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-1 statusCol">
                                <h4>Status:</h4>
                            </div>
                            <div class="col-10 statusCol">
                                <h4>${claim.approvalStage.name}</h4>
                             </div>
                             <div class=col-1 statusCol">
                                <h4>$${claim.price}</h4>
                             </div>
                        </div>

                        <div class="row">
                            <div id="description" class="col justify-content-center">
                                <h6>${claim.description}</h6>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col justify-content-left">
                                <h6>Justification: </h6>
                            </div>
                            <div class="col justify-content-center">
                                <h6>${claim.justification}</h6>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col justify-content-left">
                                <h6>Location: </h6>
                            </div>
                            <div class="col justify-content-center">
                                <h6>${claim.event.location}</h6>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col justify-content-left">
                                <h6>Passing percentage: </h6>
                            </div>
                            <div class="col justify-content-center">
                                <h6>${claim.grading.passingPercentage}</h6>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col justify-content-left">
                                <h6>Passing letter: </h6>
                            </div>
                            <div class="col justify-content-center">
                                <h6>${claim.grading.passingLetter}</h6>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col justify-content-left">
                                <h6>Type: </h6>
                            </div>
                            <div class="col justify-content-center">
                                <h6>${claim.event.eventType}</h6>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col justify-content-left">
                                <h6>Percentage covered: </h6>
                            </div>
                            <div class="col justify-content-center">
                                <h6>${claim.event.percentageCovered}</h6>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col justify-content-left">
                                <h6>Hours missed: </h6>
                            </div>
                            <div class="col justify-content-center">
                                <h6>${claim.hoursMissed}</h6>
                            </div>
                        </div>

                    `;//</div>

    if(user.role.id == 4){
        claimHTML += `</div>`;
    } else if(user.role.id <= 3){
        //console.log(claim.dsa);
        //console.log(user);
        if(user.role.id <= 2 && claim.dsa != null){
            console.log('add dsa');
            claimHTML += `<div class="row">
                                <div class="col">
                                    <h6>Direct Supervisor approval:</h6>
                                </div>
                                <div class="col">
                                    <h6>${claim.dsa.firstName} ${claim.dsa.lastName}</h6>
                                </div>
                            </div>`
        }
        if(user.role.id <= 1 && claim.dsa != null && claim.dha != null){
            claimHTML += `<div class="row">
                                <div class="col">
                                    <h6>Direct Head approval:</h6>
                                </div>
                                <div class="col">
                                    <h6>${claim.dha.firstName} ${claim.dha.lastName}</h6>
                                </div>
                            </div>`
        }
        claimHTML += `
                        <div class="row">
                            <div class="col justify-content-left">
                                <button type="button" onclick="acceptClaim(${index})">Accept</button>
                            </div>

                            <div class="col justify-content-left">
                                <button type="button" onclick="denyClaim(${index})">Deny</button>
                            </div>

                            <div class="col justify-content-left">
                                <input type="text" placeholder="Denial reason"/>
                            </div>
                        </div>
                    </div>`;
    }

    claimsDiv.innerHTML = claimHTML;
}

async function acceptClaim(index){
    console.log(claims[index]);
    let url = baseUrl + '/claims/accept/' +  claims[index].id;
    //console.log(url);
    let response = await fetch(url, {method: 'POST', body: JSON.stringify(claims[index])});

    if(response.status === 200){
        getClaimsList();
    } else {
        getClaimsList();
        alert('something went wrong');
    }
}

async function denyClaim(index){

}