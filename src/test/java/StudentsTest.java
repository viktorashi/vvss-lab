package test;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentRepository;
import validation.StudentValidator;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {
    private StudentRepository studentRepo;

    @BeforeEach
    void testSetUp() {

        StudentValidator studentValidator = new StudentValidator();
        studentRepo = new StudentRepository(studentValidator);

//        //        goleste fisieru ala inaint de fiecare test ca sa fie curat
//        mde- nu prea merge cu asta tbh
//        try {
//            Transformer XMLtransformer = TransformerFactory.newInstance().newTransformer();
//            XMLtransformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            Document XMLdocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//            XMLtransformer.transform(new DOMSource(XMLdocument), new StreamResult(filename));
//        } catch (ParserConfigurationException | TransformerException e) {
//            e.printStackTrace();
//        }

    }

    @Test
    void testAddValidStudent() {
        String id = "10";
        String nume = "Viki";
        int grupa = 832;
        Student student = new Student(id, nume, grupa);
        Student result = studentRepo.save(student);
        assertNotNull(result, "Should return 0 for successful student addition");
        Student savedStudent = studentRepo.findOne(id);
        assertNotNull(savedStudent, "Student should exist in repository");
        assertEquals(id, savedStudent.getID(), "Student ID should match");
        assertEquals(nume, savedStudent.getNume(), "Student name should match");
        assertEquals(grupa, savedStudent.getGrupa(), "Student group should match");
    }

    @Test
    void testAddInvalidStudentID() {
        String id = "";
        String nume = "Andrei";
        int grupa = 832;
        Student student = new Student(id, nume, grupa);
        Student result = studentRepo.save(student);
        assertNull(result, "Should return 1 for invalid student");
        Student savedStudent = studentRepo.findOne(id);
        assertNull(savedStudent, "Invalid student should not exist in repository");
    }

    @Test
    void testWeirdButValidStringStudentID() {
        String id = "lmao";
        String name = "markel";
        int grupa = 831;
        Student student = new Student(id, name, grupa);
        Student result = studentRepo.save(student);
        assertNotNull(result, "ar trb sa fie ok dar ciudatel");
        Student savedStudent = studentRepo.findOne(id);
        assertNotNull(savedStudent, "Student should exist in repository");
        assertEquals(id, savedStudent.getID(), "Student ID should match");
        assertEquals(name, savedStudent.getNume(), "Student name should match");
        assertEquals(grupa, savedStudent.getGrupa(), "Student group should match");
    }

    @Test
    void testInvalidName() {
        String id = "100";
        String name = "";
        int grupa = 832;
        Student student = new Student(id, name, grupa);
        Student result = studentRepo.save(student);
        assertNull(result, "Should return 1 for invalid student");
        Student savedStudent = studentRepo.findOne(id);
        assertNull(savedStudent, "Invalid student should not exist in repository");
    }

    @Test
    void testGrupaSmall() {
        String id = "101";
        String name = "mihaita";
        int grupa = 0;
        Student student = new Student(id, name, grupa);
        Student result = studentRepo.save(student);
        assertNull(result, "Should return 1 for invalid student");
        Student savedStudent = studentRepo.findOne(id);
        assertNull(savedStudent, "Invalid student should not exist in repository");
    }

    @Test
    void testGrupaBig() {
        String id = "102";
        String name = "mihaita";
        int grupa = 1000;
        Student student = new Student(id, name, grupa);
        Student result = studentRepo.save(student);
        assertNull(result, "Should return 1 for invalid student");
        Student savedStudent = studentRepo.findOne(id);
        assertNull(savedStudent, "Invalid student should not exist in repository");
    }

    @Test
    void GroupLowerBoundryValueBad() {
        String id = "301";
        String name = "markel";
        int grupa = 110;
        Student student = new Student(id, name, grupa);
        Student result = studentRepo.save(student);
        assertNull(result, "nu-l pune");
        Student savedStudent = studentRepo.findOne(id);
        assertNull(savedStudent, "Invalid student should not exist in repository");
    }

    @Test
    void GroupHigherBoundryValueBad() {
        String id = "400";
        String name = "markel";
        int grupa = 938;
        Student student = new Student(id, name, grupa);
        Student result = studentRepo.save(student);
        assertNull(result, "nu-l pune");
        Student savedStudent = studentRepo.findOne(id);
        assertNull(savedStudent, "Invalid student should not exist in repository");
    }

    @Test
    void groupLowerBoundryValueGood() {
        String id = "hahaha";
        String name = "markel";
        int grupa = 111;
        Student student = new Student(id, name, grupa);
        Student result = studentRepo.save(student);
        assertNotNull(result, "ar trebui sa-l puna");
        Student savedStudent = studentRepo.findOne(id);
        assertNotNull(savedStudent, "Student should exist in repository");
        assertEquals(id, savedStudent.getID(), "Student ID should match");
        assertEquals(name, savedStudent.getNume(), "Student name should match");
        assertEquals(grupa, savedStudent.getGrupa(), "Student group should match");
    }

    @Test
    void groupHigherBoundryValueGood() {
        String id = "laalllall";
        String name = "markel";
        int grupa = 937;
        Student student = new Student(id, name, grupa);
        Student result = studentRepo.save(student);
        assertNotNull(result, "nu-l pune");
        Student savedStudent = studentRepo.findOne(id);
        assertNotNull(savedStudent, "Student should exist in repository");
        assertEquals(id, savedStudent.getID(), "Student ID should match");
        assertEquals(name, savedStudent.getNume(), "Student name should match");
        assertEquals(grupa, savedStudent.getGrupa(), "Student group should match");
    }

    @Test
    void LongID() {
//        ok nu prea are sens ca n-are treaba cu implementarea lui saveStudent doar cu VMu dar da =))
        int length_limit = 2_147_483_645;
        try {
            String id = "a".repeat(length_limit);
            String name = "markel";
            int grupa = 831;
            Student student = new Student(id, name, grupa);
            Student result = studentRepo.save(student);
        } catch (OutOfMemoryError err) {
            assertTrue(true, "bun, da n-avea cum sa aiba loc");
        } catch (Exception err) {
            fail("Cumva a dat doar exception da trebuia sa dea eroare din aia care nus e catchiuieste");
        }
    }

    @Test
    void LongName() {
        int length_limit = 2_147_483_645;
        try {
            String id = "10sute";
            String name = "a".repeat(length_limit);
            int grupa = 831;
            Student student = new Student(id, name, grupa);
            Student result = studentRepo.save(student);
        } catch (OutOfMemoryError err) {
            assertTrue(true, "bun, da n-avea cum sa aiba loc");
        } catch (Exception err) {
            fail("Cumva a dat doar exception da trebuia sa dea eroare din aia care nus e catchiuieste");
        }
    }
}