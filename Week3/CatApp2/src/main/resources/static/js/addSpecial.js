let specialSection = document.getElementById("specialSection");
    
specialSection.innerHTML = `
    <label for='addNeed'>Type the name of a special need to add</label>
    <input type='text' id="need" placeholder='Special Need'>
    <input type='button' id='submit' value='Submit'>
`;

document.getElementById('submit').addEventListener('click', addSpecialNeeds);

async function addSpecialNeeds() {

    let url = baseUrl + '/need';

    //console.log(JSON.stringify(need));
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: document.getElementById('need').value
    });

}