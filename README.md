# Overview

A Mancala Game as a SpringBoot Application with a ReactJs interface, using MongoDb to persist resources.

# TechStack
  * Java >= 1.8
  * SpringBoot 2.5.5
  * Maven
  * MongoDB
  * ReactJS
  * Docker
  * docker-compose
  * Lombok
  * JACOCO
  
# Configuration
  * If one prefer to run the application with Maven, it needs to configure your ```/etc/hosts```and put a new line ```127.0.0.1 mongo```so MongoDb can connect correctly
  * Assert that Docker is running on the machine so maven can build the docker image.
  * Build the application with ```mvn clean install```. 
  
# Runnig
 * The best way to run the application is to run ```docker-compose up``` in the root folder of the project or if one prefer, it can be run with ```mvn spring-boot:run```
 * The application will be available at ```http://localhost:8080```
