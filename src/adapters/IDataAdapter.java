package adapters;

import connections.IConnection;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Miguel on 06/09/2017.
 */
public interface IDataAdapter<T> {
    T getRecord(IConnection db, HashMap<String, Object> options);

    List<T> getList(IConnection db, HashMap<String, Object> options);

    void insertRecord(IConnection db, T record,
                      HashMap<String, Object> options);

    void updateRecord(IConnection db, T record,
                      HashMap<String, Object> options);

    void deleteRecord(IConnection db, T record,
                      HashMap<String, Object> options);

    long migrate(IConnection db);
}
