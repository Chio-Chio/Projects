package bll;

import bll.validators.Validator;
import dao.OrderDAO;
import model.MyOrder;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class for order business logic.
 */
public class OrderBLL {

    private List<Validator<MyOrder>> validators;
    private OrderDAO orderDAO;

    /**
     * Constructs a new instance of the OrderBLL class.
     * Initializes the OrderDAO.
     */
    public OrderBLL() {
        this.orderDAO = new OrderDAO();
        //validators.add(new StudentAgeValidator());
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return The MyOrder object with the specified ID.
     * @throws NoSuchElementException If the order with the given ID is not found.
     */
    public MyOrder findById(int id) {
        MyOrder st = orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Retrieves all orders.
     *
     * @return A list of all MyOrder objects.
     * @throws NoSuchElementException If no orders are found.
     */
    public List<MyOrder> findAll() {
        List<MyOrder> order = orderDAO.findAll();
        if (order.isEmpty()) {
            throw new NoSuchElementException("No order were found!");
        }
        return order;
    }


    /**
     * Inserts a new order.
     *
     * @param order The MyOrder object to be inserted.
     */
    public void insert(MyOrder order) {
        orderDAO.insert(order);
    }


    /**
     * Updates an existing order.
     *
     * @param order The updated MyOrder object.
     * @return The updated MyOrder object.
     */
    public MyOrder update(MyOrder order) {
        return orderDAO.update(order, order.getId());
        // return null;
    }

    /**
     * Deletes an order.
     *
     * @param order The MyOrder object to be deleted.
     */
    public Void delete(MyOrder order) {
        orderDAO.delete(order, order.getId());
        return null;
    }
}
