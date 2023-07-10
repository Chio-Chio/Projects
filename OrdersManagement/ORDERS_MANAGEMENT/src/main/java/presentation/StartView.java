package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A class representing the start view of the application.
 */
public class StartView extends JFrame {

    private JLabel lblNewLabel;
    private JButton btnClient;
    private JButton btnProduct;
    private JButton btnOrder;

    /**
     * Constructs a new instance of the start view.
     */
    public StartView() {
        this.getContentPane().setBackground(new Color(128, 0, 255));
        this.setBounds(100, 100, 985, 529);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnClient = new JButton("Client");
        btnClient.setBounds(103, 251, 214, 91);
        btnClient.setBackground(new Color(255, 128, 255));
        btnClient.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));

        btnProduct = new JButton("Product");
        btnProduct.setBounds(370, 251, 214, 91);
        btnProduct.setBackground(new Color(255, 128, 255));
        btnProduct.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));

        btnOrder = new JButton("Order");
        btnOrder.setBounds(633, 251, 214, 91);
        btnOrder.setBackground(new Color(255, 128, 255));
        btnOrder.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));

        this.getContentPane().setLayout(null);

        lblNewLabel = new JLabel("Order Management");
        lblNewLabel.setBounds(242, 75, 431, 78);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 40));

        this.getContentPane().add(lblNewLabel);
        this.getContentPane().add(btnClient);
        this.getContentPane().add(btnProduct);
        this.getContentPane().add(btnOrder);
        this.setVisible(true);
    }

    /**
     * Retrieves the Client button.
     *
     * @return The Client button.
     */
    public JButton getBtnClient() {
        return btnClient;
    }

    /**
     * Retrieves the Product button.
     *
     * @return The Product button.
     */
    public JButton getBtnProduct() {
        return btnProduct;
    }

    /**
     * Retrieves the Order button.
     *
     * @return The Order button.
     */
    public JButton getBtnOrder() {
        return btnOrder;
    }

    public void clientListener(ActionListener action) {
        btnClient.addActionListener(action);
    }

    public void productListener(ActionListener action) {
        btnProduct.addActionListener(action);
    }


    public void orderListener(ActionListener action) {
        btnOrder.addActionListener(action);
    }

    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message, "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);

    }
}
