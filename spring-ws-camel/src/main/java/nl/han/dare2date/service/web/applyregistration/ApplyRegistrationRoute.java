/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.han.dare2date.service.web.applyregistration;

import nl.han.dare2date.service.web.applyregistration.model.ApplyRegistrationRequest;
import nl.han.dare2date.service.web.applyregistration.model.ApplyRegistrationResponse;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

/**
 * When you run this route, messages can be obtained from two sources:
 * - inbox-folder (located in the bin-directory of your Tomcat/Jetty instance (don't run this example with mvn tomcat:run,
 * instead use a separate server. In src/main/webapp/WEB-INF/applyregistrationservice an example messages is included which
 * you can copy to your inbox folder in case your webservice is not available.
 * - a webservice with its wsdl served at http://localhost:8080/Dare2DateCamel/applyregistration.wsdl
 * (otherwise: http://localhost:8080/applyregistration.wsdl)
 */
public class ApplyRegistrationRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        JaxbDataFormat jaxb = new JaxbDataFormat(ApplyRegistrationRequest.class.getPackage().getName());

        from("file://inbox")
                .from("spring-ws:rootqname:{http://www.han.nl/schemas/messages}ApplyRegistrationRequest?endpointMapping=#applyRegistrationEndpointMapping")
                    .unmarshal(jaxb).
                            log("${body}").
                                process(new Echo())
                                    .marshal(jaxb);
    }

    private static final class Echo implements Processor {
        public void process(Exchange exchange) throws Exception {
            ApplyRegistrationResponse registrationResponse = new ApplyRegistrationResponse();
            registrationResponse.setRegistration(exchange.getIn().getBody(ApplyRegistrationRequest.class).getRegistration());
            exchange.getOut().setBody(registrationResponse);
        }
    }
}
