package model;

/**
 * Represents a product.
 */
public class Product {
    private int id;
    String name;
    private int price;
    private int stock;

    /**
     * Constructs an empty instance of the product.
     */
    public Product() {
    }

    /**
     * Constructs an instance of the product with the specified id, name, price, and stock.
     *
     * @param id    the product id
     * @param name  the product name
     * @param price the product price
     * @param stock the product stock
     */
    public Product(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructs an instance of the product with the specified name, price, and stock.
     *
     * @param name  the product name
     * @param price the product price
     * @param stock the product stock
     */
    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Returns the id of the product.
     *
     * @return the product id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the product.
     *
     * @param id the product id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the price of the product.
     *
     * @return the product price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the product price
     */

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Returns the stock of the product.
     *
     * @return the product stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock of the product.
     *
     * @param stock the product stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns the name of the product.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }
}
