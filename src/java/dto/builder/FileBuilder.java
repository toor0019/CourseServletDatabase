/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.builder;

import dto.File;
import java.sql.Blob;

/**
 *
 * @author gurki
 */
public class FileBuilder {
    
    
      private final File student = new File();

    public FileBuilder setName( int id) {
        student.setId(id);
        return this;
    }
    
    public FileBuilder setFile(Blob blob)
    {
    student.setFile(blob);
    return this;
    }

    public File get() {
        return student;
    }
}
