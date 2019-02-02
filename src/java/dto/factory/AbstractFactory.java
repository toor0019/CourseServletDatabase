/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.factory;

import dto.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gurki
 */
public abstract class AbstractFactory<T> implements Factory<T> {
 //Here we keep calling createFromResultSet method as long as it returns not null, and it also put the values in the arryList called list, and we set the limit of list as 100 
    @Override
    public List<T> createListFromResultSet(ResultSet rs) throws SQLException {

        List<T> list = new ArrayList<>(100);

        T item = null;
        while (true) {
            item = createFromResultSet(rs);
            if (item==null) {
                break;
            }
            list.add(item);
        }
    return list;
}
    
}