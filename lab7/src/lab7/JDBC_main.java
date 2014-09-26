package lab7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Olga
 */
public class JDBC_main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Параметры для коннекта не заданы !!! ");
            return;
        }
        String login = args[0];
        String url = "jdbc:jtds:sybase://127.0.0.1:5000/" + args[1];
        String password = "";
        if (args.length > 2) {
            password = args[2];
        }
        ConnectionFactory sybaseConnection = new sybaseConnectionFactory();
        Connection conn = sybaseConnection.doConnection(url, login, password);
        MySQL mySQL = new MySQL(conn);
        while (true) {
            String sql = mySQL.getSQL();
            if (sql.contains("exit")) {
                break;
            }
            mySQL.executeSQL(sql);
        }
    }
}
