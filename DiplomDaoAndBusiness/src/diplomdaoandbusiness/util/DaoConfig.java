package diplomdaoandbusiness.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoConfig {

    private static IConnectionFactory connectionFactory = null;

    public static IConnectionFactory getConnectionFactory() {
        System.out.println("111");
        if (connectionFactory == null) {
            connectionFactory = new MyConnectionFactory();

        }
        return connectionFactory;
    }
}
