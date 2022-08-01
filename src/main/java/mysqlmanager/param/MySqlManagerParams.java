package mysqlmanager.param;

public class MySqlManagerParams {
    private final String CONNECTION_STRING;
    private final String USER;
    private final String PASSWORD;
    private final String SERVER;
    private final String DATA_BASE;
    private final String SERVER_TIME_ZONE;
    private final boolean USE_SSL;
    private final boolean USE_TIME_ZONE;
    private final int PORT;

    public MySqlManagerParams(String dataBase, String server, int port, boolean useTimeZone, String serverTimeZone, boolean useSSL, String user, String password) {
        DATA_BASE = dataBase;
        SERVER = server;
        PORT = port;
        USE_TIME_ZONE = useTimeZone;
        SERVER_TIME_ZONE = serverTimeZone;
        USE_SSL = useSSL;
        USER = user;
        PASSWORD = password;
        CONNECTION_STRING =
                "jdbc:mysql://" + server + ":" + port + "/" + dataBase + "?useTimezone=" + useTimeZone + "&serverTimezone=" + serverTimeZone + "&useSSL=" + useSSL;
    }

    public String getConnectionString() {
        return CONNECTION_STRING;
    }

    public String getUser() {
        return USER;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public String getServer() {
        return SERVER;
    }

    public String getDataBase() {
        return DATA_BASE;
    }

    public String getServerTimeZone() {
        return SERVER_TIME_ZONE;
    }

    public boolean isUseSSL() {
        return USE_SSL;
    }

    public boolean isUseTimeZone() {
        return USE_TIME_ZONE;
    }

    public int getPort() {
        return PORT;
    }
}
