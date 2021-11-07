# Article Service APP using SpringBoot and Maven
### A ArticleService App written in Java, SpringBoot using Maven for the build, that showcases a few API Endpoints.

This App demonstrates:

How a article can be created, updated, deleted and fetched from the DB using the JPA repository
Unit tests written with JUnit and Mockito.

A simple schema with table is used as shown below and can be viewed in https://dbdiagram.io

```
Table article{
 id int 
 title string 
 slug string
 body string 
 description string 
 createdAt date
 updatedAt date
} 
```

#### use below command to run the application 

 mvn spring-boot:run

#### use below command to run the test cases

 mvn test

#### use below commands on the project directory to run the application as a docker container

 mvn clean install

 docker-compose up --build 

Once the application is up and running all the Endpoints are exposed at the url http://localhost:8080/swagger-ui.html and also shown in below image 

![Simple UML](https://github.com/jpnaidu07/SpringBoot-CRUD-API/blob/master/API.PNG)


Note: JAVA_HOME must be set to JDK instead of JRE
