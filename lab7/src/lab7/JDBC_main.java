package lab7;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olga
 */
public class JDBC_main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        ConnectionFactory connFact = new SybaseConnectionFactory();
        Connection conn = connFact.doConnection(url, login, password);
        MySQL mySQL = new MySQL(conn);
        while (true) {
            String sql = null;
            sql = mySQL.getSQL();
            if (sql.contains("exit")) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(JDBC_main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
            try {
                mySQL.executeSQL(sql);
            } catch (SQLException ex) {
                Logger.getLogger(JDBC_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
