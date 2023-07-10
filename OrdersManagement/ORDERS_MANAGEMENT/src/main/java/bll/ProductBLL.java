package bll;

import dao.ProductDAO;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class for product business logic.
 */
public class ProductBLL {
    private ProductDAO productDAO;

    /**
     * Constructs a new instance of the ProductBLL class.
     * Initializes the ProductDAO.
     */
    public ProductBLL() {
        this.productDAO = new ProductDAO(); // very imp
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The Product object with the specified ID.
     * @throws NoSuchElementException If the product with the given ID is not found.
     */
    public Product findById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Retrieves all products.
     *
     * @return A list of all Product objects.
     * @throws NoSuchElementException If no products are found.
     */
    public List<Product> findAll() {
        List<Product> products = productDAO.findAll();
        if (products.isEmpty()) {
            throw new NoSuchElementException("No product were found!");
        }
        return products;
    }

    /**
     * Inserts a new product.
     *
     * @param product The Product object to be inserted.
     * @return The ID of the newly inserted product.
     */
    public int insert(Product product) {
        System.out.println("product bll insertClient");
//        for (Validator<Product> v : validators) {
//            v.validate(product);
//        }
        return productDAO.insert(product).getId();
    }

    /**
     * Updates an existing product.
     *
     * @param product The updated Product object.
     * @return The updated Product object.
     */
    public Product update(Product product) {
        //Exists e = new Exists();
//        ClientBLL clientBLL = new ClientBLL();
//        List<Client> clients = clientBLL.findAllClient();
//        if(clients.contains(client)) {
        return productDAO.update(product, product.getId());

        // return null;
    }

    /**
     * Deletes a product.
     *
     * @param product The Product object to be deleted.
     */
    public Void delete(Product product) {
        //Exists e = new Exists();
//        ClientBLL clientBLL = new ClientBLL();
//        List<Client> clients = clientBLL.findAllClient();
//        if(clients.contains(client)) {
        productDAO.delete(product, product.getId());
        // return null;
        return null;
    }
}
