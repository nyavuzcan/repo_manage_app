# Stock Control Application
Adding and editing products with authentication
- It has user registration.
- It has email verification.
- It has image upload.

## Requirements
Firstly, Postgresql database is used in the project. So a new database should be created according to the following information:

- Jdk 1.8

- Apache Maven 4.0.0

- Postgresql 


## Setup And Run

- To run stock control application at your local environment, please run the commands below;

```docker-compose up```

- After the containers started successfully, you can use app.

## Endpoints:

 **Swagger:** http://localhost:8090/swagger-ui.html#

 **React Ui:** http://localhost:3000

 **SpringBoot Api:** http://localhost:8090

## Used technologies
**Docker:** Ui, backend, database can be run easily

**SpringBoot:** For api.

**JWT Authentication:** User login using spring authentication and jwt.

**Email Verification:** When user is registered, mail verification, mail settings can be changed in application.properties.

**File upload:** Upload images on the host.

**Scheduling:** Using spring schedular to cache clear at a certain time interval.

**Caching System:** Simple cache system with spring cache.

**Swagger:** All endpoints are easily schematized.

**React:** Js library for ui.

