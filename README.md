# Tour_Planner

Team: Nehar Harpreet and Vladimir Mandic

Tour Planner is a JavaFx application that allows users to create a tour and add log to it.

# Overview

-The frontend is built with JavaFx. -The backend is built with Spring Boot. -Via HttpClient the frontend communicates
with the backend.

# How to start the application

-A postgres database is needed for this application. It is needed to update the username and password in a properties
files (tourspring/src/main/resources/application.properties). For the first run there should be a property set "
spring.jpa.hibernate.ddl-auto=create-drop", but afterwards you can change it only into "update".  
-The backend needs a database. -Then the user can start the backend (Spring Boot)
-After that the frontend can get started.

# Frontend

It has on the top a search bar that allows users to search for a tour. On the left side all tours are listed. If a tour
is selected, the details of the tour are shown. Also where the details of the tour is shown there are tabs.

Description tab shows the description of the tour. Map tab shows the map of the tour which takes a picture from a api.
User can edit almost all the details of the tour with text input fields. Only distance, duration and picture are
automatically calculated with the mapQuestAPI.

# Functionality

It is possible to add and delete a tour from the list. This is done by clicking on the buttons + (adding) and - (
deleting), above the tourList

On the Route tab the user can edit the tour info and with update the values are updated in the database and also a new
request to the map api will send.

By clicking on a tour in the list, the details of the tour are shown and also the log of it.

Like tour Logs can get added, delete and edited. By clicking on the buttons + (adding) and - (deleting) above the
logList, a new log can be added or deleted. The other icon edits the log.

And in the text-field it is possible to search for a log.

# Unit Test

## Backend

For the backend the unit test is done with JUnit. For the units tests it was important to me to test the service
classes, because here an error can arise quickly. For test data the repository test where a method to test the backend
and also play with the data on the frontend. For the communication with the backend it was significant to test the
HttpClient and the controller Test that the work.

## Frontend

For the frontend the unit test is done with JUnit and with javaaFX Robot Test framework. The test were to search for an
Item, calculate calories, delete items, API requests etc.

# Unique Feature

Our unique feature is a calories' tracker. The user can see the calories burned. The user can see how many calories
he/she would burn by walking/running or with the bike.

# Time

From the git history you can see that we have done the project in the last 2 months. Majority of time was invested in
the beginning (month May) and then a little bit less until the deadline. Time estimation was done with the help of the
git history.

## Time estimation

WH- Working Hours <br>UI design and development: 20 WH<br> Backend: 30 WH<br> Data binding and model creation: 10 WH<br>
Tour manipulation and search functionalities: 15 WH <br> Unit testing:
10 WH<br> API integration: 10 WH<br> PDF report generation: 5WH<br> Tours import/export: 5WH<br> Logging and
configuration properties load: 10 WH <br>Layering and architecture: 10WH<br> Code refactoring: 10 WH <br>
SUM: 135 WH

# Lesson Learned

Better time management for the semester. Divide code into different classes and methods so that the code is easy to
adapt later and also easier to test. Having a clear idea about the backend and also the frontend is important and also
how the can communicate with each other. The structure of the frontend and how to structure the UI and each element is
significant.

# Github

https://github.com/vladimirmandic777/Tour_Planner