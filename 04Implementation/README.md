# Team Project: *Group 06*

#ATTENTION: The whole codebase we put on a director called demo (outside of the 04Implementation)

This report details the work that was completed by together by all team members for the Implementation phase of the project.

## Team members
1. *Team member 1 - Yiming Lou (@LioshaLou)*
2. *Team member 2 - Mingwei Yan (@20205706)*
3. *Team member 3 - Jiahe Zhang (@ZhangJiahe)*
4. *Team member 4 - Feihe Huang (@20205735)*

# Implementation

This part includes the implementation of the restaurant example system. In order to execute the application you will need to have the following installed:
1. Java 17
2. Maven
3. Docker
4. Docker Compose

## Running the Application	
Assuming that you have correctly installed and set up the above, you can run the application by following these steps:
1. Use maven to create a jar file of the application by running `mvn package` in the root directory of the project.
```bash
mvn package
```
2. Run the docker compose command to build and run the application.
```bash
docker-compose up -d --build
```

3. Open the program in the localhost:8083 in your browser

This will build the application and run it on port 8083. You can then access the application by navigating to `localhost:8083` in your browser. This also starts the database, as such any changes you make shoudl persist between runs.

## Stopping the Application
When you are finished and want to stop the application, you can use the following command:
```bash
docker-compose down
```

If you want to stop the application and remove the database, you can use the following command:
```bash
docker-compose down -v
```

## Milestone 4 Implementation (Due 2022-12-02)

### Distribution of work on this phase
| Item                                | TM1  | TM2  | TM3  | TM4 | Notes on item                        | Percentage of whole task in this phase |
| ------------------------------------- | ------ | ------ | ------ | ----- | -------------------------------------- | ---------------------------------------- |
| Total                               | 25%  | 25%  | 25%  | 25% | The total percentage of contribution | 100%                                     |
| Video                               | 50%  | 50%  |      |     |                                      | 10%                                    |
| Database                            | 70%  | 10%  | 10%  | 10% |                                      | 4%                                     |
| Front-end Design                    | 10%  | 10%  | 10%  | 70% |                                      | 20%                                    |
| Use Case 1: "Add Author"            |      |      | 100% |     |                                      | 6%                                     |
| Use Case 2: "Add Book"              |      |      | 100% |     |                                      | 6%                                     |
| Use Case 3: "Add Customer"          |      | 100% |      |     |                                      | 6%                                     |
| Use Case 4: "View Customer Details" |      | 100% |      |     |                                      | 6%                                     |
| Use Case 5: "Rent Book"             | 100% |      |      |     |                                      | 6%                                     |
| Use Case 6: "Return Book"           | 100% |      |      |     |                                      | 6%                                     |
| Use Case 7: "Record Payment"        |      | 100% |      |     |                                      | 6%                                     |
| Use Case 8: "Add Book Copy"         |      |      | 100% |     |                                      | 6%                                     |
| Use Case 9: "Remove Book Copy"      |      |      | 100% |     |                                      | 6%                                     |
| Use Case 10: "Find Book"            | 100% |      |      |     |                                      | 6%                                     |
| Use Case 11: "Add To Waiting List"  | 10%  | 70%  | 10%  | 10% |                                      | 6%                                     |




### Reflection Statements
| Team Member | Contribution Reflection Statement |
|-------------|-------------------|
|TM1| Build the basic project, connect each part of the code, Implementing the use case 5,6 and some part of 11. Editing the frontend details and write all the models and repositories. |
|TM2| Implementing the use case 3, 4, 7, and 11 to the codes in controller and service, conbining the front-end and the back-end code, writing README, and recording video. |
|TM3| Independent implementation of the back-end parts corresponding to UseCase1 (AddAuthor), UseCase2 (02-AddBook), UseCase8 (08-AddBookCopy), UseCase9 (09-RemoveBookCopy) and UseCase10 (10-FindBook). Includes implementation of functions within controller (LibrarySystem.Class) and service (LibraryService.Class). Worked on front-end and back-end interfacing, including front-end adjustments and some front-end and back-end interfacing for the 5 UseCases I was responsible for. Minor adjustments to the database model.|
|TM4| I am responsible for front-end interface production including designing the number and structure of front-end pages and completing the appearance of the web pages according to the functional needs. As well as some front-end and back-end interfacing. The parts I was responsible for have been uploaded to the GitLab branch: 20205735|
