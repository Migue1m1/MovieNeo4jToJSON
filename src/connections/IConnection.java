package connections;

import connections.db.DbException;

/**
 * Created by Miguel on 06/09/2017.
 */
public interface IConnection {
    void open() throws DbException;

    void close();
}
