checkLogin().then(getPitches);

 async function getPitches(){
        let url = baseUrl + 'committeepitches';
        let response = await fetch(url);
        if(response.status === 200) {
            let requests = await response.json();
            populatePitches(requests);
        }
    }

