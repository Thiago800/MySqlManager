package mysqlmanager;

import mysqlmanager.param.MySqlManagerParams;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlManager {

    private final MySqlManagerParams PARAMS;
    private Connection connection;

    public MySqlManager(String dataBase, String server, int port, boolean useTimeZone, String serverTimeZone, boolean useSSL, String user, String password) throws IllegalArgumentException {
        PARAMS = new MySqlManagerParams(dataBase, server, port, useTimeZone, serverTimeZone, useSSL, user, password);
        verifyParams(PARAMS);
    }

    public MySqlManager(MySqlManagerParams mySqlManagerParams) throws IllegalArgumentException {
        PARAMS = mySqlManagerParams;
        verifyParams(PARAMS);
    }

    private void verifyParams(MySqlManagerParams mySqlManagerParams) {
        if (mySqlManagerParams.getDataBase() == null || mySqlManagerParams.getDataBase().equals("")) throw new IllegalArgumentException("database name is null or empty");
        if (mySqlManagerParams.getServer() == null || mySqlManagerParams.getServer().equals("")) throw new IllegalArgumentException("server name is null or empty");
        if (mySqlManagerParams.getPort() < 0) throw new IllegalArgumentException("port cannot be less than zero");
    }

    public MySqlExecutor openConnection() throws SQLException, IllegalStateException{
        if (connection != null && !connection.isClosed()) throw new IllegalStateException("connection already opened");
        connection = DriverManager.getConnection(PARAMS.getConnectionString(), PARAMS.getUser(), PARAMS.getPassword());
        return new MySqlExecutor(connection);
    }

    public void closeConnection() throws SQLException, IllegalStateException {
        if (connection == null) throw new IllegalStateException("connection is null");
        if (connection.isClosed()) throw new IllegalStateException("connection already closed");
        connection.close();
    }

    public MySqlManagerParams getManagerParams() {
        return PARAMS;
    }
}
