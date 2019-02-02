package buisness;

import dataaccess.StudentDAOImpl;
import java.util.List;
import dto.Student;
import dataaccess.DAOInterface;
///import dto.factory.DTOFactoryCreator;
//import dto.factory.Factory;
import java.util.Map;

/**
 *
 * @course Shahriar Emami
 * @author Stanley Pieda
 */
public class StudentLogic {


    private static final int STUDENT_FIRSTNAME_MAX_LENGTH = 45;
    private static final int STUDENT_LASTNAME_MAX_LENGTH = 45;

    private DAOInterface<Student> dao = null;
   // private Factory<Course> factory = null;

    public StudentLogic() {
        dao = new StudentDAOImpl();
        //factory = DTOFactoryCreator.createFactory( Course.class);
    }

    public List<Student> getAllStudents() {
        return dao.getAll();
    }

     public void addStudent(Map<String, String[]> student) throws ValidationException {
        //addCourse( factory.createFromMap(course));
        Student c = new Student(student.get(Student.COL_FIRSTNAME)[0], student.get(Student.COL_LASTNAME)[0],Integer.parseInt(student.get(Student.COL_ID)[0]));
        addStudent(c);
    }

    public void addStudent(Student student) throws ValidationException {
        validateStudent(student);
        cleanStudent(student);
        dao.add(student);
    }

    public void deleteStudents(String[] codes) throws ValidationException {
        for (String code : codes) {
            deleteStudent(code);
        }
    }

    public void deleteStudent(String code) throws ValidationException {
        dao.delete(code);
    }

    private void cleanStudent(Student student) {
        if (student.getID() != 0) {
            student.setID(student.getID());
        }
        if (student.getFirstName() != null) {
            student.setFirstName(student.getFirstName().trim());
        }
         if (student.getLastName() != null) {
            student.setLastName(student.getLastName().trim());
        }
    }

    private void validateStudent(Student student) throws ValidationException {
      //  validateString(student.getID(), "Course Code", COURSE_ID_MAX_LENGTH, false);
        validateString(student.getFirstName(), "Student FirstName", STUDENT_FIRSTNAME_MAX_LENGTH, false);
        validateString(student.getLastName(), "Student LastName", STUDENT_LASTNAME_MAX_LENGTH, false);
    }

    private void validateString(String value, String fieldName, int maxLength, boolean isNullAllowed) throws ValidationException {
        if (value == null && isNullAllowed) {
            // null permitted, nothing to validate
        } else if (value == null && !isNullAllowed) {
            throw new ValidationException(String.format("%s cannot be null", fieldName));
        } else if (value.isEmpty()) {
            throw new ValidationException(String.format("%s cannot be empty or only whitespace", fieldName));
        } else if (value.length() > maxLength) {
            throw new ValidationException(String.format("%s cannot exceed %d characters", fieldName, maxLength));
        }
    }
}
