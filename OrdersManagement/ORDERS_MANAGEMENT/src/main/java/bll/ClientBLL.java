package bll;

import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class for client business logic.
 */
public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructs a new instance of the ClientBLL class.
     * Initializes the validators and the ClientDAO.
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        clientDAO = new ClientDAO();
        //validators.add(new StudentAgeValidator());
    }

    /**
     * Finds a client by their ID.
     *
     * @param id The ID of the client to find.
     * @return The Client object with the specified ID.
     * @throws NoSuchElementException If the client with the given ID is not found.
     */
    public Client findClientById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Retrieves all clients.
     *
     * @return A list of all Client objects.
     * @throws NoSuchElementException If no clients are found.
     */
    public List<Client> findAllClient() {
        List<Client> clients = clientDAO.findAll();
        if (clients.isEmpty()) {
            throw new NoSuchElementException("No clients were found!");
        }
        return clients;
    }

    /**
     * Inserts a new client.
     *
     * @param client The Client object to be inserted.
     * @return The ID of the newly inserted client.
     */
    public int insertClient(Client client) {
        System.out.println("client bll insertClient");
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return clientDAO.insert(client).getId();
    }

    /**
     * Updates an existing client.
     *
     * @param client The updated Client object.
     * @return The updated Client object.
     */
    public Client update(Client client) {
        //Exists e = new Exists();
//        ClientBLL clientBLL = new ClientBLL();
//        List<Client> clients = clientBLL.findAllClient();
//        if(clients.contains(client)) {
            return clientDAO.update(client, client.getId());

       // return null;
    }

    /**
     * Deletes a client.
     *
     * @param client The Client object to be deleted.
     */
    public Void delete(Client client) {
        //Exists e = new Exists();
//        ClientBLL clientBLL = new ClientBLL();
//        List<Client> clients = clientBLL.findAllClient();
//        if(clients.contains(client)) {
        clientDAO.delete(client, client.getId());
        // return null;
        return null;
    }
}
