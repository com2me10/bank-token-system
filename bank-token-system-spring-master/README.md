# Banking Token System
This service to manage customer tokens and to improve customer experience.

## Setup

### Prerequisites

In order to get started you need the following:

1. JDK 7.0 or later.
2. Maven 3.0 or later
3. Spring Boot
4. Spring Data JPA
3. mysql

### Setting up Database
Create db with name 'bankdb', user 'root' and password 'password'

When application is up then access url 'localhost:8080/bank/dummyData' which will create dummy data in db like for Bank, Bank Branches, Service Counters .

### Setting up IntelliJ

- Import the project in your IntelliJ using
    -  `Welcome Screen` > `Import Project`
        - OR
    - `File` > `New` > `Project from Existing Sources`
- Select the `banking-token-system-spring` directory you cloned in the previous step
- Click `OK` to launch the Wizard
- On the first screen, select `Import project from external model` and then select `Maven`
- Click `Next`
- Click `Finish`


##### Once application is up, then we can see swagger ui for application for all modules (controllers) and we can try out all apis with their responses.
#### Swagger API : `http://localhost:8080/bank/swagger-ui.html`

### APIs to use

'/dummyData' - 'GET' : It will create dummy data for application to work

'/customer/{phoneNumber}'  - 'GET' - It will return customer details

'/customer' - 'POST' - It will create customer in db. Need to send attributes like name,phoneNumber,address,serviceType in request body (service type can be either 'premium' or 'regular')

'/serviceCounter' - 'GET' - Return all service counters list

'/serviceCounter/{serviceType}/tokens' - 'GET' - return all tokens for service type (like deposit, withdraw, account, enquiery)

'/token' - 'POST' - it will generate token for customer if exist with given phone no. Attributes - phoneNumber, serviceType (like deposit, withdraw, account, enquiery) in request body

'/token' - 'PUT' - It will process token. Attribute need to pass counterName in request body

