package adapters;

import connections.IConnection;
import connections.db.DbException;
import connections.db.Neo4jDbConnection;
import models.Actor;
import models.tmdbmodels.TmdbPerson;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.exceptions.Neo4jException;
import services.TmdbActorService;
import utils.WriteJsonArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.neo4j.driver.v1.Values.NULL;
import static org.neo4j.driver.v1.Values.parameters;
import static utils.Utils.*;

/**
 * Created by Miguel on 06/09/2017.
 */
public class ActorAdapter implements IDataAdapter<Actor> {
    @Override
    public Actor getRecord(IConnection db, HashMap<String, Object> options) {
        return null;
    }

    @Override
    public List<Actor> getList(IConnection db, HashMap<String, Object> options) {
        return null;
    }

    @Override
    public void insertRecord(IConnection db, Actor record, HashMap<String, Object> options) {

    }

    @Override
    public void updateRecord(IConnection db, Actor record, HashMap<String, Object> options) {

    }

    @Override
    public void deleteRecord(IConnection db, Actor record, HashMap<String, Object> options) {

    }

    @Override
    public long migrate(IConnection db) {
        Neo4jDbConnection neo4jDb = (Neo4jDbConnection) db;

        long count = 0;

        String cql = "MATCH (actor:Actor) RETURN actor.name AS name, actor.id AS id, actor.biography AS biography";

        try {
            StatementResult sr = neo4jDb.getStatementResult(cql, parameters());

            TmdbActorService tmdbActorService = new TmdbActorService();

            int i = 1;

            WriteJsonArray<Actor> writeJsonArray = new WriteJsonArray<>(JSON_ACTORS_FILE, "    ");

            writeJsonArray.writeStart();
            while (sr.hasNext()) {
                Record record = sr.next();

                Actor actor = new Actor(record.get("name").asString(), record.get("id").asString());

                TmdbPerson tmdb_actor = tmdbActorService.getTmdbActor(actor.getId());

                actor.setBirthDay(tmdb_actor.getBirthday());
                actor.setDeathDay(tmdb_actor.getDeathday() != null? tmdb_actor.getDeathday(): "");
                actor.setBirthPlace(tmdb_actor.getPlace_of_birth() != null? tmdb_actor.getPlace_of_birth(): "");
                actor.setGender(genderToString(tmdb_actor.getGender()));
                actor.setBiography(fixBiography(
                        tmdb_actor.getBiography() != null && !tmdb_actor.getBiography().isEmpty()?
                                tmdb_actor.getBiography():
                                record.get("biography") != NULL? record.get("biography").asString(): ""));

                actor.setImageURL(tmdb_actor.getProfile_path() != null?
                        URL_TMDB_IMAGE + tmdb_actor.getProfile_path(): "");
                actor.setImdbId(tmdb_actor.getImdb_id() != null? tmdb_actor.getImdb_id(): "");

                System.out.println(actor.toString());
                writeJsonArray.writeObject(actor, Actor.class);

                count++;

                if (i % 1000 == 0){
                    Thread.sleep(1500);
                    System.out.println("DELAY: " + i);
                }
                i++;
            }
            writeJsonArray.writeEnd();
            writeJsonArray.close();
        } catch (DbException | Neo4jException ex){
            Logger.getLogger(ActorAdapter.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
