package buisness;

import dataaccess.GroupDAOImpl;
import java.util.List;
import dto.Group;
import dataaccess.DAOInterface;
import dto.File;
import dto.factory.DTOFactoryCreater;
import dto.factory.Factory;
///import dto.factory.DTOFactoryCreator;
//import dto.factory.Factory;
import java.util.Map;

/**
 *
 * @course Shahriar Emami
 * @author Stanley Pieda
 */
public class GroupLogic {


    private static final int GROUP_NAME_MAX_LENGTH = 45;
   

    private DAOInterface<Group> dao = null;
   private Factory<Group> factory = null;

    public GroupLogic() {

        dao = new GroupDAOImpl();
        factory = DTOFactoryCreater.createFactory( Group.class);
    }

    public List<Group> getAllGroups() {
        return dao.getAll();
    }

     public void addGroup(Map<String, String[]> student) throws ValidationException {
         addGroup(factory.createFromMap(student));
    }

    public void addGroup(Group student) throws ValidationException {
        validateGroup(student);
        cleanStudent(student);
        dao.add(student);
    }

    public void deleteGroups(String[] codes) throws ValidationException {
        for (String code : codes) {
            deleteGroup(code);
        }
    }

    public void deleteGroup(String code) throws ValidationException {
        dao.delete(code);
    }

    private void cleanStudent(Group student) {
      
        if (student.getName() != null) {
            student.setName(student.getName().trim());
        }
         
    }

    private void validateGroup(Group student) throws ValidationException {
      //  validateString(student.getID(), "Course Code", COURSE_ID_MAX_LENGTH, false);
        validateString(student.getName(), "Student FirstName", GROUP_NAME_MAX_LENGTH, false);
      
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
