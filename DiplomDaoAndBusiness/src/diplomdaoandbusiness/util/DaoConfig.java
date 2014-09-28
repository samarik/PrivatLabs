package diplomdaoandbusiness.util;

public class DaoConfig {

    private static IConnectionFactory connectionFactory = null;

    public static void init(IConnectionFactory connectionFactoryParam) {
        if (connectionFactory == null) {
            connectionFactory = connectionFactoryParam;
        }
    }

    public static IConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
}
