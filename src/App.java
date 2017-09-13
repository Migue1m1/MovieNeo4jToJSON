import adapters.ActorAdapter;
import adapters.DirectorAdapter;
import adapters.MovieAdapter;
import connections.db.DbException;
import connections.db.Neo4jDbConnection;
import models.Movie;

import static utils.Utils.PASS_NEO4J;
import static utils.Utils.URL_NEO4J;
import static utils.Utils.USER_NEO4J;

/**
 * Created by Miguel on 06/09/2017.
 */
public class App {
    private static Neo4jDbConnection neo4jDb;

    public App() {
        neo4jDb = new Neo4jDbConnection(URL_NEO4J, USER_NEO4J, PASS_NEO4J);
    }

    public void migrate() {
        try {
            neo4jDb.open();
        } catch (DbException e) {
            e.printStackTrace();
        }

        migrateMovies();

        migrateActors();

        migrateDirectors();

        neo4jDb.close();
    }

    private void migrateMovies() {
        MovieAdapter movieAdp = new MovieAdapter();
        System.out.println("Numero de registros: "
                + movieAdp.migrate(movieAdp.getList(neo4jDb, null), 0, 12861));
    }

    private void migrateActors() {
        ActorAdapter actorAdp = new ActorAdapter();
        System.out.println("Numero de registros: " + actorAdp.migrate(neo4jDb));
    }

    private void migrateDirectors() {
        DirectorAdapter directorAdp = new DirectorAdapter();
        System.out.println("Numero de registros: " + directorAdp.migrate(neo4jDb));
    }

    public static void main(String[] args) {
        App app = new App();
        app.migrate();
    }
}
