package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * A class representing a table GUI component.
 */
public class Tabel {

    private JFrame frame;
    private JTable jTable;
    private DefaultTableModel tableModel;

    /**
     * Constructs a new instance of the table.
     *
     * @param columnNames an array of column names for the table
     * @param data        a 2D array of data for the table
     */
    public Tabel(String[] columnNames, Object[][] data) {
        frame = new JFrame();
        frame.setBounds(100, 100, 985, 529);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("All Clients");

        tableModel = new DefaultTableModel(data, columnNames);
        jTable = new JTable(tableModel);
        jTable.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(jTable);
        frame.add(sp);

        frame.setVisible(true);
    }

    /**
     * Adds a new row of data to the table.
     *
     * @param rowData an array of data representing a row
     */
    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }
}
