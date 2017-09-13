package adapters;

import connections.db.DbException;
import connections.IConnection;
import connections.db.Neo4jDbConnection;
import models.ActorRol;
import models.DirectorId;
import models.Movie;
import models.omdbmodels.OmdbMovie;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.exceptions.Neo4jException;
import services.OmdbMovieService;
import utils.WriteJsonArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.neo4j.driver.v1.Values.NULL;
import static org.neo4j.driver.v1.Values.parameters;
import static utils.Utils.JSON_MOVIES_FILE;

/**
 * Created by Miguel on 06/09/2017.
 */
public class MovieAdapter implements IDataAdapter<Movie> {
    @Override
    public Movie getRecord(IConnection db, HashMap<String, Object> options) {
        return null;
    }

    @Override
    public List<Movie> getList(IConnection db, HashMap<String, Object> options) {
        List<Movie> movies = new ArrayList<>();
        Neo4jDbConnection neo4jDb = (Neo4jDbConnection) db;

        try {
            String cql = "MATCH (movie:Movie) RETURN movie.title AS title, movie.imdbId AS imdbId" +
                    ", movie.studio AS studio, movie.description AS description" +
                    ", movie.genre AS genre, movie.language AS language, movie.runtime AS runtime";

            StatementResult sr = neo4jDb.getStatementResult(cql, parameters());
            while (sr.hasNext()){
                Record record = sr.next();

                Movie movie = new Movie(record.get("title").asString(), record.get("imdbId").asString());

                movie.setGenre(record.get("genre") != NULL?
                                   record.get("genre").asString(): "");
                movie.setLanguage(record.get("language") != NULL?
                                      record.get("language").asString(): "");
                movie.setStudio(record.get("studio") != NULL? record.get("studio").asString(): "N/A");
                movie.setDescription(record.get("description").asString());
                movie.setRuntime(record.get("runtime") != NULL? String.valueOf(record.get("runtime").asInt()): "");

                movie.setActors(getActorsWithRoles(movie.getTitle(), neo4jDb));
                movie.setDirectors(getDirectors(movie.getTitle(), neo4jDb));
                movies.add(movie);
            }
        }
        catch (DbException | Neo4jException ex){
            Logger.getLogger(MovieAdapter.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public void insertRecord(IConnection db, Movie record, HashMap<String, Object> options) {

    }

    @Override
    public void updateRecord(IConnection db, Movie record, HashMap<String, Object> options) {

    }

    @Override
    public void deleteRecord(IConnection db, Movie record, HashMap<String, Object> options) {

    }

    @Override
    public long migrate(IConnection db) {
        return 0;
    }

    public long migrate(List<Movie> movies, int start, int end) {
        long count = 0;

        try {
            OmdbMovieService omdbMovieService = new OmdbMovieService();

            WriteJsonArray<Movie> writeJsonArray = new WriteJsonArray<>(JSON_MOVIES_FILE, "    ");

            writeJsonArray.writeStart();
            for (int i = start; i < end; i++) {
                Movie movie = movies.get(i);

                OmdbMovie omdb_movie = omdbMovieService.getOmdbMovie(movie.getImdbId());

                movie.setReleased(omdb_movie.getReleased());
                movie.setRated(omdb_movie.getRated());
                movie.setImageURL(omdb_movie.getPoster());
                movie.setImdbRating(omdb_movie.getImdbRating());
                movie.setGenre(!omdb_movie.getGenre().isEmpty()? omdb_movie.getGenre(): movie.getGenre());
                movie.setLanguage(!omdb_movie.getLanguage().isEmpty()? omdb_movie.getLanguage(): movie.getLanguage());

                System.out.println(movie.toString());
                writeJsonArray.writeObject(movie, Movie.class);

                if (count % 70 == 0) Thread.sleep(6000);

                count++;
            }
            writeJsonArray.writeEnd();
            writeJsonArray.close();
        } catch (DbException | Neo4jException ex) {
            Logger.getLogger(MovieAdapter.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    private List<ActorRol> getActorsWithRoles(String title, Neo4jDbConnection neo4jDb) {
        List<ActorRol> actorsWhitRoles = new ArrayList<>();

        String cql = "MATCH (movie {title: \"" + title + "\"})<-[acts:ACTS_IN]-(actors) " +
                "RETURN actors.id AS actorId, acts.name AS actorRolName";

        try {
            StatementResult sr = neo4jDb.getStatementResult(cql, parameters());

            while (sr.hasNext()) {
                Record record = sr.next();

                ActorRol actorWithRol = new ActorRol(record.get("actorId").asString(),
                                                     record.get("actorRolName").asString());

                actorsWhitRoles.add(actorWithRol);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return actorsWhitRoles;
    }

    private List<DirectorId> getDirectors(String title, Neo4jDbConnection neo4jDb) {
        List<DirectorId> directorsIds = new ArrayList<>();

        String cql = "MATCH (movie {title: \"" + title + "\"})<-[:DIRECTED]-(directors) RETURN directors.id AS id";

        try {
            StatementResult sr = neo4jDb.getStatementResult(cql, parameters());

            while (sr.hasNext()) {
                Record record = sr.next();

                directorsIds.add(new DirectorId(record.get("id").asString()));
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

        return directorsIds;
    }
}
