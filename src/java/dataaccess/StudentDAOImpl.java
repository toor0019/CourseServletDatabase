package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dto.Student;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author Shawn
 */
public class StudentDAOImpl implements DAOInterface<Student> {

    private static final String GET_ALL_STUDENTS = "SELECT id, first_name, last_name FROM Student ORDER BY id";
    private static final String INSERT_STUDENTS = "INSERT INTO Student (id, first_name,last_name) VALUES(?, ?, ?)";
    private static final String DELETE_STUDENT = "DELETE FROM Student WHERE id = ?";
    private static final String DELETE_STUDENTS = "DELETE FROM Student WHERE (id) IN ";
    private static final String UPDATE_STUDENTS = "UPDATE Student SET first_name = ? WHERE id = ?";
    private static final String GET_BY_CODE_STUDENTS = "SELECT id, first_name, last_name FROM Student WHERE first_name = ?";

   // private final Factory<Course> factory = DTOFactoryCreator.createFactory(Course.class);

    @Override
    public List<Student> getAll() {
        List<Student> students = Collections.emptyList();
        Student student;
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ALL_STUDENTS);
                ResultSet rs = pstmt.executeQuery();) {
            //courses = factory.createListFromResultSet(rs);
            students = new ArrayList<>(100);
            while(rs.next()){
                student = new Student();
                student.setID(rs.getInt(Student.COL_ID));
                student.setFirstName(rs.getString(Student.COL_FIRSTNAME));
                student.setLastName(rs.getString(Student.COL_LASTNAME));
            
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    @Override
    public void add(Student student) {
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_STUDENTS);) {
            pstmt.setInt(1, student.getID());
            pstmt.setString(2, student.getFirstName());
                pstmt.setString(3, student.getLastName());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("dataaccess.StudentDAOImpl.add()");
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(DELETE_STUDENT);) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAll(String[] str) {
        StringBuilder query = new StringBuilder(DELETE_STUDENT);
        query.append("(");
        String delimiter = "";
        for (int i = 0; i < str.length; i++) {
            query.append(delimiter).append("(?)");
            delimiter = ",";
        }
        query.append(")");
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(query.toString());) {
            for (int i = 0; i < str.length; i++) {
                pstmt.setString(i + 1, str[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Student getById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(InputStream student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
