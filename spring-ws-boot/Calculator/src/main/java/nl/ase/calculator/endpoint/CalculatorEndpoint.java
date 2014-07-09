package nl.ase.calculator.endpoint;

import nl.ase.calculator.*;
import nl.ase.calculator.entity.Calculation;
import nl.ase.calculator.entity.CalculationRepository;
import nl.ase.calculator.gateway.IMoviePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CalculatorEndpoint {
    private static final String NAMESPACE_URI = "http://www.han.nl/calculator";

    private CalculationRepository calculationDao;
    private IMoviePrinter moviePrinter;

    @Autowired
    public CalculatorEndpoint(CalculationRepository calculationDao, IMoviePrinter moviePrinter)
    {
        this.calculationDao = calculationDao;
        this.moviePrinter = moviePrinter;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CalculateRequest")
    @ResponsePayload
    public CalculateResponse calculateSumForName(@RequestPayload CalculateRequest req) {
        // a sequence of a minimum of 1 and unbounded max is generated as a
        // List<>
        List<Integer> paramList = req.getInput().getParamlist().getParam();
        CalculateOperation op = req.getInput().getOperation();
        int retValue = paramList.get(0);
        StringBuffer calculationInput = new StringBuffer();
        calculationInput.append(paramList.get(0));

        for (int i = 1; i < paramList.size(); i++) {
            // CalculateOperation is generated as an enum
            if (op.equals(CalculateOperation.ADD)) {
                retValue += paramList.get(i).intValue();
                calculationInput.append(" + " + paramList.get(i).intValue());
            } else if (op.equals(CalculateOperation.SUBTRACT)) {
                retValue -= paramList.get(i).intValue();
                calculationInput.append(" -" + paramList.get(i).intValue());
            } else if (op.equals(CalculateOperation.MULTIPLY)) {
                retValue *= paramList.get(i).intValue();
                calculationInput.append(" * " + paramList.get(i).intValue());
            } else if (op.equals(CalculateOperation.DIVIDE)) {
                retValue /= paramList.get(i).intValue();
                calculationInput.append(" / " + paramList.get(i).intValue());
            }
        }

        CalculateResult result = new CalculateResult();
        result.setMessage("Here are the results of the jury for the calculation " + calculationInput);
        result.setValue(retValue);
        CalculateResponse resp = new CalculateResponse();
        resp.setResult(result);

        // OK, I know this isn't the best example of an external service for a calculator....
        moviePrinter.printMovieDetails("Bond");

        Calculation calculation = new Calculation(calculationInput.toString(),retValue+"");
        calculationDao.save(calculation);

        return resp;
    }
}
