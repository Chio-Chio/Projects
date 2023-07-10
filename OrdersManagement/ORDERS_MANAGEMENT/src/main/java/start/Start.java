package start;

import presentation.StartController;
import presentation.StartView;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 * Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
//    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) /*throws SQLException*/ {

        StartView startView = new StartView();
        StartController startController = new StartController(startView);
        // BillDAO bll = new BillDAO();
        //bll.insert("name", "cipsuri", 5, 2);
//        ClientView clientView = new ClientView();
//        ProductView productView = new ProductView();
//        Student student = new Student("dummy name", "dummy address", "dummy@address.co", 12);
//
//        StudentBLL studentBll = new StudentBLL();
//        int id = studentBll.insertStudent(student);
//        if (id > 0) {
//            studentBll.findStudentById(id);
//        }
//
//
//        // Generate error
//        try {
//            studentBll.findStudentById(1);
//        } catch (Exception ex) {
//            LOGGER.log(Level.INFO, ex.getMessage());
//        }
//
//
//        //obtain field-value pairs for object through reflection
//        ReflectionExample.retrieveProperties(student);
    }

}
