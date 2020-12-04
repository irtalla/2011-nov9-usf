
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
    let resposne = await fetch(`http://localhost:4000/api/requests/personid${id}`, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
          }
    });
    return resposne;
}