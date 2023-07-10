package model;

/**
 * Represents a client.
 */
public class Client {
    private int id;
    private String name;
    private String email;

    /**
     * Constructs an empty instance of the client.
     */
    public Client() {
    }

    /**
     * Constructs an instance of the client with the specified ID, name, and email.
     *
     * @param id    the client ID
     * @param name  the client name
     * @param email the client email
     */
    public Client(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Constructs an instance of the client with the specified name and email.
     *
     * @param name  the client name
     * @param email the client email
     */
    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Returns the ID of the client.
     *
     * @return the client ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the client.
     *
     * @param id the client ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the client.
     *
     * @return the client name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name the client name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the email of the client.
     *
     * @return the client email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the client.
     *
     * @param email the client email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the client.
     *
     * @return a string representation of the client
     */
    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
