let viewableDrafts = [];
getViewableDrafts();

function populateSections(){
    document.getElementById("draftsSection").innerHTML = getListOfViewableDrafts();
}

async function getViewableDrafts(){
    let response = await fetch(baseUrl + "/drafts", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loggedUser)
    });
    if(response.status == 200){
        viewableDrafts = await response.json();
    }else{
        alert("Could not retrieve viewable drafts.")
    }
}

function getListOfViewableDrafts(){
    let str = `<ul>`;

    viewableDrafts.forEach(draft => {
        str += `<li><a href="draft.html">${draft.pitch.title} by ${draft.pitch.pseudoFirstName} + ${draft.pitch.pseudoLastName}</a></li>`
    });

    str += `</ul>`;

    return str;
}