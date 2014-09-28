
package diplomdaoandbusiness.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.sql.ConnectionPoolDataSource;


public class ConnectionFactory implements IConnectionFactory{
    private DataSource ds = null;

    public ConnectionFactory() {
        ConnectionPoolDataSource ds = new ConnectionPoolDataSource() {};
        ds.setCreateDatabase("create");
        ds.setDatabaseName("db");
        this.ds = ds;
        this.ds = ds;
    }
    
    public ConnectionFactory(DataSource ds){
        this.ds = ds;
    }

    
    
    @Override
    public Connection getDefaultConnection() throws SQLException {
        return ds.getConnection();
    }
    
}
