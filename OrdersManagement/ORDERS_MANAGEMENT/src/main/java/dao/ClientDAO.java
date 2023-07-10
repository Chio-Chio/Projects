package dao;

import model.Client;

import java.util.logging.Logger;
/**
 * Data Access Object for Client entities.
 */
public class ClientDAO extends AbstractDAO <Client> {

    /**
     * The logger for ClientDAO class.
     */
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
//    private static final String insertStatementString = "INSERT INTO student (name,address,email,age)"
//            + " VALUES (?,?,?,?)";
//    private final static String findStatementString = "SELECT * FROM student where id = ?";

//    public static Client findById(int studentId) {
//        Client toReturn = null;
//
//        Connection dbConnection = ConnectionFactory.getConnection();
//        PreparedStatement findStatement = null;
//        ResultSet rs = null;
//        try {
//            findStatement = dbConnection.prepareStatement(findStatementString);
//            findStatement.setLong(1, studentId);
//            rs = findStatement.executeQuery();
//            rs.next();
//
//            String name = rs.getString("name");
//
//            String email = rs.getString("email");
//
//            toReturn = new Client(studentId, name, email);
//        } catch (SQLException e) {
//            LOGGER.log(Level.WARNING,"StudentDAO:findById " + e.getMessage());
//        } finally {
//            ConnectionFactory.close(rs);
//            ConnectionFactory.close(findStatement);
//            ConnectionFactory.close(dbConnection);
//        }
//        return toReturn;
//    }
//
//    public static int insert(Client client) {
//        Connection dbConnection = ConnectionFactory.getConnection();
//
//        PreparedStatement insertStatement = null;
//        int insertedId = -1;
//        try {
//            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
//            insertStatement.setString(1, client.getName());
//
//            insertStatement.setString(3, client.getEmail());
//
//            insertStatement.executeUpdate();
//
//            ResultSet rs = insertStatement.getGeneratedKeys();
//            if (rs.next()) {
//                insertedId = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
//        } finally {
//            ConnectionFactory.close(insertStatement);
//            ConnectionFactory.close(dbConnection);
//        }
//        return insertedId;
//    }
}
