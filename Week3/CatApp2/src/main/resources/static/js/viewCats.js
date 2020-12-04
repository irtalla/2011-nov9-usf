getCats();
let addCatMenuOpen = false;

if (loggedUser.role.name === 'Employee') employeeSetup();

async function getCats() {
    let url = baseUrl + '/cats';
    let response = await fetch(url);
    if (response.status === 200) {
        let cats = await response.json();
        populateCats(cats);
    }
}

function populateCats(cats) {
    let catSection = document.getElementById('catSection');

    if (cats.length > 0) {
        let table = document.createElement('table');
        table.id = 'catTable';

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Breed</th>
                <th>Special Needs</th>
                <th></th>
            </tr>
        `;

        for (let cat of cats) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${cat.id}</td>
                <td>${cat.name}</td>
                <td>${cat.age}</td>
                <td>${cat.breed.name}</td>
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');
            for (let sn of cat.specialNeeds) {
                let li = document.createElement('li');
                li.innerHTML = sn.name;
                ul.appendChild(li);
            }
            td.appendChild(ul);
            tr.appendChild(td);

            let adoptBtn = document.createElement('button');
            adoptBtn.type = 'button';
            adoptBtn.id = cat.name + '_' + cat.id;
            adoptBtn.textContent = 'Adopt';
            adoptBtn.disabled = !loggedUser;
            // <button type="button" id="Howard_6"
            //  disabled="false">Adopt</button>
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(adoptBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);
            
            adoptBtn.addEventListener('click', adoptCat);
        }

        catSection.appendChild(table);
    } else {
        catSection.innerHTML = 'No cats are available.';
    }
}

async function adoptCat() {
    let btnId = event.target.id;
    let index = btnId.indexOf('_'); // find underscore (see line 46)
    let id = btnId.slice(index+1); // get text after underscore
    let name = btnId.replace('_', ''); // remove underscore
    if (confirm('You want to adopt ' + name + '?')) {
        let url = baseUrl + '/cats/adopt/' + id;
        let response = await fetch(url, {method:'PUT'});
        switch (response.status) {
            case 200:
                alert('You adopted ' + name + '!');
                break;
            case 409:
                alert('That cat doesn\'t seem to exist...');
                break;
            case 401:
                alert('Hold on, you\'re not logged in!');
                break;
            default:
                alert('Something went wrong.');
                break;
        }
    }
}

function employeeSetup() {
    let employeeSpan = document.getElementById('emp');
    employeeSpan.hidden = 'false';
    // add cat
    let addCatBtn = document.createElement('button');
    addCatBtn.type = 'button';
    addCatBtn.textContent = 'Add Cat';
    addCatBtn.id = 'addCatBtn';
    addCatBtn.onclick = addCatMenu;

    // edit cat
    let catsTable = document.getElementById('catTable');
    for (let tr of catsTable.childNodes) {
        let td = document.createElement('td');
        if (tr != catsTable.childNodes[0]) {
            let editBtn = document.createElement('button');
            editBtn.id = 'edit_' + tr.childNodes[0].textContent;
            editBtn.type = 'button';
            editBtn.textContent = 'Edit';
            editBtn.onclick = editCat;
            td.appendChild(editBtn);
        }
        tr.appendChild(td);
    }
}

function addCatMenu() {
    let employeeSpan = document.getElementById('emp');
    addCatMenuOpen = !addCatMenuOpen;

    if (addCatMenuOpen) {
        employeeSpan.innerHTML += `<form id="add-cat-form">
        <label for="name">Name:</label>
        <input type="text" id="name" placeholder="name"/>
        
        <label for="age">Age:</label>
        <input type="text" id="age" placeholder="age"/>
        
        <label for="breed">Breed:</label>
        <input type="text" id="breed" placeholder="breed"/>

        <button onclick="addCat()" id="submit-add-cat-form" >"Add Cat"</button>
        </form>
        `;
    }
}

function editCat() {
    let btnId = event.target.id;
}