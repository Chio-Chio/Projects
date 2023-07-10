package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * A view class for managing products.
 */
public class ProductView extends JFrame{
    private JTextField textFieldStock;
    private JTextField textFieldName;
    private JTextField textFieldId;
    private JTextField textFieldPrice;
    private  JButton btnNew;
    private JButton btnDelete;
    private  JButton btnEdit;
    private JButton btnView;

    /**
     * Constructs a new instance of the product view.
     */
    public ProductView(){
        this.getContentPane().setBackground(new Color(128, 0, 255));
        this.setBounds(100, 100, 985, 529);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        textFieldStock = new JTextField();
        textFieldStock.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        textFieldStock.setColumns(10);
        textFieldStock.setBounds(152, 262, 199, 53);
        this.getContentPane().add(textFieldStock);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        lblNewLabel.setBounds(94, 144, 54, 37);
        this.getContentPane().add(lblNewLabel);

        JLabel lblStock = new JLabel("Stock");
        lblStock.setForeground(Color.WHITE);
        lblStock.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        lblStock.setBounds(94, 270, 54, 37);
        this.getContentPane().add(lblStock);

        textFieldName = new JTextField();
        textFieldName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        textFieldName.setColumns(10);
        textFieldName.setBounds(152, 136, 199, 53);
        this.getContentPane().add(textFieldName);

         btnNew = new JButton("New");
        btnNew.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnNew.setBackground(new Color(128, 255, 255));
        btnNew.setBounds(406, 188, 155, 78);
        this.getContentPane().add(btnNew);

         btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnEdit.setBackground(new Color(128, 255, 255));
        btnEdit.setBounds(586, 188, 155, 78);
        this.getContentPane().add(btnEdit);

         btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnDelete.setBackground(new Color(128, 255, 255));
        btnDelete.setBounds(776, 188, 155, 78);
        this.getContentPane().add(btnDelete);

         btnView = new JButton("View All");
        btnView.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnView.setBackground(new Color(128, 255, 255));
        btnView.setBounds(470, 278, 406, 37);
        this.getContentPane().add(btnView);

        JLabel  lblProduct = new JLabel("Product");
        lblProduct.setHorizontalAlignment(SwingConstants.CENTER);
        lblProduct.setForeground(Color.WHITE);
        lblProduct.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 40));
        lblProduct.setBounds(380, 60, 238, 78);
        this.getContentPane().add(lblProduct);

        textFieldId = new JTextField();
        textFieldId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        textFieldId.setColumns(10);
        textFieldId.setBounds(152, 325, 199, 53);
        this.getContentPane().add(textFieldId);

        JLabel lblId = new JLabel("Id");
        lblId.setForeground(Color.WHITE);
        lblId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        lblId.setBounds(94, 333, 54, 37);
        this.getContentPane().add(lblId);

        textFieldPrice = new JTextField();
        textFieldPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        textFieldPrice.setColumns(10);
        textFieldPrice.setBounds(152, 199, 199, 53);
        this.getContentPane().add(textFieldPrice);

        JLabel lblNewLabelPrice = new JLabel("Price");
        lblNewLabelPrice.setForeground(Color.WHITE);
        lblNewLabelPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        lblNewLabelPrice.setBounds(94, 215, 54, 37);
        this.getContentPane().add(lblNewLabelPrice);

        this.setVisible(true);
    }

    /**
     * Retrieves the value from the stock text field.
     *
     * @return The stock value entered by the user.
     */
    public String getTextFieldStock() {
        return textFieldStock.getText();
    }

    /**
     * Sets the value of the stock text field.
     *
     * @param textFieldStock The stock value to be set in the text field.
     */
    public void setTextFieldStock(String textFieldStock) {
        this.textFieldStock.setText( textFieldStock);
    }

    /**
     * Retrieves the value from the name text field.
     *
     * @return The name entered by the user.
     */
    public String  getTextFieldName() {
        return textFieldName.getText();
    }

    /**
     * Sets the value of the stock text field.
     *
     * @param textFieldName The stock value to be set in the text field.
     */
    public void setTextFieldName(String textFieldName) {
        this.textFieldName.setText(textFieldName);
    }

    public JButton getBtnNew() {
        return btnNew;
    }

    public void setBtnNew(JButton btnNew) {
        this.btnNew = btnNew;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(JButton btnEdit) {
        this.btnEdit = btnEdit;
    }

    public JButton getBtnView() {
        return btnView;
    }

    public void setBtnView(JButton btnView) {
        this.btnView = btnView;
    }

    public String getTextFieldId() {
        return textFieldId.getText();
    }

    public void setTextFieldId(String textFieldId) {
        this.textFieldId.setText(textFieldId);
    }

    public String getTextFieldPrice() {
        return textFieldPrice.getText();
    }

    public void setTextFieldPrice(String textFieldPrice) {
        this.textFieldPrice.setText(textFieldPrice);
    }

    // action listeners

    /**
     * Adds an ActionListener to the New button.
     *
     * @param action The ActionListener to be added.
     */
    public void newListener(ActionListener action){
        btnNew.addActionListener(action);
    }


    /**
     * Adds an ActionListener to the Edit button.
     *
     * @param action The ActionListener to be added.
     */
    public void editListener(ActionListener action){
        btnEdit.addActionListener(action);
    }

    /**
     * Adds an ActionListener to the Delete button.
     *
     * @param action The ActionListener to be added.
     */
    public void deleteListener(ActionListener action){
        btnDelete.addActionListener(action);
    }

    /**
     * Adds an ActionListener to the View all button.
     *
     * @param action The ActionListener to be added.
     */
    public void viewAllListener(ActionListener action){
        btnView.addActionListener(action);
    }

    /**
     * Displays an error message dialog with the specified message.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message, "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);

    }
}
