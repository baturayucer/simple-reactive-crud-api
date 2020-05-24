# Simple Reactive Project 

Simple Reactive Client And Server With Spring Webflux and Reactive MongoDB

# 1. Simple Reactive Crud Api (Server)

## Api Documentation

### LegacyController:

https://app.swaggerhub.com/apis/baturayucer/reactive-item-api/0.1

![legacyController](simple-reactive-crud-api/src/main/resources/legacyController.png)

### FunctionalRouter:

https://app.swaggerhub.com/apis/baturayucer/reactive-item-api-functional-router/0.1

![functionalRouter](simple-reactive-crud-api/src/main/resources/FunctionalRouter.png)


## Design

![Architecture](simple-reactive-crud-api/src/main/resources/Architecture.png)


## Installation

* brew tap mongodb/brew

* brew install mongodb-community@4.2

* brew services start mongodb-community@4.2

* git clone https://github.com/baturayucer/simple-reactive-crud-api.git

* mvn clean install

- java -jar target/simple-reactive-crud-api-0.0.1-SNAPSHOT.jar