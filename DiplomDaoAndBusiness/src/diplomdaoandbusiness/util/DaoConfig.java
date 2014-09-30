package diplomdaoandbusiness.util;

public class DaoConfig {

    private static IConnectionFactory connectionFactory = null;

    public static IConnectionFactory getConnectionFactory() {
        if (connectionFactory == null) {
            connectionFactory = new MyConnectionFactory();
        }
        return connectionFactory;
    }
}
