/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.builder;
import dto.FileDetail;
import java.sql.Date;
/**
 *
 * @author aisha
 */
public class FileDetailsBuilder {
    private final FileDetail fileDetails = new FileDetail();

   public FileDetailsBuilder setFileId(int fileId) {
        fileDetails.setFileId(fileId);
        return this;
    }

    public FileDetailsBuilder setName(String name) {
        fileDetails.setName(name);
        return this;
    }

    public FileDetailsBuilder setType(String type) {
        fileDetails.setType(type);
        return this;
    }

    public FileDetailsBuilder setDate(Date date) {
        fileDetails.setDate(date);
        return this;
    }

    public FileDetailsBuilder setSize(String size) {
        fileDetails.setSize(size);
        return this;
    }
    
    public FileDetail get(){
        return fileDetails;
    }
    
    public FileDetailsBuilder setFileID(String id){
        fileDetails.setFileId(Integer.valueOf(id));
        return this;
    }
}
