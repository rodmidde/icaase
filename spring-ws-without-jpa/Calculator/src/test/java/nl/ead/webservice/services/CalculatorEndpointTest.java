package nl.ead.webservice.services;

import nl.ead.webservice.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorEndpointTest {

    private CalculatorEndpoint calculatorEndpoint;
    private Mockery context = new Mockery();

    @Before
    public void setUp() throws Exception {
        final IMoviePrinter moviePrinter = context.mock(IMoviePrinter.class);

        // moviePrinter is a mock
        calculatorEndpoint = new CalculatorEndpoint(moviePrinter);

        // expectations
        context.checking(new Expectations() {{
            oneOf (moviePrinter).printMovieDetails("Bond");
        }});
    }

    @Test
    public void addingOneAndTwoResultsInThree() throws Exception {
        CalculateRequest calculateRequest = new CalculateRequest();
        calculateRequest.setInput(new CalculateInput(){
            {
                setOperation(CalculateOperation.ADD);
                CalculateParameters calculateParameters = new CalculateParameters();
                calculateParameters.getParam().add(1);
                calculateParameters.getParam().add(2);
                setParamlist(calculateParameters);
            }
        });

        assertEquals(3, calculatorEndpoint.calculateSumForName(calculateRequest).getResult().getValue());

        context.assertIsSatisfied();

    }
}