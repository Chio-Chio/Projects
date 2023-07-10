package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for managing clients.
 */
public class ClientView extends JFrame {
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JTextField textFieldId;
    private JButton btnNewClient;
    private JButton btnDeleteClient;
    private JButton btnEditClient;
    private JButton btnViewClients;
    /**
     * Constructs a new instance of the client view.
     */
    public ClientView(){
        this.getContentPane().setBackground(new Color(128, 0, 255));
        this.setBounds(100, 100, 985, 529);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        nameTextField = new JTextField();
        nameTextField.setBounds(151, 149, 199, 53);
        nameTextField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        nameTextField.setColumns(10);

        emailTextField = new JTextField();
        emailTextField.setBounds(151, 231, 199, 53);
        emailTextField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        emailTextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(93, 157, 54, 37);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(93, 239, 54, 37);
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        this.getContentPane().setLayout(null);
        this.getContentPane().add(emailTextField);
        this.getContentPane().add(lblNewLabel);
        this.getContentPane().add(lblEmail);
        this.getContentPane().add(nameTextField);

         btnNewClient = new JButton("New");
        btnNewClient.setBackground(new Color(128, 255, 255));
        btnNewClient.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnNewClient.setBounds(405, 157, 155, 78);
        this.getContentPane().add(btnNewClient);

        btnEditClient = new JButton("Edit");
        btnEditClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnEditClient.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnEditClient.setBackground(new Color(128, 255, 255));
        btnEditClient.setBounds(585, 157, 155, 78);
        this.getContentPane().add(btnEditClient);

        btnDeleteClient = new JButton("Delete");
        btnDeleteClient.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnDeleteClient.setBackground(new Color(128, 255, 255));
        btnDeleteClient.setBounds(775, 157, 155, 78);
        this.getContentPane().add(btnDeleteClient);

        btnViewClients = new JButton("View All");
        btnViewClients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnViewClients.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        btnViewClients.setBackground(new Color(128, 255, 255));
        btnViewClients.setBounds(469, 247, 406, 37);
        this.getContentPane().add(btnViewClients);

        JLabel title = new JLabel("Client");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(255, 255, 255));
        title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 40));
        title.setBounds(379, 29, 238, 78);
        this.getContentPane().add(title);

        textFieldId = new JTextField();
        textFieldId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        textFieldId.setColumns(10);
        textFieldId.setBounds(151, 314, 199, 53);
        this.getContentPane().add(textFieldId);

        JLabel lblId = new JLabel("Id");
        lblId.setForeground(Color.WHITE);
        lblId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        lblId.setBounds(93, 330, 54, 37);
        this.getContentPane().add(lblId);

        this.setVisible(true);
    }

    //getters and setters
    public String getNameTextField() {
        return nameTextField.getText();
    }

    public void setNameTextField(String nameTextField) {
        this.nameTextField.setText( nameTextField);
    }

    public String getEmailTextField() {
        return emailTextField.getText();
    }

    public void setEmailTextField(String emailTextField) {
        this.emailTextField.setText(emailTextField);
    }

    public JButton getBtnNewClient() {
        return btnNewClient;
    }

    public void setBtnNewClient(JButton btnNewClient) {
        this.btnNewClient = btnNewClient;
    }

    public JButton getBtnDeleteClient() {
        return btnDeleteClient;
    }

    public void setBtnDeleteClient(JButton btnDeleteClient) {
        this.btnDeleteClient = btnDeleteClient;
    }

    public JButton getBtnEditClient() {
        return btnEditClient;
    }

    public void setBtnEditClient(JButton btnEditClient) {
        this.btnEditClient = btnEditClient;
    }

    public JButton getBtnViewClients() {
        return btnViewClients;
    }

    public void setBtnViewClients(JButton btnViewClients) {
        this.btnViewClients = btnViewClients;
    }

    public String getTextFieldId() {
        return textFieldId.getText();
    }

    public void setTextFieldId(String textFieldId) {
        this.textFieldId.setText(textFieldId);
    }

    //action listeners
    public void newListener(ActionListener action){
        btnNewClient.addActionListener(action);
    }
    public void editListener(ActionListener action){
        btnEditClient.addActionListener(action);
    }

    public void deleteListener(ActionListener action){
        btnDeleteClient.addActionListener(action);
    }

    public void viewAllListener(ActionListener action) {btnViewClients.addActionListener(action);}
    /**
     * Displays an error message dialog with the specified message.
     *
     * @param message the error message to be displayed
     */
    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message, "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);

    }
}
