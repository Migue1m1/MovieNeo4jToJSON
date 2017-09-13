package models.tmdbmodels;

import java.io.Serializable;

/**
 * Created by Miguel on 07/09/2017.
 */
public class TmdbPerson implements Serializable {
    private String name;
    private String birthday;
    private String deathday;
    private int gender;
    private String place_of_birth;
    private String profile_path;
    private String imdb_id;
    private String biography;

    public TmdbPerson() {
        name = birthday = deathday = place_of_birth = profile_path = imdb_id = biography = "";
        gender = 0;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public int getGender() {
        return gender;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getBiography() {
        return biography;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", birthday: " + getBirthday() + ", deathday: " + getDeathday()
                + ", gender: " + getGender() + ", place_of_birth: " + getPlace_of_birth()
                + ", profile_path: " + getProfile_path() + ", imdb_id: " + getImdb_id()
                + ", biography: " + getBiography();
    }
}
