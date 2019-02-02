package dataaccess;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * generic interface guide to be used for all implementations of DAO classes.</br>
 * when creating an instance of DAO object store it as this interface.</br>
 * add more functions if needed. helper functions inside of the implementation class does not be defined here.</br>
 * </p>
 * 
 * @author Shawn
 * @param <T> - type of DTO to be used in this interface
 */
public interface DAOInterface<T> {
    List<T> getAll();
    T getById(String id);
    void add( T t);
    void delete(String str);
    void deleteAll(String[] str);

    public void add(InputStream student);
}