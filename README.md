# 2nd Year Specialization Requirement

## UBC Vancouver Campus Second Year Specialization Application Requirements for Science Students

*project proposal*:

**What will the application do?**
- check if the student (given the first-year courses taken) are eligible to apply to
  a specific 2nd-year specialization

**who will use it?**
- This program is intended for first-year science students at UBC Vancouver Campus

**Why is this project of interest to you?**
- I hope this program I will design for this project will help out more first-year students navigate through their 2nd
  year specialization application process.
- I was going through a hard time navigating through the UBC calendar to find out what courses I will need to
  complete in my first year to apply for a specialization. And when I dropped the CPSC 121 course in my
  2nd Term, I was unsure if I am still eligible to apply to the CS major in May. Until I went to the Science Advising
  Office, they gave me a link to this website, https://science.ubc.ca/students/degree/apply/req, that includes
  all the requirements needed to apply for each science major, and I found out that CPSC 110 is the only course that I
  am required to complete in my first year.
- Because this website was really useful to me, I want to make a program that
  shadows the function of checking a student's eligibility to apply for specialization, but with an additional user
  interaction component to it and is more direct, efficient, and personal for a specific student.

## User Stories
*PHASE ONE*
* As a user, I want to be able to add courses to my student profile
* As a user, I want to be able to pick an intended specialization and add it to my student profile
* As a user, I want to be able to change the intended specialization and update my student profile
* As a user, I want to be able to check if the courses I have added to my student profile meet the requirements
  to apply for my intended specialization

*PHASE TWO*
* As a user, I want to be able to save my student profile to file
* As a user, I want to be able to load my student profile to file

*PHASE THREE*
* Include a panel in which all the courses (Xs) that have already been added to my student profile (Y) are displayed
* Include a button that can add courses (Xs) to student profile
* Include a button that can pick an intended specialization and add it to my student profile
* Include a button that can check the user's eligibility to apply to the intended specialization
* Include two buttons that allow users to be able to load and save the state of the application

*Phase 4: Task 3*
* If I had more time to work on the project, I would 
reduce duplicate code seen in the methods addCourseFrame() & addEligibilityFrame() located inside  the StudentProfilePanel.
This can be done by extracting a method to capture the duplicate behaviour of creating a pop-up frame of the methods, and call the extracted method from inside each of the original methods instead.
For reducing duplicate code around constructing a JButton & JPanel, the same thing can be applied. Doing so will also help improve readability and increase cohesion. 
Another way is to refactor SaveAndLoadPanel and StudentProfilePanel class, and change the association with the MainGUI class to bi-directional, then remove the association with StudentProfile in both classes. 
As we are able to get a copy or update the same copy of the StudentProfile in MainGUI. Doing so, we are then able to remove the association in between the two panel class as well. 


## References:
* TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
* Bibliography: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html
* JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
* GUI: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html
* Space Invader: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
* Window closing event by Wayan: https://kodejava.org/how-do-i-handle-a-window-closing-event/


## Bibliography:
* IMG_4527.PNG adopted from https://citystudiovancouver.com/partners/ubc/
* rest of the images used in the program are taken/made by Celine Chen