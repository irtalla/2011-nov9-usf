checkLogin().then(populateCats);

function populateCats() {
    let cats = loggedUser.cats;
    let catSection = document.getElementById('catSection');

    if (cats.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Breed</th>
                <th>Special Needs</th>
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
            table.appendChild(tr);
        }

        catSection.appendChild(table);
    } else {
        catSection.innerHTML = 'You don\'t have any cats. :(';
    }
}

async function addCat(){
    const name = document.getElementById("name").value;
    const age = document.getElementById("age").value;
    const breed = document.getElementById("breed").value;

    if(!name || !age || !breed){
        alert("Invalid Input!");
        return;
    }

    let url = baseUrl + "/cats";
    let response = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': "appliction/json"
        },
        body: {
            'name': name,
            'age': age,
            'breed': breed
        }
    });

    if(response.status === 200){
        alert("Cat added!");
    }else{
        alert("Cat not added!");
    }
}