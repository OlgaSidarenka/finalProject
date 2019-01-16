package test.sidarenka.alien.pool;

import com.sidarenka.alien.pool.ConnectionPool;
import com.sidarenka.alien.pool.ConnectionPoolException;
import com.sidarenka.alien.pool.ProxyConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;


public class ConnectionPoolTest {
    @Test
    public void connectionPoolSizeTest() {
        ConnectionPool connection = ConnectionPool.getInstance();
        int poolSizeActual = connection.getPoolSize();
        int poolSizeExpected = 10;
        Assert.assertEquals(poolSizeActual, poolSizeExpected);
    }

    @Test
    public void connectionPoolTest() throws ConnectionPoolException, SQLException {
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        boolean actual = connection.isClosed();
        boolean expected = false;
        Assert.assertEquals(actual, expected);
    }
}
