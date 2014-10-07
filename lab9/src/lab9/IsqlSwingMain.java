package lab9;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Olga
 */
public class IsqlSwingMain {

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
        mySQL.myFrame();
        
    }
    
}
