import console.UI;
import repository.AssignmentXMLRepository;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.AssignmentValidator;

public class Main {
    public static void main(String[] args) {

        NotaValidator notaValidator = new NotaValidator();
        AssignmentValidator temaValidator = new AssignmentValidator();
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        AssignmentXMLRepository fileRepository2 = new AssignmentXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        UI consola = new UI(service);
        consola.run();

        //PENTRU GUI
        // de avut un check: daca profesorul introduce sau nu saptamana la timp
        // daca se introduce nota la timp, se preia saptamana din sistem
        // altfel, se introduce de la tastatura
    }
}
