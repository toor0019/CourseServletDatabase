package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dto.Group;
import dto.factory.DTOFactoryCreater;
import dto.factory.Factory;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author Shawn
 */
public class GroupDAOImpl implements DAOInterface<Group> {

    private static final String GET_ALL_GROUPS = "SELECT * FROM mapmaker.Group";
    private static final String INSERT_GROUPS = "INSERT INTO mapmaker.Group (name) VALUES(?)";
    private static final String DELETE_GROUP = "DELETE FROM mapmaker.Group WHERE name = ?";
    private static final String DELETE_GROUPS = "DELETE FROM mapmaker.Group WHERE (id) IN ";
    private static final String UPDATE_GROUPS = "UPDATE mapmaker.Group SET name = ? WHERE id = ?";
    private static final String GET_BY_CODE_GROUPS = "SELECT id, name FROM mapmaker.Group WHERE name = ?";

    

   private final Factory<Group> factory = DTOFactoryCreater.createFactory(Group.class);

    @Override
    public List<Group> getAll() {
        List<Group> groups = Collections.emptyList();
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ALL_GROUPS);
                ResultSet rs = pstmt.executeQuery();) {
            groups = factory.createListFromResultSet(rs);
        } catch (SQLException ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    @Override
    public void add(Group student) {
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_GROUPS);) {
            pstmt.setString(1, student.getName());
           
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("dataaccess.StudentDAOImpl.add()");
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(DELETE_GROUP);) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAll(String[] str) {
        StringBuilder query = new StringBuilder(DELETE_GROUPS);
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
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Group getById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(InputStream student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
