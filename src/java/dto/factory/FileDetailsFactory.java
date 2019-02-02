package dto.factory;

import dto.FileDetail;
import dto.Group;
import dto.builder.FileDetailsBuilder;
import dto.builder.GroupBuilder;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 *
 * @author Shariar
 */
public class FileDetailsFactory extends AbstractFactory<FileDetail> {

    @Override
    public FileDetail createFromResultSet(ResultSet rs) throws SQLException {
        if (rs == null || !rs.next()) {
            return null;
        }
       FileDetailsBuilder builder = new FileDetailsBuilder();
        builder.setName(rs.getString(FileDetail.COL_NAME))
               .setDate(rs.getDate(FileDetail.COL_DATE))
                .setFileID(rs.getString(FileDetail.COL_ID))
                .setSize(rs.getString(FileDetail.COL_SIZE))
                .setType(rs.getString(FileDetail.COL_TYPE))
                
                ;
        return builder.get();
    }

    @Override
    public FileDetail createFromMap(Map< String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
       FileDetailsBuilder builder = new FileDetailsBuilder();
        builder.setName(map.get(FileDetail.COL_NAME)[0])
               .setDate(Date.valueOf(map.get(FileDetail.COL_DATE)[0]))
                .setFileID(map.get(FileDetail.COL_ID)[0])
                .setSize(map.get(FileDetail.COL_SIZE)[0])
                .setType(map.get(FileDetail.COL_TYPE)[0])
                
                ;
        return builder.get();
    }
}
