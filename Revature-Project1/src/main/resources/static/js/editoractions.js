
necessarySetup();


async function necessarySetup(){
    let url = baseUrl + '/users';
    let response = await fetch(url);
    if (response.status === 200){ 
    	currentUser = await response.json() 
    };
	
	let urlStoryPitch = baseUrl + "/approval/editor/" + currentUser.editorId;
	console.log(urlStoryPitch);
	let responseStoryPitch = await fetch(urlStoryPitch);
	
	if (responseStoryPitch.status === 200){
		let normalSection = document.getElementById("main-part");
		
		let workToApproveOrNot = document.createElement("ul");
		
		console.log(responseStoryPitch);
		
		
		for(storyPitch of response){
			let li = document.createElement("li");
			//li.innerHTML = `${}`;
		}
		
	}
}