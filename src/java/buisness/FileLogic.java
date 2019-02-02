package buisness;

import dataaccess.GroupDAOImpl;
import java.util.List;
import dto.Group;
import dataaccess.DAOInterface;
import dataaccess.FileDAOImpl;
import dto.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
///import dto.factory.DTOFactoryCreator;
//import dto.factory.Factory;
import java.util.Map;

/**
 *
 * @course Shahriar Emami
 * @author Stanley Pieda
 */
public class FileLogic {


    private static final int FILE_SIZE_MAX_LENGTH = 65535;
   

    private DAOInterface<File> dao = null;
   // private Factory<Course> factory = null;

    public FileLogic() {

        dao = new FileDAOImpl();
        //factory = DTOFactoryCreator.createFactory( Course.class);
    }

    public List<File> getAllFiles() {
        return dao.getAll();
    }

     
    public void addFile(InputStream student) throws ValidationException, SQLException {
       
        dao.add(student);
    }

    public void addFile(File student) throws ValidationException, SQLException {
        validateFile(student);
        cleanFile(student);
        dao.add(student);
    }

    public void deleteFiles(String[] codes) throws ValidationException {
        for (String code : codes) {
            deleteFile(code);
        }
    }

    public void deleteFile(String code) throws ValidationException {
        dao.delete(code);
    }

    private void cleanFile(File student) {
      
        if (student.getFile() != null) {
            student.setFile(student.getFile());
        }
         
    }

    private void validateFile(File student) throws ValidationException, SQLException {
      //  validateString(student.getID(), "Course Code", COURSE_ID_MAX_LENGTH, false);
        validateFile(student.getFile(), "Student FirstName",FILE_SIZE_MAX_LENGTH, false);
      
    }

    private void validateFile(Blob value, String fieldName, int maxLength, boolean isNullAllowed) throws ValidationException, SQLException {
        if (value == null && isNullAllowed) {
            // null permitted, nothing to validate
        } else if (value == null && !isNullAllowed) {
            throw new ValidationException(String.format("%s cannot be null", fieldName));
        }  else if (value.length()> maxLength) {
            throw new ValidationException(String.format("%s cannot exceed %d characters", fieldName, maxLength));
        }
    }

   
}
