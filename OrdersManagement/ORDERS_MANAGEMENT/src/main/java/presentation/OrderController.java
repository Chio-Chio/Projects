package presentation;

import bll.BillBLL;
import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Bill;
import model.Client;
import model.MyOrder;
import model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The controller class for managing orders.
 */
public class OrderController {
    private OrderView orderView;

    /**
     * Constructs a new instance of the order controller.
     *
     * @param orderView the order view associated with the controller
     */
    public OrderController(OrderView orderView) {
        this.orderView = orderView;

        this.orderView.orderListener(new OrderListener());
        this.orderView.billListener(new BillListener());
        this.orderView.logTableBillListener(new LogTableBillListener());
        this.orderView.showAllOrdersListener(new ShowAllOrdersListener());
        this.orderView.editListener(new EditListener());
        this.orderView.deleteListener(new DeleteListener());
    }

    /**
     * ActionListener for the order button.
     */
    class OrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("Order button was pressed (OrderView)");
                int idClient = Integer.parseInt(orderView.getTextFieldClientId());
                int idProduct = Integer.parseInt(orderView.getTextFieldProductId());
                int quantity = Integer.parseInt(orderView.getTextFieldQuantity());

                ProductBLL productBLL = new ProductBLL();
                Product product = productBLL.findById(idProduct);
                if (product.getStock() < quantity) {
                    throw new IllegalAccessException("Not in stock");
                }
                MyOrder order = new MyOrder(idClient, idProduct, quantity);
                System.out.println(order.getIdClient() + " " + order.getIdProduct() + " " + order.getQuantity());

                OrderBLL bll = new OrderBLL();
                bll.insert(order);

                // decrement the stock in that product
                product.setStock(product.getStock() - quantity);
                productBLL.update(product);

            } catch (IndexOutOfBoundsException ex) {
                orderView.showErrorMessage("invalid id");
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                orderView.showErrorMessage(ex.getMessage());
                ex.printStackTrace();
            } catch (Exception ex) {
                orderView.showErrorMessage("BAD!");
                ex.printStackTrace();

            }
        }
    }

    /**
     * ActionListener for the edit button.
     */
    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("EditListener");

                OrderBLL bll = new OrderBLL();

                int id = Integer.parseInt(orderView.getTextFieldId());
                int idClient = Integer.parseInt(orderView.getTextFieldClientId());
                int idProduct = Integer.parseInt(orderView.getTextFieldProductId());
                int quantity = Integer.parseInt(orderView.getTextFieldQuantity());
                MyOrder c = bll.findById(id);
                if (c == null) {
                    throw new Exception("Not found");
                }

                MyOrder c2 = new MyOrder(c.getId(), idClient, idProduct, quantity);
                c2 = bll.update(c2);
            } catch (IndexOutOfBoundsException ex) {
                orderView.showErrorMessage("invalid id");
                ex.printStackTrace();
            } catch (Exception ex) {
                orderView.showErrorMessage(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    /**
     * ActionListener for the delete button.
     */
    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("DeleteListener");

                int id = Integer.parseInt(orderView.getTextFieldId());
                OrderBLL bll = new OrderBLL();
                MyOrder p = bll.findById(id);

                bll.delete(p);
            } catch (IndexOutOfBoundsException ex) {
                orderView.showErrorMessage("invalid id");
                ex.printStackTrace();
            } catch (Exception ex) {
                orderView.showErrorMessage("BAD!");
                ex.printStackTrace();
            }
        }
    }

    /**
     * ActionListener for the bill button.
     */
    class BillListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("Bill button was pressed (OrderView)");
                int orderId = Integer.parseInt(orderView.getTextFieldId());
                OrderBLL orderBLL = new OrderBLL();
                MyOrder myOrder = orderBLL.findById(orderId);
                int clientId = myOrder.getIdClient();
                int productId = myOrder.getIdProduct();

                ClientBLL clientBLL = new ClientBLL();
                Client client = clientBLL.findClientById(clientId);

                ProductBLL productBLL = new ProductBLL();
                Product product = productBLL.findById(productId);

                BillBLL bll = new BillBLL();
                Bill bill = bll.insert(client.getName(), product.getName(), product.getPrice(), myOrder.getQuantity());
                orderView.showMessage("Date: " + bill.timestamp() + "\nCustomer " + bill.clientName() + "\nbought " + bill.productName() + "\nprice = "
                        + bill.productPrice() + ", quantity = " + bill.quantity());
            } catch (Exception ex) {
                orderView.showErrorMessage("BAD!");
                ex.printStackTrace();
            }
        }
    }


    /**
     * ActionListener for the log table bill button.
     */
    class LogTableBillListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("LogTableBill button was pressed (OrderView)");

                String[] columns = {"Id Bill", "Client Name", "Product Name", "Product Price", "Quantity", "Time"};
                Object[][] data = {};
                Tabel tabel = new Tabel(columns, data);

                BillBLL bll = new BillBLL();
                List<Bill> list = bll.findAll();
                for (Bill bill : list) {
                    Object[] row = {
                            bill.orderId(),
                            bill.clientName(),
                            bill.productName(),
                            bill.productPrice(),
                            bill.quantity(),
                            bill.timestamp()
                    };
                    tabel.addRow(row);
                }
            } catch (Exception ex) {
                orderView.showErrorMessage("BAD!");
                ex.printStackTrace();
            }
        }
    }

    /**
     * ActionListener for the show all orders button.
     */
    class ShowAllOrdersListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.printf("ShowAllOrdersListener");

                String[] columns = {"Id", "IdClient", "IdProduct", "Quantity"};
                Object[][] data = {};
                Tabel tabel = new Tabel(columns, data);
                OrderBLL bll = new OrderBLL();

                List<MyOrder> list = bll.findAll();
                for (MyOrder c : list) {
                    Object[] rowData = {c.getId(), c.getIdClient(), c.getIdProduct(), c.getQuantity()};
                    System.out.println(c.getId() + " " + c.getIdClient() + " " + c.getIdProduct() + c.getQuantity());
                    tabel.addRow(rowData);
                }
            } catch (Exception ex) {
                orderView.showErrorMessage("BAD!");
                ex.printStackTrace();
            }
        }
    }
}
