import mysqlmanager.MySqlManager;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ConnectionTest {

    @Test
    public void openConnectionTest() throws SQLException {
        MySqlManager mySqlManager = new MySqlManager("teste", "localhost", 3306, true, "UTC", false, "root", "root");
        mySqlManager.openConnection();
    }

    @Test
    public void closeConnectionTest() throws SQLException {
        MySqlManager mySqlManager = new MySqlManager("teste", "localhost", 3306, true, "UTC", false, "root", "root");
        mySqlManager.openConnection();
        mySqlManager.closeConnection();
    }
}
