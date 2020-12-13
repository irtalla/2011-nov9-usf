let baseUrl = 'http://localhost:8080';
let loggedUser = null;
let author = null;
let retrievedPitches = null;
let retrievedMessages = null;


getUser();


async function getUser() {
    // userController checkLogin()
    let url = baseUrl + '/users';
    let response = await fetch(url, {credentials: 'include'});
    if (response.status === 200){
        loggedUser = await response.json();
        getAuthorEditor();
    }else{
        window.location.href = "D:/2011-nov9-usf/Project1/spms/src/main/resources/static/login.html"
    }
}

async function getAuthorEditor(){
    let url = baseUrl + '/users/' + loggedUser['id'];
    let response = await fetch(url, {credentials: 'include'});
    if (response.status === 200){
        author = await response.json();
        setUp();
    }else{
        alert('Error getting User Data');
    }
}

function setUp(){
    document.getElementById('welcome_text').innerHTML = 'Welcome ' + author['firstName'] + ".";
    document.getElementById('points').innerHTML = 'Points: ' + author['points'];

    inPitchesTab();
}

function inPitchesTab(){
    // Setup
    document.getElementById('list-element-pitches').className = 'active';
    document.getElementById('list-element-messages').className = '';
    document.getElementById('pitch-list').innerHTML = ` 
        <div id="view-pitch">
            <h3>View Pitch</h3>
            <div id="pitch-body" class="text-center no-content">
                No selected pitch
            </div>
        </div>

        <hr class="divider my-4" />

        <h3 class="text-center">Submitted Pitches and Stories</h3>
        <table class="table table-striped">
            <tr>
                <a>
                <th>Pitch</td>
                    <td>Pitch Title</td>
                    <td>Status: Awaiting Approval</td>
                </a>
            </tr>
            <tr>
                <th>Pitch</th>
                <th colspan="2" class="text-center" ><a>Pitch Title</a></th>
            </tr>
            <tr>
                <th>Pitch</th>
                <th colspan="2" class="text-center" ><a>Pitch Title</a></th>
            </tr>
            <tr>
                <th>Pitch</th>
                <th colspan="2" class="text-center" ><a>Pitch Title</a></th>
            </tr>
            <tr>
                <th>Pitch</th>
                <th colspan="2" class="text-center" ><a>Pitch Title</a></th>
            </tr>
        </table>
    `;

    // Listeners
    let newPitch = document.getElementById('pitch-button');
    newPitch.onclick = inNewPitchTab;
    let messages = document.getElementById('list-element-messages')
    messages.onclick = inMessagesTab;
    let logoutBtn = document.getElementById('logout');
    logoutBtn.onclick = logout;
}

function inMessagesTab(){

    // Setup
    document.getElementById('list-element-pitches').className = '';
    document.getElementById('list-element-messages').className = 'active';
    document.getElementById('pitch-list').innerHTML = ` 
        <div id="view-pitch">
            <h3>View Messages</h3>
            <div id="pitch-body" class="text-center no-content">
                No selected message
            </div>
        </div>

        <hr class="divider my-4" />

        <h3 class="text-center">Submitted Pitches and Stories</h3>
        <table class="table table-striped">`;


        /*
            <tr>
                <a>
                <th>Pitch</td>
                    <td>Pitch Title</td>
                    <td>Status: Awaiting Approval</td>
                </a>
            </tr>
            <tr>
                <th>Pitch</th>
                <th colspan="2" class="text-center" ><a>Pitch Title</a></th>
            </tr>
            <tr>
                <th>Pitch</th>
                <th colspan="2" class="text-center" ><a>Pitch Title</a></th>
            </tr>
            <tr>
                <th>Pitch</th>
                <th colspan="2" class="text-center" ><a>Pitch Title</a></th>
            </tr>
            <tr>
                <th>Pitch</th>
                <th colspan="2" class="text-center" ><a>Pitch Title</a></th>
            </tr>
        </table>
    `;*/

    // Listeners
    let newPitch = document.getElementById('pitch-button');
    newPitch.onclick = inNewPitchTab;
    let pitches = document.getElementById('list-element-pitches')
    pitches.onclick = inPitchesTab;
    let logoutBtn = document.getElementById('logout');
    logoutBtn.onclick = logout;
}


