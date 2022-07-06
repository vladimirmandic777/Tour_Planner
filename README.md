# Tour_Planner

Team: Nehar Harpreet and Vladimir Mandic


Tour Planner is a JavaFx application that allows users to create a tour and add log to it.

# Overview

-The frontend is built with JavaFx.
-The backend is built with Spring Boot.
-Via HttpClient the frontend communicates with the backend.

# How to start the application
-A postgres database is needed to change the configuration there is a properties files.
-The backend needs a database.
-Then the user can start the backend (Spring Boot)
-After that the frontend can get started.

# Frontend

It has on the top a search bar that allows users to search for a tour.
On the left side all tours are listed. If a tour is selected, the details of the tour are shown. 
Also where the details of the tour is shown there are tabs. 

Description tab shows the description of the tour.
Map tab shows the map of the tour which takes a picture from a api.

# Functionality

It is possible to add and delete a tour from the list. This is done by clicking on the buttons + (adding) and - (deleting), above the tourList

On the Route tab the user can edit the tour info and with update the values are updated in the database and also a new request to the map api will send.


By clicking on a tour in the list, the details of the tour are shown and also the log of it. 

Like tour Logs can get added, delete and edited.
By clicking on the buttons + (adding) and - (deleting) above the logList, a new log can be added or deleted.
The other icon edits the log. 

And in the textfield it is possible to search for a log.


# Unit Test
## Backend
For the backend the unit test is done with JUnit.
For the units tests it was important to me to test the service classes, because here an error can arise quickly.
For test data the repository test where a method to test the backend and also play with the data on the frontend.
For the communication with the backend it was important to test the HttpClient and the controller Test that the work.

## Frontend
For the frontend the unit test is done with JUnit and made same Robot Test. 
The test was to search for an Item. 

MapRequest 

# Unique Feature 
Our unique feature is a calories tracker.
The user can see the calories burned.
The user can see how much calories he/she would burn by walking/running or with the bike. 

# Time

Unfortunately I don't know anymore how much time I invested in the project, 
but from the git history you can see that I did a lot in the beginning and then not much again until the deadline is coming soon.


# Lesson Learned

Better time management for the semester.
Divide code into different classes and methods so that the code is easy to adapt later and also easier to test. 
Having a clear idea about the backend and also the frontend is important and also how the can communicate with each other.
The structure of the frontend and how to structure the UI and each element is important.

# Github

https://github.com/vladimirmandic777/Tour_Planner