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
    let storySection = document.getElementById('myStories');

    console.log(stories);

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
            } else if (story.status > 1 && story.status < 8) {
                if (story.status % 2 == 0)
                    status = "pending";
                else
                    status = "request";
            } else if (story.status == 8) {
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
        }

        storySection.appendChild(table);
    } else {
        storySection.innerHTML = 'You don\'t have any submitted stories.';
    }
}