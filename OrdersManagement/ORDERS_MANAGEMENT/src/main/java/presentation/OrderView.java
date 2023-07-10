package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * The view class for managing orders.
 */
public class OrderView extends JFrame {
    private JTextField textFieldProductId;
    private JTextField textFieldClientId;
    private JTextField textFieldQuantity;
    private JTextField textFieldId;
    private JButton btnBIll;
    private JButton btnOrder;
    private   JButton btnLogTableBill;
    private   JButton btnEdit;
    private   JButton btnDelete;
    private   JButton btnShowAllOrders;
    /**
     * Constructs a new instance of the order view.
     */
    public OrderView(){
        this.getContentPane().setBackground(new Color(128, 0, 255));
        this.setBounds(100, 100, 985, 529);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblId = new JLabel("Id");
        lblId.setForeground(Color.WHITE);
        lblId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        lblId.setBounds(61, 132, 103, 37);
        this.getContentPane().add(lblId);

        textFieldId = new JTextField();
        textFieldId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        textFieldId.setColumns(10);
        textFieldId.setBounds(168, 120, 199, 53);
        this.getContentPane().add(textFieldId);

        textFieldProductId = new JTextField();
        textFieldProductId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        textFieldProductId.setColumns(10);
        textFieldProductId.setBounds(168, 276, 199, 53);
        this.getContentPane().add(textFieldProductId);

        JLabel lblIdclient = new JLabel("Client id");
        lblIdclient.setForeground(Color.WHITE);
        lblIdclient.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        lblIdclient.setBounds(61, 202, 103, 37);
        this.getContentPane().add(lblIdclient);

        JLabel lblProductId = new JLabel("Product id");
        lblProductId.setForeground(Color.WHITE);
        lblProductId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        lblProductId.setBounds(52, 284, 112, 37);
        this.getContentPane().add(lblProductId);

        textFieldClientId = new JTextField();
        textFieldClientId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        textFieldClientId.setColumns(10);
        textFieldClientId.setBounds(168, 194, 199, 53);
        this.getContentPane().add(textFieldClientId);

         btnOrder = new JButton("Order");
        btnOrder.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnOrder.setBackground(new Color(128, 255, 255));
        btnOrder.setBounds(521, 181, 155, 78);
        this.getContentPane().add(btnOrder);

         btnBIll = new JButton("Bill");
        btnBIll.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnBIll.setBackground(new Color(255, 128, 255));
        btnBIll.setBounds(704, 181, 155, 78);
        this.getContentPane().add(btnBIll);

        JLabel lb = new JLabel("Order");
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setForeground(Color.WHITE);
        lb.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 40));
        lb.setBounds(396, 74, 238, 78);
        this.getContentPane().add(lb);

         btnLogTableBill = new JButton("LogTable Bill");
        btnLogTableBill.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnLogTableBill.setBackground(new Color(255, 128, 255));
        btnLogTableBill.setBounds(493, 371, 406, 37);
        this.getContentPane().add(btnLogTableBill);

        btnShowAllOrders = new JButton("Show all orders");
        btnShowAllOrders.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnShowAllOrders.setBackground(new Color(128, 255, 255));
        btnShowAllOrders.setBounds(493, 410, 406, 37);
        this.getContentPane().add(btnShowAllOrders);

        textFieldQuantity = new JTextField();
        textFieldQuantity.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        textFieldQuantity.setColumns(10);
        textFieldQuantity.setBounds(168, 355, 199, 53);
        this.getContentPane().add(textFieldQuantity);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setForeground(Color.WHITE);
        lblQuantity.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        lblQuantity.setBounds(52, 355, 112, 37);
        this.getContentPane().add(lblQuantity);

         btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnEdit.setBackground(new Color(128, 255, 255));
        btnEdit.setBounds(521, 276, 155, 78);
        this.getContentPane().add(btnEdit);

         btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnDelete.setBackground(new Color(128, 255, 255));
        btnDelete.setBounds(704, 276, 155, 78);
        this.getContentPane().add(btnDelete);

        this.setVisible(true);
    }

    public String getTextFieldProductId() {
        return textFieldProductId.getText();
    }

    public void setTextFieldProductId(String textFieldProductId) {
        this.textFieldProductId.setText(textFieldProductId);
    }

    public String getTextFieldClientId() {
        return textFieldClientId.getText();
    }

    public void setTextFieldClientId(String textFieldClientId) {
        this.textFieldClientId.setText(textFieldClientId);
    }

    public String getTextFieldQuantity() {
        return textFieldQuantity.getText();
    }

    public void setTextFieldQuantity(String textFieldQuantity) {
        this.textFieldQuantity.setText( textFieldQuantity);
    }

    public JButton getBtnBIll() {
        return btnBIll;
    }

    public void setBtnBIll(JButton btnBIll) {
        this.btnBIll = btnBIll;
    }

    public JButton getBtnOrder() {
        return btnOrder;
    }

    public void setBtnOrder(JButton btnOrder) {
        this.btnOrder = btnOrder;
    }

    public JButton getBtnLogTableBill() {
        return btnLogTableBill;
    }

    public void setBtnLogTableBill(JButton btnLogTableBill) {
        this.btnLogTableBill = btnLogTableBill;
    }

    public String getTextFieldId() {
        return textFieldId.getText();
    }

    public void setTextFieldId(String textFieldId) {
        this.textFieldId.setText(textFieldId);
    }

    /**
     * Sets an action listener for the order button.
     *
     * @param action the action listener to be set
     */
    public void orderListener(ActionListener action){
        btnOrder.addActionListener(action);
    }

    /**
     * Sets an action listener for the bill button.
     *
     * @param action the action listener to be set
     */
    public void billListener(ActionListener action){
        btnBIll.addActionListener(action);
    }

    /**
     * Sets an action listener for the logTableBill button.
     *
     * @param action the action listener to be set
     */
    public void logTableBillListener(ActionListener action){
        btnLogTableBill.addActionListener(action);
    }
    /**
     * Sets an action listener for the edit button.
     *
     * @param action the action listener to be set
     */
    public void editListener(ActionListener action){
        btnEdit.addActionListener(action);
    }
    /**
     * Sets an action listener for the delete button.
     *
     * @param action the action listener to be set
     */
    public void deleteListener(ActionListener action){
        btnDelete.addActionListener(action);
    }
    /**
     * Sets an action listener for the show all orders button.
     *
     * @param action the action listener to be set
     */
    public void showAllOrdersListener(ActionListener action){
        btnShowAllOrders.addActionListener(action);
    }
    /**
     * Displays an error message dialog with the specified message.
     *
     * @param message the error message to be displayed
     */
    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message, "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
