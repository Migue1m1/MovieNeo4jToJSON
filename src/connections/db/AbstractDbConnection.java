package connections.db;

import connections.IConnection;

/**
 * Created by Miguel on 06/09/2017.
 */
public abstract class AbstractDbConnection implements IConnection {
    protected String dbName;
    protected String dbFileName;
    protected String username;
    protected String password;
    protected String serverURL;
    protected String collectionPath;

    @Override
    public void open() throws DbException {}

    @Override
    public void close() {}
}
