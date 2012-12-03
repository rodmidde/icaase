* To start:
	* Fix your JDK to 1.6
	* Try to run mvn tomcat:run
	* If it fails on javax.activation: 
		* Download the file manually: http://mirrors.ibiblio.org/maven2/javax/xml/bind/activation/1.0.2/activation-1.0.2.jar
		* Add it to your local repo: 
			mvn install:install-file -DgroupId=javax.activation -DartifactId=activation -Dversion=1.0.2 -Dpackaging=jar -Dfile=activation-1.0.2.jar
	* Try to run mvn tomcat:run again
	* Visit the WSDL manually or use SOAPUI: http://localhost:8080/Calculator/calculatorservice.wsdl
* To debug: 
	* export (or set on Windows) MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n"
	* In your IDE: Debug->Remote Server/Process (and attach to debub port 4000)
* Uses spring-ws 1.5.2
* Uses XSD validation
* Uses JAXB XML binding
* Two sets of XSD documents
* Uses XML-RPC call to MovieMeter, so this service is actually a client too
* Extensive configuration of spring-ws-servlet.xml
