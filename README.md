# Infra Microservice
    Microservice framework with all the necessary dependencies and {API}
- Table of content
  * [Prerequisite for project](#1-prerequisite-project)
  * [Building Project](#2-building-project)
  * [Run Microservice using JAR](#3-run-microservice-using-jar)
  * [Run Microservice using Docker](#4-run-microservice-using-docker)
  * [Endpoint](#5-endpoint)
  * [Directory Structure](#6-directory-structure)
  * [Performance test using Gatling](#performance-test-using-gatling)
  * [Testing](#testing)
  * [Code quality and Code coverage](#Code-quality-Code-coverage)

## 1. Prerequisite Project

- If building Docker Images on Windows, install [Docker for Windows](https://docs.docker.com/docker-for-windows/). Make sure you configured the following things:

    * expose daemon on http

    * container type should be linux

    * Hyper-V feature disabled for windows

- Install [java](https://oracle.com) and [Maven](https://maven.apache.org) if it is not done already.

- Pull the sonarqube docker image from central repository using following command
    
    ```shell script
    docker run -d --name sonarqube -p 9000:9000 sonarqube
    ```
    
    By default sonarqube mvn dependency looks for sonarqube service on localhost:9000

- Install postgres locally on port 5432 from [here](https://www.postgresql.org/download/windows/).
    

## 2. Building Project

- Clone the code from GitHub into a folder, lets call this ***project_root***

- Run below maven commands from ***project_root*** directory for various tasks.

    - clean the project
    ```shell script
    mvn clean
    ```

    - build the project
    ```shell script
    mvn clean install
    ```
  
    - perform clean, compile, package, and build docker image
    ```shell script
    mvn clean install dockerfile:build
    ```

## 3. Run Microservice using JAR

- If you need to perform deployment on local system, it can be done by running jar directly or using a Docker image. Use the below mentioned command to run the microservice directly using jar file. 

    ```shell script
    java -jar <<app-name>>.jar &
    ```

> Note the **&** at the end of the command, this is to run the process in background

- By default, the server runs on port ***8080***, we can change that by passing the port number as below (_make sure to kill the above process if it is already running_)

    ```shell script
    java -Dserver.port=9023 -jar microservice-1.0-SNAPSHOT.jar &
    ```

## 4. Run Microservice using Docker

- If you need to perform deployment on any other system, it can be done by running docker directly without worrying about runtime and dependencies. Use the below mentioned command to create the microservice docker image. 

    ```shell script
    mvn clean install dockerfile:build 
    ```
> Note before creating docker image make sure in application.yaml you change localhost in spring.datasource.url's value to machine's IP. 

- To run the docker on a port passed in command with -p, use following command

    ```shell script
    docker run -p 8081:8080 -it example-microservice
    ```

    You can hit localhost:8081 to run microservice as docker is running on 8081 port.

## 5. Endpoint
- Endpoint: 
    - http://localhost:8080/catalog/{customerId}
    valid customerIds are 'one' and 'two' as only these two recoerds are being added at the time of program running.
    
```html
Type: GET
HEADERS:
Content-Type: application/json
```


- Response
    ```json
     {
        "customerid":"one",
        "customername":"Jacob"
    }
    ```

## 6. Directory Structure
```html
.
│   .gitignore
│   Dockerfile
│   java
│   pom.xml
│   README.md
│   sample_microservice.iml
│
├───src
   ├───main
   │   │   main.iml
   │   │
   │   ├───java
   │   │   └───com
   │   │       └───ikea
   │   │           └───isx
   │   │               │   StarterApplication.java
   │   │               │
   │   │               ├───common
   │   │               │   └───utils
   │   │               │           CommonConstants.java
   │   │               │           ErrorMessage.java
   │   │               │
   │   │               ├───controller
   │   │               │       TestController.java
   │   │               │
   │   │               ├───dao
   │   │               │   ├───impl
   │   │               │   │       CustomerDAOImpl.java
   │   │               │   │
   │   │               │   └───interfaces
   │   │               │           CustomerDAO.java
   │   │               │           CustomerRepository.java
   │   │               │
   │   │               ├───model
   │   │               │       Customer.java
   │   │               │
   │   │               └───service
   │   │                   ├───impl
   │   │                   │       CustomerServiceImpl.java
   │   │                   │
   │   │                   └───interfaces
   │   │                           CustomerService.java
   │   │
   │   └───resources
   │           application.yml
   │
   └───test
       ├───java
       │   └───com
       │       └───ikea
       │           └───isx
       │               │   AbstractTest.java
       │               │
       │               ├───controller
       │               │       TestTestController.java
       │               │
       │               ├───dao
       │               │       TestCustomerDAOImpl.java
       │               │
       │               └───service
       │                       TestCustomerServiceImpl.java
       │
       ├───resources
       │       gatling.conf
       │       logback.xml
       │       recorder.conf
       │
       └───scala
           │   Engine.scala
           │   IDEPathHelper.scala
           │   Recorder.scala
           │
           ├───basic
           │       PerformanceTest.scala
           │
           └───com
               └───ikea
                   └───isx
                       ├───scenarios
                       │       CustomerMicroservice.scala
                       │
                       ├───simulation
                       │       MicroserviceSimulation.scala
                       │
                       └───util
                               Environment.scala
                               Headers.scala


```

### Performance test using Gatling
- Run
    ```shell script
    mvn gatling:test
    ``` 

    Gatling report can be seen by opening index.html file from target/gatling/microservicesimulation-{someno}/index.html in browser
    
### Testing
- Run _**unit testing, jacoco report, gatling performance test & sonarscanner analysis**_ at once using this command:
    ```shell script
    mvn clean test
    ```

### Code quality and Code coverage

- We can see jacoco reports form file index.html in folder target/site/jacoco. Just double click the index.html and it will open in browser.

- For sonarqube as the docker of sonarqube already running on localhost:9000, just hit [localhost:9000](localhost:9000).
  To analyse it in IDE only add sonalint plugin and right click on project folder in ide and select SonalLint Analyze option.

- For SpingActuator hit [localhost:8080/actuator/{endpoint}](localhost:8080/actuator/health)
  Endpoints can be /health, /matrics, /info