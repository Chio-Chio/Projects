package dao;

import model.MyOrder;

import java.util.logging.Logger;

/**
 * Data Access Object for MyOrder entities.
 */
public class OrderDAO extends AbstractDAO<MyOrder> {

    /**
     * The logger for OrderDAO class.
     */
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
}
