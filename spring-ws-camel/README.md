How to run this route
---------------------
1. Run Maven: ```mvn clean package```
2. Create a run-configuration for Tomcat 7 or 8 (you can also use Jetty, see the alternative)
3. Deploy the war (Dare2DateCamel.war) to the Tomcat instance
4. Run Tomcat. If only the Tomcat homepage shows up you have to fall back to the <a name="alternative">alternative</a>
5. Visit http://localhost:8080/Dare2DateCamel/applyregistration.wsdl or drop a SOAP message in the inbox folder

[Alternative](#alternative)
-----------
For steps 2-4 you can also Run Maven with Jetty ```mvn jetty:run```. 

Implementation
--------------
When you run the ApplyRegistrationRoute, messages can be obtained from two sources:
* inbox-folder (located in the bin-directory of your Tomcat/Jetty instance (don't run this example with mvn tomcat:run, instead use a separate Tomcat server. In src/main/webapp/WEB-INF/applyregistrationservice an example messages is included which
 you can copy to your inbox folder in case your webservice is not available.
* a webservice with its wsdl served at http://localhost:8080/Dare2DateCamel/applyregistration.wsdl
 (otherwise: http://localhost:8080/applyregistration.wsdl)
