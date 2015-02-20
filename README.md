# Connectivity proof of concept with AppDirect
## Goal
Create a sample java project that interacts with AppDirect's api.

## Author
Jean-Philippe Boudreault
jean-philippe.boudreault@polymtl.ca

## Background and Vision
Senior Java guy a bit rusty after spending the last year doing Rails and Angular only.

Acme Retail App was a holiday project to experiment on the new Spring features (Boot for example) featured on Spring.io 
website. When AppDirect's opportunity came by, I decided to use it as a base. This proved challenging for a couple of 
reasons, one of them being my familiarity with the new way of configuring things.

The vision was to have some data visible only when the user had a particular subscription level. Changes could have been
seen live. Unfortunately, I did not get the time to finish it up to this point.

## Configuration
Copy `application.properties.sample`, configure with appropriate values and rename to `application.properties`

## Creating a war
* on *linux* run `./graddlew war`
* on *windows* run `graddlew.bat war`
* war is located in `build\libs`

## Choices
### Gradle
First time using it, loving it!

### Spring Boot
First time using it, really unsure about it! But it seems like the new way.

### Lombok
Used it in most of my projets! Used the constructor too much a bit here.

### Guava
So useful but not really required here since there is no complex business logic (yet)

### JPA Repositories

### No Impls

### No real DB (sortof)
My choice was Postgresql but with time running short and AppFog supporting only MySQL, 
I decided that the in memory one from Spring Boot Starter JPA would do the trick.

## TODO
* use inheritance for models when possible
* use clean up serializer if possible
* readme setup with appdirect
* setup oauth
* order properties and stuff
* try to get rid of theleaf
* use mysql for persistence across reboots

* https://www.appdirect.com/rest/api/events/dummyAssign

http://projects.spring.io/spring-security-oauth/docs/oauth1.html
* http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth/consumer/client/OAuthRestTemplate.html

## REFERENCE
* http://info.appdirect.com/developers/docs/api_integration/api_overview/
*
*http://info.appdirect.com/developers/docs/sample_code_libraries/oauth_api_authorization/
*
*