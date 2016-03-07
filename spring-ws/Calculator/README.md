* To start:
    * Install MySQL, create a database named dare2date accessible for the root user using no password. For connection details see persistence.xml.
	* Fix your JDK to 1.6 or higher
	* When you use IntelliJ just open the pom.xml instead of importing the project
	* Make sure you installed Maven 3.3 or higher
	* Try to run mvn clean package (use mvn generate-sources later to re-generate the Java stubs based on the XSD-files)
	* If it fails on javax.activation: 
		* Watch the within-a-minute screencast: https://vimeo.com/67329514
		* Download the file manually: http://mirrors.ibiblio.org/maven2/javax/xml/bind/activation/1.0.2/activation-1.0.2.jar
		* Add it to your local repo: 
			mvn install:install-file -DgroupId=javax.activation -DartifactId=activation -Dversion=1.0.2 -Dpackaging=jar -Dfile=activation-1.0.2.jar
	* Try to run mvn tomcat:run (or deploy the app to a Tomcat server running in your IDE)
	* Visit the WSDL manually or use SOAPUI: http://localhost:8080/Calculator/calculatorservice.wsdl
	* When running the projects results in a HTTP Bad Request, try to run the web-application in a separate Tomcat instance: Download and install Tomcat, Create a Server Configuration in your IDE and start/debug your application on Tomcat in your IDE.
* To debug: 
	* export (or set on Windows) MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n"
	* In your IDE: Debug->Remote Server/Process (and attach to debub port 4000)
* Uses spring-ws 1.5.2
* Uses XSD validation
* Uses JAXB XML binding
* Two sets of XSD documents
* Uses REST call to MovieMeter, so this service is actually a client too
* Extensive configuration of spring-ws-servlet.xml
* Uses JPA/Hibernate with MySQL
* Watch the demo if you want a step-by-step introduction to this codebase: https://vimeo.com/66848876
