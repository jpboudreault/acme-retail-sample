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

The vision was to have some data visible only when the user had a particular subscription level. This behavior can be
observed when logged as a FREE or PREMIUM subscriber.

I decided to leave some sections of the app unsecured by design to always display AppDirect's integration. Some sections
do require to be logged in.

## AppDirect Configuration
* Create a developer account at https://www.appdirect.com/developers/register
* Create a new product
* Configure your product to have 2 editions with the following Edition Codes : FREE, PREMIUM
* Configure the endpoints on AppDirect based on the section below

| Endpoint                                                        | URL         |
| -------------------------------------------------------------- | ----------- |
| `server-url`/login/openid?openid_identifier={openid}           | Login       |
| `server-url`/*                                                 | Realm       |
| `server-url`/api/app-direct/subscription-order?url={eventUrl}  | Create      |
| `server-url`/api/app-direct/subscription-change?url={eventUrl} | Change      |
| `server-url`/api/app-direct/subscription-cancel?url={eventUrl} | Cancel      |
| `server-url`/api/app-direct/subscription-notice?url={eventUrl} | Notice      |
| `server-url`/api/app-direct/user-assignment?url={eventUrl}     | User Add    |
| `server-url`/api/app-direct/user-unassignment?url={eventUrl}   | User Delete |

## Code Configuration
* Clone this project
* Copy `application.properties.sample`, adjust with appropriate values and rename to `application.properties`
* Run the application using `./gradlew bootRun`
* Login in the application through your AppDirect account.

## Creating a war
* on *linux* run `./graddlew war`
* on *windows* run `graddlew.bat war`
* war is located in `build\libs`

## About the choices
### Gradle
First time using it, loving it! Similar to BuildConfig.groovy on Grails.

### Spring Boot
First time using it, a bit lost. According to spring.io, this is the new way.

### JPA Repositories
Repositories vs DAOs, though choice. A good old base DAO can be so useful. A lot of Spring Starter Guides made me 
pick Spring Data JPA for a try.

### No Impls!
Interesting discussion with a collegue over that and YAGNI. Basically every project I worked on always had Interfaces and their one to one Impl.
Decided to try it out but still use Spring dependency injection.

### No real DB (sortof)
My choice was Postgresql but with time running short and AppFog supporting only MySQL, 
the In Memory DB from Spring Boot Starter JPA would do the trick.

### Lombok
Used it in most of my projets! 

### Guava
Useful library for any serious Java project. Overkill here.

### Jodatime
Couldn't find a use for it in the current version of Acme Retail! Will become the standard with Java 8.

### No final modifier
Skipped the final fields and variables for this projects since not everyone like it. I am usually a bit crazy
about placing finals everywhere.

### Angular
A no brainer, unfortunately with the time allowed I couldn't go further than some basic stuff.