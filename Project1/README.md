
### Architectural Change: 

Project0 and Project1 are fairly similar in that they are both basic CRUD applications. 
Where Project1 differs is that it has a front-end, whereas Project0 was a CLI application. 
This matters because controller logic and user role were closely associated in Project0. 
After all, the controller had to expose an interface appropriate to the privileges of the 
user. However, Project1 has a true front-end that exposes the appropriate interface.
Because the controllers are no longer responsible for UI, we achieve separation of concerns, 
and can therefore generalize the controller logic. The benefit of this is that we avoid
an isomorphic mapping of controllers to user roles.

### Technologies Used
ES6, HTML, CSS, BOOTSTRAP, Java, Javalin, Maven

Testing:
Postman


### Challenges and Solutions 

Serialization and Deserialization of objects. 
Converting between representational forms proved
to be a little tricky. Javalin's bodyAsClass() method is not very reliable. So I installed 
GSON and Jackson to facilitate converting between JSON Objects and Java Beans. 

### Authentication and User Session 
I chose a multi-page front-end. After sign-in, I needed a way to save the user information, so
I used the widely-supported Web Storage API along with JSON.stringify() and JSON.parse(). This was
my first time needing a browser session, so it made the mechanics of session storage clearer to me. 
Details: [Using_the_Web_Storage_API](https://developer.mozilla.org/en-US/docs/Web/API/Web_Storage_API/Using_the_Web_Storage_API) 