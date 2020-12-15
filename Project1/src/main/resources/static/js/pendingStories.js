setNav();
getStories();

async function getStories() {
    let url = baseUrl + '/stories?';
    url += 'id=' + localStorage.getItem('id') + '&';
    url += 'role=' + localStorage.getItem('roleId');
    let response = await fetch(url, {method: 'PUT'});
    if (response.status == 200) {
        let stories = await response.json();
        populateStories(stories);
    }
}

function populateStories(stories) {
    let storySection = document.getElementById('pendingStories');

    console.log(stories);

    if (stories.length > 0) {
        let table = document.createElement('table');
        table.id = "pendingTable";

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
        
       let i = 0
    for (let story of stories) {
        let tr = document.createElement('tr');
            
    let genre;
    let type;
    let status;

    switch(story.genre) {
        case 1:
            genre = "adventure";
            break;
        case 2:
            genre = "biography";
            break;
        case 3:
            genre = "drama";
            break;
        case 4:
            genre = "fantasy";
            break;
        case 5:
            genre = "history";
            break;
        case 6:
            genre = "horror";
            break;
        case 7:
            genre = "science fiction";
            break;
        case 8:
            genre = "self help";
            break;
        case 9:
            genre = "travel";
            break;
    }

            switch(story.storyType) {
                case 1:
                    type = "novel";
                    break;
                case 2:
                    type = "novella";
                    break;
                case 3:
                    type = "short story";
                    break;
                case 4:
                    type = "article";
                    break;
            }

            if (story.status == 1) {
                status = "hold";
            } else if (story.status > 0 && story.status < 8) {
                status = "pending";
            } else if (story.status == 8){
                status = "approved";
            } else {
                status = "rejected";
            }

    let submitted = story.submissionDate.year + "-" + story.submissionDate.monthValue + "-" + story.submissionDate.dayOfMonth;

    tr.innerHTML = `
        <td>${story.title}</td>        
        <td>${story.firstName + " " + story.lastName}</td>
        <td>${genre}</td>
        <td>${type}</td>
        <td>${story.tagline}</td>
        <td>${story.description}</td>
        <td>${status}</td>
        <td>${submitted}</td>
        <td>${story.completionDate}</td>
    `;

            table.appendChild(tr);
            i+=1;
    }

        storySection.appendChild(table);
        let actionForm = document.createElement('form');
        actionForm.innerHTML = `
            <label for="num">Input index to approve/deny</label>
            <input type="text" id="num">
            <input type="button" id="approve" value="Approve">
            <input type="button" id="deny" value="Deny">
            <input type="button" id="req" value="Request">
        `;

        storySection.appendChild(actionForm);

        document.getElementById('approve').addEventListener('click', function() {
            approve(stories[document.getElementById('num').value]);
        });
        document.getElementById('deny').addEventListener('click', function(){
            deny(stories[document.getElementById('num').value]);
        });
        document.getElementById('req').addEventListener('click', function(){
            requestMore(stories[document.getElementById('num').value]);
        });

    } else {
        storySection.innerHTML = 'You don\'t have any pending stories.';
    }
}

async function approve (story) {
    console.log(story.id);
    let url = baseUrl + '/approve?';
    url += 'id=' + story.id;
    let response = await fetch(url, {method: 'PUT'});
    window.location.replace("index.html");
}

async function deny (story) {
    let url = baseUrl + '/deny?';
    url += 'id=' + story.id;
    let response = await fetch(url, {method: 'PUT'});
    window.location.replace("index.html");
}

async function requestMore (story) {
    let url = baseUrl + '/req?';
    url += 'id=' + story.id;
    let response = await fetch(url, {method: 'PUT'});
    window.location.replace("index.html");
}

function viewBookInfo(story) {
    let genre;
    let type;
    let status;

    table.innerHTML = "";

    switch(story.genre) {
        case 1:
            genre = "adventure";
            break;
        case 2:
            genre = "biography";
            break;
        case 3:
            genre = "drama";
            break;
        case 4:
            genre = "fantasy";
            break;
        case 5:
            genre = "history";
            break;
        case 6:
            genre = "horror";
            break;
        case 7:
            genre = "science fiction";
            break;
        case 8:
            genre = "self help";
            break;
        case 9:
            genre = "travel";
            break;
    }

            switch(story.storyType) {
                case 1:
                    type = "novel";
                    break;
                case 2:
                    type = "novella";
                    break;
                case 3:
                    type = "short story";
                    break;
                case 4:
                    type = "article";
                    break;
            }

            if (story.status == 1) {
                status = "hold";
            } else if (story.status > 0 && story.status < 8) {
                status = "pending";
            } else {
                status = "approved";
            }

    let submitted = story.submissionDate.year + "-" + story.submissionDate.monthValue + "-" + story.submissionDate.dayOfMonth;

    tr.innerHTML = `
        <td>${story.title}</td>
        <td>${story.firstName + " " + story.lastName}</td>;
        <td>${genre}</td>
        <td>${type}</td>
        <td>${story.tagline}</td>
        <td>${story.description}</td>
        <td>${status}</td>
        <td>${submitted}</td>
        <td>${story.completionDate}</td>
    `;

    table.appendChild(tr);
}