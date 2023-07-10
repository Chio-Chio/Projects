package presentation;

import bll.ProductBLL;
import model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The controller class for managing products.
 */
public class ProductController {
    private ProductView productView;

    /**
     * Constructs a new instance of the product controller.
     *
     * @param productView the product view associated with the controller
     */
    public ProductController(ProductView productView) {
        this.productView = productView;

        this.productView.newListener(new NewListener());
        this.productView.editListener(new EditListener());
        this.productView.deleteListener(new DeleteListener());
        this.productView.viewAllListener(new ViewAllListener());
    }

    /**
     * ActionListener implementation for the new button.
     */
    class NewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("New button was pressed (ProductView)");

                String name = productView.getTextFieldName();
                int price = Integer.parseInt(productView.getTextFieldPrice());
                int stock = Integer.parseInt(productView.getTextFieldStock());
                Product product = new Product(name, price, stock);
                System.out.println(product.getName() + " " + product.getPrice() + " " + product.getStock());

                ProductBLL productBLL = new ProductBLL();
                productBLL.insert(product);
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }

    /**
     * ActionListener implementation for the edit button.
     */
    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("Edit button was pressed (ProductView)");
//                String[] columns = {"Id Product", "Name", "Price", "Stock"};
//                Object[][] data = {};
//                Tabel tabel = new Tabel(columns, data);
                ProductBLL productBLL = new ProductBLL();

                int id = Integer.parseInt(productView.getTextFieldId());
                String name = productView.getTextFieldName();
                int price = Integer.parseInt(productView.getTextFieldPrice());
                int stock = Integer.parseInt(productView.getTextFieldStock());
                Product c = productBLL.findById(id);
                if (c == null) {
                    throw new Exception();
                }

                Product c2 = new Product(c.getId(), name, price, stock);
                c2 = productBLL.update(c2);
            } catch (IndexOutOfBoundsException ex) {
                productView.showErrorMessage("invalid id");
                ex.printStackTrace();
            } catch (Exception ex) {
                productView.showErrorMessage("BAD!");
                ex.printStackTrace();

            }
        }
    }

    /**
     * ActionListener implementation for the delete button.
     */
    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("Delete button was pressed (ProductView)");
                int id = Integer.parseInt(productView.getTextFieldId());
                ProductBLL bll = new ProductBLL();
                Product p = bll.findById(id);

                bll.delete(p);
            } catch (IndexOutOfBoundsException ex) {
                productView.showErrorMessage("invalid id");
                ex.printStackTrace();
            } catch (Exception ex) {
                productView.showErrorMessage("NO!");
                ex.printStackTrace();

            }
        }
    }

    /**
     * ActionListener implementation for the view all button.
     */
    class ViewAllListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("View all button was pressed (ProductView)");
                String[] columns = {"Id", "Name", "Price", "Stock"};
                Object[][] data = {};
                Tabel tabel = new Tabel(columns, data);
                ProductBLL bll = new ProductBLL();

                List<Product> list = bll.findAll();
                for (Product c : list) {
                    Object[] rowData = {c.getId(), c.getName(), c.getPrice(), c.getStock()};
                    System.out.println(c.getId() + " " + c.getName() + " " + c.getPrice() + c.getStock());
                    tabel.addRow(rowData);
                }
            } catch (NullPointerException ex) {
                productView.showErrorMessage("null");
                ex.printStackTrace();
            } catch (Exception ex) {
                productView.showErrorMessage("BAD!");
                ex.printStackTrace();
            }
        }
    }

}
