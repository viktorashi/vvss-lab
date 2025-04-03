import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.Assignment;
import org.junit.jupiter.api.TestTemplate;
import repository.AssignmentFileRepository;
import repository.AssignmentRepository;
import repository.AssignmentXMLRepository;
import repository.StudentRepository;
import service.Service;
import validation.AssignmentValidator;
import validation.StudentValidator;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {
    private AssignmentRepository assignmentRepo;

    @BeforeEach
    void setUp() {
        AssignmentValidator assignmentValidator = new AssignmentValidator();
        assignmentRepo = new AssignmentRepository(assignmentValidator);
    }

    @Test
    void testValidAssignment() {
        String id = "1";
        String descriere = "tema1";
        int startline = 3;
        int deadline = 5;
        Assignment assignment = new Assignment(id, descriere, startline, deadline);
        Assignment result = assignmentRepo.save(assignment);
        assertNotNull(result, "Shouldn't be null");
        Assignment savedAssignment = assignmentRepo.findOne(id);
        assertNotNull(savedAssignment, "Assignment should exist in repository");
        assertEquals(id, savedAssignment.getID(), "Assignment ID should match");
        assertEquals(descriere, savedAssignment.getDescriere(), "Assignment description should match");
        assertEquals(deadline, savedAssignment.getDeadline(), "Assignment deadline should match");
        assertEquals(startline, savedAssignment.getStartline(), "Assignment startline should match");
    }

    @Test
    void testInvalidAssignmentIDEmpty() {
        String id = "";
        String descriere = "tema1";
        int startline = 3;
        int deadline = 5;
        Assignment assignment = new Assignment(id, descriere, startline, deadline);
        Assignment result = assignmentRepo.save(assignment);
        assertNull(result, "Should return null because id is empty");
        Assignment savedAssignment = assignmentRepo.findOne(id);
        assertNull(savedAssignment, "Invalid assignment should not exist in repository");
    }

    @Test
    void testInvalidAssignmentIDnull() {
        String id = null;
        String descriere = "tema1";
        int startline = 3;
        int deadline = 5;
        Assignment assignment = new Assignment(id, descriere, startline, deadline);
        Assignment result = assignmentRepo.save(assignment);
        assertNull(result, "Should return null because id is null");
        try {
            Assignment savedAssignment = assignmentRepo.findOne(id);
            fail("Invalid assignment should not exist in repository");
        } catch (Exception e) {
            System.out.println("ID is null");
        }
    }

    @Test
    void testInvalidAssignmentDescriptionEmpty() {
        String id = "1";
        String descriere = "";
        int startline = 3;
        int deadline = 5;
        Assignment assignment = new Assignment(id, descriere, startline, deadline);
        Assignment result = assignmentRepo.save(assignment);
        assertNull(result, "Should return null because description is empty");
        Assignment savedAssignment = assignmentRepo.findOne(id);
        assertNull(savedAssignment, "Invalid assignment should not exist in repository");
    }

    @Test
    void testInvalidAssignemntDescriptionNull() {
        String id = "1";
        String descriere = null;
        int startline = 3;
        int deadline = 5;
        Assignment assignment = new Assignment(id, descriere, startline, deadline);
        Assignment result = assignmentRepo.save(assignment);
        assertNull(result, "Should return null because description is null");
        Assignment savedAssignment = assignmentRepo.findOne(id);
        assertNull(savedAssignment, "Invalid assignment should not exist in repository");
    }

    @Test
    void testInvalidStartlineSmall() {
        String id = "1";
        String descriere = "tema1";
        int startline = -1;
        int deadline = 5;
        Assignment assignment = new Assignment(id, descriere, startline, deadline);
        Assignment result = assignmentRepo.save(assignment);
        assertNull(result, "Should return null because startline is negative");
        Assignment savedAssignment = assignmentRepo.findOne(id);
        assertNull(savedAssignment, "Invalid assignment should not exist in repository");
    }

    @Test
    void testInvalidStartlineBig() {
        String id = "1";
        String descriere = "tema1";
        int startline = 20;
        int deadline = 21;
        Assignment assignment = new Assignment(id, descriere, startline, deadline);
        Assignment result = assignmentRepo.save(assignment);
        assertNull(result, "Should return null because startline is bigger than 14");
        Assignment savedAssignment = assignmentRepo.findOne(id);
        assertNull(savedAssignment, "Invalid assignment should not exist in repository");
    }

    @Test
    void testInvalidDeadLineSmall() {
        String id = "1";
        String descriere = "tema1";
        int startline = 13;
        int deadline = 0;
        Assignment assignment = new Assignment(id, descriere, startline, deadline);
        Assignment result = assignmentRepo.save(assignment);
        assertNull(result, "Should return null because deadline is 0");
        Assignment savedAssignment = assignmentRepo.findOne(id);
        assertNull(savedAssignment, "Invalid assignment should not exist in repository");
    }

    @Test
    void testInvalidDeadlineBig() {
        String id = "1";
        String descriere = "tema1";
        int startline = 3;
        int deadline = 16;
        Assignment assignment = new Assignment(id, descriere, startline, deadline);
        Assignment result = assignmentRepo.save(assignment);
        assertNull(result, "Should return null because deadline is over 14");
        Assignment savedAssignment = assignmentRepo.findOne(id);
        assertNull(savedAssignment, "Invalid assignment should not exist in repository");
    }

    @Test
    void testInvalidStartlineBiggerThanDeadline() {
        String id = "1";
        String descriere = "tema1";
        int startline = 5;
        int deadline = 3;
        Assignment assignment = new Assignment(id, descriere, startline, deadline);
        Assignment result = assignmentRepo.save(assignment);
        assertNull(result, "Should return null because startline is bigger than deadline");
        Assignment savedAssignment = assignmentRepo.findOne(id);
        assertNull(savedAssignment, "Invalid assignment should not exist in repository");
    }

}
