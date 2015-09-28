When you run the ApplyRegistrationRoute, messages can be obtained from two sources:
* inbox-folder (located in the bin-directory of your Tomcat/Jetty instance (don't run this example with mvn tomcat:run, instead use a separate Tomcat server. In src/main/webapp/WEB-INF/applyregistrationservice an example messages is included which
 you can copy to your inbox folder in case your webservice is not available.
* a webservice with its wsdl served at http://localhost:8080/Dare2DateCamel/applyregistration.wsdl
 (otherwise: http://localhost:8080/applyregistration.wsdl)
