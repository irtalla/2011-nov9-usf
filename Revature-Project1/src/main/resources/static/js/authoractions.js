

async function addAProposedWork(){
	url = baseUrl + "/proposedWork";
	
	let supposedDate = document.getElementById("date").value;

	
	
	
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
			author: "random replaced text",
			title: document.getElementById("title").value,
			genre: {id: genreID,
					name: genreName},
			description: document.getElementById("description").value,
			lengthOfWork: {id: lengthOfWorkID,
						   name: lengthOfWorkName,
						   associatedPoints: lengthOfWorkPoints},
			date: supposedDate
	};
	
	let response = await fetch(url, {
		method: 'POST',
		body: literaryWorkObject
	});
	
	switch (response.status) {
    case 200:
     	console.log("At least it went through nicely.")
        currentUser = await response.json();
        if (currentUser){
        	alert(`Logged into ${currentUser.username}`);
        }
        break;
    default: // other error
     	console.log("There is no upside at this point")
        alert('An unknown error happened, which sucks because we can\'t hint at it.');
        break;

 }
	
	
}

document.getElementById("new-pitch").onclick = addAProposedWork;