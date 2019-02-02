/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisness;


import buisness.ValidationException;
import dataaccess.DAOInterface;
import dataaccess.DAOInterface;
import dataaccess.FileDetailDAOImpl;
import dto.File;
import dto.FileDetail;
import dto.factory.DTOFactoryCreater;
import dto.factory.Factory;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vipha
 */
public class FileDetailLogic {

    private static final int FILEDETAIL_ID_MAX_LENGTH = 12;
    private static final int FILEDETAIL_NAME_MAX_LENGTH = 45;
    private static final int FILEDETAIL_TYPE_MAX_LENGTH = 45;
    //private static final int FILEDETAIL_DATE_MAX_LENGTH = 45;
    private static final int FILEDETAIL_SIZE_MAX_LENGTH = 45;

        private DAOInterface<FileDetail> dao = null;
    private Factory<FileDetail> factory = null;

    public FileDetailLogic() {
        dao = new FileDetailDAOImpl();
      //  factory = DTOFactoryCreater.createFactory(FileDetail.class);
    }

    public List<FileDetail> getAllFileDetail() {
        return dao.getAll();
    }

    public void addFileDetail(Map<String, String[]> fileDetail) throws ValidationException {
     FileDetail fd = new FileDetail(Integer.parseInt(fileDetail.get(FileDetail.COL_ID)[0]),fileDetail.get(FileDetail.COL_NAME)[0],fileDetail.get(FileDetail.COL_TYPE)[0],Date.valueOf(fileDetail.get(FileDetail.COL_DATE)[0]),fileDetail.get(FileDetail.COL_SIZE)[0]);
    addFileDetail(fd);
    }

    public void addFileDetail(FileDetail fileDetail) throws ValidationException {
        validateFileDetail(fileDetail);
        cleanFileDetail(fileDetail);
        dao.add(fileDetail);
    }

    public void deleteFileDetail(String[] codes) throws ValidationException {
        for (String code : codes) {
            deleteFileDetail(code);
        }
    }

    public void deleteFileDetail(String code) throws ValidationException {
        dao.delete(code);
    }

    private void cleanFileDetail(FileDetail fileDetail) {
//        if (student.getId() != null) {
//            student.setId(student.getId());
//        }
        if (fileDetail.getName() != null) {
            fileDetail.setName(fileDetail.getName().trim());
        }
        if (fileDetail.getType() != null) {
            fileDetail.setType(fileDetail.getType().trim());
        }
        if (fileDetail.getDate() != null) {
            fileDetail.setDate(Date.valueOf(fileDetail.getDate().toString().trim()));
        }
        if (fileDetail.getSize() != null) {
            fileDetail.setSize(fileDetail.getSize().trim());
        }
    }

    private void validateFileDetail(FileDetail fileDetail) throws ValidationException {
        //validateString(fileDetail.getFileID(), "FileDetail ID", FILEDETAIL_ID_MAX_LENGTH, false);
        validateString(fileDetail.getName(), "FileDetail Name", FILEDETAIL_NAME_MAX_LENGTH, false);
        validateString(fileDetail.getType(), "FileDetail Type", FILEDETAIL_TYPE_MAX_LENGTH, false);
        //validateString(fileDetail.getDate(), "FileDetail Date", FILEDETAIL_DATE_MAX_LENGTH, false);
        validateDate(fileDetail.getDate(), "FileDetail Date", false);
        validateString(fileDetail.getSize(), "FileDetail Size", FILEDETAIL_SIZE_MAX_LENGTH, false);

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

    private void validateDate(Date value, String fieldName, boolean isNullAllowed) throws ValidationException {
        if (value == null && !isNullAllowed) {
            throw new ValidationException(String.format("%s cannot be null", fieldName));
        }

    }
}