package model;

/**
 * Represents an order.
 */
public class MyOrder {
    private int id;
    private int idClient;
    private int idProduct;
    private int quantity;

    /**
     * Constructs an empty instance of the order.
     */
    public MyOrder() {
    }

    /**
     * Constructs an instance of the order with the specified client ID, product ID, and quantity.
     *
     * @param idClient  the ID of the client associated with the order
     * @param idProduct the ID of the product in the order
     * @param quantity  the quantity of the product in the order
     */
    public MyOrder(int idClient, int idProduct, int quantity) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * Constructs an instance of the order with the specified order ID, client ID, product ID, and quantity.
     *
     * @param id        the order ID
     * @param idClient  the ID of the client associated with the order
     * @param idProduct the ID of the product in the order
     * @param quantity  the quantity of the product in the order
     */
    public MyOrder(int id, int idClient, int idProduct, int quantity) {
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * Returns the ID of the client associated with the order.
     *
     * @return the client ID
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Sets the ID of the client associated with the order.
     *
     * @param idClient the client ID
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * Returns the ID of the product in the order.
     *
     * @return the product ID
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * Sets the ID of the product in the order.
     *
     * @param idProduct the product ID
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Returns the quantity of the product in the order.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the order.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the ID of the order.
     *
     * @return the order ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id the order ID
     */
    public void setId(int id) {
        this.id = id;
    }
}
