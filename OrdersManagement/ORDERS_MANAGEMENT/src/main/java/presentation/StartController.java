package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A controller class for the start view.
 */
public class StartController {
    private StartView startView;

    /**
     * Constructs a new instance of the start controller.
     *
     * @param startView the start view associated with the controller
     */
    public StartController(StartView startView) {
        this.startView = startView;
        //System.out.println("here");
        this.startView.clientListener(new ClientListener());
        this.startView.productListener(new ProductListener());
        this.startView.orderListener(new OrderListener());
    }

    /**
     * ActionListener implementation for the client button.
     */
    class ClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("Client button was pressed (StartView)");
                ClientView clientView = new ClientView();
                ClientController clientController = new ClientController(clientView);
            } catch (Exception ex) {
                //printStackTrace();
                startView.showErrorMessage("BAD!");
                ex.printStackTrace();
            }

        }
    }
    /**
     * ActionListener implementation for the product button.
     */
    class ProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("Product button was pressed (StartView)");
                ProductView productView = new ProductView();
                ProductController productController = new ProductController(productView);
            }catch (Exception ex){
                startView.showErrorMessage("BAD!");
                ex.printStackTrace();
            }
        }
    }

    /**
     * ActionListener implementation for the order button.
     */
    class OrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("Order button was pressed (StartView)");
                OrderView orderView = new OrderView();
                OrderController orderController = new OrderController(orderView);
            }catch (Exception ex){
                startView.showErrorMessage("BAD!");
                ex.printStackTrace();
            }
        }
    }
}
