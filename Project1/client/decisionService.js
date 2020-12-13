
const postDecision = async (decision) => {
    let response = await fetch(`http://localhost:4000/api/decisions`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(decision)
    }); 
    return response; 
}
