package dao;

import model.Product;

import java.util.logging.Logger;

/**
 * Data Access Object for Product entities.
 */
public class ProductDAO extends AbstractDAO<Product> {
    /**
     * The logger for ProductDAO class.
     */
    protected static final Logger LOGGER = Logger.getLogger(dao.ProductDAO.class.getName());

}
