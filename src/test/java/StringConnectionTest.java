import mysqlmanager.MySqlManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import mysqlmanager.param.MySqlManagerParams;

public class StringConnectionTest {

    @Test
    public void connectionStringTest() {
        String conn = "jdbc:mysql://localhost:3306/database?useTimezone=true&serverTimezone=UTC&useSSL=false";
        MySqlManager mySqlManager = new MySqlManager("database", "localhost", 3306, true, "UTC", false, "root", "root");
        MySqlManagerParams mySqlManagerParams = mySqlManager.getManagerParams();
        Assertions.assertEquals(conn, mySqlManagerParams.getConnectionString());
        Assertions.assertEquals("localhost", mySqlManagerParams.getServer());
        Assertions.assertEquals(3306, mySqlManagerParams.getPort());
        Assertions.assertTrue(mySqlManagerParams.isUseTimeZone());
        Assertions.assertEquals("UTC", mySqlManagerParams.getServerTimeZone());
        Assertions.assertFalse(mySqlManagerParams.isUseSSL());
        Assertions.assertEquals("root", mySqlManagerParams.getUser());
        Assertions.assertEquals("root", mySqlManagerParams.getPassword());
    }

    @Test
    public void connectionStringTest02() {
        String conn = "jdbc:mysql://localhost:3306/database?useTimezone=true&serverTimezone=UTC&useSSL=false";
        MySqlManagerParams mySqlManagerParams = new MySqlManagerParams("database", "localhost", 3306, true, "UTC", false, "root", "root");
        MySqlManager mySqlManager = new MySqlManager(mySqlManagerParams);
        Assertions.assertEquals(conn, mySqlManager.getManagerParams().getConnectionString());
        Assertions.assertEquals("localhost", mySqlManager.getManagerParams().getServer());
        Assertions.assertEquals(3306, mySqlManager.getManagerParams().getPort());
        Assertions.assertTrue(mySqlManager.getManagerParams().isUseTimeZone());
        Assertions.assertEquals("UTC", mySqlManager.getManagerParams().getServerTimeZone());
        Assertions.assertFalse(mySqlManager.getManagerParams().isUseSSL());
        Assertions.assertEquals("root", mySqlManager.getManagerParams().getUser());
        Assertions.assertEquals("root", mySqlManager.getManagerParams().getPassword());
    }

    @Test
    public void connectionStringTest03() {
        String conn = "jdbc:mysql://localhost:3306/database?useTimezone=true&serverTimezone=UTC&useSSL=false";
        MySqlManagerParams mySqlManagerParams = new MySqlManagerParams("database", "localhost", 3306, true, "UTC", false, "root", "root");
        MySqlManager mySqlManager = new MySqlManager(mySqlManagerParams);
        Assertions.assertEquals(conn, mySqlManager.getManagerParams().getConnectionString());
        Assertions.assertEquals("localhost", mySqlManager.getManagerParams().getServer());
        Assertions.assertEquals(3306, mySqlManager.getManagerParams().getPort());
        Assertions.assertTrue(mySqlManager.getManagerParams().isUseTimeZone());
        Assertions.assertEquals("UTC", mySqlManager.getManagerParams().getServerTimeZone());
        Assertions.assertFalse(mySqlManager.getManagerParams().isUseSSL());
        Assertions.assertEquals("root", mySqlManager.getManagerParams().getUser());
        Assertions.assertEquals("root", mySqlManager.getManagerParams().getPassword());
    }
}
