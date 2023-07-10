package presentation;

import bll.ClientBLL;
import model.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The controller class for managing clients.
 */
public class ClientController {
    private ClientView clientView;

    /**
     * Constructs a new instance of the client controller.
     *
     * @param clientView the client view associated with the controller
     */
    public ClientController(ClientView clientView) {
        this.clientView = clientView;

        this.clientView.newListener(new NewListener());
        this.clientView.editListener(new EditListener());
        this.clientView.deleteListener(new DeleteListener());
        this.clientView.viewAllListener(new ViewAllListener());
    }

    /**
     * ActionListener implementation for the New button.
     */
    class NewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("New button was pressed (ClientView)");
                //ClientDAO clientDAO = new ClientDAO();
                String name = clientView.getNameTextField();
                String email = clientView.getEmailTextField();
                Client client = new Client(name, email);

                ClientBLL clientBLL = new ClientBLL();
                clientBLL.insertClient(client);
            } catch (IllegalArgumentException ex) {
                clientView.showErrorMessage("Email not valid");
                ex.printStackTrace();
            } catch (Exception ex) {
                System.out.println("error");
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
                System.out.println("Edit button was pressed (ClientView)");
                String[] columns = {"Id Client", "Name", "Email"};
                Object[][] data = {};
                //Tabel tabel = new Tabel(columns, data);
                ClientBLL clientBLL = new ClientBLL();

                int id = Integer.parseInt(clientView.getTextFieldId());
                Client c = clientBLL.findClientById(id);
                if (c == null) {
                    throw new Exception();
                }
                String name = clientView.getNameTextField();
                String email = clientView.getEmailTextField();
                Client c2 = new Client(c.getId(), name, email);
                c2 = clientBLL.update(c2);
//                    Object[] rowData = {c2.getId(), c2.getName(), c2.getEmail()};
//                    tabel.addRow(rowData);
            } catch (IndexOutOfBoundsException ex) {
                clientView.showErrorMessage("invalid id");
                ex.printStackTrace();
            } catch (NumberFormatException ex) {
                clientView.showErrorMessage("invalid input");
                ex.printStackTrace();
            } catch (Exception ex) {
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
                System.out.println("Delete button was pressed (ClientView)");
                int id = Integer.parseInt(clientView.getTextFieldId());
                ClientBLL clientBLL = new ClientBLL();
                Client client = clientBLL.findClientById(id);

                clientBLL.delete(client);
            } catch (IndexOutOfBoundsException ex) {
                clientView.showErrorMessage("invalid id");
                ex.printStackTrace();
            } catch (Exception ex) {
                clientView.showErrorMessage("NO!");
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
                System.out.println("View all button was pressed (ClientView)");
                String[] columns = {"Id Client", "Name", "Email"};
                Object[][] data = {};
                Tabel tabel = new Tabel(columns, data);
                ClientBLL clientBLL = new ClientBLL();

                List<Client> list = clientBLL.findAllClient();
                for (Client c : list) {
                    Object[] rowData = {c.getId(), c.getName(), c.getEmail()};
                    tabel.addRow(rowData);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}