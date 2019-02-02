package dto.factory;

import dto.Student;
import dto.builder.StudentBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 *
 * @author Shariar
 */
public class StudentFactory extends AbstractFactory< Student> {

    @Override
    public Student createFromResultSet(ResultSet rs) throws SQLException {
        if (rs == null || !rs.next()) {
            return null;
        }
        StudentBuilder builder = new StudentBuilder();
        builder.setFirstName(rs.getString(Student.COL_FIRSTNAME)).setLastName(Student.COL_LASTNAME).setID(rs.getInt(Student.COL_ID))
                ;
        return builder.get();
    }

    @Override
    public Student createFromMap(Map< String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        StudentBuilder builder = new StudentBuilder();
         builder.setFirstName(map.get(Student.COL_FIRSTNAME)[0]).setLastName(map.get(Student.COL_LASTNAME)[0]).setID(Integer.parseInt(map.get(Student.COL_ID)[0]));
        return builder.get();
    }
}
