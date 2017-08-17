# Java Project (back-end)

This project is part of a full-stack project, involving a Java API, MySQL database running as a Docker Volume and ReactJS front-end. 

The front-end, which needs to be run seperately and concurrently, is available at <https://github.com/SalVN/java-project-front>.

## Getting Started

To run the back end, the following steps should be taken:

### Prerequisites

This project requires Java 1.8, maven and docker to be installed on your machine.

If maven is not installed on your machine, it can be acquired [here](https://maven.apache.org/download.cgi).

If docker is not installed on your machine, the Docker Community Edition can be acquired [here](https://www.docker.com/get-docker).

To confirm you have docker installed on your machine, run the following code:

```
docker -v
```

To confirm you have maven installed on your machine, run the following code:
```
mvn -v
```

### Instructions

1. Start the server

Having navigated to the correct file on the command line, run:
```
mvn spring-boot:run
```

This will install the dependencies and start the server.

2. Start docker in the command line to run the MySQL database.

```
docker run \
-e MYSQL_ROOT_PASSWORD=password \
-e MYSQL_DATABASE=cameras \
-p 3306:3306 \
-v mysql:/var/lib/mysql \
mysql:5.7.19
```

N.B. It is recommended to change the password for the ROOT_PASSWORD.

3. Start MySQL

```
mysql -u root -p -h 127.0.0.1 cameras
```

4. The API can now be interacted with using the following routes on the browser or command line, or can be linked with the React Front-End which is available at <https://github.com/SalVN/java-project-front>.

[GET] /cameras : returns a list of all cameras on the database

[GET] /cameras/{cameraId} returns the details for the specified camera

[POST] /cameras/{cameraId} allows a camera to be added to the database from the command line or using a tool such as [Postman](https://www.getpostman.com/).


## Test Suite


### Uses

To run this project in full, the [front-end](https://github.com/SalVN/java-project-front) server needs to be running.

Whilst a full list of dependencies is available on the pom.xml, the main libraries used are:

PROJECT
[Spring](https://projects.spring.io/spring-framework/)
[Flyway](https://flywaydb.org/)
[MySQL Java Connector](https://dev.mysql.com/downloads/connector/j/5.1.html) 
[Docker](https://www.docker.com/community-edition)

### Authors

[Sally Newell](https://github.com/SalVN/)

### Acknowledgments

Completed as part of a project during the Java Course at [Northcoders](https://northcoders.com/).