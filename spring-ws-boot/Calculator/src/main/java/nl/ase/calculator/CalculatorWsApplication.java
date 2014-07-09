package nl.ase.calculator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class CalculatorWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorWsApplication.class, args);
    }
}
