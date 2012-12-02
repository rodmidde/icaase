* To start:
        * Try to run mvn tomcat:run
        * If it fails on javax.activation:
                * Download the file manually: http://mirrors.ibiblio.org/maven2/javax/xml/bind/activation/1.0.2/activation-1.0.2.jar
                * Add it to your local repo:
                        mvn install:install-file -DgroupId=javax.activation -DartifactId=activation -Dversion=1.0.2 -Dpackaging=jar -Dfile=activation-1.0.2.jar
        * Try to run mvn tomcat:run again
        * Visit the WSDL manually or use SOAPUI: http://localhost:8080/holidayService/holiday.wsdl
* Uses spring-ws 2.0.2
* Uses JDOM and XPATH to parse SOAP/XML
* One XSD document
* Simple configuration of spring-ws-servlet.xml
* Uses DI/IoC to inject an external service
