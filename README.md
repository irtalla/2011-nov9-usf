# Story Pitch Management System

## Project Description

A website designed to submit story pitches to some publishing company. It helps the publishing company mange all the incoming requests better by automatically assigning stories to an author who works in that genre to evaluate it, and then works on further evaluations to ensure a high quality product.

## Technologies Used

* Maven
* Jackson
* Javalin
* Java
* Eclipse
* Tomcat
* Mockito
* JUnit4
* JDBC

## Features

List of features ready:
* sign up (as an author)
* sign in
* submit a work

TODO list:
* Visibly show the works that you have submitted
* (Step 1) Assign the story to an initial junior editor of the genre to be reviewed, and have that book show up in junior editor's home screen as a book the editor must check out 
* (Step 2) After Step 1, assign the story to an editor not of the genre to be reviewed, and have that book show up in their home screen 
* (Step 3) After Step 2, assign the story to a senior editor of the genre to be reviewed, and have that book show up in their home screen 
  * The senior editor can make major edits to your story, and the author can choose to accept these changes or withdraw their work
* (Step 4) After Step 3, have everyone who edits st
ories of that genre plus the editor in Step 2 look at the almost-finished copy. After final approval, the book can be sold 
  * If the story is an article, only the senior editor approves of it.
  * If the story is a short story, the senior editor and another editor in the same genre must approve it.
  * If the story is a novella or a novel, a majority of the people that can view the story in this state must approve it.
* In all steps except Step 4, an editor can accept or reject a story. If the story is rejected, reasoning must be given.
* A points system which does not allow you to submit more than 100 points' worth of stories. Articles are worth 10 points. Short stories are worth 20 points. Novellas are worth 25 points. Novels are worth 50 points
  * When a book is accepted in Step 4 or rejected at any Step, the points it takes to submit the work are refunded to the author.
* Making the website not look like a featureless black/white screen.

## Getting Ready

If there is no server ready, navigate to the Servers perspective and create a server. You will want to create a Tomcat 9.0 server. The hostname is localhost.

Click on the Revature project called Revature-Project1. If the JRE System Library is J2SE-1.5, change it to JSE-1.8 by right clicking on JRE System Library > Properties > Change the execution environment to JSE-1.8.

Start up the server. Go to any web browser and type in localhost:5432 to navigate to the website. You can sign up as a new user and log in.  You can also submit a story.






