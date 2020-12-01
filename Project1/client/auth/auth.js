const authorRedirect = `/Users/majestikmind/Revature_Training/2011-nov9-usf/Project1/client/author/authorDashBoard.html`;
const editorRedirect = `/Users/majestikmind/Revature_Training/2011-nov9-usf/Project1/client/editor/editorDashBoard.html`;


const checkUser = async (user) => { 

}; 

const auth = async (event) => {
    alert('attempting to sign in');

    let inputUsername = document.getElementById('inputUsername').value; 
    let inputPassword = document.getElementById('inputPassword').value; 
    const user = {
        username : inputUsername,
        password : inputPassword
    }; 

    console.log(user);

    if (inputUsername === 'Chris' && inputPassword == '1234') {
        window.location.href = authorRedirect;
    } else {
        alert('Authentication failed'); 
    }
};
