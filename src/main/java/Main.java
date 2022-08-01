import mysqlmanager.MySqlExecutor;
import mysqlmanager.MySqlManager;
import mysqlmanager.mapping.MySqlMapManager;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {
        MySqlMapManager.add(User.class);
        MySqlMapManager.add(Address.class);

        MySqlManager mySqlManager = new MySqlManager("teste", "localhost", 3306, true, "UTC", false, "root", "root");
        MySqlExecutor mySqlExecutor = mySqlManager.openConnection();

        mySqlExecutor
                .createTableIfNotExist(Address.class)
                .createIndexIfNotExist(Address.class)

                .createTableIfNotExist(User.class)
                .createIndexIfNotExist(User.class);

        mySqlManager.closeConnection();
    }
}