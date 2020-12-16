let registerSection = document.getElementById('registerSection');
let div= document.createElement('div'); 

div.classList.add("container");

div.innerHTML = `
	<div class="container">	
		<form>
				<div class="mb-3"> 
					<label class="form-label" for="username">Username</label> 
					<input class="form-control" id="username" class="form-control"> 
				</div>
				<div class="mb-3"> 
					<label class="form-label" for="password">Password</label> 
					<input class="form-control" id="password" class="form-control"> 
				</div>
				<div class="mb-3"> 
					<label class="form-label" for="roles">User Role</label>
					<select class="form-select" name="roles" id="roles">
						<option value="1">1. Requestor</option>
						<option value="2">2. Approver</option>
					</select>	
				</div>
				<div class="mb-3"> 
					<label class="form-label" for="titles">User Title</label>
					<select class="form-select" name="titles" id="titles">
						<option value="Employee">1. Employee</option>
						<option value="Benefits Coordinator">2. Benefits Coordinator</option>
						<option value="Direct Supervisor">3. Direct Supervisor</option>
						<option value="Deparment Head">4. Deparment Head</option>
					</select>
				</div>
				<br />
				<input class="btn btn-primary" type='submit' onClick="register()" > 	
			</div>
		</form>
	</div>
    `

registerSection.appendChild(div); 

async function register() {
	let registration = {};
	registration.username = document.getElementById("username").value;
	registration.password = document.getElementById("password").value;
    registration.role_id = document.getElementById("roles").value;
    registration.title = document.getElementById("titles").value;

	let url = baseUrl + '/users/';
	let response = await fetch(url, {method:'POST', body:JSON.stringify(registration)});
	 if (response.status === 201) {
        alert('Added user successfully.');
    } else {
        alert('Something went wrong.');
    }

}