# Test Application for akkodis
This application contains a service and exposes an endpoint for product consultation.

## Technologies
It has been implemented following the hexagonal architecture pattern with three main packages:
- `application`: contains the service and use cases
- `domain`: contains the domain model
- `infrastructure`: contains the adapters to the external world, controller and repository

The main technologies used are:
- Java v.17 (jdk-17.0.1)
- Spring Boot v.3.0.6
- Maven v.3.8.7

This application also makes use of:
- `H2` as in-memory database with an initial script to populate the database (data.sql)
- `Project Lombok` for class annotations
- `Mapstruct` as mapper
- `Swagger` for API documentation accesible from <http://localhost:8080/swagger-ui/index.html#/>
  
## Testing
The application contains different unit tests and also an **End To End** test for the requested test cases using the data-test.sql file as source.

## How to build
mvn clean install

## How to run
java -jar test-java-0.0.1-SNAPSHOT.jar
