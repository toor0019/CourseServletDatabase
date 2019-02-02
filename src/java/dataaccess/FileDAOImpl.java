package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dto.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.util.ArrayList;

/**
 *
 * @author Shawn
 */
public class FileDAOImpl implements DAOInterface<File> {

    private static final String GET_ALL_FILES = "SELECT id, file FROM mapmaker.File";
    private static final String INSERT_FILES = "INSERT INTO mapmaker.File (file) VALUES(?)";
    private static final String DELETE_FILE = "DELETE FROM mapmaker.File WHERE file = ?";
    private static final String DELETE_FILES = "DELETE FROM mapmaker.File WHERE (id) IN ";
    private static final String UPDATE_FILES = "UPDATE mapmaker.File SET file = ? WHERE id = ?";
    private static final String GET_BY_CODE_FILES = "SELECT id, name FROM mapmaker.File WHERE file = ?";

   // private final Factory<Course> factory = DTOFactoryCreator.createFactory(Course.class);

    @Override
    public List<File> getAll() {
        List<File> students = Collections.emptyList();
        File student;
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ALL_FILES);
                ResultSet rs = pstmt.executeQuery();) {
            //courses = factory.createListFromResultSet(rs);
            students = new ArrayList<>(100);
            while(rs.next()){
                student = new File();
                student.setId(rs.getInt(File.COL_ID));
                student.setFile(rs.getBlob(File.COL_FILE));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public void add(InputStream in){
     try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_FILES);) {
            pstmt.setBlob(1, in);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void add(File student) {
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_FILES);) {
            pstmt.setBlob(1, student.getFile());
           
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("dataaccess.StudentDAOImpl.add()");
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(DELETE_FILE);) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAll(String[] str) {
        StringBuilder query = new StringBuilder(DELETE_FILE);
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
    public File getById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static int insertFile( Connection con, String query, String path) throws IOException, SQLException{
		try( PreparedStatement statement = con.prepareStatement( query)){
			//replace the ? in the query with the given loaded path
			statement.setBlob( 1, Files.newInputStream( Paths.get( path)));
			return statement.executeUpdate();
		}
	}

    
    public static int deleteFile( Connection con, String query, int id) throws SQLException{
		try( PreparedStatement statement = con.prepareStatement( query)){
			//replace the ? in the query with the given loaded path
			statement.setInt( 1, id);
			return statement.executeUpdate();
		}
	}

	/**
	 * update the file in the row with given the id
	 * @param con - connection to the DB
	 * @param query - update the row with given the id
	 * @param id - id of the row containing the file
	 * @param path - address to load the file
	 * @return number of lines modified on DB
	 * @throws IOException - problem loading the row
	 * @throws SQLException - problem updating the file on the DB
	 */
	public static int updateFile( Connection con, String query, int id, String path) throws SQLException, IOException{
		try( PreparedStatement statement = con.prepareStatement( query)){
			//replace the ? in the query with the given loaded path
			statement.setBlob( 1, Files.newInputStream( Paths.get( path)));
			statement.setInt( 2, id);
			return statement.executeUpdate();
		}
	}

	/**
	 * get a file from connection using the given query and id and store it in path
	 * @param con - connection to the database
	 * @param query - select a file from DB given the id
	 * @param id - id of the given file
	 * @param path - address to save the file
	 * @return number of bytes that are read
	 * @throws IOException - problem creating the file
	 * @throws SQLException - problem getting the file from DB
	 */
	public static long getFile( Connection con, String query, int id, String path) throws IOException, SQLException{
		try( PreparedStatement statement = con.prepareStatement( query)){
			//replace the ? in the query with the given id
			statement.setInt( 1, id);
			ResultSet result = statement.executeQuery();
			if( result.next()){
				//get the Binary Large Object.
				Blob blob = result.getBlob( "file");
				//get input stream from Blob and copy it to the given path, if file exists replace it.
				return Files.copy( blob.getBinaryStream(), Paths.get( path), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		return 0;
	}
}
