package connections.db;

import org.neo4j.driver.v1.*;

/**
 * Created by Miguel on 06/09/2017.
 */
public class Neo4jDbConnection extends AbstractDbConnection {
    private static Driver neo4jDriver;
    private static Session neo4jSesion;

    public Neo4jDbConnection(String serverURL, String username, String password){
        this.serverURL = serverURL;
        this.username = username;
        this.password = password;
    }

    @Override
    public void open() throws DbException {
        neo4jDriver = GraphDatabase
                        .driver(serverURL, AuthTokens.basic(username, password));
        neo4jSesion = neo4jDriver.session();
    }

    public StatementResult getStatementResult(String statement, Value parameters) throws DbException {
        if (neo4jDriver != null){
            return neo4jSesion.run(statement, parameters);
        }
        return null;
    }

    @Override
    public void close(){
        neo4jSesion.close();
        neo4jDriver.close();
    }
}
