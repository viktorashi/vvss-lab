package repository;

import domain.Assignment;
import validation.Validator;

public class AssignmentRepository extends AbstractCRUDRepository<String, Assignment> {
    public AssignmentRepository(Validator<Assignment> validator) {
        super(validator);
    }
}

