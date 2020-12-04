checkLogin().then(populateNeeds);
/*setTimeout(function(){populateNeeds();}, 50);*/
document.getElementById("loadBtn").addEventListener("click", function() {populateNeeds();});
document.addEventListener('click',function(e){
    if(e.target && e.target.id== 'loginBtn'){
		setTimeout(function(){populateNeeds();}, 50);
     }
 });
function populateNeeds() {
	let needSection = document.getElementById('needSection');
	if(loggedUser!=null && loggedUser.role.name=='Employee'){
		 needSection.innerHTML = `
            <form>
				<br>
                <label for="need">Need: </label>
                <input id="need" name="need" type="text" />
                <button type="button" id="needBtn">inputneed</button>
            </form>
        `;

		document.getElementById('needBtn').onclick = addNeed;
	}else{
		 needSection.innerHTML = 'You are not an employee';
	}
}

async function addNeed() {
	console.log('adding');
    let url = baseUrl + '/cats/specialneeds?';
	url += 'need=' + document.getElementById('need').value;
    let response = await fetch(url, {method: 'PUT'});

 switch (response.status) {
        case 200: // successful
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}