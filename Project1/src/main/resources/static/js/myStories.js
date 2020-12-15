setNav();
populateCats();

async function getStories() {
    let url = baseUrl + '/stories?';
    url += 'id=' + localStorage.getItem('id') + '&';
    url += 'role=' + localStorage.getItem('roleId');
    let response = await fetch(url, {method: 'PUT'});
    if (response.status == 200) {
        let stories = await response.json();
        populateStories();
    }
}

function populateStories() {
    let storySection = document.getElementById('myStories');

    if (stories.length > 0) {
        let table = document.createElement('table');

        table.innerHTML = `
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Type</th>
                <th>Tagline</th>
                <th>Description</th>
                <th>Status</th>
                <th>Submitted</th>
                <th>Completion</th>
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
        storySection.innerHTML = 'You don\'t have any submitted stories.';
    }
}