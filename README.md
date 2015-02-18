# Connectivity proof of concept with AppDirect
## Goal
Create a sample java project that interacts with AppDirect's api.

## Author
Jean-Philippe Boudreault
jean-philippe.boudreault@polymtl.ca

## Configuration
Copy `application.properties.sample`, configure with appropriate values and rename to `application.properties`

## Creating a war
* on *linux* run `./graddlew war`
* on *windows* run `graddlew.bat war`
* war is located in `build\libs`

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