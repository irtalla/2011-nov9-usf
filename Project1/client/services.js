
const checkUser = async (user) => {
    let response = await fetch('http://localhost:4000/api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(user)
    });
    return response;
}


const fetchRequests = async (id) => {
    let resposne = await fetch(`http://localhost:4000/api/requests/personid/${id}`, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    });
    return resposne;
}

const postPitch = async (pitch) => {
    let response = await fetch('http://localhost:4000/api/pitches', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(pitch)
    });
    return response;
}

const closeRequest = async(id) => {

    let response = await fetch(`http://localhost:4000/api/requests/close/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }); 
    return response; 
}

const putUpdatedRequest = async(request) => {

    let response = await fetch(`http://localhost:4000/api/requests`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(request)
    }); 
    return response; 
}