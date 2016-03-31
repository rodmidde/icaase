Steps
-----
1. Register a Facebook App and generate an accesstoken, appid and secret (https://developers.facebook.com/apps/ and https://developers.facebook.com/tools/explorer/)
2. Register a Twitter App and generate an accesstoken, secret, consumerkey and consumersecret (https://apps.twitter.com/)
3. Modify the spring-ws-servlet.xml
4. Run mvn generate-sources
5. Run mvn package
6. Run the .war on Tomcat or Jetty (mvn tomcat:run may fail so mvn jetty:run can save your ass)
7. Visit localhost:8080/match/match.wsdl for the WSDL and call it with SOAPUI, you'll find match.xsd.xml as an example input
