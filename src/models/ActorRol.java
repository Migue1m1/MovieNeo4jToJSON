package models;

/**
 * Created by Miguel on 08/09/2017.
 */
public class ActorRol {
    private String id;
    private String role;

    public ActorRol(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
