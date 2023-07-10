package dao;


import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract Data Access Object class.
 *
 * @param <T> the type of the entity being accessed
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Constructs an AbstractDAO with the specified entity type.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Creates a SELECT query for the specified field.
     *
     * @param field the field name
     * @return the SELECT query string
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Creates an INSERT query for the entity type.
     *
     * @return the INSERT query string
     */
    private String createInsertQuery() {
        // INSERT INTO table_name (column1, column2, column3,etc) VALUES (value1, value2, value3, etc);
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i].getName());
            if (i != fields.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(") VALUES (");
        for (int i = 0; i < fields.length; i++) {
            sb.append("?");
            if (i != fields.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");

        return sb.toString();
    }

    /**
     * Creates a SELECT query to retrieve all entities of the type.
     *
     * @return the SELECT query string
     */

    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Creates an UPDATE query for the specified ID.
     *
     * @param id the ID of the entity to update
     * @return the UPDATE query string
     */
    private String updateQuery(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field[] fields = type.getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            sb.append(fields[i].getName());
            sb.append(" = ?");
            if (i != fields.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(" WHERE ");
        sb.append(fields[0].getName());
        sb.append(" = ?");
        System.out.println(sb);
        return sb.toString();
    }

    /**
     * Creates an DELETE query for the specified ID.
     *
     * @param id the ID of the entity to delete
     * @return the DELETE query string
     */
    private String deleteByIdQuery(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    /**
     * Retrieves all entities of the type.
     *
     * @return a list of entities
     */
    public List<T> findAll() {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Retrieves the entity with the specified ID.
     *
     * @param id the ID of the entity
     * @return the entity with the specified ID
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creates objects from the specified ResultSet.
     *
     * @param resultSet the ResultSet containing data
     * @return a list of created objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Inserts the specified entity into the database.
     *
     * @param t the entity to insert
     * @return the inserted entity
     */
    public T insert(T t) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            Field[] fields = type.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(t);
                statement.setObject(i + 1, value);
            }

            //resultSet = statement.executeQuery();
            statement.executeUpdate();
            //return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return t;
    }

    /**
     * Updates the specified entity in the database.
     *
     * @param t  the entity to update
     * @param id the ID of the entity
     * @return the updated entity
     */
    public T update(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = updateQuery(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            Field[] fields = type.getDeclaredFields();
            fields[0].setAccessible(true);
            int index = 1;
            for (int i = 1; i < fields.length; i++) {
                index++;
                System.out.println("i = " + i);
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(t);
                System.out.println(value);
                statement.setObject(i, value);
                System.out.println();
            }
            statement.setInt(index, id);
            statement.executeUpdate();
            return t;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Deletes the specified entity from the database.
     *
     * @param t  the entity to delete
     * @param id the ID of the entity to delete
     */
    public void delete(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = deleteByIdQuery(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Row with ID " + id + " deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    //return t;
}


