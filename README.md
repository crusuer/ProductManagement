# Product Management
This project was created as a challenge for a software company.
It is possible:
##### Product
* Create a new product
* Get a list of all products
* Update a product
##### ORDER
* Placing an order
* Retrieving all orders within a given time period

## Considerations
* The project uses H2 database, but it can be easily changed to any database due the use of JPA.
* The project doesn't contain *authentication*, but to implement it I would choose the Bearer Token, 
because it is a secure method and it is really simple to create a service in the server side that authenticates credentials and generates a token, 
this token must be provided in the header of requests during a session.
* To make this service redundant, it should be deployed in different application servers (cloud solutions make it easy), pointing to the same database. 
Then, it is necessary to create an application router, it should check if the service is running before send the requests, this can be reached using a health check in the application server, like Spring Boot Actuator.

# Application setup (Locally)

## Requirements
Before running the application, ensure the following dependencies are installed:

```
Java 11
Maven 3.2.2
Git
```

## Installation
Download the project:
```
git clone https://github.com/crusuer/ProductManagement.git
```
Go to the project directory:
```
cd [project_dir]
```
Compile and download dependencies:
```
mvn clean package
```
Run application:
```
mvn spring-boot:run
```
# API Endpoints
Postman requests: <p>
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/a44084ada439c4fb5c49)

## GET
`HOST`[/products](#get-products)<br/>
`HOST`[/orders](#get-orders)<br/>

## POST
`HOST`[/products](#post-products)<br/>
`HOST`[/orders](#post-orders)<br/>

## PUT
`HOST`[/products](#put-products)<br/>

___
To see the documentation in SWAGGER UI, go to [http://localhost:8080/swagger-ui.html](#get-swagger-ui)