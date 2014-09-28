
package diplomdaoandbusiness.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;


public class ConnectionFactory implements IConnectionFactory{
    private DataSource ds = null;

    public ConnectionFactory() {
        //SybDataSource dataSource = new SybDataSource();
        //ds.setCreateDatabase("create");
        //ds.setDatabaseName("db");
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
