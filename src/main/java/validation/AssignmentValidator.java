package validation;

import domain.Assignment;

public class AssignmentValidator implements Validator<Assignment> {
    public void validate(Assignment assignment) throws ValidationException {
        if (assignment.getID() == null || assignment.getID().equals("")) {
            throw new ValidationException("ID invalid! \n");
        }
        if (assignment.getDescriere() == null || assignment.getDescriere().equals("")) {
            throw new ValidationException("Descriere invalida! \n");
        }
        if (assignment.getStartline() < 1 || assignment.getStartline() > 14) {
            throw new ValidationException("Data de primire invalida! trebuie intre [1-14] \n");
        }
        if (assignment.getDeadline() < 1 || assignment.getDeadline() > 14) {
            throw new ValidationException("Deadline invalid!  trebuie intre [1-14] \n");
        }
        if (assignment.getStartline() > assignment.getDeadline()) {
            throw new ValidationException("Deadlineu inainte de startline!! \n");
        }
    }
}

