package models;

/**
 * Created by Miguel on 08/09/2017.
 */
public class Person {
    protected String name;
    protected String id;
    protected String birthDay;
    protected String deathDay;
    protected String birthPlace;
    protected String gender;
    protected String biography;
    protected String imageURL;
    protected String imdbId;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
        birthDay = deathDay = birthPlace = gender = biography = imageURL = imdbId = "";
    }

}
