package nl.ase.calculator.entity;

import org.springframework.data.repository.CrudRepository;

public interface CalculationRepository extends CrudRepository<Calculation, Long> {
}
