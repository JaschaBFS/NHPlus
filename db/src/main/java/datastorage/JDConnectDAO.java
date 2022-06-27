package datastorage;

import model.DefaultUser;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific DefaultUser-SQL-queries.
 */
public class JDConnectDAO extends DAOimp<DefaultUser> {
    private static final String SELECT_QUERY = "SELECT * FROM USER WHERE USER.USER= ? AND USER.PASSWORD =?";
    /**
     * constructs Onbject. Calls the Constructor from <code>DAOImp</code> to store the connection.
     * @param conn
     */
    public JDConnectDAO(Connection conn) {
        super(conn);
    }
    /**
     * constructs a query, input username and password are being checked with
     * the database returns true if input combination can be found in the database
     *
     */
    public boolean validate(String user, String password) throws SQLException {
        try (
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    /**
     * throws an exception if the boolean before runs into an error
     *
     */
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    /**
     * generates a <code>INSERT INTO</code>-Statement for a given User
     * @param user for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(DefaultUser user) {
        return String.format("INSERT INTO user (name, Password, Admin, firstLogin) VALUES ('%s', '%s', '%s', '%s')",
                user.getName(), user.getPassword(), user.getAdmin(), user.getFirstLogin());
    }
    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM user WHERE pid = %d", key);
    }
    /**
     * maps a <code>ResultSet</code> to a <code>Patient</code>
     * + checks ob diese Daten zualt sind oder ob diese gesperrt sind
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return patient with the data from the resultSet.
     */
    @Override
    protected DefaultUser getInstanceFromResultSet(ResultSet result) throws SQLException {
        DefaultUser user = null;
        user = new DefaultUser(result.getInt(1), result.getString(2),
                result.getString(3), result.getBoolean(4), result.getBoolean(5));
        return user;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all users.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM user";
    }
    /**
     * maps a <code>ResultSet</code> to a <code>User-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to user-object.
     * @return ArrayList with users from the resultSet.
     */
    @Override
    protected ArrayList<DefaultUser> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<DefaultUser> list = new ArrayList<>();
        DefaultUser user = null;
        while (result.next()) {
            user = new DefaultUser(result.getInt(1), result.getString(2),
                    result.getString(3), result.getBoolean(4), result.getBoolean(5));
            list.add(user);
        }
        return list;
    }
    /**
     * generates a <code>UPDATE</code>-Statement for a given user
     * @param normalUser for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(DefaultUser normalUser) {
        return String.format("UPDATE user SET user = '%s', password = '%s', admin = '%s', firstlogin = '%s' " + "WHERE pid = %d", normalUser.getName(), normalUser.getPassword(), normalUser.getAdmin(),
                normalUser.getFirstLogin());
    }
    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM user WHERE pid=%d", key);
    }
    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getBlockStatementString(long key) {
        return String.format("Delete FROM patient WHERE pid=%d", key);
    }
}
