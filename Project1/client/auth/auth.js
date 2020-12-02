const authorRedirect = `/Users/majestikmind/Revature_Training/2011-nov9-usf/Project1/client/author/authorDashBoard.html`;
const editorRedirect = `/Users/majestikmind/Revature_Training/2011-nov9-usf/Project1/client/editor/editorDashBoard.html`;


const checkUser = async (user) => {

};



const auth = async (event) => {

    let inputUsername = document.getElementById('inputUsername').value;
    let inputPassword = document.getElementById('inputPassword').value;
    const user = {
        id: 0,
        username: inputUsername,
        password: inputPassword
    };

    console.log(user);

    let response = await fetch('http://localhost:4000/api/auth/login', {
        method: 'POST',
        headers: {
            'content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });

    console.log(response);

    if (response.status == 200) {
        window.location.href = authorRedirect;
    } else {
        alert('Authentication failed'); 
    }
};
