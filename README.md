# Connectivity proof of concept with AppDirect
## Goal
Create a sample java project that interacts with AppDirect's api.

## Author
Jean-Philippe Boudreault
jean-philippe.boudreault@polymtl.ca

## Background and Vision
Senior Java guy a bit rusty after spending the last year doing Rails and Angular only.

Acme Retail App was a holiday project to experiment on the new Spring features (Boot specifically) featured on Spring.io 
website. When AppDirect's opportunity came by, I decided to use it as a base. This proved challenging for a couple of 
reasons, one of them being my familiarity with the new way of configuring things.

The vision was to have some data visible only when the user had a particular subscription level. Changes could have been
seen live. Unfortunately, I did not get the time to finish it up to this point.

I decided to leave some sections of the app unsecured by design to always display AppDirect's integration. Some sections
do require to be logged in.

## Configuration
* Create a developer account at https://www.appdirect.com/developers/register
* Clone this project
* Copy `application.properties.sample`, adjust with appropriate values and rename to `application.properties`
* Configure the endpoints on AppDirect with the following samples
* Run the application using `./gradlew bootRun`
* Login through AppDirect.

## Creating a war
* on *linux* run `./graddlew war`
* on *windows* run `graddlew.bat war`
* war is located in `build\libs`

## About the choices
### Gradle
First time using it, loving it! Similar to BuildConfig.groovy on Grails.

### Spring Boot
First time using it, a bit unsure about it! But this might become the new way of doing things.

### JPA Repositories
Repositories vs DAOs, though choice. A good old base DAO can be so usefull but a lot of Spring Starter Guides made me give Spring Data JPA a try.

### No Impls!
Interesting discussion with a collegue over that and YAGNI. Basically every project I worked on always had Interfaces and their one to one Impl.
Decided to try it out but still use Spring dependency injection.

### No real DB (sortof)
My choice was Postgresql but with time running short and AppFog supporting only MySQL, 
the In Memory DB from Spring Boot Starter JPA would do the trick.

### Lombok
Used it in most of my projets! @RequiredArgsConstructors is not really useable on real projects but could be used here to speed up object creation.

### Guava
So useful library for a serious Java project. Not really required here since there is no complex business logic (yet).

### Jodatime
Couldn't find a use for it in the current version of Acme Retail! Will become the standard with Java 8.

### No final
Skipped the final fields and variables for this projects since not everyone likes it, but I am usually a bit crazy
about placing finals everywhere.

### Angular
A no brainer, unfortunately with the time allowed I couldn't go further.

## TODO
* make stuff visible within angular
* test with appfog
* order properties and stuff
