package dto.factory;

import dto.Group;
import dto.builder.GroupBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 *
 * @author Shariar
 */
public class GroupFactory extends AbstractFactory< Group> {

    @Override
    public Group createFromResultSet(ResultSet rs) throws SQLException {
        if (rs == null || !rs.next()) {
            return null;
        }
        GroupBuilder builder = new GroupBuilder();
        builder.setName(rs.getString(Group.COL_NAME))
                .setId(rs.getInt(Group.COL_ID));
        return builder.get();
    }

    @Override
    public Group createFromMap(Map< String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        GroupBuilder builder = new GroupBuilder();
        builder.setName(map.get(Group.COL_NAME)[0])
                .setId(map.get(Group.COL_ID)[0]);
        return builder.get();
    }
}
