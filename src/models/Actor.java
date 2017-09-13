package models;

/**
 * Created by Miguel on 06/09/2017.
 */
public class Actor extends Person {

    public Actor(String name, String id) {
        super(name, id);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getDeathDay() {
        return deathDay;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getGender() {
        return gender;
    }

    public String getBiography() {
        return biography;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setDeathDay(String deathDay) {
        this.deathDay = deathDay;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Override
    public String toString() {
        return "name: " + getName() + ", id: " + getId() + ", gender: " + getGender() + ", birthday: " + getBirthDay()
                + ", deathday: " + getDeathDay() + ", birthplace: " + getBirthPlace() + ", biography: " + getBiography()
                + ", imageURL: " + getImageURL() + ", imdbId: " + getImdbId();
    }
}
