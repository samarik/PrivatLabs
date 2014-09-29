package diplomdaoandbusiness.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class MyConnectionFactory implements IConnectionFactory {

    private DataSource ds = null;

    public MyConnectionFactory(){
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:jtds:sybase://127.0.0.1:5000:mydb0", "sa", "");
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);
        //return dataSource;
        //ds.setCreateDatabase("create");
        //.setDatabaseName("db");
        this.ds = dataSource;
    }

    public MyConnectionFactory(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Connection getDefaultConnection() throws SQLException {
        return ds.getConnection();
    }

}
