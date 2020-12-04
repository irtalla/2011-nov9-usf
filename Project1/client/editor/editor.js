/**
 * This section gets the currentUser object from local broswer storage
 */

if (!sessionStorage.getItem("currentUser")) {
    populateStorage();
  }
  let currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
  console.log(typeof (currentUser));
  console.log(currentUser);
  
  document.getElementById('main-header').innerText = `Welcome Back, ${currentUser.username}!`;
  
  const logCurrentUser = () => {
    console.log(currentUser);
  }
  

/*
 onload, we want to fetch ALL open requests associated with the user. 
*/

/**
 * Dictionary that stores all the current user's
 * request. The key is request id, the value is the request object 
 */
const requestMap = new Map();

/**
 * Fetch requestes for current user. 
 */
const getRequests = async (id) => {


  let response = await fetchRequests(id); 

  if ( response.status === 200 ) {
   console.log(response);    

   let requests = JSON.parse( await response.json() );

//    console.log(typeof (requests));
//    console.log(requests);
 
//    for (const request of requestes) {
//      requestMap.set(request.id, request);
//      console.log(request);
//      // loadArticleCard(request)
//    }

//    //   loadrequestData(); 

  } else {
      alert("could not fetch requests");
  }

}

getRequests(currentUser.id);


/*

	private Integer id; 
	private Integer requestorId; 
	private Integer requesteeId; 
	private String requestContent; 
	private String responseContent; 
	private Status status;

*/