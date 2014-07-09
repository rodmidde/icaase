/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.ase.calculator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CalculatorWsApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class CalculatorWsApplicationTests {

	@Rule
	public OutputCapture output = new OutputCapture();

	private WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

	@Value("${local.server.port}")
	private int serverPort;

	@Before
	public void setUp() {
		this.webServiceTemplate.setDefaultUri("http://localhost:" + this.serverPort
				+ "/ws/");
	}

	@Test
	public void testSendingHolidayRequest() {
		final String request = "<cal:CalculateRequest xmlns:cal=\"http://www.han.nl/calculator\">\n" +
                "         <input>\n" +
                "            <!--You may enter the following 2 items in any order-->\n" +
                "            <paramlist>\n" +
                "               <!--1 or more repetitions:-->\n" +
                "               <param>1</param>\n" +
                "               <param>4</param>\n" +
                "            </paramlist>\n" +
                "            <operation>add</operation>\n" +
                "         </input>\n" +
                "      </cal:CalculateRequest>";

		StreamSource source = new StreamSource(new StringReader(request));
		StreamResult result = new StreamResult(System.out);

		this.webServiceTemplate.sendSourceAndReceiveToResult(source, result);
		assertThat(this.output.toString(), containsString("<value>5</value>"));
	}

}
