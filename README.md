# COMP3013J-Object-Oriented-Design-Project

**ATTENTION:**\
The whole codebase we put on a director called demo (outside of the 04Implementation)

This report details the work that was completed by together by all team members for the Implementation phase of the project.

## Team members
We have four talented team members!

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

