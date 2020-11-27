
# Project 0 Overview 
The application is a CLI application that permits users of different
access privileges (customer, employee, manager) to interact with a
virtual shop. 

This application is a demonstration of my ability ability to build
a n-tiered application to specification under time-constraints (10 days). 

The application is written in Java. While I had experience with C/C++,
I had no experience with Java before building this project. 

Data is persisted to a remote AWS PostgresQL database. 

### Architecture
The architecture of the application is as follows:

- **Application** : implements user interface, delegates requests to controllers
- **Controllers** : handle user requests, delegate request to services
- **Services** : filter results of CRUD operations, delegate user requests to DAO
- **DAO** : perform CRUD operations against remote database 

### Features 
The application features a number of features, including : 
- User registration and authentication
- **Lazy-loading controller elements**, which improve security
- **Singleton design pattern** (for application and controller components)
- **Abstract Factory design pattern** (for maintainability and flexibility)
- **Remote procedure calls** (off loads complexity onto database)
- Unit testing with **JUnit**
- fully encapsulated **JavaBeans**

### Challenges and Learning Moments
The biggest takeaway from this project for me was the interaction between the 
database access layer and the remote database. In the months leading up to this
project, I was working the document-based databases (mostly MongoDB), so having
to write SQL statements *and* a complex database access layer in a new
programming language was tricky. I overcame this by testing my SQL statements in 
DBeaver before implementing them in my application. Honestly, for me, this project 
was a refresher in OOP. However, I did learn about transactions and making remote
procedure calls using JDBC


### Running the application 
To run the app, you'll need to connect to a postgresQL database. Configuration
information for connecting to a database is stored under src/main/resources in 
the database.properties file. It is currently configured to connect to a free-tier
AWS database. If you want to use another database, you'll need to initialize the 
database with the appropriate schema, tables, and data, all of which you can get 
from the scripts from the postgresQL_scripts file. Just copy, paste, and execute the 
DDL and DML sections and go from there. 


