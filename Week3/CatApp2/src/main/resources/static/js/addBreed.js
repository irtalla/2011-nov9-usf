checkLogin().then(setupBreed);

function setupBreed(){
    let breedSection = document.getElementById('breedSection');
    let label = document.createElement('label');
    let input = document.createElement('input');
    label.innerHTML('Please enter new breed name: ');
    input.type = "text";
    input.placeholder = "Breed name here";
    input.id = "id";
    let inputButton = document.createElement('button');
    inputButton.type = 'button';
    inputButton.textContent = 'create breed';
    inputButton.disabled = !loggedUser;
    //check if user is employee here
    //inputButton.disabled = !loggedUser

    breedSection.appendChild(label);
    breedSection.appendChild(input);
    breedSection.appendChild(inputButton);

    inputButton.addEventListener('click', addBreed);
}

async function addBreed(){
    let input = document.getElementById('id').value;
    let url = baseUrl + '/breeds?';
    url += 'breed=' + input;
    let response = await fetch(url, {method: 'POST'});

    if(response.status === 200){
        alert('Successfully added');
    }
    else{
        alert('Error');
    }
}