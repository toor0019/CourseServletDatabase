/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Blob;

/**
 *
 * @author gurki
 */
public class File {

    public static final String COL_FILE="file";
    public static final String COL_ID="id";
    private Blob file;
    private int id;
    public File() {
    }

    public File(int id,Blob file) {
        this.id=id;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }
}
