import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.Assignment;
import repository.AssignmentXMLRepository;
import service.Service;
import validation.AssignmentValidator;
import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {
    private Service service;
    private AssignmentXMLRepository assignmentRepo;

    @BeforeEach
    void setUp() {
        String filename = "test_assignments.xml";
        AssignmentValidator assignmentValidator = new AssignmentValidator();
        assignmentRepo = new AssignmentXMLRepository(assignmentValidator, filename);
        service = new Service(null, assignmentRepo, null);
    }

    @Test
    void testValidAssignment(){
//
//        String id = "1";
//        String descriere = "tema1";
//        int deadline = 5;
//        int startline = 3;
//        int result = service.saveTema(id, descriere, deadline, startline);
//        assertEquals(0, result, "Should return 0 for successful assignment addition");
//        Assignment savedAssignment = assignmentRepo.findOne(id);
//        assertNotNull(savedAssignment, "Assignment should exist in repository");
//        assertEquals(id, savedAssignment.getID(), "Assignment ID should match");
//        assertEquals(descriere, savedAssignment.getDescriere(), "Assignment description should match");
//        assertEquals(deadline, savedAssignment.getDeadline(), "Assignment deadline should match");
//        assertEquals(startline, savedAssignment.getStartline(), "Assignment startline should match");
    }
}
