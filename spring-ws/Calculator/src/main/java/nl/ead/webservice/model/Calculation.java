package nl.ead.webservice.model;

import javax.persistence.*;

/**
 * Description for the class Calculation:
 * <p/>
 * Example usage:
 * <p/>
 * <pre>
 *
 * </pre>
 *
 * @author mdkr
 * @version Copyright (c) 2012 HAN University, All rights reserved.
 */
@Entity
public class Calculation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String calculationPerformed;
    private String calculationResult;

    public Calculation(String calculationPerformed, String calculationResult) {
        this.calculationPerformed = calculationPerformed;
        this.calculationResult = calculationResult;
    }

    public String getCalculationPerformed() {
        return calculationPerformed;
    }

    public void setCalculationPerformed(String calculationPerformed) {
        this.calculationPerformed = calculationPerformed;
    }

    public String getCalculationResult() {
        return calculationResult;
    }

    public void setCalculationResult(String calculationResult) {
        this.calculationResult = calculationResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
