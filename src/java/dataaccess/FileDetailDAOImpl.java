/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;


import dto.FileDetail;
import dto.factory.DTOFactoryCreater;
import dto.factory.Factory;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jon
 */
public class FileDetailDAOImpl implements DAOInterface<FileDetail>{

    private static final String GET_ALL_FILEDETAIL = "SELECT * FROM mapmaker.FileDetail ORDER BY file_id";
    private static final String INSERT_FILEDETAILS = "INSERT INTO mapmaker.FileDetail (file_id, name,type,date,size) VALUES(?,?,?,?,?)";
    private static final String DELETE_FILEDETAIL = "DELETE FROM mapmaker.FileDetail WHERE file_id = ?";
    private static final String DELETE_FILEDETAILS = "DELETE FROM mapmaker.FileDetail WHERE (file_id) IN ";
    private static final String UPDATE_FILEDETAILS = "UPDATE mapmaker.FileDetail SET name = ? && type = ? && date =? && size =? WHERE file_id = ?";
    private static final String GET_BY_ID = "SELECT  file_id, name,type,date,size FROM mapmaker.FileDetail WHERE name = ?";
    
 //   private final Factory<FileDetail> factory = DTOFactoryCreater.createFactory(FileDetail.class);

    public FileDetailDAOImpl() {
       
    }

    
    @Override
    public List<FileDetail> getAll() {
        List<FileDetail> fileDetails = new ArrayList<FileDetail>();
        FileDetail fileDetail;
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ALL_FILEDETAIL);
                ResultSet rs = pstmt.executeQuery();) {
            while(rs.next()){
            fileDetail = new   FileDetail();
            fileDetail.setFileId(rs.getInt(FileDetail.COL_ID));
            fileDetail.setName(rs.getString(FileDetail.COL_NAME));
            fileDetail.setSize(rs.getString(FileDetail.COL_SIZE));
            fileDetail.setType(rs.getString(FileDetail.COL_TYPE));
            fileDetail.setDate(rs.getDate(FileDetail.COL_DATE));
            fileDetails.add(fileDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FileDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileDetails;
    }

    @Override
    public void add(FileDetail fileDetail) {
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_FILEDETAILS);) {
            pstmt.setInt(1, fileDetail.getFileId());
            pstmt.setString(2, fileDetail.getName());
            pstmt.setString(2, fileDetail.getType());
            pstmt.setDate(2, fileDetail.getDate());
            pstmt.setString(2, fileDetail.getSize());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FileDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String code) {
        try (Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(DELETE_FILEDETAIL);) {
            pstmt.setString(1, code);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FileDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAll(String[] str) {
        StringBuilder query = new StringBuilder(DELETE_FILEDETAILS);
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
            Logger.getLogger(FileDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    
    public FileDetail getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FileDetail getById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(InputStream student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}