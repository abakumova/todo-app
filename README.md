# todo-app
Todo application is built with Spring Boot (REST API backend) using build automation tool Gradle, MongoDB database and Angular framework for the frontend.

## Setup
### Requirements (Used in project)
- Java 11
- Gradle 6.4
- MongoDB Server 4.2

### How to launch
1. Start MongoDB server on your computer
2. Build and run the backend part
```bash
gradlew.bat bootRun
```
3. Run frontend part (be in root directory)
```bash
cd angular-frontend
npm install
npm start
```
4. Go to http://localhost:4200

## Swagger
To work with backend part (see endpoints and make requests) you can use Swagger
http://localhost:8080/swagger-ui.html

## Stack
- Backend 
  - Java
  - Spring Boot
  - Lombok
  - Swagger 
  - JUnit 5

- Frontend  
  - Angular
