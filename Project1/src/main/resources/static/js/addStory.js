let addSection = document.getElementById("addStory");

addSection.innerHTML = `
<form id="submitForm">
    <label for="title">Title</label>
    <input type="text" id="title">
    <label for="genre">Genre</label>
    <select id="genre">
        <option value="1">Adventure</option>
        <option value="2">Biography</option>
        <option value="3">Drama</option>
        <option value="4">Fantasy</option>
        <option value="5">History</option>
        <option value="6">Horror</option>
        <option value="7">Sci-Fi</option>
        <option value="8">Self Help</option>
        <option value="9">Travel</option>
    </select>
    <label>Type</label>
    <select id="type">
        <option value="1">Novel</option>
        <option value="2">Novella</option>
        <option value="3">Short Story</option>
        <option value="4">Article</option>
    </select>
    <label for="tagline">Tagline</label>
    <input type="text" id="tagline">
    <label for="description">Description</label>
    <input type="text" id="description">
    <label for="draft">Draft</label>
    <input type="file" id="draft">
    <label for="completed">Completion Date</label>
    <input type="date" id="completed">
    <input type="button" id="submit" value="Submit">
</form>
`;

document.getElementById("submit").addEventListener("click", addStory);

async function addStory () {
    let dateArray = document.getElementById("completed").value.split('/');
    let date = dateArray[2] + '-' + dateArray[0] + '-' + dateArray[1];

    let story = {};
    story.authorId = localStorage.getItem('id');
    story.title = document.getElementById('title').value;
    story.firstName = localStorage.getItem('firstName');
    story.lastName = localStorage.getItem('lastName');
    story.genre = document.getElementById('genre').value;
    story.storyType = document.getElementById('type').value;
    story.tagline = document.getElementById('tagline').value;
    story.description = document.getElementById('description').value;
    story.filePath = document.getElementById('draft').value;
    story.completionDate = date;

    const options = {
        method: 'POST',
        body: JSON.stringify(story),
        headers: {
            'Content-Type': 'application/json'
        }
    }

    let url = baseUrl + "/stories";

    let response = await fetch(url, options);

    addSection.innerHTML = "Story Added";
}