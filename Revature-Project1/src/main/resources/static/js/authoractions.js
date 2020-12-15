
document.getElementById("new-pitch").onclick = addAProposedWork;
document.getElementById("logout").onclick = logout;

async function addAProposedWork(){
	url = baseUrl + "/proposedWork";
	
	let supposedDate = document.getElementById("date").value;
	let date = new Date();
	date.setMonth(parseInt(supposedDate.substring(0, 2), 10));
	date.setDate(parseInt(supposedDate.substring(3, 5), 10));
	date.setYear(parseInt(supposedDate.substring(6, 10), 10));
	date.setHours(0);
	date.setMinutes(0);
	date.setSeconds(0);
	date.setMilliseconds(0);
	
	
	
	let genreName = document.getElementById("genre").value;
	let genreID = 0;
	
	if (genreName === "science-fiction"){
		genreID = 1;
	}
	else if (genreName === "non-fiction"){
		genreID = 2;
	}
	else if (genreName === "fantasy"){
		genreID = 3;
	}
	else if (genreName === "history"){
		genreID = 5;
	}
	else if (genreName === "fashion"){
		genreID = 6;
	}
	else if (genreName === "gothic-horror"){
		genreID = 7;
	}
	else if (genreName === "realistic-fiction"){
		genreID = 8;
	}
	else if (genreName === "young adult"){
		genreID = 9;
	}
	else if (genreName === "epic"){
		genreID = 10;
	}
	
	
	let lengthOfWorkName = document.getElementById("literary-work-length").value;
	let lengthOfWorkID = 0;
	let lengthOfWorkPoints = 0;
	
	if (lengthOfWorkName === "article"){
		lengthOfWorkID = 1;
		lengthOfWorkPoints = 10;
	}
	else if (lengthOfWorkName === "short story"){
		lengthOfWorkID = 2;
		lengthOfWorkPoints = 20;
	}
	else if (lengthOfWorkName === "novella"){
		lengthOfWorkID = 3;
		lengthOfWorkPoints = 25;
	}
	else if (genreName === "novel"){
		lengthOfWorkID = 4;
		lengthOfWorkPoints = 50;
	}
	
	
	let literaryWorkObject = {
			id: 0,
			author: currentUser,
			title: document.getElementById("title").value,
			genre: {id: genreID,
					genreName: genreName},
			description: document.getElementById("description").value,
			lengthOfWork: {id: lengthOfWorkID,
						   name: lengthOfWorkName,
						   associatedPoints: lengthOfWorkPoints},
			tentativeCompletionDate: date
	};
	
	currentUser.pointsRemaining -= lengthOfWorkPoints;
	if (currentUser.pointsRemaining < 0){
		alert("Warning! You cannot pitch the " + lengthOfWorkName + " because you do not have enough points.");
		currentUser.pointsRemaining;
		return;
	}
	
	let response = await fetch(url, {
		method: 'PUT',
		body: JSON.stringify(literaryWorkObject)
	});
	
	switch (response.status) {
    case 200:
    	let theResponse = await response.json();
     	console.log("You have submitted your pitch for " + title + " the " + genreName + " " + lengthOfWorkName);
        break;
    default: // other error
     	console.log("There is no upside at this point")
        alert('An unknown error happened, which sucks because we can\'t hint at it.');
        break;

 }
	
	
}

async function logout(){
	url = baseUrl + "/users";
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedUser = null;
}