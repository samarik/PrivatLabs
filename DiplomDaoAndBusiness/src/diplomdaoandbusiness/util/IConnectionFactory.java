package diplomdaoandbusiness.util;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionFactory {

    Connection getDefaultConnection() throws SQLException;
}