function inNewPitchTab(){
    console.log('in new pitch tab')

    // Setup
    document.getElementById('list-element-pitches').className = '';
    document.getElementById('list-element-messages').className = '';
    document.getElementById('pitch-list').innerHTML = `
    <form class="compose-pitch">
        <div class="row">
            <h3> New Pitch </h3>
        </div>
        <div class="row">
            <h5> Anything field with * is required.</h5>
        </div>
        <div class="row align-items-center ">
            <div class="col-auto ">
                <label for="pitch-title" >Title *: </label>
            </div>
            <div class="col-auto">
                <input id="pitch-title" name="pitch-title" type="text" class="form-control" placeholder="Title"/>
            </div>
        </div>
        <div class="row align-items-center ">
            <div class="col-auto">
                <label for="pitch-complettion-date" >Completion Date *: </label>
            </div>
            <div class="col-auto">
                <input id="pitch-completion-date" name="pitch-completion-date" type="date" class="form-control" placeholder="Completion Date"/>
            </div>
        </div>
        <div class="row align-items-center ">
            <div class="col-auto">
                <label for="pitch-story-type" >Story Type *: </label>
            </div>
            <div class="col-auto">
                <select name="pitch-story-type" id="pitch-story-type" class="form-control" style="font-size: medium;">
                        <option value="novel">Novel (50p)</option>
                        <option value="novellas">Novella (25p)</option>
                        <option value="short stories">Short Story (20p)</option>
                        <option value="articles">Article (10p)</option>
                </select>
            </div>
        </div>
        <div class="row align-items-center ">
            <div class="col-auto">
                <label for="pitch-genre" >Genre *: </label>
            </div>
            <div class="col-auto">
                <select name="pitch-genre" id="pitch-genre" class="form-control" style="font-size: medium;">
                        <option value="action">Action</option>
                        <option value="adventure">Adventure</option>
                        <option value="fantasy">Fantasy</option>
                        <option value="horror">Horror</option>
                        <option value="mystery">Mystery</option>
                        <option value="romance">Romance</option>
                        <option value="science_fiction">Science Fiction</option>
                </select>
            </div>
        </div>
        <div class="row align-items-center ">
            <div class="col-auto">
                <label for="pitch-tagline" >Tagline *: </label>
            </div>
            <div class="col-auto">
                <input id="pitch-tagline" name="pitch-tagline" type="text" class="form-control" placeholder="Tagline"/>
            </div>
        </div>
        <div class="row align-items-center ">
            <div class="col-auto">
                <label for="pitch-description" >Detailed Description *: </label>
            </div>
            <div class="col-auto">
                <input id="pitch-description" name="pitch-description" type="text" class="form-control" placeholder="Description"/>
            </div>
        </div>
        <div class="row align-items-center ">
            <div class="col-auto">
                <label for="pitch-story">Story: </label>
            </div>
            <div class="col-auto">
                <textarea id="pitch-story" name="pitch-story" rows="4"  cols="70" class="form-control"> </textarea>
            </div>
        </div>
        <div class="col text-center">
            <button type="button" class="btn btn-light btn-l" id="submitBtn">Submit</button>
        </div>
    </form>
    `;

    //Listeners
    let newPitch = document.getElementById('pitch-button');
    newPitch.onclick = inNewPitchTab;
    let pitches = document.getElementById('list-element-pitches')
    pitches.onclick = inPitchesTab;
    let messages = document.getElementById('list-element-messages')
    messages.onclick = inMessagesTab;
    let submitPitchBtn = document.getElementById('submitBtn');
    submitPitchBtn.onclick = submitPitch;
    let logoutBtn = document.getElementById('logout');
    logoutBtn.onclick = logout;
}



async function submitPitch(){
    let story = {};
    story.id=0;
    story.author = author;
    story.title = document.getElementById('pitch-title').value;
    story.completionDate = document.getElementById('pitch-completion-date').value;
    story.type = document.getElementById('pitch-story-type').value;
    story.genre = document.getElementById('pitch-genre').value;
    story.tagline = document.getElementById('pitch-tagline').value;
    story.description = document.getElementById('pitch-description').value;
    story.story = document.getElementById('pitch-story').value;

    console.log("JSON: " + JSON.stringify(story));

    let url = baseUrl + '/author';
    let response = await fetch(url, {credentials: 'include', method:'POST', body:JSON.stringify(story)});
    if (response.status == 200){
        alert('Submitted pitch successfully.');
    }else{
        alert('Something went wrong.');
    }
    getAuthorEditor();
}

async function retrievedPitchesFunction(){

}

async function retrievedMessagesFunction(){

}


async function logout() {
    let url = baseUrl + '/users';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) {
        alert('Something went wrong.')
    } else {
        loggedUser = null;
        window.location.href = "D:/2011-nov9-usf/Project1/spms/src/main/resources/static/index.html";
    }
}