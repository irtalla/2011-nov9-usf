let registerSection = document.getElementById('registerSection');
let div= document.createElement('div'); 

div.classList.add("container");

div.innerHTML = `
	<div class="row col-md-6 col-md-offset-3">
		<div class="panel panel-primary">
			<div class="panel-heading text-center">Registration Form</div>

			<div class="panel-body">
				<form>
						<div class="form-div form-group"> 
							<label for="username">Username</label> 
							<input id="username" class="form-control"> 
						</div>
						<div class="form-div form-group"> 
							<label for="password">Password</label> 
							<input id="password" class="form-control"> 
						</div>
						<div class="form-div form-group"> 
							<label for="roles">User Role</label>
							<select name="roles" id="roles">
								<option value="1">1. Requestor</option>
								<option value="2">2. Approver</option>
							</select>	
						</div>
						<div class="form-div form-group"> 
							<label for="titles">User Title</label>
							<select name="titles" id="titles">
								<option value="Employee">1. Employee</option>
								<option value="Benefits Coordinator">2. Benefits Coordinator</option>
								<option value="Direct Supervisor">3. Direct Supervisor</option>
								<option value="Deparment Head">4. Deparment Head</option>
							</select>
						</div>
						<br />
<<<<<<< HEAD
						<input class="btn btn-primary" type='submit' onClick="register()" > 	
=======
						<input type='submit' onClick="register()" > 	
>>>>>>> df51458cb2f15ea4949e5418db43191105523d0a
					</div>
				</form>

			</div>

		<div class="panel-footer text-right">&copy; Revature</div>
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