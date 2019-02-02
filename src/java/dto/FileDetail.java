/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;
import java.sql.Date;
import java.util.Objects;
/**
 *
 * @author aisha
 */
public class FileDetail {
    public static final String COL_ID = "file_id";
    public static final String COL_NAME = "name";
    public static final String COL_TYPE = "type";
    public static final String COL_DATE= "date";
    public static final String COL_SIZE = "size";
    
    
    private int fileId;
    private String name;
    private String type;
    private Date date;
    private String size;

    public FileDetail() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.fileId;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.date);
        hash = 89 * hash + Objects.hashCode(this.size);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileDetail other = (FileDetail) obj;
        if (this.fileId != other.fileId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    public FileDetail(int fileId, String name, String type, Date date, String size) {
        this.fileId = fileId;
        this.name = name;
        this.type = type;
        this.date = date;
        this.size = size;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

   

   

    

}