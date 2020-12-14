
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
Front-End: ES6, HTML, CSS, BOOTSTRAP
Back-End:  Java, Javalin, Maven, Hibernate
Database:  AWS PostgreSQL, DBeaver
Testing:   JUnit, Postman


### Challenges and Solutions 

Serialization and Deserialization of objects. 
Converting between representational forms proved
to be a little tricky. Javalin's bodyAsClass() method is not very reliable. So I installed 
GSON and Jackson to facilitate converting between JSON Objects and Java Beans. 

Another issue I faced was mapping requests to their targets. Unlike the mapping between comments and requests, a request can be mapped to a previous decision, a pitch, or a draft. A
request HAS reciever id, but this is not sufficient to indicate the function of the request. A request meant for an editor has a different function that a request meant for an author. Moreover, it should not be the case that *all* of an author's pitches are held up because of one outstanding request. So logically, requests should be associated with pitches. But requests are not *necessarily* associated with pitches, so this information should not be placed in the request relation. I could create multiple join tables. I could create multiple request types. The best solution seems to be to create a target_type relation and assign a target_id and target_type_id to the request relation. The problem is that we could not place a foreign-key constraint on target_id, which seems like bad practice. Another solution is to 
maintain dummy tuples in decision, pitch, and draft, and give each request a reference to each like so: 

	...
	target_draft_id references draft,
	target_pitch_id references pitch,
	target_decision_id references decision 
	}

A little inelegant, but straightforward enough. Two values will be -1, and one value will be a positive integer. This can be designated client-side. To maintain referential integrity, we will have dummy rows for decision, pitch, draft, author, and editor. 

UPDATE : A even quicker way to do this is to remove the target_draft_id fk in the request column. Since, 
pitches and drafts are in one-to-one relation, there is no need to reference a pitch directly. Further, since requests represent persistent communication channels organized around some topic, an editor can simply specify the content with the initial comment. That leaves reqeusts targeting editors' decisions. 



One interesting observation is that almost every attribute of a pitch is a target for a query. This makes sense, as pitches are the main product. But it is an interesting observation.


Testing is another issue. In the previous project, tests depended on a database administrator (me) initializing tables from a separate UI (DBeaver). This time around, I used the @order annotation to
specify the execution of DAO unit tests. Generally, the strategy is: add -> query | update -> delete. This should make test suites self-contained.

Requests
My strategy for requests is to have each request have a set of comments associated with it. This way, a user can submit multiple comments in a row, instead of a one-off reply. Then, if the date-time of the latest receiver comment is later than the latest sender comment, we append a red exclamation point to the senders card representation, otherwise, the receiver's card representation. This gives an indication of responses and is more fluid and intuitive. 






Mystery
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.google.gson.internal.reflect.UnsafeReflectionAccessor (file:/Users/majestikmind/.m2/repository/com/google/code/gson/gson/2.8.5/gson-2.8.5.jar) to field java.time.LocalDateTime.date
WARNING: Please consider reporting this to the maintainers of com.google.gson.internal.reflect.UnsafeReflectionAccessor
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release


### Authentication and User Session 
I chose a multi-page front-end. After sign-in, I needed a way to save the user information, so
I used the widely-supported Web Storage API along with JSON.stringify() and JSON.parse(). This was
my first time needing a browser session, so it made the mechanics of session storage clearer to me. 
Details: [Using_the_Web_Storage_API](https://developer.mozilla.org/en-US/docs/Web/API/Web_Storage_API/Using_the_Web_Storage_API) 