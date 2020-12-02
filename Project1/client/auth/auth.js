const authorRedirect = `/Users/majestikmind/Revature_Training/2011-nov9-usf/Project1/client/author/authorDashBoard.html`;
const editorRedirect = `/Users/majestikmind/Revature_Training/2011-nov9-usf/Project1/client/editor/editorDashBoard.html`;

const checkUser = async (user) => {
    logCurrentUser(); 
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
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(user)
    });
 
    console.log(response);
    let returnedUser = await response.json();
    console.log(returnedUser);


    if (response.status == 200) {
        // alert('Authentication succeeded');
        currentUser = returnedUser; 
        console.log(currentUser);
        currentUser.password = '*****'; 

        sessionStorage.setItem("currentUser", JSON.stringify(currentUser));
        window.location.href = authorRedirect;
    } else {
        alert('Authentication failed'); 
    }
};