package validation;

import domain.Assignment;

public class AssignmentValidator implements Validator<Assignment> {
    public void validate(Assignment assignment) throws ValidationException {
        if (assignment.getID() == null || assignment.getID().equals("")) {
            throw new ValidationException("ID invalid! nu poate fi null sau gol '' ");
        }
        if (assignment.getDescriere() == null || assignment.getDescriere().equals("")) {
            throw new ValidationException("Descriere invalida! nu poate fi nul sau gol ''");
        }
        if (assignment.getStartline() < 1 || assignment.getStartline() > 14) {
            throw new ValidationException("Data de primire invalida! trebuie intre [1-14] ");
        }
        if (assignment.getDeadline() < 1 || assignment.getDeadline() > 14) {
            throw new ValidationException("Deadline invalid!  trebuie intre [1-14] ");
        }
        if (assignment.getStartline() > assignment.getDeadline()) {
            throw new ValidationException("Deadlineu inainte de startline!! ");
        }
    }
}

