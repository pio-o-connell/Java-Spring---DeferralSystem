# DeferralSystem

## Overview

DeferralSystem is a web application for managing student deferrals within an academic programme.

The application allows authorised users to manage students, lecturers, programmes, modules, semesters and registrations, while tracking whether individual deferral requests have been approved.

## Features

* Login and role-based access through Spring Security
* View and search student records
* View student deferrals and related academic information
* Create, list and update deferral requests
* Approve or reject deferral requests
* Download the deferral form
* Manage lecturer, programme, module and semester records
* JDBC-backed persistence for academic and authentication data
* Email-related service support for application notifications
* JSP pages organised into reusable layouts with Apache Tiles

## Application Architecture

The application follows a layered Spring MVC architecture:

* **Controllers** handle web requests and prepare model data for the JSP views.
* **Services** contain application operations for deferrals, students, lecturers, modules, programmes, registrations and semesters.
* **Repositories** use Spring JDBC and DAO classes to read and write database records.
* **Domain classes and mappers** represent application data and map SQL results to Java objects.
* **JSP views** provide the web interface, with Apache Tiles used for shared page layouts.
* **Spring Security** provides form login and JDBC-based user and role lookup.

## Technologies

* Java 8
* Spring Framework 4
* Spring MVC
* Spring Security
* Spring JDBC and Spring transactions
* Apache Tomcat
* JSP and JSTL
* Apache Tiles
* Maven
* MySQL Connector/J
* PostgreSQL support for Heroku deployment
* JUnit, DBUnit and Selenium for testing

## Database

The included `DatabaseSetup.sql` script creates a MySQL database named `spring_project`.

The schema contains tables for:

| Table | Description |
| ----- | ----------- |
| **deferrals** | Student deferral requests and approval status |
| **student** | Student details |
| **lecturer** | Lecturer details |
| **modules** | Module and CRN information |
| **programme** | Programme and coordinator information |
| **registration** | Student module and programme registrations |
| **semester** | Programme semester information |
| **users** | Application login accounts |
| **user_roles** | User authorities used by Spring Security |

Database connection properties are read from `src/main/resources/prop.properties`. The project also contains a PostgreSQL data-source implementation for deployment environments such as Heroku.

## Project Structure

```text
Java-Spring---DeferralSystem/
│
├── pom.xml
├── DatabaseSetup.sql
├── Procfile
├── system.properties
│
├── src/
│   ├── main/
│   │   ├── java/com/grouptwo/
│   │   │   ├── controllers/
│   │   │   ├── domain/
│   │   │   ├── exceptions/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   │
│   │   ├── resources/
│   │   │   ├── configuration.xml
│   │   │   ├── databaseEntries.xml
│   │   │   └── prop.properties
│   │   │
│   │   └── webapp/
│   │       ├── resources/
│   │       └── WEB-INF/
│   │           ├── jsp/
│   │           ├── configuration.xml
│   │           ├── mvc-dispatcher-servlet.xml
│   │           ├── spring-security.xml
│   │           └── tiles.xml
│   │
│   └── test/
│       └── java/com/grouptwo/
│
└── target/
    └── ... generated Maven build output
```

## Main Application Packages

| Package | Responsibility |
| ------- | -------------- |
| `com.grouptwo.controllers` | Spring MVC controllers and the embedded Tomcat entry point |
| `com.grouptwo.domain` | Domain objects and database row mappers |
| `com.grouptwo.repository` | DAO interfaces and JDBC repository implementations |
| `com.grouptwo.service` | Service interfaces and application logic |
| `com.grouptwo.exceptions` | Application-specific exceptions |

## Development

The project is built with Maven and produces a WAR file named `SpringWebProject.war`.

After configuring the database properties and creating the schema, the project can be packaged with:

```bash
mvn package
```

The repository includes a `Procfile` and an embedded Tomcat launcher for running the packaged web application on Heroku-style environments. The application uses the `PORT` environment variable when it is available and otherwise defaults to port `8080`.

## Author

**Pio O'Connell**

## Repository

[DeferralSystem on GitHub](https://github.com/pio-o-connell/Java-Spring---DeferralSystem)
