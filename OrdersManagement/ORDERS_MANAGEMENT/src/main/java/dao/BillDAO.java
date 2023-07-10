package dao;

import connection.ConnectionFactory;
import model.Bill;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillDAO {

    protected static final Logger LOGGER = Logger.getLogger("bill");

    public Bill insert(String clientName, String productName, int productPrice, int quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO bill (client_name, product_name, product_price, quantity) VALUES (?, ?, ?, ?);";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, clientName);
            statement.setString(2, productName);
            statement.setInt(3, productPrice);
            statement.setInt(4, quantity);
            statement.executeUpdate();

            // Retrieve the generated ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int billId = -1;
            if (generatedKeys.next()) {
                billId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve generated bill ID.");
            }

            return new Bill(billId, clientName, productName, productPrice, quantity, LocalDateTime.now());
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    public List<Bill> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM bill";
        ArrayList<Bill> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Bill(rs.getInt("idbill"), rs.getString("client_name"),
                        rs.getString("product_name"), rs.getInt("product_price"),
                        rs.getInt("quantity"), LocalDateTime.now()));
            }

            return list;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
            ConnectionFactory.close(rs);
        }
        return null;
    }

}
