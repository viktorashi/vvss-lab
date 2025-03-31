package repository;

import domain.Assignment;
import validation.Validator;

public class TemaRepository extends AbstractCRUDRepository<String, Assignment> {
    public TemaRepository(Validator<Assignment> validator) {
        super(validator);
    }
}

