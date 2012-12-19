* To start:
        * Fix your JDK to 1.6
        * Try to run mvn tomcat:run
        * If it fails on javax.activation:
                * Download the file manually: http://mirrors.ibiblio.org/maven2/javax/xml/bind/activation/1.0.2/activation-1.0.2.jar
                * Add it to your local repo:
                        mvn install:install-file -DgroupId=javax.activation -DartifactId=activation -Dversion=1.0.2 -Dpackaging=jar -Dfile=activation-1.0.2.jar
        * Try to run mvn tomcat:run again
        * Visit the WSDL manually or use SOAPUI: http://localhost:8080/holidayService/holiday.wsdl
	* When running the projects results in a HTTP Bad Request, try to run the web-application in a separate Tomcat instance: Download and install Tomcat, Create a Server Configuration in your IDE and start/debug your application on Tomcat in your IDE.
* To debug:         
	* export (or set on Windows) MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n"
        * In your IDE: Debug->Remote Server/Process (and attach to debub port 4000)
* Uses spring-ws 2.0.2
* Uses JDOM and XPATH to parse SOAP/XML
* One XSD document
* Simple configuration of spring-ws-servlet.xml
* Uses DI/IoC to inject an external service
