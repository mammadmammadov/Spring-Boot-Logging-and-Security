# Spring Boot Logging and Security :books:

This README provides instructions on how to start and use the application from command line, and it includes a link to the video recording.
## Instructions

### Starting the Application

####
**Note: You need to have JDK 17 and Gradle installed on your machine before following the instructions below.**

To start the application from Windows command line, follow these steps:

1. Clone the repository or download it as a ZIP from github:

   ```bash
   git clone --depth 1 https://github.com/SITE-ADA/as1-spring-boot-logging-and-security-mammadmammadov.git

2. Navigate to the project directory:
   ```bash
   cd as1-spring-boot-logging-and-security-mammadmammadov
3. Build the application:
   ```bash
   .\gradlew build
4. Run the application
   ```bash
   .\gradlew bootRun
5. To access the application, enter http://localhost:8080

<h3>You already have two registered users in the system:</h3>

**Username:** admin  
**Password:** admin

**Username:** mammad  
**Password:** 12345

## Video Recording

<a href="https://www.youtube.com/watch?v=9LVDAC2FKAk" target="_blank">Link to Video Recording</a><br><br>




<p style="color:red"><strong>Below are the instructions set by the instructor:</strong></p>

<h1> Assignment 2 </h1>
<h3> Security and Logging. </h3>

Dear students, <br />

In this assignment, you are required to create a very simple MVC application using Spring similar to the previous
Assignment. (One entity and CRUD operations on that entity is enough). In the index file, provide necessary links to
nagivate through you web site. You may pick one of the entities from the previous assignment to save up the time.

<h4> Task related: (20%)</h4>
<ul>
    <li> Create a CRUD for a single entity</li>
    <li> Perform a validation for data entry in create and update</li>
    <li> Use DTO pattern instead of exposing your entities to the client</li>
</ul>

<h4> Task related: (25%)</h4>
<ul>
    <li> Enable Spring Security and create simple login page </li>
    <li> You might need to create two default users (one with admin role another with user role) during the application
        startup</li>
    <li> In the above CRUD apply necessary authorization rules so that only ADMIN users can CREATE, DELETE and UPDATE
        the records while any user can list all the records</li>
    <li> Provide logout mechanism from any existing page</li>
</ul>

<h4> Task related: (25%)</h4>
<ul>
    <li> Create a sign-up page. Add links to login and signup pages in the index.</li>
    <li> Users should be able to register themselves (by default with USER role) and login using this user.</li>
    <li> After successfull login newly registered users should be able to list all the records as all predefined users.
    </li>
</ul>


<h4> Task related: (10%)</h4>
<ul>
    <li> Use logging to show info, warning, and errors in the controller actions.
        Note: Use different logging levels for each controller. </li>
    <li> Write a good README file to describe what you have done.</li>
</ul>

<h4> Submission related: (10%) </h4>
<ul>
    <li> Make sure you configured your git client (i.e., username and email is set). Use firstname_lastname as the
        username. </li>
    <li> Please, also ensure that you regularly check, add and commit your changes to the remote repo so that we can
        see
        your progress. </li>
    <li>Things to be submitted to the BB grader in a .zip format:
        <ol>
            <li>Documentation pages of your app created by the Gradle, (during the development use javadoc comments
                appropriately)</li>
            <li>This README.md file updated to have
                <ul>
                    <li>the instructions on how to start and use the application</li>
                    <li>link to the video recording</li>
                </ul>
            </li>
            <li>Application source after <em>./gradlew clean</em></li>
        </ol>
    </li>
    <li> Submissions without a video submission will not be graded.</li>
</ul>

<p><b>Note:</b> Please work every day; this is definitely not a task you can complete on the last day!
    To keep track of the progress, create a git repository, commit, and push each change (ideally at least every time
    you
    complete a feature) to the repository.
    Ensure that you have at least <strong>1 commit a day</strong> STARTING from <strong>April 27<sup>th</sup></strong>
    otherwise you will lose points.
</p>
<p>For <strong>late submissions</strong> please refer to the SYLLABUS.</p>


<h4> Advanced 1 (30%) </h4>
<ul>
    <li>This might require additional reading and a bit of extra work!</li>
    <li>There are different authentication methods, one of which you have just implemented (basic auth using username
        and password)!</li>
    <li>The others might include token-based auth, O-auth and etc.</li>
    <li>For this task, you are required to implement a JSON Web Token (JWT) authentication.</li>
    <li>If you have completed the previous tasks and recorded the video, you may update your code.</li>
    <li>Once completed, record another video demonstrating token-based auth using JWT</li>
    <li>Note: The app should still be an MVC, not a restful app. Many tutorials on the web are based on a restful app,
        but you can easily implement it on MVC.</li>
</ul>



This assignment will give you <strong>10%</strong> of the total. <br />
<em> Good luck! </em> <br />
<em> NS </em>