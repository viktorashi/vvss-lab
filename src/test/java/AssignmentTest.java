import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AssignmentXMLRepository;
import service.Service;
import validation.AssignmentValidator;

public class AssignmentTest {
    private Service service;
    private AssignmentXMLRepository assignmentRepo;

    @BeforeEach
    void setUp() {
        String filename = "test_assignments.xml";
        AssignmentValidator assignmentValidator = new AssignmentValidator();
        assignmentRepo = new AssignmentXMLRepository(assignmentValidator, filename);
        Service service = new Service(null, assignmentRepo, null);
    }

    @Test
    void testValidAssignment(){

    }
}
